package com.instantor.dap.springbootbackend.character;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class StarWarsCharacterServiceTest {

    @Mock
    private RestTemplate restTemplate;
    private StarWarsCharacterService service;

    @Before
    public void setUp() throws Exception {
        service = new StarWarsCharacterService(restTemplate);
    }

    @Test
    public void shouldReturnValidJson() {
        String url = "https://swapi.co/api/people/?search=vader";
        String mockResponse = "{ \"count\": 1, \"next\": null, \"previous\": null, \"results\": [ { \"name\": \"Darth Vader\", \"height\": \"202\", \"mass\": \"136\", \"hair_color\": \"none\", \"skin_color\": \"white\", \"eye_color\": \"yellow\", \"birth_year\": \"41.9BBY\", \"gender\": \"male\", \"homeworld\": \"https://swapi.co/api/planets/1/\", \"films\": [ \"https://swapi.co/api/films/2/\", \"https://swapi.co/api/films/6/\", \"https://swapi.co/api/films/3/\", \"https://swapi.co/api/films/1/\" ], \"species\": [ \"https://swapi.co/api/species/1/\" ], \"vehicles\": [], \"starships\": [ \"https://swapi.co/api/starships/13/\" ], \"created\": \"2014-12-10T15:18:20.704000Z\", \"edited\": \"2014-12-20T21:17:50.313000Z\", \"url\": \"https://swapi.co/api/people/4/\" } ] }";
        Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn(mockResponse);

        String actual = service.getCharacter("vader");

        Assert.assertTrue(actual.contains("Darth Vader"));
        Assert.assertTrue(actual.contains("\"height\":\"202\""));
        Assert.assertTrue(actual.contains("\"birth_year\":\"41.9BBY\""));
        Assert.assertFalse(actual.contains("count"));
        Assert.assertFalse(actual.contains("next"));
    }

    @Test
    public void shouldReturnEmptyJson() {
        String url = "https://swapi.co/api/people/?search=vader";
        String mockResponse = "{ \"count\": 0, \"next\": null, \"previous\": null, \"results\": [] }";
        Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn(mockResponse);

        String actual = service.getCharacter("vader");

        assertEquals(2, actual.length());

    }
}