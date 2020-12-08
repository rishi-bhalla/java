This is spring boot's simple, default, zero configuration logging application.

By default the logging level is set to INFO. Hence only info, warn and error logs will be shown in the logs. In case you want to view the other two logs i.e. TRACE and DEBUG logged in the LoggingController, please enable the properties present in the application.properties file.

In order to view the logs, run the following command from the root directory of the project:

mvn spring-boot:run

Once the application is up and running, hit the below url in the browser:

http://localhost:8080/

You can see the logs in the console.


Rishi Bhalla
