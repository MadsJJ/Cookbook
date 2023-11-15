package ui.access;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServerStatusChecker {

    public static Boolean ServerStatus(){
        // Replace with your server's URL
        String serverUrl = "http://localhost:8080/cookbook/";

        try {
            // Create an HttpClient
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create an HttpRequest to the server's endpoint
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(serverUrl))
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check the response status code
            if (response.statusCode() == 200) {
              System.out.println("Server is running. Response: " + response.body());
              return true;
            } else {
                System.out.println("Server returned an error. Response code: " + response.statusCode());
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error checking server status: " + e.getMessage());
            return false;
        }
    }
}
