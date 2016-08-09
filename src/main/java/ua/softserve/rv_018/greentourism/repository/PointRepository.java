package ua.softserve.rv_018.greentourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.softserve.rv_018.greentourism.model.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
//	Optional<Point> findByUsername(String pointname);
}
