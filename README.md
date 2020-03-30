# Covid-Vaadin-App
Application to visualize COVID-19 in Czech Republic

This project was created from https://start.vaadin.com. It's a fully working Vaadin application that you continue developing locally.
It has all the necessary dependencies and files to help you get going.

The project is a standard Maven project, so you can import it to your IDE of choice. You'll need to have Java 8+ and Node.js 10+ installed.

To run from the command line, use `mvn spring-boot:run` and open [http://localhost:8080](http://localhost:8080) in your browser.


## Build application

By default will be created jar Springboot application. If you need to create war file for tomcat you must update pom file with changing `jar` to `war` : 

`<packaging>war</packaging>`

By default, building and running are made in 'development' mode. This is good while working on the app as the build/start time is shorter, but the app would not work in IE11. In order to use production mode, you need to add -Pproduction to build/run commands:

`build: mvn package -Pproduction`

## Running the Application

Import the project to the IDE of your choosing as a Maven project.

Run the application using `mvn spring-boot:run` or by running the `Application` class directly from your IDE.

Open http://localhost:8080/ in your browser.

If you want to run the application locally in the production mode, run `mvn spring-boot:run -Pproduction`.

To run Integration Tests, execute `mvn verify -Pintegration-tests`.

## More Information

- [Vaadin Flow](https://vaadin.com/flow) documentation
- [Using Vaadin and Spring](https://vaadin.com/docs/v14/flow/spring/tutorial-spring-basic.html) article



