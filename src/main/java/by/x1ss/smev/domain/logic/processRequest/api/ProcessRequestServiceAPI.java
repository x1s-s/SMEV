package by.x1ss.smev.domain.logic.processRequest.api;

import by.x1ss.smev.domain.object.ResponseList;
import by.x1ss.smev.domain.object.RequestQueue;

import javax.validation.Valid;
import java.util.UUID;

public interface ProcessRequestServiceAPI {
    void putRequest(@Valid RequestQueue requestQueue);
    ResponseList getResponse(UUID uuid);
    void confirmResponse(UUID uuid);
}
