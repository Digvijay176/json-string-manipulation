package com.assignment.string_manipulation.dao;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.string_manipulation.entities.StringManipulationEntity;
import com.assignment.string_manipulation.repositories.StringManipulationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Service
public class StringManipulationService {
	
	@Autowired
	private StringManipulationRepository repo;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	
	public String stringJsonManipulate(String json, String[] inputs) throws IOException {
        JsonNode rootNode = objectMapper.readTree(json);
        
        for (String input : inputs) {
            String[] parts = input.split(":::");
            if (parts.length == 2) {
                String find = parts[0];
                String replace = parts[1];
                JsonNode menuNode = rootNode.path("menu");
                manipulateNode(menuNode, find, replace);
            }
        }
        return objectMapper.writeValueAsString(rootNode);
    }
	
	
	
	private void manipulateNode(JsonNode node, String find, String replace) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            objectNode.fields().forEachRemaining(entry -> {
                if (entry.getValue().isObject() || entry.getValue().isArray()) {
                    manipulateNode(entry.getValue(), find, replace);
                } else if (entry.getValue().isTextual()) {
                    String value = entry.getValue().asText();
                    objectNode.put(entry.getKey(), value.replaceAll(find, replace));
                }
            });
        } else if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode) node;
            arrayNode.elements().forEachRemaining(thisNode -> manipulateNode(thisNode, find, replace));
        }
    }
	
	
	
	public String manipulateJsonAndSave(String json, String[] inputs) throws IOException {
        String modifiedJson = stringJsonManipulate(json, inputs);
        StringManipulationEntity m = new StringManipulationEntity();
        m.setJsonModel(modifiedJson);
        repo.save(m);
        System.out.print("saved entity successfully !!");
        return modifiedJson;
	}

}
