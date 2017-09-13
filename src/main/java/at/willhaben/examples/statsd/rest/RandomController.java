package at.willhaben.examples.statsd.rest;

import at.willhaben.examples.statsd.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @author Benjamin Hubert (benjamin.hubert@willhaben.at)
 */
@RestController
public class RandomController {

    private final RandomService service;

    @Autowired
    public RandomController(RandomService service) {
        this.service = service;
    }

    @RequestMapping("/random/number")
    public BigInteger randomNumber() {
        return service.generateRandomNumber();
    }

}
