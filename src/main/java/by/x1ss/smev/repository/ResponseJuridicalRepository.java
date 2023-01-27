package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponseJuridical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponseJuridicalRepository extends JpaRepository<ResponseJuridical, UUID> {


    @Query("from ResponseJuridical where uuid = ?1")
    ResponseJuridical findFirstByUuid(UUID uuid);

    @Modifying
    @Query("delete from ResponseJuridical where uuid = ?1")
    void deleteByUuid(UUID uuid);
}
