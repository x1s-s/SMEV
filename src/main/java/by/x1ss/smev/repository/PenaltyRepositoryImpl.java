package by.x1ss.smev.repository;

import by.x1ss.smev.domain.logic.processRequest.contracts.PenaltyRepository;
import by.x1ss.smev.domain.object.Penalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PenaltyRepositoryImpl implements PenaltyRepository {

   private final RowMapper<Penalty> ROW_MAPPER;

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_BY_IDENTIFIER = "SELECT * FROM Penalty WHERE client_identifier = ?";

    public PenaltyRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.ROW_MAPPER = (resultSet, i) -> Penalty.builder()
                .clientIdentifier(resultSet.getString("client_identifier"))
                .isJuridical(resultSet.getBoolean("is_juridical"))
                .accrualAmount(resultSet.getDouble("accrual_amount"))
                .administrativeCode(resultSet.getString("administrative_code"))
                .amountPay(resultSet.getDouble("amount_pay"))
                .resolutionDate(resultSet.getDate("resolution_date").toLocalDate())
                .resolutionNumber(resultSet.getDouble("resolution_number"))
                .build();
    }

    @Override
    public List<Penalty> findByClientIdentifier(String clientIdentifier) throws IndexOutOfBoundsException{
        return jdbcTemplate.query(FIND_BY_IDENTIFIER, ROW_MAPPER, clientIdentifier);
    }
}
