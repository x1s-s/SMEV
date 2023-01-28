package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponseQueue;
import org.springframework.jdbc.core.RowMapper;

import java.util.UUID;

public interface ResponseRepository{
    RowMapper<ResponseQueue> ROW_MAPPER = (resultSet, i) -> ResponseQueue.builder()
            .uuid(UUID.fromString(resultSet.getString("uuid")))
            .clientIdentifier(resultSet.getString("client_identifier"))
            .isJuridical(resultSet.getBoolean("is_juridical"))
            .accrualAmount(resultSet.getDouble("accrual_amount"))
            .administrativeCode(resultSet.getString("administrative_code"))
            .amountPay(resultSet.getDouble("amount_pay"))
            .resolutionDate(resultSet.getDate("resolution_date").toLocalDate())
            .resolutionNumber(resultSet.getDouble("resolution_number"))
            .build();


    ResponseQueue findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    void save(ResponseQueue responseQueue);
}
