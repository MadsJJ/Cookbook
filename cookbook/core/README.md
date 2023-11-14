# Core

### Contains

**Main classes**  
[CookBook.java](src/main/java/cookbook/core/CookBook.java)  
[Ingredient.java](src/main/java/cookbook/core/Ingredient.java)  
[Recipe.java](src/main/java/cookbook/core/Recipe.java)  
[User.java](src/main/java/cookbook/core/User.java)   
[UserDataFilehandling.java](src/main/java/cookbook/core/UserDataFilehandling.java) 

**Test classes**  
[CookBookTest.java](src/test/java/core/CookBookTest.java)  
[IngredientTest.java](src/test/java/core/IngredientTest.java)  
[RecipeTest.java](src/test/java/core/RecipeTest.java)  
[UserTest.java](src/test/java/core/UserTest.java)

## Connections between classes 

The "User.java" class is responsible for creating and managing user objects. Each user is associated with their own personal cookbook, which consists of a collection of recipes, and each recipe includes a list of ingredients.

On the other hand, "UserDataFilehandling" is a component dedicated to handling file operations related to user data. It offers various methods for working with user data, including retrieving a list of all users, fetching details of a specific user, facilitating the registration of new users, verifying user existence, and updating user data in the files. 

It's worth noting that, in the interest of efficiency, we have refrained from developing a separate JSON module since we already have methods in place for converting data to and from JSON format. Furthermore we found that since all our classes are connected through the user, there was only one object that needed to be serialized. 

Test classes test the functionality of their respective classes. 