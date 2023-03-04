package by.x1ss.smev.repository;

import by.x1ss.smev.entity.Penalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PenaltyRepositoryImpl implements PenaltyRepository{

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_BY_IDENTIFIER = "SELECT TOP(1) * FROM Penalty WHERE client_identifier = ?";

    public PenaltyRepositoryImpl(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Penalty findByClientIdentifier(String clientIdentifier) throws IndexOutOfBoundsException{
        return jdbcTemplate.query(FIND_BY_IDENTIFIER, PenaltyRepository.ROW_MAPPER, clientIdentifier).get(0);
    }
}
