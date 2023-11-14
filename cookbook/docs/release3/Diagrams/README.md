# Diagrams 

## Class diagram
### Class diagram core
Following is a class diagram presenting all the classes in core, and theirs attributes and methods. 
- **UserDataFilehanding:**
have zero or many Users. 
- **User:**
Have one CookBook.
- **CookBook:**
Belongs to one User, and has zero or many Recipes. 
- **Recipe:**
Belongs to one CookBook, and has zero or many ingredients. 
- **Ingredient:** 
Belongs to one or many recipes. 


![CoreClassDiagram](/cookbook/docs/release3/Diagrams/CoreClassDiagram.png)

### Class diagram REST server
The following class diagram represents the organization of all the classes in the REST server for the CookBook application. 

- **CookbookApplication:** Initiating the REST server. 
- **CookbookController:** Representing the controller. **COOKBOOK_SERVICE_PATH** indicates the path for cookbook-related services.
It also contains a instance of CookbookService and methods to interact with users, set user information, and perform auto-saving.
- **CookbookService:**  Representing the service layer for managing user data and interactions.
![RestserverClassDiagram](/cookbook/docs/release3/Diagrams/RestserverClassDiagram.png)

### Class diagram controllers

This diagram is representing the structure of UserControler and CookBookController, and theirs attributes and methods.

![ControllerClassDiagram](/cookbook/docs/release3/Diagrams/UIControllerClassDiagram.png)

## Sequence diagram 
Here is a sequence diagram showcasing the timeline of the steps involved in registering a new user to our cookbook-application.


### Components/Actors
- **User:** Initiates the registration by pressing "register new user" and filling in username and password.
- **UI (User Interface):** Interacts with the user by collecting their input
- **UserController:** Manages user-related actions and interactions  
- **RemoteCookbookAccess:** Handles remote access to the cookbook through HTTP POST requests
- **CookbookController (restserver/):** Handles HTTP requests and delegates operations to CookbookService
- **CookbookService:** Contains the logic for the user-related operations. 
- **UserDataFilehandler:** Serializes/Deserializes strings and manages local user data files
- **springboot.json:** Represents the JSON file used for data storage
- **CookbookController (ui/):** Represents the controller on the ui-side, manages the UI components.

### Steps 
- **User input:** the user inputs username and password
- **User registration:** The UI sends a registration request to the UserController
- **Username Availability Check:** UserController checks with RemoteCookbookAccess and CookbookService to see if the username is available
- **User creation:** If the username is available, UserController triggers sign up through CookbookService
- **Initialization:** THe system initializes, and the user can use tha application 

![SequenceDiagramRegisterNewUser](/cookbook/docs/release3/Diagrams/SequenceDiagramRegisterNewUser.png)


## Package diagram 
Following is a package diagram illustrating the high-level architecture of our packages and the dependencies in this project. 

### Components and Packages
We've already described most of our packages in [README.md](README.md) so here we'll just explain which external libraries we have used. 

**gson:** A library for JSON serialization and deserialization
**JavaFX:** A Java-based framework for building UI  
**SpringBoot:** A framework for building Java-based applications

### Relationships
UI:
- *access* depends on gson and core
- *ui* depends on JavaFX and core  

CORE:
- *core* depends on gson  

RESTSERVER:
- restserver depends on SpringBoot, gson and core

![PackageDiagram](cookbook/docs/release3/Diagrams/PackageDiagram.png)

