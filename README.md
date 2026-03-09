# Medilabo Solutions


## Project Architecture:  

The Medilabo Solutions application is a demonstrative project that was written using a microservices pattern. There are 5 independent  microservices, each serving a different business function for the Medilabo Solutions client. These include the following: 
- **patient-api**: which allows the client to interact with the patients database to add new patients, or retrieve/update/delete existing patient information.  
- **notes-api**: an interface to retrieve or add medical notes from a separate notes database  
- **risk-assessment-api**: its business function is for risk analysis based on data from multiple sources. (e.g. assessing a patient's risk for diabetes based on demographics and medical history)   
- **gateway-service**: a strictly technical microservice to combine all the network traffic and potentially improve security and efficiency. 
- *medilabo-ui*: a minimalistic browser-based GUI for the client to see everything in one place.  


## Technologies Used:  

1. Java 25 
2. PostgreSQL Database  
3. MongoDB Database  
4. Java Spring Boot w/:   
    - Spring Cloud Gateway 
    - Spring Security 
5. React.js 
6. Docker 


## Project Setup: 

1. Build each Spring Boot microservice individually by running `mvn clean install` 
2. Run `docker compose up --build -d` from the root of the application
3. *Optional*: Run **medilabo-ui** outside of Docker by running `npm install` and `npm run dev`
    - Note: make sure to comment out section on `medilabo-ui:` from docker-compose.yml if choosing this option. 
4. To stop the application services, run `docker compose down`.  


## Endpoints: 

> /patients 
*  ***(See PatientController)*** 
* This endpoint can be used to retreive all exising patients in the database. 

> /patient
*  ***(See PatientController)*** 
* This endpoint will allow the following actions via Get/Post/Put/Delete with HTTP: 
    * Retrieve an existing patient 
    * Add a new patient 
    * Update information for an existing patient (any field other than patient ID)
    * Delete a patient from the database 

> /notes
*  ***(See NotesController)*** 
* This endpoint will allow the following actions via Get/Post with HTTP: 
    * Retrieve medical notes for an existing patient 
    * Add new notes for an existing patient 

> /risk-assessment/diabetes-report
*  ***(See RiskAssessmentController)*** 
* This endpoint will do the following actions via a Get request with HTTP: 
    * Retrieve patient information and medical notes based on patient id 
    * Generate a diabetes risk analysis report for patients based on criteria provided by the client


> /auth/login
*  ***(See AuthController in gateway-service)*** 
* This endpoint will do the following actions via a Post request with HTTP: 
    * Retrieve a JWT session token and log in the user for a specified time interval (kept it at 10 minutes for testing purposes)  

> /api/user/me
*  ***(See TestController in gateway-service)*** 
* This endpoint will do the following actions via a Get request with HTTP: 
    * Retrieve the username for the logged in user (based on Authorization token generated at time of login)  


> /api/public/hello
> /api/user/hello
> /api/admin/hello 
*  ***(See TestController in gateway-service)*** 
* These 3 endpoints will do the following actions via a Get request with HTTP: 
    * Return a unique message based on the role of the logged in user. 



### Some examples of *Green Code* in this Project: 
    - Followed Spring-Boot MVC architecture to organize the code into smooth layers of communication.  
    - Made use of util packages to define and reuse commonly used functions (see risk-assessment-api)
    - Made use of existing powerful libraries, such as JpaRepository and MongoRepository, to use industry best practices and spend less time doing what's already been done well. 