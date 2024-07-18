package com.realit.spring_task.controller;

import com.realit.spring_task.model.Menu;
import com.realit.spring_task.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping("/modify")
    public Menu modifyJsonModel(@RequestParam String inputs) {
        // Predefined JSON model
        String jsonModel = "{\"menu\":{\"id\":\"file\",\"value\":\"File\",\"popup\":{\"menuitem\":[{\"value\":\"New\",\"onclick\":\"CreateDoc()\"},{\"value\":\"Open\",\"onclick\":\"OpenDoc()\"},{\"value\":\"Save\",\"onclick\":\"SaveDoc()\"}]}}}";

        // Parse inputs and perform replacements
        String modifiedJson = replaceKeywordsInJson(jsonModel, inputs);

        // Create a new Menu entity and save to the database
        Menu menu = new Menu();
        menu.setJsonModel(modifiedJson);
        menuRepository.save(menu);

        return menu;
    }

    private String replaceKeywordsInJson(String jsonModel, String inputs) {
        // Remove double quotes around the inputs string
        inputs = inputs.replace("\"", "");

        // Split the input string by comma to get key-value pairs
        String[] pairs = inputs.split(",");

        // Perform replacements
        for (String pair : pairs) {
            String[] keyValue = pair.split(":::");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                jsonModel = jsonModel.replace(key, value);
            }
        }

        return jsonModel;
    }
}
