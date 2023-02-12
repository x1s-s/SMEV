package by.x1ss.smev.worker;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.entity.ResponseQueue;
import by.x1ss.smev.repository.RequestRepository;
import by.x1ss.smev.repository.ResponseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Worker extends Thread {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public void run() {
        while (true) {
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
