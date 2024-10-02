package com.portal.web.domain.game;

import java.util.Random;



public class Board {
    private final int size;
    private final Tile[][] board;
    private int score;

    private Long id;
    private String nickname;
    public Board(int size) {
        this.id = id;
        this.size = size;
        this.board = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new Tile(0);
            }

        }
//        setUpUser(nickname);
    }
//    private void setUpUser(String nickname){
//
//        connection = libraryDB.dbConnector("postgres", "postgres");
////        libraryDB.readData(connection, "score");
//
//        this.score = libraryDB.getScoreByName(connection, "tenTwentyFour", nickname);
//        if(this.score < 0){
//            libraryDB.insertRow(connection, "tenTwentyFour", nickname, 0);
//        }
//        this.score = 0;
//    }

    public int getSize() {
        return this.size;
    }

    public Tile[][] getBoard() {
        Tile[][] boardCopy = new Tile[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardCopy[i][j] = new Tile(board[i][j].getValue());
            }
        }
        return boardCopy;
    }

    public void setBoard(Tile[][] boardCopy) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].setValue(boardCopy[i][j].getValue());            }
        }
    }





//    public String[][] getBoard() {
//        String[][] boardArray = new String[size][size];
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                boardArray[i][j] = Integer.toString(board[i][j].getValue());
//            }
//        }
//        return boardArray;
//    }



    public int getScore() {
        return this.score;
    }

    public boolean isFull() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if (this.board[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canMove(SwipeDirect swipeDirect) {
        if (!this.isFull()) {
            return true;
        }else if (swipeDirect == SwipeDirect.NONE){
            for (int i = 0; i < this.size - 1; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (this.board[i][j].getValue() ==
                            this.board[i + swipeDirect.getDeltaY()][j].getValue()) {
                        return true;
                    }
                }
            }

            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size - 1; j++) {
                    if (this.board[i][j].getValue() ==
                            this.board[i][j + swipeDirect.getDeltaX()].getValue()) {
                        return true;
                    }
                }
            }
        } else {
            int startIndex;
            int endIndex;
            int iterator;
            boolean isEnd;
            if(swipeDirect == SwipeDirect.LEFT ){
                startIndex = this.size - 1;
                endIndex = 0;
                iterator = -1;
                for (int i = startIndex; i > -1; i -= 1) {
                    for (int j = startIndex; j > endIndex; j+= iterator) {
                        if (this.board[i][j].getValue() ==
                                this.board[i + swipeDirect.getDeltaY()][j + swipeDirect.getDeltaX()].getValue()) {
                            return true;
                        }
                    }
                }


            } else if(swipeDirect == SwipeDirect.UP){
                startIndex = this.size - 1;
                endIndex = 0;
                iterator = -1;
                for (int i = startIndex; i > endIndex; i += iterator) {
                    for (int j = startIndex; j > -1; j-= 1) {
                        if (this.board[i][j].getValue() ==
                                this.board[i + swipeDirect.getDeltaY()][j + swipeDirect.getDeltaX()].getValue()) {
                            return true;
                        }
                    }
                }
            }else if(swipeDirect == SwipeDirect.RIGHT){
                startIndex = 0;
                endIndex = this.size - 1;
                iterator = 1;
                for (int i = 0; i < this.size; i+= 1) {
                    for (int j = 0; j < endIndex; j+= iterator) {
                        if (this.board[i][j].getValue() ==
                                this.board[i + swipeDirect.getDeltaY()][j + swipeDirect.getDeltaX()].getValue()) {
                            return true;
                        }
                    }
                }
            }else if(swipeDirect == SwipeDirect.DOWN){
                startIndex = 0;
                endIndex = this.size - 1;
                iterator = 1;
                for (int i = 0; i < endIndex; i+= iterator) {
                    for (int j = 0; j < this.size; j+= 1) {
                        if (this.board[i][j].getValue() ==
                                this.board[i + swipeDirect.getDeltaY()][j + swipeDirect.getDeltaX()].getValue()) {
                            return true;
                        }
                    }
                }
            }



        }
        return false;
    }

    public void addTile() {
        Random random = new Random();
        int x;
        int y;
        do {
            x = random.nextInt(this.size);
            y = random.nextInt(this.size);
        } while (this.board[x][y].getValue() != 0);

        this.board[x][y].setValue(2); // Set the new tile value to 2
    }

    private Tile[] moveRow(Tile[] row, int ident) {
        Tile[] mergedRow = new Tile[row.length];
        for(int i = 0; i < size; i++){
            mergedRow[i] = new Tile(0);
        }

        int mergeIndex = ident == 1 ? 0 : (size - 1);
        boolean start = true;

        for (int i = mergeIndex; (i >= 0 && i < size); i += ident) {
            if (row[i].getValue() != 0) {
                if (start) {
                    mergedRow[mergeIndex] = row[i];
                    start = false;
                } else {
                    if (row[i].equals(mergedRow[mergeIndex + (ident * -1)])) {
                        mergedRow[mergeIndex + (ident * -1)] = new Tile(row[i].getValue() * -2);

                        this.score += row[i].getValue() * 2;
//                        if(this.score > ) {
//                            libraryDB.updateScoreByName(connection, "tenTwentyFour", nickname, this.score);
//                        }
                        mergeIndex -= ident;
                    } else {
                        mergedRow[mergeIndex] = row[i];
                    }
                }
                mergeIndex += ident;
            }
        }
        for (int i = 0; i < size; i++) {
            if (mergedRow[i].getValue() < 0) {
                mergedRow[i].setValue(mergedRow[i].getValue() * -1);
            }
        }

        return mergedRow;
    }

    public void moveAlongXAxis(boolean rightOrLeft) {
        int direction = rightOrLeft ? -1 : 1; // case true it's right case false it's left

        if(rightOrLeft && !canMove(SwipeDirect.RIGHT)){
            return;
        } else if(!rightOrLeft && !canMove(SwipeDirect.LEFT)){
            return;
        }

        for (int i = 0; i < this.size; ++i) {
            Tile[] row = this.board[i];
            Tile[] newRow = this.moveRow(row, direction);
            this.board[i] = newRow;
        }
        this.addTile();

    }

    public void moveAlongYAxis(boolean upOrDown) {
        int direction = upOrDown ? -1 : 1; // case true it's down case false it's up

        if(upOrDown && !canMove(SwipeDirect.DOWN)){
            return;
        } else if(!upOrDown && !canMove(SwipeDirect.UP)){
            return;
        }

        for (int j = 0; j < this.size; ++j) {
            Tile[] column = new Tile[this.size];

            for (int i = 0; i < this.size; ++i) {
                column[i] = this.board[i][j];
            }
            Tile[] newColumn = this.moveRow(column, direction);
            for (int i = 0; i < this.size; ++i) {
                this.board[i][j] = newColumn[i];
            }
        }
            this.addTile();

    }


    public boolean isWin(){
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if(this.board[i][j].getValue() == 1024) return true;
            }
        }
        return false;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j].getValue() + "\t");
            }
            System.out.println();
        }
//        if(scoreService.getScoreById("1024" ,id) != null)
//            System.out.println("Score: " + score + " Record: " + scoreService.getScoreById("1024", id));
//        else
            System.out.println("Score: " + score);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                sb.append(String.format("%4s", this.board[i][j]));
            }
            sb.append("\n");
        }
        sb.append("Score: ").append(this.score).append("\n");
        return sb.toString();
    }
}

