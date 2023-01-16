package by.x1ss.smev.controller;

import by.x1ss.smev.entity.ResponseJuridical;
import by.x1ss.smev.entity.ResponsePhysical;
import by.x1ss.smev.service.RequestService;
import by.x1ss.smev.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smev")

public class SmevController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private ResponseService responseService;

    @PostMapping("/request/juridical/")
    public HttpStatus putJuridicalRequest(@RequestBody String inn) {
        requestService.putJuridicalRequest(inn);
        return HttpStatus.OK;
    }

    @PostMapping("/request/physical/")
    public HttpStatus putPhysicalRequest(@RequestBody String sts) {
        requestService.putPhysicalRequest(sts);
        return HttpStatus.OK;
    }


    @GetMapping("response/juridical/{inn}")
    public ResponseJuridical getJuridicalResponse(@PathVariable String inn) {
        return responseService.getJuridicalResponse(inn);
    }

    @GetMapping("response/physical/{sts}")
    public ResponsePhysical getPhysicalResponse(@PathVariable String sts) {
        return responseService.getPhysicalResponse(sts);
    }

    @DeleteMapping("response/juridical/{inn}/confirm")
    public void confirmJuridicalResponse(@PathVariable String inn) {
        responseService.confirmJuridicalResponse(inn);
    }

    @DeleteMapping("response/physical/{sts}/confirm")
    public void confirmPhysicalResponse(@PathVariable String sts) {
        responseService.confirmPhysicalResponse(sts);
    }
}
