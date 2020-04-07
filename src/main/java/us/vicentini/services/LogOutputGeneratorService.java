package us.vicentini.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogOutputGeneratorService {

    @Scheduled(fixedDelay = 10000)
    public void createLogEntries() {
        log.info("-------------------------");
        log.trace("This is a TRACE message");
        log.debug("This is a DEBUG message");
        log.info("This is a INFO message");
        log.warn("This is a WARN message");
        log.error("This is a ERROR message");
        log.info("-------------------------");
    }

}
