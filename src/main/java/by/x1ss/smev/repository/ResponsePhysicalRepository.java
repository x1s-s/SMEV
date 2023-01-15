package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponsePhysical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsePhysicalRepository extends JpaRepository<ResponsePhysical, Long> {
    @Query("from ResponsePhysical where sts = ?1")
    ResponsePhysical findFirstBySts(String inn);
}
