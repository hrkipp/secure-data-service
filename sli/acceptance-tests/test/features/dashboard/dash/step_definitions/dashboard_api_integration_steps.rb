require 'selenium-webdriver'
require_relative '../../../utils/sli_utils.rb'


$SLI_DEBUG=ENV['DEBUG'] if ENV['DEBUG']


# TODO: externalize this to a method so we can reuse in the future
When /^I select "([^"]*)" and click go$/ do |arg1|
  sleep(1)
  
 realm_select = @driver.find_element(:name=> "realmId")

  
  options = realm_select.find_elements(:tag_name=>"option")
  options.each do |e1|
    if (e1.text == arg1)
      e1.click()
      break
    end
  end
  clickButton("go", "id")
  
end

When /^I login as "([^"]*)" "([^"]*)"/ do | username, password |
    sleep(1)
    wait = Selenium::WebDriver::Wait.new(:timeout => 5) # explicit wait for at most 5 sec
    wait.until{@driver.find_element(:id, "IDToken1")}.send_keys username
    @driver.find_element(:id, "IDToken2").send_keys password
    @driver.find_element(:name, "Login.Submit").click
end

Then /^I should be redirected to the app selection page$/ do
  expected_url = getBaseUrl() + PropLoader.getProps['dashboard_app_selector_page']
  puts "Expected URL = " + expected_url + ", Current URL = " + @driver.current_url 
  assert(@driver.current_url == expected_url)
end


When /^I click on the Dashboard page$/ do
  @driver.find_element(:link_text=> "Dashboard").click
  sleep(2)
end

Then /^The students who have an ELL lozenge exist in the API$/ do
  @res = RestClient.get("https://devapp1.slidev.org/sp/identity/authenticate?username=mario.sanchez&password=mario.sanchez1234"){|response, request, result| response }
  @sessionId = @res.body[@res.body.rindex('=')+1..-2]
  
  students_w_lozenges = getStudentsWithELLLozenge()
  students_w_lozenges.each do |student_id|
    urlHeader = makeUrlAndHeaders('get' ,"/students/"+student_id, @sessionId, @format)
    @res = RestClient.get(urlHeader[:url], urlHeader[:headers]){|response, request, result| response }
    @result = JSON.parse(@res.body)
    assert(@result["limitedEnglishProficiency"].to_s == "Yes")
  end  
end

def getStudentsWithELLLozenge()
  studentTable = @driver.find_element(:id, "studentList");
  student_cells = studentTable.find_elements(:xpath, "//td[@class='name_w_link']")
  
  students_with_lozenges = []
  i = 0
  student_cells.each do |student_cell|
    
    all_lozengeSpans = student_cell.find_elements(:tag_name, "span")
    
    
    all_lozengeSpans.each do |lozengeSpan|
      if lozengeSpan.attribute("innerHTML").to_s.include?("ELL")
        lid = lozengeSpan.attribute("id")
        y = lid.split(".")
        students_with_lozenges[i] = y[1]
        i += 1
      end
    end
    

  end  
  return students_with_lozenges    
end

