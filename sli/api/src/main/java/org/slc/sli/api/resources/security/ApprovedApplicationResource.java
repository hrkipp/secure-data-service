/*
 * Copyright 2012 Shared Learning Collaborative, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.slc.sli.api.resources.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.slc.sli.api.representation.EntityBody;
import org.slc.sli.api.resources.Resource;
import org.slc.sli.api.security.SLIPrincipal;
import org.slc.sli.api.security.oauth.ApplicationAuthorizationValidator;
import org.slc.sli.api.util.SecurityUtil;
import org.slc.sli.domain.Entity;
import org.slc.sli.domain.NeutralCriteria;
import org.slc.sli.domain.NeutralQuery;
import org.slc.sli.domain.Repository;
import org.slc.sli.domain.enums.Right;

/**
 * Used to retrieve the list of apps that a user is allowed to use.
 *
 * @author pwolf
 *
 */
@Component
@Scope("request")
@Path("/userapps")
@Produces({ Resource.JSON_MEDIA_TYPE + ";charset=utf-8" })
public class ApprovedApplicationResource {

    public static final String RESOURCE_NAME = "application";
    public static final String DELEGATED_ADMIN_PLACEHOLDER = "DELEGATED_ADMIN";
    public static final String CUSTOM_ROLE_ADMIN_PLACEHOLDER = "CUSTOM_ROLE_ADMIN";

    private static final String[] ALLOWED_ATTRIBUTES = new String[] {
        "application_url", "administration_url", "image_url", "description",
 "name", "vendor", "version", "is_admin", "behavior", "endpoints"
    };

    @Autowired
    private ApplicationAuthorizationValidator appValidator;

    @Autowired
    @Qualifier("validationRepo")
    private Repository<Entity> repo;

    @Autowired
    private DelegationUtil delegationUtil;

    @GET
    public Response getApplications(@DefaultValue("") @QueryParam("is_admin") String adminFilter) {
        List<String> allowedApps = getAllowedAppIds();

        List<EntityBody> results = new ArrayList<EntityBody>();

        NeutralQuery query = new NeutralQuery(0);
        query.addCriteria(new NeutralCriteria("_id", "in", allowedApps, false));

        for (Entity result : repo.findAll("application", query)) {

            EntityBody body = new EntityBody(result.getBody());
            if (!shouldFilterApp(body, adminFilter)) {

                filterAttributes(body);
                results.add(body);
            }

        }
        return Response.status(Status.OK).entity(results).build();
    }

    private boolean shouldFilterApp(EntityBody result, String adminFilter) {
        if (result.containsKey("endpoints")) {
            List<Map<String, Object>> endpoints = (List<Map<String, Object>>) result.get("endpoints");
            filterEndpoints(endpoints);

            //we ended up filtering out all the endpoints - no reason to display the app
            if (endpoints.size() == 0) {
                return true;
            }
        }

        boolean isAdminApp = result.containsKey("is_admin") ? Boolean.valueOf((Boolean) result.get("is_admin")) : false;

        //is_admin query param specified
        if (!adminFilter.equals("")) {
            boolean adminFilterVal = Boolean.valueOf(adminFilter);

            //non-admin app, but is_admin == true
            if (!isAdminApp && adminFilterVal) {
                return true;
            }

            //admin app, but is_admin == false
            if (isAdminApp && !adminFilterVal) {
                return true;
            }
        }

        // don't allow "installed" apps
        if (result.get("installed") == null || (Boolean) result.get("installed")) {
            return true;
        }

        return false;
    }

    private List<String> getUsersRoles() {
        SLIPrincipal principal = (SLIPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<String> toReturn = new ArrayList(principal.getRoles());

        //This is a fake role we use mean that a user is either an LEA admin or an SEA admin with delegated rights
        if (hasAppAuthorizationRight()) {
            toReturn.add(DELEGATED_ADMIN_PLACEHOLDER);
        } else if (SecurityUtil.hasRight(Right.CRUD_ROLE)) {
            toReturn.add(CUSTOM_ROLE_ADMIN_PLACEHOLDER);
        }

        return toReturn;
    }

    private boolean hasAppAuthorizationRight() {
        if (SecurityUtil.hasRight(Right.EDORG_APP_AUTHZ)) {
            //edorg authz users always have the right to authorize apps
            return true;
        } else if (SecurityUtil.hasRight(Right.EDORG_DELEGATE)) {
            //We need to figure out if any districts have delegated to us
            return delegationUtil.getAppApprovalDelegateEdOrgs().size() > 0;
        }
        return false;
    }

    private void filterEndpoints(List<Map<String, Object>> endpoints) {
        List<String> userRoles = getUsersRoles();

        for (Iterator<Map<String, Object>> i = endpoints.iterator(); i.hasNext();) {

            @SuppressWarnings("unchecked")
            List<String> reqRoles = (List<String>) i.next().get("roles");

            //if no roles specified, don't filter it
            if (reqRoles.size() == 0) {
                continue;
            }

            List<String> intersection = new ArrayList<String>(reqRoles);
            intersection.retainAll(userRoles);
            if (userRoles.size() == 0 || intersection.size() == 0) {
                debug("Removing endpoint because users roles {} did not match required roles {}.", userRoles, reqRoles);
                i.remove();
            }
        }
    }

    private List<String> getAllowedAppIds() {
        SLIPrincipal principal = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() != null) {
            principal = (SLIPrincipal) context.getAuthentication().getPrincipal();
        }
        if (principal == null) {
            throw new InsufficientAuthenticationException("Application list is a protected resource.");
        }
        List<String> toReturn = appValidator.getAuthorizedApps(principal);
        return toReturn;
    }

    /**
     * Filters out attributes we don't want ordinary users to see.
     * For example, they should never see the client_secret.
     *
     * @param result
     */
    private void filterAttributes(EntityBody result) {
        for (Iterator<Map.Entry<String, Object>> i = result.entrySet().iterator(); i.hasNext();) {
            String key = i.next().getKey();
            if (!Arrays.asList(ALLOWED_ATTRIBUTES).contains(key)) {
                i.remove();
            }
        }

    }
}
