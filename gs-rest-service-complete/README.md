All the necessary information in contained in this text. 
Do the absolute bare minimum required for the application. Out of the box configurations will do just fine.
Take as much time as you need for this assignment, but it shouldn�t take more than 60-90min. 

In a basic Dockerized Springboot Maven application, develop a single REST endpoint GET /greeting which behaves in the following manner:

1. Given the following input values account=personal and id=123 
and 
the allowable values for account are personal and business
and 
the allowable values for id are all positive integers

then return "Hi, userId 123".

2. Given the following input values account=business and type=small
and
the allowable values for account are personal and business
and
the allowable values for type are small and big

then return an error that the path is not yet implemented.

3. Given the following input values account=business and type=big 
and
the allowable values for account are personal and business
and
the allowable values for type are small and big
then return "Welcome, business user!".

We should be able to:
- build application with Maven
- build the Docker image and run it
- make a request to localhost:5000/greeting and verify the behavior

Please provide an archive with the source code and a list of the terminal commands to build and run the application.

//

Instructions: (Windows 10, Command Prompt as admin)

1.
...\gs-rest-service-complete>mvnw clean install
...\gs-rest-service-complete>docker build -t javademo:v1.0 .
...\gs-rest-service-complete>docker run -d -p 5000:8080 javademo:v1.0

2.
In browser: 

http://localhost:5000/greeting?account=business&type=big

http://localhost:5000/greeting?account=personal&id=12345

etc


