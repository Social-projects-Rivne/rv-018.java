package ua.softserve.rv_018.greentourism.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
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
	public List<Place> findAll() {
		logger.info("> Place findAll");

		List<Place> places = placeRepository.findAll();

        logger.info("< Place findAll");
        
        return places;
	}
	
	@Override
	public Place findOne(int id) {
		logger.info("> Place findOne id:{}", id);

        Place place = placeRepository.findById(id);

        logger.info("< Place findOne id:{}", id);

        return place;
	}
	
	@Override
	public List<Place> findByName(String name, boolean checkIgnoreCase, boolean checkContaining) {
		logger.info("> Place findByName");

		List<Place> places = new ArrayList<>();
		
		if (checkIgnoreCase && checkContaining) {
			places = placeRepository.findByNameIgnoreCaseContaining(name);
		}
		// here will be other findByName... methods due to checkIgnoreCase && checkWholeWord values

        logger.info("< Place findByName");

		return places;
	}

	@Override
	public List<Point> getPlacePointsBetweenTwoCoordinates(Point southWest, Point northEast) {
		logger.info("> Place getPlacePointsBetweenTwoCoordinates");

		List<Place> places = placeRepository.findBetweenTwoPoints(
				southWest.getLatitude(), southWest.getLongitude(),
				northEast.getLatitude(), northEast.getLongitude());
		List<Point> points = getPointsFromPlaces(places);
		
        logger.info("< Place getPlacePointsBetweenTwoCoordinates");

		return points;
	}

	private List<Point> getPointsFromPlaces(List<Place> places) {
		List<Point> points = new ArrayList<>();
		
		for (Place place : places) {
			points.add(place.getPoint());
		}
		
		return points;
	}
	
	@Override
	public Place create(Place place) {
		logger.info("> Place create");
		
		Place savedPlace = placeRepository.save(place);
		
		logger.info("> Place create");
		
		return savedPlace;
	}
}
