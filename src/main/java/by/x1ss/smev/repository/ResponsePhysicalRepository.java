package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponsePhysical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsePhysicalRepository extends JpaRepository<ResponsePhysical, String> {

    @Query("from ResponsePhysical where uuid = ?1")
    ResponsePhysical findFirstByUuid(String uuid);


    @Modifying
    @Query("delete from ResponsePhysical where uuid = ?1")
    void deleteByUuid(String uuid);
}
