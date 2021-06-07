Feature: FlipKart Automation
Scenario: FlipKart mobile search 
Given User is on FlipkartMobile page
When Fetch the mobile name and rating if less than 40000
Then All the data is written in csv file
