package ee.smkv.diffihelmann;

import java.math.BigInteger;
import java.util.Random;

public class RandomNumberGenerator {
    private Random random = new Random();

    public BigInteger generateKey(){
        return new BigInteger(String.valueOf(random.nextInt())).abs();
    }

    public BigInteger generatePrime(){
        return BigInteger.probablePrime(32 , random);
    }
}
