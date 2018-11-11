# Currency Converter App

Currency Converter App converts the given amount from one currency to another currency,shows latest currency rates and last 10 conversion queries.
It uses https://docs.openexchangerates.org/ api's to fetch latest currency rates. 


## Getting Started

### Prerequisites

* JDK 1.8 + versions
* Git
* Maven 3+
* IDE (Eclipse or STS)

### Clone

To get started you can simply clone this repository using git:
```
git clone https://github.com/SandeepKmr/CurrencyConverter.git
```
### Build an executable JAR

You can run the application from the command line using:
```
cd CurrencyConverter/

mvn spring-boot:run
```
Or you can build a single executable JAR file that contains all the necessary dependencies, classes, and resources with:
```
mvn clean package
```
### Deploy an executable JAR

You can run the JAR file with:
```
java -jar target/*.war
```
It will auto deployed in embedded tomcat provided with Spring Boot at port 8080.

```
http://localhost:8080/

```

### Built With

* Spring Boot
* Spring Security
* Spring MVC
* Maven
* H2 Database
* HTML
* CSS
* Materialize
* JSP
* JQuery
* EhCache

### Contributor

* **Sandeep Kumar**  

### License

* Currency Converter is a open source software.Code released under the MIT license.  
