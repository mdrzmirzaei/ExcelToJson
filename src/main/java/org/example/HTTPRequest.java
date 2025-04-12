package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequest {

    String url = "hris-api.tavanir.org.ir/api/v3.0/Auth/Login";
    String jsonBody="{Username:,Password:}";

    public static String sendHttpRequest(String url) throws MalformedURLException, IOException {
        URL myurl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
        connection.setRequestMethod("GET");
//        connection.setRequestProperty("");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "Request failed with response code: " + responseCode;
        }
    }
}
