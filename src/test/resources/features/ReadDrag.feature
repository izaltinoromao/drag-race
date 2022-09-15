@DragRace
@CreateDrag
Feature: Reading drags

  Scenario Outline: Successfully when read a drag by a valid driver name
    When I successfully read a drag by a valid driver "<driver>"
    Then the status code must be 200
    And the driver must be equals to "<driver>"
    And the carModel must be equals to "<carModel>"
    And the carYear must be equals to <carYear>
    And the dragTime must be equals to <dragTime>
    And the speedTrap must be equals to <speedTrap>
    Examples:
      | driver | carModel | carYear | dragTime | speedTrap |
      | Netim  |    M3    |   2019  |   15.65  |  136.89   |



  Scenario Outline: Unsuccessfully when read a drag by a invalid driver name
    When i unsuccessfully read a drag by a invalid driver "<driver>"
    Then the status code must be 404
    And the message must be "<message>"
    Examples:
      | driver | message                                        |
      | asdvar |   No driver asdvar was found at Data Base      |



 Scenario: Successfully when read a list of time winners
   When I successfully read a list of time winners
   Then the status code must be 200
   And the list must contain 1 elements
