# REST API

### Introduction

We chose to set up the rest-server with help from the Springboot-framework. The restserver module contains:

- CookbookApplication
    
    Contains the start-method for the server application
    
- CookbookController
    
    Contains methods to handle HTTP-requests. 
    
- CookbookService
    
    Contains methods used by CookbookController to handle User-objects. Uses an UserDataFilehandling object to access the existing users and add new ones.