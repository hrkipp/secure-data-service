=begin
#--

Copyright 2012-2013 inBloom, Inc. and its affiliates.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

=end

require "rest-client"

#++
# This module contains a lot of the recursive view logic needed to display
# items correctly in the databrowser views.
#
# The basic process is that the data gets split into the simple view and
# a complex view which represent the full entity and the tabular list of
# entities.
#
# To build and display them we go through a number of different methods to
# pull out the configured table columns based on the entity type and move
# the data into those columns
#
# Some of the columns are are also based on what we have configured in
# config/views.yml
#
module EntitiesHelper

  # Here we go through the entities the API has returned and figure out what
  # type it is and then get the table columns out of set up.
  def get_table_fields(entities)
    tableFields = Array.new
    return tableFields if entities.nil? || (entities.is_a?(Array) && entities.empty?)

    entity = entities.first
    if (entity.has_key?('entityType') && VIEW_CONFIG.has_key?(entity['entityType']))
      tableFields = VIEW_CONFIG[entity['entityType']]
    end

    if tableFields.empty?
      entity.each do |key, val|
        next if val.is_a?(Array) || val.is_a?(Hash)
        tableFields.push(key)
        break if tableFields.length >= 5
      end
    end

    return tableFields
  end

  # Because entities are complex json objects this method will look at the
  # "path" we have described to extract the data to show in the table, and then
  # dig into that data to pull out the individual items
  #
  # At this point it doesn't support Arrays in complex types, it will just take
  # the first item in that array. Some example of where this will happen are in
  # addresses and names.
  def value_for_table_view (type, entity)
    return nil if entity.nil? or type.nil?
    return entity[type] unless type.include? '/'
    temp_hash = entity
    type_split = type.split '/'
    type_split.each do |split|
      if temp_hash.is_a?(Array)
        temp_hash = temp_hash.first[split] unless temp_hash.first.nil?
      elsif temp_hash.has_key? split
        temp_hash = temp_hash[split]
      else
        return nil
      end
    end
    temp_hash
  end

  # This method takes an API URL and then does a simple substitution to change
  # it to point to the databrowser instead, this is how we make the HATEOS
  # links work.
  def localize_url(url)
    url.gsub(%r(#{APP_CONFIG['api_base']}\.?\d+?), "#{request.protocol}#{request.host_with_port}/entities") unless url.nil?
  end

  # Here we detect links and turn it into an alphabetized list of links that
  # reference the databrowser instead of the API. We also use the i18n
  # translation file to make some of the links more readable.
  def build_links(hash)
    html = ""
    if hash.is_a?(Array)
      html << "<ul class='values'>"
      hash.sort_by{|link| t(link["rel"]).downcase}.each do |link|
        # This is the 'kill' switch for counts. If this is changed to false in config.yml then
        # counts will not be displayed
        if APP_CONFIG['link_counts_enabled'] == true

          begin
            url = link['href']
            if (url.include? "?")
              url = "#{url}&limit=0"
            else
              url = "#{url}?limit=0"
            end

            entities = RestClient.get(url, get_header)
            entities = JSON.parse(entities)
            totalCount = entities.size()
            currentCount = 0
            entities.each do |entity|
              if (is_current(entity))
                currentCount += 1
              end
            end

            html << '<li>' << link_to(t(link["rel"]), localize_url(link["href"])) << " (#{totalCount}/#{currentCount})" << '</li>'
          
          rescue => e
            logger.debug("Failed to successfully get to the url: #{e.message}")
            if (link['rel'].include? 'self')
              html << '<li>' << link_to(t(link["rel"]), localize_url(link["href"])) << " (1/1)" << '</li>'
            else
              html << '<li>' << link_to(t(link["rel"]), localize_url(link["href"])) << " (N/A)" << '</li>'
            end
          end
        else
          html << '<li>' << link_to(t(link["rel"]), localize_url(link["href"])) << '</li>'
        end
      end
      html << '</ul>'
    else
      html << link_to(t(hash["rel"]), localize_url(hash["href"]))
    end
    html
  end

  # This method sorts the entity into the order determined by ./config/order.yml
  # It assumes that the entity object is a Hash. If the entity has the key entityType,
  # then it will use that configuration from ./config/order.yml. Otherwise, it will use
  # the 'default' configuration from that file. It sorts the hash into the desired order,
  # appends the keys that were not given in the configuration and then appends "links" as
  # it was desired that "links" be the very last component displayed.
  def sort_entity (entity)
    if (entity.has_key?('entityType') && SORT_CONFIG.has_key?(entity['entityType']))
      order = SORT_CONFIG[entity['entityType']].clone.to_a
    else
      order = SORT_CONFIG["default"].clone.to_a
    end
    entity.each { |key, value|
      order << key if order.index(key).nil? && key != "links"
    }
    if !order.include?('links')
      order << "links"
    end
    Hash[entity.sort_by {|k, v| order.index(k)}]
  end

  # This method is pretty complex, it's recursive, so what it does is it digs
  # through the hashmap of data we have available and builds a definition list
  # of the data to all be displayed. It makes use of the other methods in this
  # module to accomplish this
  #
  # We would like to support nested lists, but at this point everything winds
  # up flattened in one single definition list.
  def display_entity (entity)
    html ||= ""
    if entity.is_a?(Hash)
      entity = sort_entity(entity)
      entity.each { |key, value|
        val_text = (key == 'links' || key == 'link') ? build_links(value) : display_entity(value)
        val_text = "&nbsp" if val_text.nil? || val_text.empty?
        address_text = (key == 'address') ? " address" : ""
        html << "<div class='row'><div class='key left'>#{t(key)}:</div><div class='value#{address_text}'>#{val_text}</div></div>"
      }
    elsif entity.is_a?(Array)
      # The following is so that a list a values (like Links) can be displayed in a
      # different way than a list of hashes (like address)
      if entity[0].is_a?(Hash)
        html << "<ul class='hashes'>"
      else
        html << "<ul class='values'>"
      end
      entity.each do |item|
        html << '<li>' << display_entity(item) << '</li>'
      end
      html << '</ul>'
    else
      html << entity.to_s
    end
    html
  end

  # Return default header information for the RestClient
  private
  def get_header
    header = Hash.new
    header[:Authorization] = "Bearer #{Entity.access_token}"
    header[:content_type] = :json
    header[:accept] = :json
    header
  end

private
def is_current(entity)
  result = false

  now = Date.today
  
  begin_date_field = "beginDate"
  end_date_field = "endDate"
  
  begin_date = nil
  end_date = nil
  
  if (entity['entityType'] == "studentSchoolAssociation")
    begin_date_field = "entryDate"
    end_date_field = "exitWithdrawDate"
  end
  
  if (entity[begin_date_field].nil?)
    begin_date = now - 1
  else
    begin_date = Date.strptime(entity[begin_date_field], "%F") 
  end

  if (entity[end_date_field].nil?)
    end_date = now + 1
  else 
    end_date = Date.strptime(entity[end_date_field], "%F")
  end

  if (begin_date <= now) and (end_date >= now)
    result = true
  end
  
  if (entity['studentId'] == "b8e346c8-025e-44ba-9ae1-f2fa4e832b08_id")
    logger.info("Begin Date: #{begin_date} End Date: #{end_date} Result: #{result} Id: #{entity['id']}")
  end
  result
 
end

end
