Feature: Search Current Day Air Quality

Scenario: Search for Today's Air Quality in Aveiro
  Given I am on the 'http://localhost:5173/' website
  When I click on the Today's Air Quality button
  Then I should be redirected to a new page with the title 'Today\'s Air Quality'

  When I insert 'Aveiro' in the City field
  And I insert 'PT' in the Country Code field
  And I click on the Search button
  Then I should see the text 'Today\'s Air Quality in "Aveiro"'
  And I should see the coordinates '(40.6405, -8.6538)'
  And I should see at least one result


Scenario: Search for Today's Air Quality for an invalid location
  Given I am on the 'http://localhost:5173/' website
  When I click on the Today's Air Quality button
  Then I should be redirected to a new page with the title 'Today\'s Air Quality'

  When I insert 'AAAA' in the City field
  And I insert 'PT' in the Country Code field
  And I click on the Search button
  Then I should see the message 'There are no results for this location'



