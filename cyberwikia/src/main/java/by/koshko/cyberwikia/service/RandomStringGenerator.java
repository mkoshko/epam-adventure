package by.koshko.cyberwikia.service;

import java.util.Random;

public class RandomStringGenerator {
    private static Random rand = new Random();
    private static final int MIN = 48;
    private static final int MAX = 122;

    public static String generate(final int length) {
        byte[] arr = new byte[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (byte) (rand.nextInt(MAX - MIN) + MIN);
        }
        return new String(arr);
    }
}
