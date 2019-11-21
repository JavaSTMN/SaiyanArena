package main.utils;

import java.util.Random;

public class Coin {
    private Random random;

    public Coin() {
        random = new Random();
    }

    public int flip() {
        return random.nextInt(2);
    }
}
