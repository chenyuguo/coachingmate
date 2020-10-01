## Project Name & Pitch 
Coachmate

An application used to connect the Garmin connect and retrieve activity data, built with Springboot framework.
This is a unimelb project of subject COMP90082 which is responsible for acquiring and processing data.
 Data collected in Garmin Connect is acquired through Garmin wearable. devices used by athletes training under CoachingMate.

## Project Status

This project is currently in development. 
1. Users can register an account and login to this system. 
2. User can authorised this account to garmin connect so that garmin connect can transfer activity data to this backend.

## Components diagram
![image](https://github.com/chenyuguo/coachingmate/blob/master/Components%20Diagram.pdf)

## the package introduction
 
- controller: provide interface for front end
- entity: includes all the entity class
- dao : interact with database
- service : responsible for business logic
- utils : encapsulate some useful tools
- test : unit test module

## Installation and Setup Instructions

Clone down this repository. You will need `maven` and `JDK1.8` installed globally on your machine.  

`git clone https://github.com/chenyuguo/coachingmate`

Installation:

`cd coachingmate` <br>
`mvn install`  

To Start Server:

`java -jar ./target/coachingmate-0.0.1-SNAPSHOT.jar`  

To login App:

`localhost:8080/login?username={username}&password={password}`  
## How to run the project on a new Server
### There are serveal steps we need to do to run our app on heroku
### First we need to create a new application on heroku
![image](https://github.com/chenyuguo/coachingmate/blob/master/Resources/runonserver/picture/pic/1newApp.jpg)

### Next we need to text our app name and region
![image](https://github.com/chenyuguo/coachingmate/blob/master/Resources/runonserver/picture/pic/2newCreate.jpg)

### Select Github and find our project in our repository
![image](https://github.com/chenyuguo/coachingmate/blob/master/Resources/runonserver/picture/pic/3connect.jpg)

### connect successfully
![image](https://github.com/chenyuguo/coachingmate/blob/master/Resources/runonserver/picture/pic/4success.jpg)

### Finally, find our app and click the Openapp button
![image](https://github.com/chenyuguo/coachingmate/blob/master/Resources/runonserver/picture/pic/5open.jpg)

### we can find our URL in the build log
![image](https://github.com/chenyuguo/coachingmate/blob/master/Resources/runonserver/picture/pic/6.jpg)

URL: <https://coachingmate2020.herokuapp.com/>



