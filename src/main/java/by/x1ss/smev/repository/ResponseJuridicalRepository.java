package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponseJuridical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseJuridicalRepository extends JpaRepository<ResponseJuridical, String> {


    @Query("from ResponseJuridical where uuid = ?1")
    ResponseJuridical findFirstByUuid(String uuid);

    @Modifying
    @Query("delete from ResponseJuridical where uuid = ?1")
    void deleteByUuid(String uuid);
}
