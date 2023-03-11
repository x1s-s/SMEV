package by.x1ss.smev.controller;

import by.x1ss.smev.DTO.ResponseList;
import by.x1ss.smev.entity.RequestQueue;
import by.x1ss.smev.service.RequestService;
import by.x1ss.smev.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/smev")
@Tag(name = "User", description = "SMEV service contoller for another service")
public class SmevController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private ResponseService responseService;

    @Operation(summary = "Put request to query", tags = "request")
    @PostMapping("/request/put/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void putRequest(@Valid @RequestBody RequestQueue requestQueue) {
        requestService.putRequest(requestQueue);
    }

    @Operation(summary = "Try to get response", tags = "response")
    @GetMapping("response/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseList getResponse(@PathVariable UUID uuid) {
        return responseService.getResponse(uuid);
    }

    @Operation(summary = "Delete response", tags = "response")
    @DeleteMapping("response/confirm/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmResponse(@PathVariable UUID uuid) {
        responseService.confirmResponse(uuid);
    }
}
