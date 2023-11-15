# Cookbook

## Description
This is a cookbook app created for your personal use. You can use it to store and look at your favorite recipes at your convenience. If you are not sure about what meal to cook, you can get recipe suggestions based on what type of meal you want.

## Application fundementals

The project uses maven to build and to run. Below is 3 different step-by-step-guides to use Cookbook

Java-version: 17.0.8
Maven-version: 3.9.2 

To run in **Eclipse che** follow this step-by-step:
1. Set up your eclipse che account with an access token
2. Follow [Open in Eclipse Che](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2023/gr2308/gr2308?new) 
3. Run `mvn clean install` from the root project directory (cookbook-folder). This will build the project and run all tests (to skip tests add `-DskipTests`).
4. Run `cd restserver && mvn spring-boot:run` to launch spring boot. (or run `cd restserver` followed by `mvn spring-boot:run`)
5. Run  `mvn javafx:run -f ui/pom.xml` to launch the application (or run `cd ui` followed by `mvn javafx:run`)
6. Look in endpoint for the 6080-link and paste it into your preferred browser and add /cookbook at the end
7. Log-in or sign up to create your very own personal cookbook!

To run the application as normal in your IDE follow this step-by-step:
1. Run `mvn clean install` from the root project directory (cookbook-folder). This will build the project and run all tests (to skip tests add `-DskipTests`).
2. Run  `mvn javafx:run -f ui/pom.xml` to launch the program (or run `cd ui` followed by `mvn javafx:run`)
3. Log-in or sign up to create your very own personal cookbook!

To run the application with **jlink and jpackage** follow this step-by-step:
1. Run `mvn clean install` from the root project directory (cookbook-folder). This will build the project and run all tests (to skip tests add `-DskipTests`).
2. Run `cd restserver && mvn spring-boot:run` to launch spring boot (or run `cd restserver` followed by `mvn spring-boot:run`)
3. Run  `cd ui && mvn clean compile javafx:jlink jpackage:jpackage`. JLink downloads the project as a .zip-file and JPackage convertes it to an "application". To find the .zip-file look in */ui/target*
4. Find the application and open it as you would any other application on your computer. 
5. Log-in or sign up to create your very own personal cookbook!

## Content

What each folder contains. Each module also has their own readme, which is linked at the bottom of their section.

### core  
See [core](cookbook/core/src/main/java/core)  

***main***  
Contains the classes for the main functionality of the application, its core   
[CookBook.java](cookbook/core/src/main/java/cookbook/core/CookBook.java)  
[Ingredient.java](cookbook/core/src/main/java/cookbook/core/Ingredient.java)  
[Recipe.java](cookbook/core/src/main/java/cookbook/core/Recipe.java)  
[User.java](cookbook/core/src/main/java/cookbook/core/User.java)   
[UserDataFilehandling.java](cookbook/core/src/main/java/cookbook/core/UserDataFilehandling.java) 


***test***  
Contains the respective tests for the core-classes + UserDataFilehandling  
[CookBookTest.java](cookbook/core/src/test/java/core/CookBookTest.java)  
[IngredientTest.java](cookbook/core/src/test/java/core/IngredientTest.java)  
[RecipeTest.java](cookbook/core/src/test/java/core/RecipeTest.java)  
[UserTest.java](cookbook/core/src/test/java/core/UserTest.java)  
[UserDataFilehandlingTest.java](cookbook/core/src/test/java/core/UserDataFilehandlingTest.java)

Read more about the core module in [README for core module](cookbook/core/README.md) 

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
[CookbookApplication.java](cookbook/restserver/src/main/java/cookbook/springboot/restserver/CookbookApplication.java)   
[CookbookController.java](cookbook/restserver/src/main/java/cookbook/springboot/restserver/CookbookController.java)   
[CookbookService.java](cookbook/restserver/src/main/java/cookbook/springboot/restserver/CookbookService.java)   
[GsonConfiguration.java](cookbook/restserver/src/main/java/cookbook/springboot/restserver/GsonConfiguration.java)  

***test***  
Tests the Restserver  
[CookbookModelApplicationTest.java](cookbook/restserver/src/test/java/cookbook/springboot/restserver/CookbookModelApplicationTest.java)


Read more about restserver module in [README for REST API](cookbook/restserver/README.md) 


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

Read more about the ui module in [README for ui module](cookbook/ui/README.md) 

## Code quality

[***jacoco***](https://www.jacoco.org/) - collects and presents the test-coverage grade for each module. Located in `/target/site/index.html` in each module.  
[***spotbugs***](https://spotbugs.github.io/)- checks for bugs and vulnerabilities.  
[***checkstyle***](https://checkstyle.sourceforge.io/)-  Superficial checks on code layout and formatting issues to enforce a coding standard


## Implicit Saving

Our app, Cookbook uses *implicit saving* to make the user experience as seamless and enjoyable as the users favourite recipe. As soon as you’ve registered as a user, all the recipes you add will automatically be saved together in your personal cookbook. Whenever you log into Cookbook, your recipes will be right there - instantly. No need to look through files and folders. 


