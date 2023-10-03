package core;
import com.google.gson.Gson;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    // Constructors, getters, and setters
    // ...

    // Serialize the object to JSON
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Deserialize JSON to object
    public static Person fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Person.class);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
