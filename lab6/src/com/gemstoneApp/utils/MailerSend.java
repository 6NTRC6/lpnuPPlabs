package com.gemstoneApp.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class MailerSend {
    public static void sendEmail(String recipientEmail, String subject, String bodyText) {
        try {
            // MailerSend API URL
            String apiUrl = "https://api.mailersend.com/v1/email";

            // Create URL object from API URL
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");  // Set request method to POST
            connection.setRequestProperty("Authorization", "api key here");  // Set Authorization header
            connection.setRequestProperty("Content-Type", "application/json");  // Set Content-Type to JSON
            connection.setDoOutput(true);

            // Create the JSON payload
            String jsonInputString = String.format(
                    "{\"from\": {\"email\": \"email here\", \"name\": \"Notificator\"}, " +
                            "\"to\": [{\"email\": \"%s\"}], " +
                            "\"subject\": \"%s\", " +
                            "\"text\": \"%s\", " +
                            "\"html\": \"<b>%s</b>\"}",
                    recipientEmail, subject, bodyText, bodyText
            );

            // Send the JSON payload in the request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == 202) {
                System.out.println("Successfully sent email");
            };

            // Read the response or error message
//            try (BufferedReader in = new BufferedReader(new InputStreamReader(
//                    responseCode == HttpURLConnection.HTTP_OK ? connection.getInputStream() : connection.getErrorStream(), "utf-8"))) {
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                System.out.println("Response: " + response.toString());
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
