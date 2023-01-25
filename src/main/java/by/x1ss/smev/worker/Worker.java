package by.x1ss.smev.worker;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.entity.ResponseJuridical;
import by.x1ss.smev.entity.ResponsePhysical;
import by.x1ss.smev.repository.RequestRepository;
import by.x1ss.smev.repository.ResponseJuridicalRepository;
import by.x1ss.smev.repository.ResponsePhysicalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Worker extends Thread {
    @Autowired
    private ResponseJuridicalRepository responseJuridicalRepository;
    @Autowired
    private ResponsePhysicalRepository responsePhysicalRepository;
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
                    e.printStackTrace();
                }
            }
        }
    }

    private void processRequests() {
        List<RequestQueue> requests = requestRepository.findAll();
        log.info("Worker got {} requests", Arrays.toString(requests.toArray()));
        for (RequestQueue request : requests) {
            if (request.getIsJuridical()) {
                ResponseJuridical responseJuridical = ResponseJuridical.builder().
                        inn(request.getStr()).
                        uuid(request.getUuid()).
                        resolutionNumber(123).
                        resolutionDate(LocalDate.MAX).
                        administrativeCode("123").
                        accrualAmount(123).
                        amountPay(123).
                        build();
                responseJuridicalRepository.save(responseJuridical);
            } else {
                ResponsePhysical responsePhysical = ResponsePhysical.builder().
                        sts(request.getStr()).
                        uuid(request.getUuid()).
                        resolutionNumber(123).
                        resolutionDate(LocalDate.MAX).
                        administrativeCode("123").
                        accrualAmount(123).
                        amountPay(123).
                        build();
                responsePhysicalRepository.save(responsePhysical);
            }
        }
        requestRepository.deleteAll(requests);
    }
}
