package ua.softserve.rv_018.greentourism.service;

import java.util.List;

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
	public List<Place> findByName(String name) {
		return placeRepository.findByNameIgnoreCaseContaining(name);
	}

}
