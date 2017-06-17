package ee.smkv.diffihelmann;

import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigInteger;

import static org.junit.Assert.*;


public class SessionKeyGeneratorTest {
    @Test
    public void testHandshakeMocked() throws Exception {

        RandomNumberGenerator generator = Mockito.mock(RandomNumberGenerator.class);

        Mockito.when(generator.generateKey()).thenReturn(
                new BigInteger("6"),
                new BigInteger("15")
        );

        Mockito.when(generator.generatePrime()).thenReturn(
                new BigInteger("5"),
                new BigInteger("23")
        );

        SessionKeyGenerator alice = new SessionKeyGenerator(generator);
        SessionKeyGenerator bob = new SessionKeyGenerator(generator);

        PublicKey alicePublicKey = alice.createPublicKey();
        PublicKey bobPublicKey = bob.createPublicKey(alicePublicKey);

        assertEquals("g = ?", new BigInteger("5"), alicePublicKey.g);
        assertEquals("g = ?", new BigInteger("5"), bobPublicKey.g);
        assertEquals("p = ?", new BigInteger("23"), alicePublicKey.p);
        assertEquals("p = ?", new BigInteger("23"), bobPublicKey.p);

        assertEquals("A = ?", new BigInteger("8"), alicePublicKey.key);
        assertEquals("B = ?", new BigInteger("19"), bobPublicKey.key);

        assertEquals(
                alice.createSessionKey(bobPublicKey),
                bob.createSessionKey(alicePublicKey)
        );
    }

    @Test
    public void testHandshake() throws Exception {
        SessionKeyGenerator alice = new SessionKeyGenerator();
        SessionKeyGenerator bob = new SessionKeyGenerator();

        PublicKey alicePublicKey = alice.createPublicKey();
        PublicKey bobPublicKey = bob.createPublicKey(alicePublicKey);

        System.out.println("Alice: " + alicePublicKey);
        System.out.println("Bob: " + bobPublicKey);
        System.out.println("Session key: " + alice.createSessionKey(bobPublicKey));

        assertEquals(
                alice.createSessionKey(bobPublicKey),
                bob.createSessionKey(alicePublicKey)
        );
    }
}