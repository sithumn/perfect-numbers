# perfect-numbers

## Pre-requisites

```
Java 8
```

## Setup

* Execute the following command to get the repo from github
```
git clone https://github.com/sithumn/perfect-numbers.git
```

* Navigate to perfect-numbers directory
```
cd perfect-numbers
```

* To download dependencies execute;
```
./gradlew build
```

## Configuration

* Configuration file `config.properties` exists in `src/main/resources`

## Running

### Executing tests

Test are written using JUnit. Integration tests were run by firing up the embedded jetty server in the test cases and calling the API endpoints in the test cases.

```
./gradlew test
```
* Unit tests are placed in `src/test/java/org/perfectnumbers/util`
* Integrations tests are placed in `src/test/java/org/perfectnumbers/integration`

### Start the service
```
./gradlew run
```
or
```
./gradlew clean bundle && cd build/libs/ && java -jar perfectnumbers-1.0-SNAPSHOT.jar
```
Above commands will build the source and run the server on `http://0.0.0.0:8080`

The difference between two commands is the first one runs the server from the gradle itself using built in task and does not create a jar file in the build directory.

The second command will build and bundle sources in to a jar file and copy all the dependencies in to a directory called `dependencies` which contains all the artifacts required for the production build.

### Tryout the service

The api has three endpoints exposed.

```
// Check if a given number is a perfect number
GET /perfectnumbers/{number}
// Get all the perfect numbers between a number range
GET /perfectnumbers?start={start}&end={end}
// Health check url
GET /health
```
* Use curl or postman to send requests
    * Check if a given number is a perfect number
    ```
    curl -XGET http://0.0.0.0:8080/perfectnumbers/6
    ```
    Response:
    ```js
    {
        "number": 6,
        "valid": true
    }
    ```
    * Get all the perfect numbers with in a given range
    ```
    curl -XGET http://0.0.0.0:8080/perfectnumbers?start=0&end=1000
    ```
    Response:
    ```js
    {
        "perfectNumbers": [
            6,
            28,
            496
        ]
    }
    ```
    * Health check of the service will only return a 200 if the service is running healthyly.
    ```
    curl -XGET  http://0.0.0.0:8080/health
    ```
### Deployment build
Following command generates a deployable distribution as a zip file containing all the dependancies and two startup scripts as zip file in the `build/distributions`
```
./gradlew clean distZip
```

To run the distribution unzip the distribution zip file, navigate to the bin directory inside the inflated zip file and run the following command;
```
./perfectnumbers
```
## Design & implementation
* Service is designed & implemented carefully to improve the testability. Thus making it more maintainable, robust.
* Design patterns are used where appropriate.
    * Ex: Command pattern used in service implementation, Factory pattern is used to instantiote service classes(`src/main/java/org/perfectnumbers/service`)
* Health check URI is provided to check the health of the service
* Code was written both security and performance in mind. Thus request parameter validtion and various gaurded code was implemented and some performance improvement techniques were used.

## Improvements
* Long data type is used to do the implementation to simplify the implementation. Thus the service can only find perfect numbers up to 2^64 - 1. This can be improved by using BigInteger or another math API which support huge number calculations.
* Logging configuration can be improved
* API versioning can be added for it is an important part of an API