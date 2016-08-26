package ua.softserve.rv_018.greentourism.placeTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.repository.PlaceRepository;
import ua.softserve.rv_018.greentourism.service.PlaceServiceImpl;

/**
 * Class to test possibility of searching Place by name.
 * 
 * @version 1.1 26 Aug 2016
 * @author Andrey Kluyuko
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaceSearchUnitTest {

	@InjectMocks
	private PlaceServiceImpl service;
	@Mock
	private PlaceRepository placeRepository;
	@Mock
	private Place place;

	private static List<Place> places;
	public static final String PLACE_NAME = "Name of place";
	public static final String EMPTY_NAME = "";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		places = new ArrayList<Place>();
		places.add(place);
		places.add(place);
		places.add(place);
	}

	/*
	 * A method to test whether finding places by name is successful or not. It
	 * is known that at least one Place with name, that begins at PLACE_NAME
	 * exists.
	 */
	@Test
	public void testSearchPlaceByNameThatExists() {
		when(placeRepository.findByNameIgnoreCaseContaining(PLACE_NAME))
				.thenReturn(places);
		assertEquals(true, (service.findByName(PLACE_NAME, true, true)
				.containsAll(places) && places.containsAll(service.findByName(
				PLACE_NAME, true, true))));
	}

	/*
	 * A method to test whether finding places by name is successful or not. It
	 * is known that there are no Places, that begin at PLACE_NAME.
	 */
	@Test
	public void testSearchPlaceByNameThatDoesNotExistAndByEmptyName() {
		when(placeRepository.findByNameIgnoreCaseContaining(PLACE_NAME))
				.thenReturn(new ArrayList<Place>());
		assertNotEquals(places, service.findByName(PLACE_NAME, true, true));
	}

}
