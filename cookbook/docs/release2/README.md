# Release 2

## Application Functionality
 
- Login or signup
 - Look at all your recipes
 - Filter your recipes based on category
 - Add recipe
 - Get random recipe
 - Remove recipe
 - Log out

## Architecture Diagram
The PlantUML diagram is representing the architecture of the application.

The diagram can be previewed i Visual Studio Code if you install the extantion PlantUML. 
The official website: https://www.plantuml.com/plantuml

You also need to install Graphviz. Go to https://graphviz.org/download/ for instructions on how to download on your computer. 

**architecture.puml**
![Architecture Diagram](UIDesign/diagram.png)

For description of what the packages contains, view 
[README.md](README.md).
### The interactions between the packages
[core](cookbook/core/src/main/java/core) may interact with the external librarie as Jackson, for data processing with JSON. 

[ui](cookbook/ui) relies on the javafx component for creating the graphical interface. It will also interact with core, to fetch and display data from the logic.

The javafx component is essential when creating the graphical UI. It will load UI resources from the [ui](cookbook/ui) package.

This architecture separates the core logic, user interface, and data handling components. It allows flexible development, and makes it easier to update or replace specific parts of the system without affecting the entire Cookbook application.

 ## Screenshots

 ### General Functionality  
We have not changed much on the interface. See comments before each picture for possible changes

**Login**  
Moved the boxes further down to make room for potenial error message
![LoginDesign](UIDesign/Login2.PNG)
**CookBook**  
Changed the font and added a box to type in which recipe you want to delete
![CookBookDesign](UIDesign/CookBookMain2.PNG)
**Add Recipe**  
Changed the unit-chooser to a dropdown-box instead of a text box. 
![CookBookAddDesign](UIDesign/CookBookAdd2.PNG)
**Random Recipe**
Layout
![CookBookRandomDesign](UIDesign/CookBookRandom2.PNG)
Get random recipe from a category
![RandomAppetizer](UIDesign/RandomAppetizer.png)
**View Recipes**
View all Recipes
![ViewAll](UIDesign/ViewAll.png)

From one category
![ViewAppetizer](UIDesign/ViewAppetizer.png)

 ### Pop-up error messages

 **Login**  
 Username or password not meeting criterias
![UsernameOrPasswordTooShort](UIErrorMessages/UsernameOrPassqordTooShort.png)  
Username or Password incorrect
![IncorrectUsernameOrPassword](UIErrorMessages/IncorrectUserInfo.png)

**Adding Recipe**  
Adding Recipe without filling in the title
![AddRecipeWithoutTitle](UIErrorMessages/AddRecipeWithoutTitle.png)
Adding Recipe without setting the category
![MissingCategory](UIErrorMessages/MissingCategory.png) 
Adding Recipe without filling in any ingredients
![MissingIng](UIErrorMessages/MissingIngredients.png)

**Adding Ingredient**  
Adding ingredient without setting the unit
![Add Ingredient without unit](UIErrorMessages/AddIngWithoutUnit.png)
Adding ingredient without setting amount
![MissingAmount](UIErrorMessages/MissingAmount.png)
Adding ingredient without writing the name of the ingredient.
![AddIngWithoutName](UIErrorMessages/AddIngWithoutName.png)  
**Removing**  
Removing a recipe that does not exist.  
![RemoveRecipeNotInList](UIErrorMessages/RemoveNotExistingRecipe.png)  
Removing an ingredient that is not in the list
![RemoveIngNotInList](UIErrorMessages/RemoveIngNotInList.png)

**Random Recipes**
No Random Recipe of this category  
![NoRandomDessert](UIErrorMessages/NoRandomDessert.png)

**View Recipes**
No Recipe of this category
![NoRecipeOfThisCategory](UIErrorMessages/ViewNoDessert.png)
## Work habits and workflow
We have been focusing on the following aspects while working on release 2.  

1. **Pair Programming:** We are using pair programming approach. Where two developers collaborate on the same computer. The name of the co-author is written in the commit message. Pair programming makes it easier to share knowledge, and helps us identify and address issues more efficiently.
2. **GitLab Issues:** We use GitLab issues to identify and track our work. Each task or feature begins as an issue.
3. **Branch-Based Development:** To manage and isolate all coding tasks, we use branches. Each task or feature is developed on its own branch. This allows us to work on multiple aspects of the project simultaneously without interfering with each other's code.
4. **Active Group Discussions:** We engage in group discussions whenever we encounter challenges during the development process. This makes it faster to solve the problem, and the knowledge is shared among the whole team.
5. **Milestones** We use milestones to track the issues we have for release 2. This helps to organize the work and the project´s progress. 