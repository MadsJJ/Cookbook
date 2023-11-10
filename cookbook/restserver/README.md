# REST API

## Introduction

We chose to set up the rest-server with help from the Springboot-framework. The restserver module contains:

- CookbookApplication
    
    Contains the start-method for the server application
    
- CookbookController
    
    Contains methods to handle HTTP-requests. 
    
- CookbookService
    
    Contains methods used by CookbookController to handle User-objects. Uses an UserDataFilehandling object to access the existing users and add new ones.


## Supported Requests:

### Request to get and display all Users: 
**GET**  /cookbook/   
Content-Type: application/json  
Host: [localhost:8080](http://localhost:8080) 

### Request to log in
**POST** /cookbook/login?username={username}&password={password}  
Content-Type: application/json  
Host: [localhost:8080](http://localhost:8080) 

### Request to register new user
**POST** /cookbook/register?username={username}&password={password}  
Content-Type: application/json    
Host: [localhost:8080](http://localhost:8080)  

### Request to update user  
**POST** /cookbook/update?username={username}  
Content-Type: application/json    
Host: [localhost:8080](http://localhost:8080)   