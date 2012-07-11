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

package org.slc.sli.sif.agent;

import java.io.File;

import openadk.library.ADK;
import openadk.library.Agent;
import openadk.library.SIFVersion;
import openadk.library.Zone;
import openadk.library.tools.cfg.AgentConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import org.slc.sli.sif.zone.SubscribeZoneConfigurator;
import org.slc.sli.sif.zone.ZoneConfigurator;
@Component
public class SifAgent extends Agent {

    private static final Logger LOG = LoggerFactory.getLogger(SifAgent.class);

    private AgentConfig fCfg;

    @Value("classpath:/sif/agent-subscribe-config.xml")
    private Resource configFile;

    ZoneConfigurator zoneConfigurator;

    public SifAgent() {
        this("SifAgent");
    }

    public SifAgent(String id){
        super(id);
        setName(id);
        this.zoneConfigurator = new SubscribeZoneConfigurator();
    }

    public SifAgent(String id, Resource configFile, ZoneConfigurator zoneConfig ){
        super(id);
        setName(id);
        this.configFile = configFile;
        this.zoneConfigurator = zoneConfig;
    }

    public void startAgent() throws Exception {

        File file = configFile.getFile();
        String configPath = file.getAbsolutePath();

        LOG.info("Using config file: " + configPath);

        // Read the configuration file
        fCfg = new AgentConfig();
        fCfg.read(file.getAbsolutePath(), false);

        // Override the SourceId passed to the constructor with the SourceId
        // specified in the configuration file
        setId(fCfg.getSourceId());

        // Inform the ADK of the version of SIF specified in the sifVersion=
        // attribute of the <agent> element
        SIFVersion version = fCfg.getVersion();
        LOG.info("Using SIF version: " + version);
        ADK.setVersion(version);

        // initialize once the configuration file has been read
        super.initialize();

        fCfg.apply(this, true);

        // Connect to each zone specified in the configuration file, registering
        // this agent as the Provider of the SIS objects.

        Zone[] allZones = getZoneFactory().getAllZones();

        zoneConfigurator.configure(allZones);
    }
}
