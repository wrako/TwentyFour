package com.portal.web.domain.server.controller;

import com.portal.web.domain.entity.Account;
import com.portal.web.domain.entity.Comment;
import com.portal.web.domain.game.Game;
import com.portal.web.domain.service.CommentService;
import com.portal.web.domain.service.RatingService;
import com.portal.web.domain.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import com.portal.web.domain.config.AccountDetails;
import com.portal.web.domain.entity.Rating;
import com.portal.web.domain.entity.Score;
import com.portal.web.domain.service.*;

import java.util.Date;
import java.util.List;

@RestController
public class GameController {
    @Autowired
    private final Game game; // ваш класс Game, который управляет игрой
    @Autowired
    public GameController(Game game) {
        this.game = game;
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ScoreService scoreService;

    @Autowired
    CommentService commentService;

    @Autowired
    RatingService ratingService;

    RestTemplate restTemplate = new RestTemplate();

    private boolean cheat = false;


    @GetMapping("/initial-state")
    public int[][] getInitialState() {
        return game.getBoard(); // метод getBoard() возвращает текущее состояние игрового поля
    }

    @GetMapping("/move")
    public int[][] makeMove(@RequestParam("direction") String direction) {
        game.move(direction); // метод makeMove() выполняет ход в указанном направлении
        return game.getBoard(); // возвращаем обновленное состояние игрового поля после хода
    }

    @GetMapping("/is-over")
    public boolean isGameOver() {
        return game.isGameOver();
    }
    @GetMapping("/is-win")
    public boolean isWin() {
        return game.isGameWin();
    }


    @GetMapping("/reset")
    public void resetGame() {
        game.resetGame();
        cheat = false;
    }


    @GetMapping("/cheat")
    public int[][] removeTile() {
        if(cheat) return game.getBoard();
        game.removeTile();
        return game.getBoard();
    }

    @RequestMapping("/save-score")
    public RedirectView saveScore() {
//        System.out.println("\n\n\n----------------------\nTry update Score\n------------------------------------\n\n\n");

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account =((AccountDetails) userDetailsService.loadUserByUsername(name)).getAccount();
        Score score = new Score(account.getUsrId(),game.getScore(), new Date(), "1024");
        Score oldScore;
        if((oldScore = scoreService.getScoreById("1024", account.getUsrId())) == null){
            scoreService.addScore(score);
        } else if(oldScore.getPoints() < score.getPoints()){
            oldScore.setPoints(score.getPoints());
            scoreService.updateScore(oldScore);
        }
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/start");
        return redirectView;
    }

    @PostMapping("/add-comment")
    @ResponseStatus(HttpStatus.SEE_OTHER) // Устанавливаем статус перенаправления
    public RedirectView addComment(@RequestParam String commentText) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account =((AccountDetails) userDetailsService.loadUserByUsername(name)).getAccount();
        Comment comment = new Comment(account.getUsrId(), commentText, new Date(), "1024");
        commentService.addComment(comment);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/comments");
        return redirectView;
    }

    @PostMapping("/rate-game")
    @ResponseStatus(HttpStatus.SEE_OTHER) // Устанавливаем статус перенаправления
    public RedirectView rateGame(@RequestParam int valueRat) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account =((AccountDetails) userDetailsService.loadUserByUsername(name)).getAccount();
        Rating rating = new Rating(account.getUsrId(), valueRat, "1024", new Date());
        ratingService.setRating(rating);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/rating");
        return redirectView;
    }


    @GetMapping("/get/comment")
    public List<Comment> getComments() {
        return commentService.getComments("1024");

    }
    @GetMapping("/get/score/top")
    public List<Score> getScores() {
        return scoreService.getTopScores("1024");

    }

    @GetMapping("/get/rating")
    public int getRating() {
        return ratingService.getAverageRating("1024");
    }

    @GetMapping("/get/score")
    public int getScore() {
        return game.getScore();
    }


}
