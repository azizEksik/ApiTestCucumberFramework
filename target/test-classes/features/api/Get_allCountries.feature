
Feature: Database'de kayitli olan butun ulkelerin listesini dondurmak icin kullanilir
  @Api
  Scenario: Success Response
    Given Api kullanicisi "api/profile/allCountries" path parametreleri set eder
    Then AllCountries icin get request gonderilir