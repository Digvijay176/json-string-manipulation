package com.assignment.string_manipulation.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.string_manipulation.dao.StringManipulationService;

@RestController
public class StringManipulationController {

	@Autowired
    private StringManipulationService StringManipulationService;
    
    @PostMapping("/manipulate-json")
    public ResponseEntity<String> manipulateMenu(@RequestParam("inputs") String inputs) {
        String json = "{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Save\",\"onclick\":\"SaveDoc()\"}]}}}";
        
        String[] inputArray = inputs.split(",");
        
        try {
        	System.out.print("calling manipulationMenu method of service");
            String manipulatedJson = StringManipulationService.manipulateJsonAndSave(json, inputArray);
            
            return ResponseEntity.ok(manipulatedJson);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error manipulating JSON: " + e.getMessage());
        }
    }
	
}
