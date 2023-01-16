package by.x1ss.smev.service;

import by.x1ss.smev.entity.ResponseJuridical;
import by.x1ss.smev.entity.ResponsePhysical;
import by.x1ss.smev.repository.ResponseJuridicalRepository;
import by.x1ss.smev.repository.ResponsePhysicalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResponseService {
    @Autowired
    private ResponsePhysicalRepository responsePhysicalRepository;
    @Autowired
    private ResponseJuridicalRepository responseJuridicalRepository;

    public ResponseJuridical getJuridicalResponse(String inn) {
        log.info("ResponseService got juridical request with inn {}", inn);
        return responseJuridicalRepository.findFirstByInn(inn);
    }

    public ResponsePhysical getPhysicalResponse(String sts) {
        log.info("ResponseService got physical response with sts {}", sts);
        return responsePhysicalRepository.findFirstBySts(sts);
    }

    public void confirmJuridicalResponse(String inn) {
        log.info("ResponseService delete juridical response with inn {}", inn);
        responseJuridicalRepository.deleteByInn(inn);
    }

    public void confirmPhysicalResponse(String sts) {
        log.info("ResponseService delete sts {}", sts);
        responsePhysicalRepository.deleteBySts(sts);
    }
}
