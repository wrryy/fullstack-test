package com.instantor.dap.springbootbackend.character;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/sw")
public class StarWarsCharacterController {

    private StarWarsCharacterService service;

    public StarWarsCharacterController(StarWarsCharacterService service) {
        this.service = service;
    }

    /**
     * @return character from Star Wars
     */
    @GetMapping("/character/{name}")
    public ResponseEntity getCharacter(@PathVariable String name) {
        return ResponseEntity.ok(service.getCharacter(name));
    }

    @GetMapping("/character")
    public ResponseEntity getCharacter() {
        return ResponseEntity.ok(service.getCharacter("vader"));
    }
}
