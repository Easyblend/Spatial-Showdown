package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.example.demo.model.CountriesConquered;
import com.example.demo.model.SpatialCoordinate;
import com.example.demo.model.UserMessage;


@Controller
@RestController
public class WebSocketController {

    // Broadcast to all clients subscribed to /topic/messages

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public UserMessage sendPublicMessage(@Payload UserMessage message){
        System.out.println(message.getContent());
        return message;
    }

    @MessageMapping("/private-message")
    public UserMessage sendPrivateMessage(@Payload UserMessage message) {
        // Send message to the specific user's queue
        messagingTemplate.convertAndSendToUser(
            message.getRecipientUsername(),
            "/private",
            message // Send the whole UserMessage object
        );
        System.out.println(message.getContent());
        return message; // Return the UserMessage object
    }

    @MessageMapping("/cordinate")
    public SpatialCoordinate sendCoordinate(@Payload SpatialCoordinate coordinate) {
        // Send message to the specific user's queue
        messagingTemplate.convertAndSendToUser(
            coordinate.getProperties().getProp0(), // Assuming this holds the username
            "/private",
            coordinate // Send the whole SpatialCoordinate object
        );
        return coordinate; // Return the SpatialCoordinate object
    }

    private List<CountriesConquered> allConqueredCountries = new ArrayList<>();

    @MessageMapping("/countries")
    public CountriesConquered sendCountry(@Payload CountriesConquered countries) {
        allConqueredCountries.add(countries); // Add the new country to the list
    
        // Send the entire list to the specific user's queue
        messagingTemplate.convertAndSendToUser(
            countries.getUsername(),
            "/conquered",
            countries // Send the updated list
        );
    
        System.out.println(allConqueredCountries);
        return countries; // Return the new country object
    } 

    @MessageMapping("/countries/remove")
public CountriesConquered removeCountry(@Payload CountriesConquered countries) {
    // Remove the country from the list if necessary, handle logic here
    System.out.println(allConqueredCountries);
    allConqueredCountries.remove(countries); // Assuming you have a way to uniquely identify the country
    // Optionally send an updated list to the user
    messagingTemplate.convertAndSendToUser(
        countries.getUsername(),
        "/conquered",
        countries // Send the updated list
    );
    
    return countries; // Return the removed country object
}

}
