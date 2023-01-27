package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponsePhysical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponsePhysicalRepository extends JpaRepository<ResponsePhysical, UUID> {

    @Query("from ResponsePhysical where uuid = ?1")
    ResponsePhysical findFirstByUuid(UUID uuid);


    @Modifying
    @Query("delete from ResponsePhysical where uuid = ?1")
    void deleteByUuid(UUID uuid);
}
