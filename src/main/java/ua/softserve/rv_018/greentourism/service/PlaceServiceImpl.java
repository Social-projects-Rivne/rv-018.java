package ua.softserve.rv_018.greentourism.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.repository.PlaceRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceRepository placeRepository;

	@Override
	public Place findPlaceByName(String name) {
		Place place = placeRepository.findByName(name);
		return place;
	}

}
