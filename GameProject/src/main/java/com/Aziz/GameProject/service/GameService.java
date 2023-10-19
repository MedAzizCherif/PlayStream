package com.Aziz.GameProject.service;

import com.Aziz.GameProject.entity.Game;
import com.Aziz.GameProject.repo.GameRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game updatedGame) {
        if (!gameRepository.existsById(id)) {
            throw new NotFoundException("Game not found");
        }
        updatedGame.setId(id);
        return gameRepository.save(updatedGame);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public void syncGames(List<Game> games) {
        // Iterate through the list of games from the RAWG API response
        for (Game rawgGame : games) {
            // Check if the game already exists in your database by name
            Game existingGame = gameRepository.findByName(rawgGame.getName());

            // If the game doesn't exist, add it to the database
            if (existingGame == null) {
                // Set the description, imageUrl, and other properties for the new game
                existingGame = new Game();
                existingGame.setName(rawgGame.getName());
                existingGame.setDescription(rawgGame.getDescription());
                existingGame.setGenre(rawgGame.getGenre());
                existingGame.setBackground_image(rawgGame.getBackground_image());

                gameRepository.save(existingGame);
            }
        }
    }



}

