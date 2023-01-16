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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
            log.info("Worker started processing requsets");
            List<RequestQueue> requests = getRequests();
            List<ResponseJuridical> responseJuridical = new ArrayList<>();
            List<ResponsePhysical> responsePhysicals = new ArrayList<>();
            for (RequestQueue request : requests) {
                if (request.getIsJuridical()) {
                    responseJuridical.add(
                            ResponseJuridical.builder().
                                    inn(request.getStr()).
                                    resolutionNumber(123).
                                    resolutionDate(LocalDate.MAX).
                                    administrativeCode("123").
                                    accrualAmount(123).
                                    amountPay(123).
                                    build()
                    );
                } else {
                    responsePhysicals.add(
                            ResponsePhysical.builder().
                                    sts(request.getStr()).
                                    resolutionNumber(123).
                                    resolutionDate(LocalDate.MAX).
                                    administrativeCode("123").
                                    accrualAmount(123).
                                    amountPay(123).
                                    build()
                    );
                }
            }
            log.info("Worker got {} juridical responses", responseJuridical.size());
            log.info("Worker got {} physical responses", responsePhysicals.size());
            putResponses(responseJuridical, responsePhysicals);
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

    @Transactional
    protected List<RequestQueue> getRequests() {
        List<RequestQueue> requests = requestRepository.findAll();
        requestRepository.deleteAll(requests);
        return requests;
    }

    @Transactional
    protected void putResponses(List<ResponseJuridical> responseJuridical, List<ResponsePhysical> responsePhysicals) {
        if (!responseJuridical.isEmpty()) {
            responseJuridicalRepository.saveAll(responseJuridical);
        }
        if (!responsePhysicals.isEmpty()) {
            responsePhysicalRepository.saveAll(responsePhysicals);
        }
    }
}
