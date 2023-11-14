# Cookbook

[Open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308?new)

## Description
This is a cookbook app created for your personal use. You can use it to store and look at your favorite recipes at your convenience. If you are not sure about what meal to cook, you can get recipe suggestions based on what type of meal you want.

## Application fundementals
The project uses maven to build and to run

Java-version: 17.0.8
Maven-version: 3.9.2 

To run the application run  `mvn install` from the root (cookbook-folder). This will build the project and run all tests. Afterwards run  `cd ui` to get to the ui-folder, followed by  `mvn javafx:run` to run the program. 

to use jlink and jpackage to run cookbook as an "application"  
run  `mvn clean compile javafx:jlink jpackage:jpackage` 

## Content

What each folder contains. Each module also has their own readme, which is linked at the bottom of their section.

### core  
See [core](cookbook/core/src/main/java/core)  

***main***  
Contains the classes for the main functionality of the application, its core   
[CookBook.java](cookbook/core/src/main/java/core/CookBook.java)  
[Ingredient.java](cookbook/core/src/main/java/core/Ingredient.java)  
[Recipe.java](cookbook/core/src/main/java/core/Recipe.java)  
[User.java](cookbook/core/src/main/java/core/User.java)   
[UserDataFilehandling.java](cookbook/core/src/main/java/core/UserDataFilehandling.java) 


***test***  
Contains the respective tests for the core-classes + UserDataFilehandling  
[CookBookTest.java](cookbook/core/src/test/java/core/CookBookTest.java)  
[IngredientTest.java](cookbook/core/src/test/java/core/IngredientTest.java)  
[RecipeTest.java](cookbook/core/src/test/java/core/RecipeTest.java)  
[UserTest.java](cookbook/core/src/test/java/core/UserTest.java)  
[UserDataFilehandlingTest.java](cookbook/core/src/test/java/core/UserDataFilehandlingTest.java)

Read more about the core module [here](link) 

### docs
See [docs](cookbook/docs)  
Contains the documentation for each release. This starts as a plan, but after each release is completed it will be an overview of our work. 

Each release contains screenshots of the updated UIDesign and a README.md with additional information about the application functionality. 

[release1](cookbook/docs/release1)  - contains UIDesign and UIPrototypes for release 1, UserStories and a README.md that contains the application functionality. 

[release2](cookbook/docs/release2) - contains updated UIDesign, error messages, architecture diagramand a descriptive README.md-file for this release.

[release3](cookbook/docs/release3) - contains diagrams, updated UIPrototypes, updated userstories and a README.md for this release.

### restserver

***main***  
Contains the classes for the Restserver    
[CookbookApplication.java](link) 
[CookbookController.java](link) 
[CookbookService.java](link) 
[GsonConfiguration.java](link)  

***test***  
Tests the Restserver  
[BOOM](link)


Read more about restserver module [here](link) 


### ui

***main***  
Contains the classes for the user interface    
[module-info.java](cookbook/ui/src/main/java/module-info.java)  
[ui](cookbook/ui/src/main/java/ui)   
[resources](cookbook/ui/src/main/resources)

***test***  
Contains the Controller-tests and setup for the tests to run. 
[CookbookControllerTest.java](cookbook/ui/src/test/java/ui/CookBookControllerTest.java)  
[MainAppTest.java](cookbook/ui/src/test/java/ui/MainAppTest.java)  
[UserControllerTest](cookbook/ui/src/test/java/ui/UserControllerTest.java)  

Read more about the ui module [here](link) 

## Code quality

[***jacoco***](https://www.jacoco.org/) - collects and presents the test-coverage grade for each module. Located in `/target/site/index.html` in each module.  
[***spotbugs***](https://spotbugs.github.io/)- checks for bugs and vulnerabilities.  
[***checkstyle***](https://checkstyle.sourceforge.io/)-  Superficial checks on code layout and formatting issues to enforce a coding standard


## Implicit Saving

Our app, Cookbook uses *implicit saving* to make the user experience as seamless and enjoyable as the users favourite recipe. As soon as you’ve registered as a user, all the recipes you add will automatically be saved together in your personal cookbook. Whenever you log into Cookbook, your recipes will be right there - instantly. No need to look through files and folders. 


