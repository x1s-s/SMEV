package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponseJuridical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ResponseJuridicalRepository extends JpaRepository<ResponseJuridical, Long> {
    @Query("from ResponseJuridical where inn = ?1")
    ResponseJuridical findFirstByInn(String inn);

    @Transactional
    @Modifying
    @Query("delete from ResponseJuridical where inn = ?1")
    void deleteByInn(String inn);
}
