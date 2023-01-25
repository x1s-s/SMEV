package by.x1ss.smev.repository;

import by.x1ss.smev.entity.RequestQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestQueue, String> {
    List<RequestQueue> findAll();

}
