package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponseQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ResponseRepositoryImpl implements ResponseRepository{

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_BY_UUID = "SELECT TOP(1) * FROM response_queue WHERE uuid = ?";
    private static final String SQL_DELETE_BY_UUID = "DELETE FROM response_queue WHERE uuid = ?";
    private static final String SQL_SAVE = "INSERT INTO response_queue " +
            "(uuid, client_identifier, accrual_amount, amount_pay, resolution_number, resolution_date, administrative_code, is_juridical) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Autowired
    public ResponseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ResponseQueue findByUuid(UUID uuid) throws IndexOutOfBoundsException{
        return jdbcTemplate.query(SQL_FIND_BY_UUID, ResponseRepository.ROW_MAPPER, uuid).get(0);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jdbcTemplate.update(SQL_DELETE_BY_UUID, uuid);
    }

    @Override
    public void save(ResponseQueue responseQueue) {
        jdbcTemplate.update(SQL_SAVE,
                responseQueue.getUuid(), responseQueue.getClientIdentifier(), responseQueue.getAccrualAmount(),
                responseQueue.getAmountPay(), responseQueue.getResolutionNumber(), responseQueue.getResolutionDate(),
                responseQueue.getAdministrativeCode(), responseQueue.getIsJuridical());
    }
}
