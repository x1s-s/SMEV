package by.x1ss.smev.worker;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.entity.ResponseQueue;
import by.x1ss.smev.repository.RequestRepository;
import by.x1ss.smev.repository.ResponseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Worker extends Thread {
    private static Boolean stooped = false;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private RequestRepository requestRepository;

    @PostConstruct
    @Profile("!test")
    public void beginning(){
        this.start();
        log.info("Worker started");
    }


    @PreDestroy
    @Profile("!test")
    public void finish(){
        stooped = true;
        log.info("Worker finished");
    }


    @Override
    public void run() {
        while (!stooped) {
            log.info("Worker started processing requests");
            processRequests();
            log.info("Worker finished processing requests");
            while (requestRepository.count() == 0) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    log.error("Worker was interrupted", e);
                }
            }
        }
    }

    public void processRequests() {
        List<RequestQueue> requests = requestRepository.findAll();
        log.info("Worker got {} requests", Arrays.toString(requests.toArray()));
        for (RequestQueue request : requests) {
            responseRepository.save(new ResponseQueue(request));
            requestRepository.deleteByUUID(request.getUuid());
        }
    }
}
