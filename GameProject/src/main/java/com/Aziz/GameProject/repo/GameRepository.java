package com.Aziz.GameProject.repo;

import com.Aziz.GameProject.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByName(String name);
}