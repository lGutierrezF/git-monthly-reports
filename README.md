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
Execute the following command to set the token that will be used to connect to
the GitHub API as an environment variable.
```
macOS/Linux bash:
export ACCESS_TOKEN_GITLAB=your_access_token

Windows PowerShell:
$env:ACCESS_TOKEN_GITLAB = "your_access_token"
```
It's important to make sure that your token has authorization to access the
organization data that will be inputted at the application execution.

### 3- Execute application:
Execute the following command at the root of the project to start the Springboot application and input
the organization name you want to make the report on and the month to analyze.
```
mvn clean compile -Dexec.mainClass=springboot.SpringbootApplication exec:java
```
Once the execution is finished, the CSV reports will be stored in the report
folder in the root of the project.