package by.x1ss.smev.domain.worker;

import by.x1ss.smev.domain.logic.processRequest.contracts.PenaltyRepository;
import by.x1ss.smev.domain.logic.processRequest.contracts.RequestRepository;
import by.x1ss.smev.domain.logic.processRequest.contracts.ResponseRepository;
import by.x1ss.smev.domain.object.Penalty;
import by.x1ss.smev.domain.object.RequestQueue;
import by.x1ss.smev.domain.object.ResponseQueue;
import lombok.extern.slf4j.Slf4j;
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
    private final ResponseRepository responseRepository;
    private final RequestRepository requestRepository;
    private final PenaltyRepository penaltyRepository;

    public Worker(ResponseRepository responseRepository, RequestRepository requestRepository, PenaltyRepository penaltyRepository) {
        this.responseRepository = responseRepository;
        this.requestRepository = requestRepository;
        this.penaltyRepository = penaltyRepository;
    }

    @PostConstruct
    @Profile("!test")
    public void beginning() {
        this.start();
        log.info("Worker started");
    }


    @PreDestroy
    @Profile("!test")
    public void finish() {
        stooped = true;
        log.info("Worker finished");
    }


    @Override
    public void run(){
        while (!stooped) {
            log.info("Worker started processing requests");
            processRequests();
            log.info("Worker finished processing requests");
            while (requestRepository.count() == 0) {
                try {
                    Thread.sleep(100);
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
            List<Penalty> penalty = penaltyRepository.findByClientIdentifier(request.getClientIdentifier());
            log.info("Worker got {} penalties", Arrays.toString(penalty.toArray()));
            if(!penalty.isEmpty()){
                penalty.forEach(p -> responseRepository.save(new ResponseQueue(p, request.getUuid())));
            }
            requestRepository.deleteByUUID(request.getUuid());
        }
    }
}
