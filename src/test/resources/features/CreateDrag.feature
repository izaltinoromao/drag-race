@DragRace
@CreateDrag
Feature: Creating a drag

  Scenario Outline: Successfully when creating a new drag
    When I successfully create a new drag with car year <carYear> and car model "<carModel>" and driver "<driver>" and drag time <dragTime> and speed trap <speedTrap>
    Then the status code must be 201
    And the driver must be equals to "<driver>"
    And the carModel must be equals to "<carModel>"
    And the carYear must be equals to <carYear>
    And the dragTime must be equals to <dragTime>
    And the speedTrap must be equals to <speedTrap>

    Examples:
      | carYear | carModel | driver  | dragTime | speedTrap |
      | 2018    | Model 3  | Netin   | 35.68    | 152.65    |
      | 2018    | Golf R   | Clebin  | 38.79    | 147.84    |
      | 2017    | Macan    | Junin   | 27.45    | 135.69    |
      | 2020    | Forester | Dilsin  | 30.14    | 125.55    |


  Scenario Outline: Unsuccessfully when creating a new drag with a non invalid carModel
    When I unsuccessfully create a new drag with car year <carYear> and car model "<carModel>" and driver "<driver>" and drag time <dragTime> and speed trap <speedTrap>
    Then the status code must be 404
    And the message must be "<message>"

    Examples:
      | carYear | carModel | driver  | dragTime | speedTrap | message                                                 |
      | 2018    | abcde    | Freud   | 35.68    | 152.65    | Car model: abcde and year: 2018 not found at CarDataApi |
      | 2018    | fghij    | Jackcon | 38.79    | 147.84    | Car model: fghij and year: 2018 not found at CarDataApi |



  Scenario Outline: Unsuccessfully when creating a new drag with a non invalid carYear
    When I unsuccessfully create a new drag with car year <carYear> and car model "<carModel>" and driver "<driver>" and drag time <dragTime> and speed trap <speedTrap>
    Then the status code must be 404
    And the message must be "<message>"

    Examples:
      | carYear | carModel  | driver  | dragTime | speedTrap | message                                                   |
      | 1580    | Model 3   | Vito    | 35.68    | 152.65    | Car model: Model 3 and year: 1580 not found at CarDataApi |
      | 1258    | Golf R    | Joe    | 38.79    | 147.84    | Car model: Golf R and year: 1258 not found at CarDataApi  |



  Scenario Outline: Unsuccessfully when creating a new drag with no driver's name
    When I unsuccessfully create a new drag with car year <carYear> and car model "<carModel>" and driver "<driver>" and drag time <dragTime> and speed trap <speedTrap>
    Then the status code must be 400
    And the message must be "<message>"

    Examples:
      | carYear | carModel  | driver  | dragTime | speedTrap | message                  |
      | 2018    | Model 3   |         | 35.68    | 152.65    | driver must not be empty and not negative for number |
      | 2018    | Golf R    |         | 38.79    | 147.84    | driver must not be empty and not negative for number |


    