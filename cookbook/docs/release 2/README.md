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
![CookBookRandomDesign](UIDesign/CookBookRandom2.PNG)


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
Adding Recipe without filling in any ingrediens


**Adding Ingredient**  
Adding ingredient without setting the unit
![Add Ingredient without unit](UIErrorMessages/AddIngWithoutUnit.png)
Adding ingredient without setting amount
Adding ingredient without writing the name of the ingredient.  
**Removing**  
Removing a recipe that does not exist  
Removing an ingredient that is not in the list


