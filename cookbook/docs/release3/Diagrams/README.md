# Diagrams 

## Class diagram
### Class diagram core
Following is a class diagram presenting all the classes in core.   
We start from the top:
    User can have one cookbook, and a cookbook can only have one user. Therfore the classes are 1 to 1 assosiated(?).   
    A cookbook can have 0 or many recipes.  
    A recipe can have one or many ingredients, and 
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