package com.Aziz.GameProject.controller;

import com.Aziz.GameProject.entity.Game;
import com.Aziz.GameProject.service.GameService;
import com.Aziz.GameProject.service.RawgApiService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;



    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;

    }


    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game updatedGame) {
        try {
            Game updated = gameService.updateGame(id, updatedGame);
            return ResponseEntity.ok(updated);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

