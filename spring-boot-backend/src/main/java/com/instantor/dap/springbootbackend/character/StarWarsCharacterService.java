package com.instantor.dap.springbootbackend.character;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsCharacterService {
    private static final String SWAPI_URL = "https://swapi.co/api/people/?search=";
    private RestTemplate restTemplate;

    public StarWarsCharacterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String getCharacter(String name) {
        String swapiResponse = restTemplate.getForObject(SWAPI_URL + name, String.class);
        JSONObject jsonResponse = new JSONObject(swapiResponse);
        return jsonResponse.getJSONArray("results").toString();
    }
}
