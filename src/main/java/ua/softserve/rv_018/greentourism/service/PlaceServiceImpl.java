package ua.softserve.rv_018.greentourism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.repository.PlaceRepository;

@Service
public class PlaceServiceImpl implements PlaceService{

	@Autowired
	private PlaceRepository placeRepository;
	
	@Override
	public Place create(Place place) {
		Place addedPlace = placeRepository.save(place);
		return addedPlace;
	}

}
