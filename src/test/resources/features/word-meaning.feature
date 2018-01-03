#Author: Hussain.Koser@gmail.com
@OxfordWordMeaning
Feature: 
 As a user i want to pass 

  @MeaningFound @Positive
  Scenario: User passes valid word
    Given user has passed headers
    And user has passed "plumber" and "en"
    When user access the "Oxford" api
    Then user gets success
    And user gets description

  @MeaningNotFound @Negative
  Scenario: User passes invalid word
    Given user has passed headers
    And user has passed "fg55d" and "en"
    When user access the "Oxford" api
    Then user gets failure
