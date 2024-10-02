package com.portal.web.domain.game;

import org.springframework.stereotype.Component;

@Component
public class Game {
    private final int size;
    private Board board;
    private int score;
    private boolean gameOver;

    public Game() {
        this.size = 4; // Размер игрового поля
        this.board = new Board(size);
        this.score = 0;
        this.gameOver = false;
        initializeBoard();
    }

    // Инициализация начального состояния игрового поля
    private void initializeBoard() {
        board.addTile();
        board.addTile();
    }

    // Метод для получения текущего состояния игрового поля в формате JSON
    public int[][] getBoard() {
        int[][] boardCopy = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardCopy[i][j] = this.board.getBoard()[i][j].getValue();
            }
        }
        return boardCopy;
    }

    public void resetGame() {
        this.board = new Board(size);
        this.score = 0;
        this.gameOver = false;
    }

    public boolean removeTile(){
        Tile[][] boardCopy = board.getBoard();
        int g = 0;
        for (int i = 0; i < size; i++) {
            if(g == 2) break;
            for (int j = 0; j < size; j++) {
                if(g == 2) break;
                if(boardCopy[i][j].getValue() == 2){
//                    System.out.println("\n\n\n\n REMOVE TILE\n\n\n\n\n\n");
                    boardCopy[i][j].setValue(0);
//                    System.out.println(this.board.getBoard()[i][j].getValue());
                    g++;
                }
            }
        }
        board.setBoard(boardCopy);
        if(g > 0) return true;
        return false;

    }

    // Метод для обработки хода игрока
    public void move(String direction) {
        if (!gameOver) {
            switch (direction.toLowerCase()) {
                case "up":
                    board.moveAlongYAxis(false);
                    System.out.println("Up is |" + board.canMove(SwipeDirect.UP) + "|");
                    break;
                case "down":
                    board.moveAlongYAxis(true);
                    System.out.println("Down is |" + board.canMove(SwipeDirect.DOWN) + "|");
                    break;
                case "left":
                    board.moveAlongXAxis(false);
                    System.out.println("Left is |" + board.canMove(SwipeDirect.LEFT) + "|");
                    break;
                case "right":
                    board.moveAlongXAxis(true);
                    System.out.println("Right is |" + board.canMove(SwipeDirect.RIGHT) + "|");
                    break;
                default: break;
            }
            this.score = board.getScore();
            if (!board.canMove(SwipeDirect.NONE)) {
                gameOver = true; // Проверяем, закончилась ли игра
                System.out.println("Game over");
            } if(!board.isFull()){
                board.addTile(); // Добавляем новую плитку после каждого хода
            }

        }
    }

    // Метод для проверки окончания игры
    public boolean isGameOver() {
        return gameOver;
    }
    public boolean isGameWin(){
        return board.isWin();
    }


    // Метод для получения текущего счета
    public int getScore() {
        return score;
    }
}
