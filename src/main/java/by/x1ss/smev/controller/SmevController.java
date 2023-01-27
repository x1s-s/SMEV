package by.x1ss.smev.controller;

import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.entity.ResponseQueue;
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

    @GetMapping("response/{uuid}")
    public ResponseQueue getResponse(@PathVariable UUID uuid) {
        return responseService.getResponse(uuid);
    }

    @DeleteMapping("response/confirm/{uuid}")
    public void confirmResponse(@PathVariable UUID uuid) {
        responseService.confirmResponse(uuid);
    }
}
