package ua.softserve.rv_018.greentourism.service;

import java.util.List;

import ua.softserve.rv_018.greentourism.model.Place;

public interface PlaceService {
	List<Place> findByNameIgnoreCaseContaining(String name);
}
