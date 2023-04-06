Feature: Search Air Quality Forecast


Scenario: Search Air Quality for Aveiro for the next 2 days
  Given I am on the 'http://localhost:5173/' website
  When I click on the Air Quality Forecast button
  Then I should be redirected to a new page with the title 'Air Quality Forecast'

  When I insert 'Aveiro' in the City field
  And I insert 'PT' in the Country Code field
  And I insert 2 in the totalDays field
  And I click on the Search button
  Then I should see the text 'Air Quality Forecast for "Aveiro"'
  And I should see the coordinates '(40.6405, -8.6538)'
  And I should see exactly 2 available dates
  # And I close the browser


Scenario: Search for Air Quality Forecast for an invalid location
  Given I am on the 'http://localhost:5173/' website
  When I click on the Air Quality Forecast button
  Then I should be redirected to a new page with the title 'Air Quality Forecast'

  When I insert 'AAAA' in the City field
  And I insert 'PT' in the Country Code field
  And I insert 2 in the totalDays field
  And I click on the Search button
  Then I should see the message 'There are no results for this location'
  # And I close the browser



Scenario: Search for Air Quality Forecast for an invalid number of days
  Given I am on the 'http://localhost:5173/' website
  When I click on the Air Quality Forecast button
  Then I should be redirected to a new page with the title 'Air Quality Forecast'

  When I insert 'Aveiro' in the City field
  And I insert 'PT' in the Country Code field
  And I insert 30 in the totalDays field
  And I click on the Search button
  Then I should see the message 'The maximum days for forecast is 4'
  # And I close the browser