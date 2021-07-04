# WebDriver TestNG Project

## Run tests

`mvn test`

## Generate report

`mvn allure:report`

## Check for dependecy updates

`mvn versions:display-dependency-updates`

## Update to latest release version

`versions:update-properties`

## Update configurations in 
[src/main/resources/test_environment.properties](src/main/resources/test_environment.properties)

#### Browser type
`#browser=chrome`

`browser=firefox` -> active

#### Headless
`headless=false`
