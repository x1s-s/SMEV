package by.x1ss.smev.domain.logic.processRequest.contracts;

import by.x1ss.smev.domain.object.ResponseQueue;

import java.util.List;
import java.util.UUID;

public interface ResponseRepository {


    List<ResponseQueue> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    void save(ResponseQueue responseQueue);
}
