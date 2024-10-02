package com.portal.web.domain.game;

public class Tile {
    public int value;

    public Tile(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tile other = (Tile) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
