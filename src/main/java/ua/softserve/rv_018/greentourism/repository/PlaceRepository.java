package ua.softserve.rv_018.greentourism.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.softserve.rv_018.greentourism.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
	List<Place> findByNameIgnoreCaseContaining(String name);
}
