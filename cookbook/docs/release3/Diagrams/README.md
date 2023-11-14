# Diagrams 

## Class diagram
### Class diagram core
Following is a class diagram presenting all the classes in core.   
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