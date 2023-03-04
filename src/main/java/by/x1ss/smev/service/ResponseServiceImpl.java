package by.x1ss.smev.service;

import by.x1ss.smev.entity.ResponseQueue;
import by.x1ss.smev.exception.NotFoundPenaltyException;
import by.x1ss.smev.repository.RequestRepository;
import by.x1ss.smev.repository.ResponseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ResponseServiceImpl implements ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private RequestRepository requestRepository;

    public ResponseQueue getResponse(UUID uuid){
        log.info("ResponseService got response with uuid {}", uuid);
        try {
            requestRepository.findByUuid(uuid);
            return null;
        } catch (IndexOutOfBoundsException e){
            try {
                return responseRepository.findByUuid(uuid);
            } catch (IndexOutOfBoundsException exception){
                throw new NotFoundPenaltyException();
            }
        }
    }

    public void confirmResponse(UUID uuid) {
        log.info("ResponseService delete response with uuid {}", uuid);
        responseRepository.deleteByUuid(uuid);
    }
}
