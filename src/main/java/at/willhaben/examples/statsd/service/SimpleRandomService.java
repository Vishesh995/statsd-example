package at.willhaben.examples.statsd.service;

import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author Benjamin Hubert (benjamin.hubert@willhaben.at)
 */
@Component
public class SimpleRandomService implements RandomService {

    private final StatsDClient statsd;

    @Autowired
    public SimpleRandomService(StatsDClient statsd) {
        this.statsd = statsd;
    }

    @Override
    public BigInteger generateRandomNumber() {
        statsd.incrementCounter("service.random");

        BigInteger veryBig = new BigInteger(1000, new Random());
        return veryBig.nextProbablePrime();
    }
}
