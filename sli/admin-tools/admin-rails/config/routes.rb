SLIAdmin::Application.routes.draw do
  resources :realm_management
  post "landing_zone/provision", :to => 'landing_zone#provision'
  get "landing_zone/provision", :to => 'landing_zone#success'
  get "landing_zone", :to => 'landing_zone#index'

  resources :account_managements
  resources :application_authorizations

  get "sessions/new"

  # The priority is based upon order of creation:
  # first created -> highest priority.
  
  # Sample of regular route:
  #   match 'products/:id' => 'catalog#view'
  # Keep in mind you can assign values other than :controller and :action

  # Sample of named route:
  #   match 'products/:id/purchase' => 'catalog#purchase', :as => :purchase
  # This route can be invoked with purchase_url(:id => product.id)
    # match 'mappings/:id/add' => 'mappings#add'
  # Sample resource route (maps HTTP verbs to controller actions automatically):

    resources :roles
    resources :sessions
    resources :apps
    resources :realms
    match '/apps/approve', :to => 'apps#approve'
    match '/apps/unregister', :to => 'apps#unregister'

    resources :realms do
      member do
        put :update
      end
    end
    
    
    match '/logout', :to => 'sessions#destroy'
    match '/callback', :to => 'application#callback'
	
  # Sample resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Sample resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Sample resource route with more complex sub-resources
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', :on => :collection
  #     end
  #   end

  # Sample resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end

  # You can have the root of your site routed with "root"
  # just remember to delete public/index.html.
  root :to => 'roles#index'

  # See how all your routes lay out with "rake routes"

  # This is a legacy wild controller route that's not recommended for RESTful applications.
  # Note: This route will make all actions in every controller accessible via GET requests.
  # match ':controller(/:action(/:id(.:format)))'
end
