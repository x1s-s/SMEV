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
    public void putRequest(RequestQueue requestQueue) {
        log.info("RequestService put request {}", requestQueue);
        requestRepository.save(requestQueue);
    }
}
