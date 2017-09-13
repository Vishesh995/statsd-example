package at.willhaben.examples.statsd.service;

import java.math.BigInteger;

/**
 * @author Benjamin Hubert (benjamin.hubert@willhaben.at)
 */
public interface RandomService {

    /**
     * Generates a big random number.
     * @return A big random number. Never null.
     */
    BigInteger generateRandomNumber();

}
