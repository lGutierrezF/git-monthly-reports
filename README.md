# git-monthly-reports
## Prerequisites
- Docker
- Maven
- CURL
## Stack used
- Java 17
- SpringBoot 3

### 1- Create MongoDB container:
Execute the following command to create and start a MongoDB docker container
that will store the organization's monthly report.
```
docker run -d --name my-mongodb-container -p 27017:27017 mongo:latest
```

### 2- Execute application:
Execute the following command to start the Springboot application and input
the organization name you want to make the report on and the month to analyze.
```
mvn clean compile -Dexec.mainClass=springboot.SpringbootApplication exec:java
```
Once the execution is finished, the CSV reports will be stored in the report
folder in the root of the project.