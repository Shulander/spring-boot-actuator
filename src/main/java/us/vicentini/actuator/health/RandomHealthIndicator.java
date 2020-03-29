package us.vicentini.actuator.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class RandomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) {
        log.info("running...");
        Random rd = new Random(System.currentTimeMillis());
        if (rd.nextBoolean()) {
            builder.down()
                    .withDetail("ERROR", "randomly we are down");
            log.info("DOWN");
        } else {
            builder.up()
                    .withDetail("randomly up", "UP");
            log.info("UP");
        }
    }
}
