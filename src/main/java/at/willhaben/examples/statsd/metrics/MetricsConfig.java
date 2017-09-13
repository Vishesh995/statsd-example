package at.willhaben.examples.statsd.metrics;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Benjamin Hubert (benjamin.hubert@willhaben.at)
 */
@Configuration
@EnableAspectJAutoProxy
public class MetricsConfig {

    @Bean
    public StatsDClient statsDClient(
            @Value("${metrics.statsd.host:localhost}") String host,
            @Value("${metrics.statsd.port:8125}") int port,
            @Value("${metrics.prefix:example.app}") String prefix
    ) {
        return new NonBlockingStatsDClient(prefix, host, port);
    }

    @Bean
    public MethodProfiler methodProfiler(StatsDClient statsDClient) {
        return new MethodProfiler(statsDClient);
    }

}
