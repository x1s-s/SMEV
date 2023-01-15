package by.x1ss.smev.repository;

import by.x1ss.smev.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("from Answer where inn = ?1")
    Answer findByInn(String inn);
    @Query("from Answer where sts = ?1")
    Answer findBySts(String sts);
}
