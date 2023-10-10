# Cookbook

## Description
This is a cookbook app created for your personal use. You can use it to store and look at your favorite recipes at your convenience. If you are not sure about what meal to cook, you can get recipe suggestions based on what type of meal you want and your currently available ingredients. 

## Application fundementals
The project uses maven to build and to run

Java-version: 17.0.8
Maven-version: 3.9.2 

To run the application run `mvn install` from the root (cookbook-folder). This will build the project and run all tests. Afterwards run `cd ui` to get to the ui-folder, followed by `mvn javafx:run` to run the program. 


## Content

What each folder contains

### core

See [core](link)  
***main***  
Contains the classes for the main functionality of the application. This folder contains four classes;

[CookBook.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/main/java/core/CookBook.java?ref_type=heads)  
[Ingredient.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/main/java/core/Ingredient.java?ref_type=heads)  
[Recipe.Java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/main/java/core/Recipe.java?ref_type=heads)  
[User.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/main/java/core/User.java?ref_type=heads)  

***test***  
Contains the respective tests for the core-classes

[CookBookTest.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/test/java/core/CookBookTest.java?ref_type=heads)  
[IngredientTest.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/test/java/core/IngredientTest.java?ref_type=heads)  
[RecipeTest.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/test/java/core/RecipeTest.java?ref_type=heads)  
[UserTest.java](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/blob/master/core/src/test/java/core/UserTest.java?ref_type=heads)  



### docs
Contains the documentation for each release. This starts as a plan, but after each release is completed it will be an overview of our work. 

Each release contains screenshots of the updated UIDesign and a README.md with additional information about the application functionality. 

See [docs](link)

[release1]()  - contains UIDesign and UIPrototypes for release 1, UserStories and a README.md that contains the application functionality. 

[release2]() - contains the demands for this release, updated UIDesign and a descriptive README.md-file for this release.

### ui

***main***  
Contains the classes for the user interface, see:

[ui](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/tree/master/ui/src/main/java/ui?ref_type=heads) - Contains the Controller-classes, and the App-classes  
[resources](https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308/-/tree/master/ui/src/main/resources/ui?ref_type=heads) - Contains the .fxml-files, the .txt file with users name and password and the .json file for the users and their respective recipes. 

***test***  
Contains the Controller-tests

## Code quality

***jacoco*** - collects and presents the test-coverage grade for each module. Located in `/target/site/index.html` in each module.
***spotbugs*** - checks for bugs and vulnerabilities. 





