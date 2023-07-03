Feature: Users service
  Background: API request header
    Given API Header

  @TC-001
  Scenario Outline: Get user list on page 1
    And Content type is "json"
    When Query params "page" is "<page>"
    Then Response status is 200
    Examples:
      | page |
      | 1    |
      | 2    |

  @TC-002
  Scenario Outline: Get single user
    And Content type is "json"
    When Query params "users" is "<userId>"
    Then Response status is 200
    Examples:
      | userId |
      | 1      |
      | 2      |

  @TC-003
  Scenario Outline: Get single user but id not found
    And Content type is "json"
    When Query params "users" is "<userId>"
    Then Response status is 404
    Examples:
      | userId |
      | 23     |
      | 24     |

  @TC-004
  Scenario Outline: Create new user
    And Content type is "json"
    When Create new "user" with payload "<name>" and "<job>"
    Then Response status is 201
    Examples:
      | name     | job        |
      | Lebron   | NBA Player |
      | Hamilton | F1 Driver  |

  @TC-005
  Scenario Outline: Update existing user
    And Content type is "json"
    When Update existing user with id "<userId>" using payload "<name>" and "<job>"
    Then Response status is 200
    Examples:
      | name        | job         | userId |
      | Jordan      | NBA Players | 1      |
      | Steph Curry | NBA Player  | 2      |

  @TC-006
  Scenario Outline: Delete existing user
    And Content type is "json"
    When Delete existing user with id "<userId>"
    Then Response status is 204
    Examples:
      | userId |
      | 1      |
      | 2      |

  @TC-007
  Scenario Outline: Register new account
    And Content type is "json"
    When Create new "account" with payload "<email>" and "<password>"
    Then Response status is 200
    Examples:
      | email           | password    |
      | michael.lawson@reqres.in | bestplayer  |
      | lindsay.ferguson@reqres.in  | bestshooter |

  @TC-008
  Scenario Outline: Login using registered account - successful
    And Content type is "json"
    When Login with payload "<email>" and "<password>"
    Then Response status is 200
    Examples:
      | email           | password    |
      | michael.lawson@reqres.in | bestplayer  |
      | lindsay.ferguson@reqres.in  | bestshooter |


