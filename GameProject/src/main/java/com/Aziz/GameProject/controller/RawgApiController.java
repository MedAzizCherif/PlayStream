package com.Aziz.GameProject.controller;

import com.Aziz.GameProject.entity.Game;
import com.Aziz.GameProject.entity.RawgApiResponse;
import com.Aziz.GameProject.service.GameService;
import com.Aziz.GameProject.service.RawgApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rawg")
public class RawgApiController {
    private final RawgApiService rawgApiService;
    private final GameService gameService;

    @Autowired
    public RawgApiController(RawgApiService rawgApiService, GameService gameService) {
        this.rawgApiService = rawgApiService;
        this.gameService = gameService;
    }

    @GetMapping("/sync")
    public void syncGamesWithRawgApi() {
        List<Game> games = rawgApiService.fetchGamesWithDetailsFromRawgApi();
        gameService.syncGames(games);
    }



}
