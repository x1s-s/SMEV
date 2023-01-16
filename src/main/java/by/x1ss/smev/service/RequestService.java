package by.x1ss.smev.service;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Transactional
    public void putJuridicalRequest(String value) {
        log.info("RequestService got juridical request with inn {}", value);
        RequestQueue requestQueue = RequestQueue.builder()
                .str(value)
                .isJuridical(true)
                .build();
        log.info("RequestService put juridical request {}", requestQueue);
        requestRepository.save(requestQueue);
    }

    @Transactional
    public void putPhysicalRequest(String value) {
        log.info("RequestService got physical request with sts {}", value);
        RequestQueue requestQueue = RequestQueue.builder()
                .str(value)
                .isJuridical(false)
                .build();
        log.info("RequestService put physical request {}", requestQueue);
        requestRepository.save(requestQueue);
    }
}
