package tddmicroexercises.telemetrysystem;


import java.util.Random;

public class ConnectionSimulator implements RandomNumberGenerator {

    private int seed;
    ConnectionSimulator(int seed){
        this.seed = seed;
    }
    private final Random random = new Random(seed);
    public int connect(int bound) {
        return random.nextInt(bound);
    }
}
