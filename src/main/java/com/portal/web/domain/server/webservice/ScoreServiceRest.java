package com.portal.web.domain.server.webservice;

import com.portal.web.domain.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.portal.web.domain.service.ScoreService;

import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceRest {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/{game}")
    public List<Score> getTopScores(@PathVariable String game) {
        return scoreService.getTopScores(game);
    }

    @GetMapping("/{game}/{usrId}")
    public Score getScoreById(@PathVariable String game, @PathVariable Long usrId) {
        return scoreService.getScoreById(game, usrId);
    }

    @PostMapping("/add") // Уникальный маппинг для метода добавления
    public void addScore(@RequestBody Score score) {
        scoreService.addScore(score);
    }

    @PostMapping("/update") // Уникальный маппинг для метода обновления
    public void updateScore(@RequestBody Score score) {
        scoreService.updateScore(score);
    }
}