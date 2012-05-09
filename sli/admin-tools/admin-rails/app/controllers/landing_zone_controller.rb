class LandingZoneController < ApplicationController
  before_filter :check_roles
  rescue_from ActiveResource::ForbiddenAccess, :with => :render_403
  rescue_from ProvisioningError, :with => :handle_error
  rescue_from ActiveResource::ResourceConflict, :with => :already_there
  
  def provision
    if (params[:cancel] == "Cancel")
      redirect_to "/"
      return
    end
    
    tenant = get_tenant
    if (tenant == nil)
      render_403
      return
    end

    ed_org_id = params[:ed_org]
    ed_org_id = params[:custom_ed_org] if ed_org_id == 'custom'
    if (ed_org_id == nil || ed_org_id.gsub(/\s/, '').length == 0)
      redirect_to :action => 'index', :controller => 'landing_zone'
    else
      ed_org_id = ed_org_id.gsub(/^ed_org_/, '')
      LandingZone.provision ed_org_id, tenant
    end
  end

  def index
    @edOrgs = LandingZone.possible_edorgs
  end
  
  def handle_error
    render :status => 500, :text => "An error occured when provisioning the landing zone"
  end
  
  def check_roles
    unless session[:roles].include? "LEA Administrator"
      logger.warn "Rejecting user #{session[:full_name]} due to insufficient privilages: roles: #{session[:roles]}"
      render_403
    end
  end

  def get_tenant
    check = Check.get ""
    if APP_CONFIG["is_sandbox"]
      return check["user_id"]
    else
      return check["tenantId"]
    end
  end

  def already_there
    respond_to do |format|
      format.html { render :file => "#{Rails.root}/public/409.html", :status => :conflict }
      format.any  { head :conflict }
    end
  end
end
