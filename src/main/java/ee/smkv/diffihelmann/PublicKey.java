package ee.smkv.diffihelmann;

import java.math.BigInteger;

public class PublicKey {
    final BigInteger key;
    final BigInteger g;
    final BigInteger p;

    public PublicKey(BigInteger key, BigInteger g, BigInteger p) {
        this.key = key;
        this.g = g;
        this.p = p;
    }

    public BigInteger getKey() {
        return key;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getP() {
        return p;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PublicKey{");
        sb.append("key=").append(key);
        sb.append(", g=").append(g);
        sb.append(", p=").append(p);
        sb.append('}');
        return sb.toString();
    }
}
