package by.x1ss.smev.service;

import by.x1ss.smev.entity.ResponseJuridical;
import by.x1ss.smev.entity.ResponsePhysical;
import by.x1ss.smev.repository.ResponseJuridicalRepository;
import by.x1ss.smev.repository.ResponsePhysicalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
public class ResponseService {
    @Autowired
    private ResponsePhysicalRepository responsePhysicalRepository;
    @Autowired
    private ResponseJuridicalRepository responseJuridicalRepository;


    public ResponseJuridical getJuridicalResponse(UUID uuid) {
        log.info("ResponseService got juridical request with uuid {}", uuid);
        return responseJuridicalRepository.findFirstByUuid(uuid.toString());
    }

    public ResponsePhysical getPhysicalResponse(UUID uuid) {
        log.info("ResponseService got physical response with uuid {}", uuid);
        return responsePhysicalRepository.findFirstByUuid(uuid.toString());
    }

    @Transactional
    @Modifying
    public void confirmJuridicalResponse(UUID uuid) {
        log.info("ResponseService delete juridical response with uuid {}", uuid);
        responseJuridicalRepository.deleteByUuid(uuid.toString());
    }

    @Transactional
    @Modifying
    public void confirmPhysicalResponse(UUID uuid) {
        log.info("ResponseService delete physical response with uuid {}", uuid);
        responsePhysicalRepository.deleteByUuid(uuid.toString());
    }
}
