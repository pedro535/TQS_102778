Feature: Buy a trip

  Scenario: Select a flight and buy a ticket
    Given I am on the 'https://blazedemo.com/' website
    When I select 'Boston' as departure city
    And I select 'Rome' as destination city
    And I click on the Find Flights button
    Then I should be redirected to a page with the title 'BlazeDemo - reserve'
    And I should see the 'h3' phrase 'Flights from Boston to Rome:'

    When I choose the flight 1
    Then I should be redirected to a page with the title 'BlazeDemo Purchase'
    And I should see the 'h2' phrase 'Your flight from TLV to SFO has been reserved.'

    When I fill the name form with 'Pedro Rodrigues'
    And I fill the Location form with the Address 'Rua dos Moinhos', City 'Viseu', State 'PT' and ZipCode '1234'
    And I fill the Card form with Card Type 'Visa', Card Number '123', Month '11', Year '2024' and Name on Card 'Pedro Rodrigues'
    And I click on the Purchase Flight button
    Then I should be redirected to a page with the title 'BlazeDemo Confirmation'
    And I should see the 'h1' phrase 'Thank you for your purchase today!'
