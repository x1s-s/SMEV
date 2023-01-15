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
            log.info("Worker started");
            List<RequestQueue> requests = requestRepository.findAll();
            requestRepository.deleteAll(requests);
            log.info("Worker got {} requests", requests.size());
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
                            new ResponsePhysical()
                    );
                }
            }
            log.info("Worker got {} juridical responses", responseJuridical.size());
            log.info("Worker got {} physical responses", responsePhysicals.size());
            if(!responseJuridical.isEmpty()) {
                responseJuridicalRepository.saveAll(responseJuridical);
            }
            if(!responsePhysicals.isEmpty()) {
                responsePhysicalRepository.saveAll(responsePhysicals);
            }
            log.info("Worker finished");
            while (requestRepository.count() == 0) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
