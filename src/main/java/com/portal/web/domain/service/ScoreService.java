package com.portal.web.domain.service;

import com.portal.web.domain.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;

    void updateScore(Score score) throws ScoreException;
    List<Score> getTopScores(String game) throws ScoreException;
    void reset() throws ScoreException;

    Score getScoreById(String game, Long id);
}
