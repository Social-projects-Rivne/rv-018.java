package ua.softserve.rv_018.greentourism.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;
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

	/**
     * The Spring Data repository for Attachment entities.
     */
    @Autowired
    private GalleryRepository galleryRepository;

	@Override
	public List<Place> findAll() {
		logger.info("> Place findAll");

		List<Place> places = placeRepository.findAll();

        logger.info("< Place findAll");
        
        return places;
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
	public List<Point> findPlacePointsBetweenTwoCoordinates(Point southWest, Point northEast) {
		logger.info("> Place findPlacePointsBetweenTwoCoordinates");

		List<Place> places = placeRepository.findBetweenTwoPoints(
				southWest.getLatitude(), southWest.getLongitude(),
				northEast.getLatitude(), northEast.getLongitude());
		List<Point> points = getPointsFromPlaces(places);
		
        logger.info("< Place findPlacePointsBetweenTwoCoordinates");

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
	public List<Place> findPlacesBetweenTwoCoordinates(Point southWest, Point northEast) {
		logger.info("> Place findPlacePointsBetweenTwoCoordinates");

		List<Place> places = placeRepository.findBetweenTwoPoints(
				southWest.getLatitude(), southWest.getLongitude(),
				northEast.getLatitude(), northEast.getLongitude());
		
        logger.info("< Place findPlacePointsBetweenTwoCoordinates");

		return places;
	}
	
	@Override
	public Place create(Place place) {
		logger.info("> Place create");
		
		Place savedPlace = placeRepository.save(place);
		
		logger.info("> Place create");
		
		return savedPlace;
	}
	
	@Override
	public Place findOne(Integer id) {
		logger.info("> Place findOne id:{}", id);

        Place place = placeRepository.findOne(id);

        logger.info("< Place findOne id:{}", id);

        return place;
	}

	@Override
	public List<Place> findByUserId(Long id) {
		logger.info("> Place findByUserId");
		
		List<Place> places = placeRepository.findByUserId(id);
		
		logger.info("< Place findByUserId");
		
		return places;
	}

	@Override
	public List<Place> findByUserIdWithAttachments(Long id) {
		logger.info("> Place findByUserIdWithAttachment");
		
		List<Place> places = placeRepository.findByUserId(id);
		
		List<Gallery> galleries = galleryRepository.findByPlaces(places);
		for (Gallery gallery : galleries) {
			if (gallery == null) {
				continue;
			}
			
			for (Place place : places) {
				if (place.getId() == gallery.getPlace().getId()) {
					place.getAttachments().add(gallery.getAttachment());
				}
			}
		}
		
		logger.info("< Place findByUserIdWithAttachment");
		
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
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Place update(Place place) {
		logger.info("> Place update id:{}", place.getId());

		// Ensure the entity object to be updated exists in the repository to
		// prevent the default behavior of save() which will persist a new
		// entity if the entity matching the id does not exist
		Place placeToUpdate = findOne(place.getId());
		if (placeToUpdate == null) {
			// Cannot update Place that hasn't been persisted
			logger.error("Attempted to update a Place, but the entity does not exist.");
			throw new NoResultException("Requested entity not found.");
		}

		if (place.getName() != null) {
			placeToUpdate.setName(place.getName());
		}
		if (place.getDescription() != null) {
			placeToUpdate.setDescription(place.getDescription());
		}
		
		Place updatedPlace = placeRepository.save(placeToUpdate);

		logger.info("< Place update id:{}", updatedPlace.getId());

		return updatedPlace;
	}
}
