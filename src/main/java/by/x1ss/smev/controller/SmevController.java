package by.x1ss.smev.controller;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.entity.ResponseJuridical;
import by.x1ss.smev.entity.ResponsePhysical;
import by.x1ss.smev.service.RequestService;
import by.x1ss.smev.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/smev")

public class SmevController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private ResponseService responseService;

    @PostMapping("/request/put/")
    public HttpStatus putRequest(@RequestBody RequestQueue requestQueue) {
        requestService.putRequest(requestQueue);
        return HttpStatus.PROCESSING;
    }

    @GetMapping("response/juridical/{uuid}")
    public ResponseJuridical getJuridicalResponse(@PathVariable UUID uuid) {
        return responseService.getJuridicalResponse(uuid);
    }

    @GetMapping("response/physical/{uuid}")
    public ResponsePhysical getPhysicalResponse(@PathVariable UUID uuid) {
        return responseService.getPhysicalResponse(uuid);
    }


    @DeleteMapping("response/juridical/confirm/{uuid}")
    public void confirmJuridicalResponse(@PathVariable UUID uuid) {
        responseService.confirmJuridicalResponse(uuid);
    }

    @DeleteMapping("response/physical/confirm/{uuid}")
    public void confirmPhysicalResponse(@PathVariable UUID uuid) {
        responseService.confirmPhysicalResponse(uuid);
    }
}
