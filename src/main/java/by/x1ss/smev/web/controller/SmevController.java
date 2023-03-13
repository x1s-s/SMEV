package by.x1ss.smev.web.controller;

import by.x1ss.smev.domain.logic.processRequest.api.ProcessRequestServiceAPI;
import by.x1ss.smev.domain.object.RequestQueue;
import by.x1ss.smev.web.dto.ResponseListDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/smev")
@Tag(name = "User", description = "SMEV service contoller for another service")
public class SmevController {
    private final ProcessRequestServiceAPI processRequestServiceAPI;

    public SmevController(ProcessRequestServiceAPI processRequestServiceAPI) {
        this.processRequestServiceAPI = processRequestServiceAPI;
    }

    @Operation(summary = "Put request to query", tags = "request")
    @PostMapping("/request/put/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void putRequest(@RequestBody RequestQueue requestQueue) {
        processRequestServiceAPI.putRequest(requestQueue);
    }

    @Operation(summary = "Try to get response", tags = "response")
    @GetMapping("response/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseListDTO getResponse(@PathVariable UUID uuid) {
        return ResponseListDTO.fromResponseList(processRequestServiceAPI.getResponse(uuid));
    }

    @Operation(summary = "Delete response", tags = "response")
    @DeleteMapping("response/confirm/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmResponse(@PathVariable UUID uuid) {
        processRequestServiceAPI.confirmResponse(uuid);
    }
}
