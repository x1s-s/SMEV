package by.x1ss.smev.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@Profile("!test")
public class WorkerStarter {
    @Autowired
    private Worker worker;

    @PostConstruct
    public void startWorker() {
        worker.start();
        log.info("WorkerStarter started");
    }
}
