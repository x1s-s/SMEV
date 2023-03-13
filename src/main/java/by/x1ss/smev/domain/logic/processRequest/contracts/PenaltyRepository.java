package by.x1ss.smev.domain.logic.processRequest.contracts;

import by.x1ss.smev.domain.object.Penalty;

import java.util.List;

public interface PenaltyRepository {
    List<Penalty> findByClientIdentifier(String clientIdentifier);
}
