package by.x1ss.smev.repository;

import by.x1ss.smev.domain.logic.processRequest.contracts.RequestRepository;
import by.x1ss.smev.domain.object.RequestQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class RequestRepositoryImpl implements RequestRepository {

    private final RowMapper<RequestQueue> ROW_MAPPER;

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM request_queue";
    private static final String SQL_SAVE = "INSERT INTO request_queue (uuid, client_identifier, is_juridical) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_BY_UUID = "DELETE FROM request_queue WHERE uuid = ?";
    private static final String SQL_COUNT = "SELECT COUNT(*) FROM request_queue";
    private static final String SQL_FIND_BY_UUID = "SELECT TOP(1) * FROM request_queue WHERE uuid = ?";

    public RequestRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.ROW_MAPPER = (resultSet, i) -> RequestQueue.builder()
                .uuid(UUID.fromString(resultSet.getString("uuid")))
                .clientIdentifier(resultSet.getString("client_identifier"))
                .isJuridical(resultSet.getBoolean("is_juridical"))
                .build();
    }

    @Override
    public List<RequestQueue> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER);
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
    public RequestQueue findByUuid(UUID uuid) throws IndexOutOfBoundsException{
        return jdbcTemplate.query(SQL_FIND_BY_UUID, ROW_MAPPER, uuid).get(0);
    }
}
