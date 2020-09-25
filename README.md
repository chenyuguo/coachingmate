# coachingmate
 This is a unimelb project of subject COMP90082.
 this module is responsible for acquiring and processing data.
 Data collected in Garmin Connect is acquired through Garmin wearable 
 devices used by athletes training under CoachingMate.
 
 # the package introduction
 
 - config: the configuration class
 - controller: provide interface for front end
 - domain: includes all the entity class
 - exception: is responsible for catching and responsing exceptions
 - logs : define where logs are needed
 - mapper : interact with database
 - service : responsible for business logic
 - utils : encapsulate some useful tools
 - test : unit test module


## Project Name & Pitch 
Coachmate

An application used to connect the Garmin connect and retrieve activity data, built with Springboot framework.

## Project Status

This project is currently in development. 
1. Users can register an account and login to this system. 
2. User can authorised this account to garmin connect so that garmin connect can transfer activity data to this backend.



## Installation and Setup Instructions

Clone down this repository. You will need `maven` and `JDK1.8` installed globally on your machine.  

`git clone https://github.com/chenyuguo/coachingmate`

Installation:

`cd coachingmate`
`mvn install`  

To Start Server:

`java -jar ./target/coachingmate-0.0.1-SNAPSHOT.jar`  

To login App:

`localhost:8080/login?username={username}&password={password}`  

