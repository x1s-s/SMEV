package by.x1ss.smev.domain.logic.processRequest.contracts;

import by.x1ss.smev.domain.object.RequestQueue;

import java.util.List;
import java.util.UUID;

public interface RequestRepository {

    List<RequestQueue> findAll();

    void save(RequestQueue requestQueue);

    void deleteByUUID(UUID uuid);

    Long count();

    RequestQueue findByUuid(UUID uuid);

}
