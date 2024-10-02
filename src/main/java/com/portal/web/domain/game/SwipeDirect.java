package com.portal.web.domain.game;


public enum SwipeDirect {
    DOWN(0, 1),
    UP(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    NONE(1, 1);

    private final int deltaX;
    private final int deltaY;

    SwipeDirect(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
