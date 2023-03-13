package by.x1ss.smev.domain.logic.processRequest.service;

import by.x1ss.smev.domain.object.ResponseList;
import by.x1ss.smev.domain.object.RequestQueue;
import by.x1ss.smev.domain.object.ResponseQueue;
import by.x1ss.smev.domain.logic.processRequest.service.exception.PenaltyNotFoundException;
import by.x1ss.smev.domain.logic.processRequest.contracts.RequestRepository;
import by.x1ss.smev.domain.logic.processRequest.contracts.ResponseRepository;
import by.x1ss.smev.domain.logic.processRequest.api.ProcessRequestServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProcessRequestService implements ProcessRequestServiceAPI {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private RequestRepository requestRepository;

    public void putRequest(RequestQueue requestQueue) {
        log.info("RequestService put request {}", requestQueue);
        requestRepository.save(requestQueue);
    }

    public ResponseList getResponse(UUID uuid){
        log.info("ResponseService try to give response with uuid {}", uuid);
        try {
            requestRepository.findByUuid(uuid);
            return null;
        } catch (IndexOutOfBoundsException e){
            List<ResponseQueue> responseQueues = responseRepository.findByUuid(uuid);
            if (!responseQueues.isEmpty()) {
                log.info("ResponseService gave response with uuid {}", Arrays.toString(responseQueues.toArray()));
                return new ResponseList(responseQueues);
            } else {
                throw new PenaltyNotFoundException();
            }
        }
    }

    public void confirmResponse(UUID uuid) {
        log.info("ResponseService delete response with uuid {}", uuid);
        responseRepository.deleteByUuid(uuid);
    }
}
