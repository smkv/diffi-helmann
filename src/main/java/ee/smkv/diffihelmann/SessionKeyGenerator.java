package ee.smkv.diffihelmann;


import java.math.BigInteger;

public class SessionKeyGenerator {
    private final RandomNumberGenerator randomNumberGenerator;
    private final BigInteger privateKey;

    public SessionKeyGenerator() {
        this(new RandomNumberGenerator());
    }

    protected SessionKeyGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.privateKey = randomNumberGenerator.generateKey();
    }

    public PublicKey createPublicKey() {
        BigInteger g = randomNumberGenerator.generatePrime();
        BigInteger p = randomNumberGenerator.generatePrime();
        BigInteger A = g.modPow(privateKey, p);
        return new PublicKey(A, g, p);
    }

    public PublicKey createPublicKey(PublicKey publicKey) {
        BigInteger g = publicKey.g;
        BigInteger p = publicKey.p;
        BigInteger B = g.modPow(privateKey, p);
        return new PublicKey(B, g, p);
    }

    public BigInteger createSessionKey(PublicKey publicKey) {
        return publicKey.key.modPow(privateKey, publicKey.p);
    }

}
