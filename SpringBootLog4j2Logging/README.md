This is spring boot's simple log4j2 logging application. It requires to exclude the default logging present in the spring boot starter project and include the log4j2 dependency in the pom file explicitly.

The logs will be logged under the logs directory that will get created under the project's root directory and will also be shown on the console.

In order to view the logs, run the following command from the root directory of the project:

mvn spring-boot:run

Once the application is up and running, hit the below url in the browser:

http://localhost:8080/

You can see the logs in the console and also in the logs directory created under the root directory of the project.


Rishi Bhalla
