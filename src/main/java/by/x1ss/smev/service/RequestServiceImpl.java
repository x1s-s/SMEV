package by.x1ss.smev.service;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    public void putRequest(RequestQueue requestQueue) {
        log.info("RequestService put request {}", requestQueue);
        requestRepository.save(requestQueue);
    }
}
