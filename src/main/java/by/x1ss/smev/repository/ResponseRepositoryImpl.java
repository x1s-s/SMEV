package by.x1ss.smev.repository;

import by.x1ss.smev.domain.logic.processRequest.contracts.ResponseRepository;
import by.x1ss.smev.domain.object.ResponseQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ResponseRepositoryImpl implements ResponseRepository {

    private final RowMapper<ResponseQueue> ROW_MAPPER;
    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_BY_UUID = "SELECT * FROM response_queue WHERE uuid = ?";
    private static final String SQL_DELETE_BY_UUID = "DELETE FROM response_queue WHERE uuid = ?";
    private static final String SQL_SAVE = "INSERT INTO response_queue " + "(uuid, client_identifier, accrual_amount, amount_pay, resolution_number, resolution_date, administrative_code, is_juridical) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public ResponseRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.ROW_MAPPER = (resultSet, i) -> ResponseQueue.builder().uuid(UUID.fromString(resultSet.getString("uuid"))).clientIdentifier(resultSet.getString("client_identifier")).isJuridical(resultSet.getBoolean("is_juridical")).accrualAmount(resultSet.getDouble("accrual_amount")).administrativeCode(resultSet.getString("administrative_code")).amountPay(resultSet.getDouble("amount_pay")).resolutionDate(resultSet.getDate("resolution_date").toLocalDate()).resolutionNumber(resultSet.getDouble("resolution_number")).build();
    }

    @Override
    public List<ResponseQueue> findByUuid(UUID uuid) {
        return jdbcTemplate.query(SQL_FIND_BY_UUID, ROW_MAPPER, uuid);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        jdbcTemplate.update(SQL_DELETE_BY_UUID, uuid);
    }

    @Override
    public void save(ResponseQueue responseQueue) {
        jdbcTemplate.update(SQL_SAVE, responseQueue.getUuid(), responseQueue.getClientIdentifier(), responseQueue.getAccrualAmount(), responseQueue.getAmountPay(), responseQueue.getResolutionNumber(), responseQueue.getResolutionDate(), responseQueue.getAdministrativeCode(), responseQueue.getIsJuridical());
    }
}
