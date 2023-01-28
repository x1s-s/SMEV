package by.x1ss.smev.repository;

import by.x1ss.smev.entity.RequestQueue;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.UUID;

public interface RequestRepository{

    RowMapper<RequestQueue> ROW_MAPPER = (resultSet, i) -> RequestQueue.builder()
            .uuid(UUID.fromString(resultSet.getString("uuid")))
            .clientIdentifier(resultSet.getString("client_identifier"))
            .isJuridical(resultSet.getBoolean("is_juridical"))
            .build();

    List<RequestQueue> findAll();
    void save(RequestQueue requestQueue);
    void deleteByUUID(UUID uuid);

    Long count();

}
