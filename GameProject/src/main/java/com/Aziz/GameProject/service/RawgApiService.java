package com.Aziz.GameProject.service;

import com.Aziz.GameProject.entity.Game;
import com.Aziz.GameProject.entity.RawgApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawgApiService {
    private final RestTemplate restTemplate;

    @Autowired
    public RawgApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // RawgApiService.java
    public List<Game> fetchGamesWithDetailsFromRawgApi() {
        String apiKey = "c8d353f6c14248fbb36d95d86999b00f";
        ResponseEntity<RawgApiResponse> responseEntity = restTemplate.getForEntity(
                "https://api.rawg.io/api/games?key=" + apiKey,
                RawgApiResponse.class
        );
        List<Game> games = responseEntity.getBody().getResults();

        for (Game game : games) {
            ResponseEntity<Game> gameDetailsResponseEntity = restTemplate.getForEntity(
                    "https://api.rawg.io/api/games/" + game.getId() + "?key=" + apiKey,
                    Game.class
            );

            Game detailedGame = gameDetailsResponseEntity.getBody();



            game.setBackground_image(detailedGame.getBackground_image());
            game.setGenre(detailedGame.getGenre());
        }

        return games;
    }





}
