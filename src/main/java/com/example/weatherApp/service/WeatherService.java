package com.example.weatherApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String _apiKey;
    private final String _urlTemplate;

    public WeatherService() {
        _apiKey = "333a24a9c699dd629c313017fb1086e6";
        _urlTemplate = "http://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}&units=metric";
    }

    public String getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = _urlTemplate.replace("{city}", city).replace("{apiKey}", _apiKey);
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            // Handle errors from the API (e.g., 4xx or 5xx responses)
            return "Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString();
        } catch (Exception e) {
            // Handle other exceptions
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}
