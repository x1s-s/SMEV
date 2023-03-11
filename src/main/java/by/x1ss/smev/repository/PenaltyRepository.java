package by.x1ss.smev.repository;

import by.x1ss.smev.entity.Penalty;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface PenaltyRepository {
    RowMapper<Penalty> ROW_MAPPER = (resultSet, i) -> Penalty.builder()
            .clientIdentifier(resultSet.getString("client_identifier"))
            .isJuridical(resultSet.getBoolean("is_juridical"))
            .accrualAmount(resultSet.getDouble("accrual_amount"))
            .administrativeCode(resultSet.getString("administrative_code"))
            .amountPay(resultSet.getDouble("amount_pay"))
            .resolutionDate(resultSet.getDate("resolution_date").toLocalDate())
            .resolutionNumber(resultSet.getDouble("resolution_number"))
            .build();

    List<Penalty> findByClientIdentifier(String clientIdentifier);
}
