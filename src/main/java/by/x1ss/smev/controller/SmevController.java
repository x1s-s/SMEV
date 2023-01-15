package by.x1ss.smev.controller;

import by.x1ss.smev.entity.ResponseJuridical;
import by.x1ss.smev.service.ResponseService;
import by.x1ss.smev.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/request/physical/{sts}")
    public void putPhysicalRequest(@RequestBody String sts) {
        requestService.putPhysicalRequest(sts);
    }


    @GetMapping("response/juridical/{inn}")
    public ResponseJuridical getJuridicalResponse(@PathVariable String inn) {
        return responseService.getJuridicalResponse(inn);
    }

    @GetMapping("response/physical")
    public ResponseEntity<?> getPhysicalResponse(@RequestParam String value) {
        ResponseJuridical responseJuridical = responseService.getJuridicalResponse(value);
        if(responseJuridical != null){
            return ResponseEntity.ok(responseJuridical);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("response/juridical/{inn}/confirm")
    public void confirmJuridicalResponse(@PathVariable String inn) {
        responseService.confirmJuridicalResponse(inn);
    }
}
