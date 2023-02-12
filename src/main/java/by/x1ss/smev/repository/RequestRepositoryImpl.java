package by.x1ss.smev.repository;

import by.x1ss.smev.entity.RequestQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class RequestRepositoryImpl implements RequestRepository{

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM request_queue";
    private static final String SQL_SAVE = "INSERT INTO request_queue (uuid, client_identifier, is_juridical) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_BY_UUID = "DELETE FROM request_queue WHERE uuid = ?";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM request_queue";
    private static final String SQL_FIND_BY_UUID = "SELECT * FROM request_queue WHERE uuid = ?";

    @Autowired
    public RequestRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<RequestQueue> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, RequestRepository.ROW_MAPPER);
    }

    @Override
    public void save(RequestQueue requestQueue) {
        jdbcTemplate.update(SQL_SAVE,
                requestQueue.getUuid(),
                requestQueue.getClientIdentifier(),
                requestQueue.getIsJuridical());
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        jdbcTemplate.update(SQL_DELETE_BY_UUID, uuid);
    }

    @Override
    public Long count() {
        return jdbcTemplate.queryForObject(SQL_COUNT, Long.class);
    }

    @Override
    public RequestQueue findByUuid(UUID uuid) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_UUID, RequestRepository.ROW_MAPPER, uuid);
    }
}
