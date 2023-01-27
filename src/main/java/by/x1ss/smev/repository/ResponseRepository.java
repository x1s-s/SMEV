package by.x1ss.smev.repository;

import by.x1ss.smev.entity.ResponseQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResponseRepository extends JpaRepository<ResponseQueue, UUID> {
    @Query("FROM ResponseQueue WHERE uuid = ?1")
    ResponseQueue findByUuid(UUID uuid);

    @Modifying
    @Query("DELETE FROM ResponseQueue WHERE uuid = ?1")
    void deleteByUuid(UUID uuid);
}
