package at.willhaben.examples.statsd.metrics;

import com.google.common.base.Stopwatch;
import com.timgroup.statsd.StatsDClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;

/**
 * @author Benjamin Hubert (benjamin.hubert@willhaben.at)
 */
@Aspect
public class MethodProfiler {

    private final StatsDClient statsd;

    public MethodProfiler(StatsDClient statsd) {
        this.statsd = statsd;
    }

    @Pointcut("execution(* at.willhaben.examples.statsd.rest.RandomController.*(..))")
    public void restServiceMethods() {
    }

    @Around("restServiceMethods()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {

        // execute the method, record the result and measure the time
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object output = pjp.proceed();
        stopwatch.stop();

        // send the recorded time to statsd
        String key = String.format("%s.%s", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        statsd.recordExecutionTime(key, stopwatch.elapsed(TimeUnit.MILLISECONDS));

        // return the recorded result
        return output;

    }

}
