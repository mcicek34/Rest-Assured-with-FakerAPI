Feature: Faker API validation

  Scenario: The quantity field is validated
    Given I perform Get operations for Books
    Then I perform GET for the quantity number

  Scenario: Data fields validation
    Given I perform Get operations for Text
    Then Data fields are checked

  Scenario: Character fields validation
    Given I perform Get operations for Text
    When Character fields are checked for character length "0"
    When Character fields are checked for character length "200"
    Then Character fields are checked for character length "500"

  Scenario: Special Data fields validation
    Given I perform Get operations for Custom
    Then Special Data fields are checked
