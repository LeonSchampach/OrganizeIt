package com.organizeit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/short-links")
public class ShortLinkController {

    private final Map<String, Long> linkMap = new HashMap<>();

    // Endpoint to generate a short link
    @PostMapping("/generate")
    public String generateShortLink(@RequestParam long listId) {
        String shortId = UUID.randomUUID().toString().substring(0, 8); // Generate a short ID
        linkMap.put(shortId, listId); // Map the short ID to the list ID
        return "http://ec2-51-20-185-24.eu-north-1.compute.amazonaws.com/short-links/l/" + shortId; // Return the short link
    }

    // Endpoint to handle the redirection
    @GetMapping("/l/{shortId}")
    public ResponseEntity<?> redirectToList(@PathVariable String shortId) {
        try {
            long listId = linkMap.get(shortId);
            if (listId > 0) {
                // Redirect to the frontend with the list ID or directly serve the list
                return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/app/deeplink?listId=" + listId)).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Link not found");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Linkdsn lkjghjnsdfjokgjkoi2");
    }
}
