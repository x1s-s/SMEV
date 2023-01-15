package by.x1ss.smev.service;

import by.x1ss.smev.entity.ResponseJuridical;
import by.x1ss.smev.entity.ResponsePhysical;
import by.x1ss.smev.repository.ResponseJuridicalRepository;
import by.x1ss.smev.repository.ResponsePhysicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponseService {
    @Autowired
    private ResponsePhysicalRepository responsePhysicalRepository;
    @Autowired
    private ResponseJuridicalRepository responseJuridicalRepository;

    public ResponseJuridical getJuridicalResponse(String inn) {
        return responseJuridicalRepository.findFirstByInn(inn);
    }

    public ResponsePhysical getPhysicalResponse(String sts) {
        return responsePhysicalRepository.findFirstBySts(sts);
    }

    @Transactional
    public void confirmJuridicalResponse(String inn) {
        responseJuridicalRepository.deleteByInn(inn);
    }
}
