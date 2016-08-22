package ua.softserve.rv_018.greentourism.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.repository.PlaceRepository;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class PlaceServiceImpl implements PlaceService {
	/**
     * The logger service for logging purpose.
     */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * The Spring Data repository for Places entities.
     */
    @Autowired
    private PlaceRepository placeRepository;

	@Override
	public Collection<Place> findAll() {
		logger.info("> Place findAll");

        Collection<Place> places = placeRepository.findAll();

        logger.info("< Place findAll");
        
        return places;
	}
	@Override
	public Collection<Place> findByNameIgnoreCaseContaining(String name) {
		return placeRepository.findByNameIgnoreCaseContaining(name);
	}
	
	@Override
	public Place create(Place place) {
		Place addedPlace = placeRepository.save(place);
		return addedPlace;
	}
}
