Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.

  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
    When the customer searches for books published between 2011-01-01 and 2013-04-01
    Then 2 books should have been found
      And Book 1 should have the title 'One good book'
      And Book 2 should have the title 'How to cook a dino'


  Scenario: Search books by category
    Given a list of books
    | title | author | published | category |
    | The hunger games | Suzanne Collins | 2008-09-14 | fiction |
    | Pride and Prejudice | Jane Austen | 1813-01-28 | novel |
    | The Fault in Our Stars | John Green | 2012-01-10 | romance | 
    | City Under One Roof | Iris Yamashita | 2023-01-10 | novel |
    | Klara and the Sun | Kazuo Ishiguro | 2021-03-02 | fiction |
    When the customer searches for 'fiction' books
    Then 2 books should have been found
      And Book 1 should have the title 'Klara and the Sun'
      And Book 2 should have the title 'The hunger games'


  Scenario: Search books by author
    Given a list of books
    | title | author | published | category |
    | The hunger games | Suzanne Collins | 2008-09-14 | fiction |
    | Pride and Prejudice | Jane Austen | 1813-01-28 | novel |
    | The Fault in Our Stars | John Green | 2012-01-10 | romance | 
    | City Under One Roof | Iris Yamashita | 2023-01-10 | novel |
    | Klara and the Sun | Kazuo Ishiguro | 2021-03-02 | fiction |
    When the customer searches for books written by 'Jane Austen'
    Then 1 books should have been found
      And Book 1 should have the title 'Pride and Prejudice'
    When the customer searches for books written by 'J'
    Then 2 books should have been found
      And Book 1 should have the title 'The Fault in Our Stars'
      And Book 2 should have the title 'Pride and Prejudice'

