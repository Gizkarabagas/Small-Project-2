Feature: booking whitout login dan booking with login payment method and multiple booking

  Scenario: user can booking hotel with login
    Given user go to url "http://qa.cilsy.id:8080/en/" hompage and user can login
    When user can search hotel and choose date
    And user can choose a hotel with multiple categorie
    And user go to payment and proceed to checkout
    Then user booking accept

    Scenario: user can booking hotel without login
      Given user go to url "http://qa.cilsy.id:8080/en/" hompage..............
      When user choose date and hotel
      And user go to registerd account with email and address name
      And user go to payment and proceed checkout
      Then user booking acceot

      Scenario: User can booking with multiple categories hotel
        Given user go to url "http://qa.cilsy.id:8080/en/" homepage Hotel
        When user choose hotel and date
        And user can booking with defrent categories hotel
        And user go to payment and proceed checkout hotel
        Then user booking acccepted

        Scenario: User can booking with Payment Paypall
          Given user go to url "http://qa.cilsy.id:8080/en/"
          When user choose hotel and datee
          And user can booking with multiple categories hotel
          And user go to payment and choose paypal method dan checkout
          Then user see booking confirmed

          Scenario: user can payment with Bankwire
            Given user go to Url SP dua "http://qa.cilsy.id:8080/en/"
            When user chose hotel and date
            And user booking with two hotels deffrent
            And User choose payment with BankWire account
            Then User see bookihg confirmed

            Scenario: User login and booking withLogin\
              Given user got to Url Hotel "http://qa.cilsy.id:8080/en/"
              When user Login and choose hotel and date
              And user can booking with multiple Hotel Categories
              And user choose payment method
              Then user see booking accepted


