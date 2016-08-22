package ua.softserve.rv_018.greentourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.softserve.rv_018.greentourism.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
