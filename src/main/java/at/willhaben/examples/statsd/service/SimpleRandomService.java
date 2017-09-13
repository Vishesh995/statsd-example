package at.willhaben.examples.statsd.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author Benjamin Hubert (benjamin.hubert@willhaben.at)
 */
@Component
public class SimpleRandomService implements RandomService {

    @Override
    public BigInteger generateRandomNumber() {
        BigInteger veryBig = new BigInteger(1000, new Random());
        return veryBig.nextProbablePrime();
    }
}
