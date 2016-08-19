package ua.softserve.rv_018.greentourism.placeTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.service.PlaceServiceImpl;

public class PlaceSearchUnitTest {

	@Mock
	private Place place;
	@Mock
	private PlaceServiceImpl service;
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

	@Test
	public void testSearchPlaceByNameThatExists() {
		when(service.findByName(PLACE_NAME)).thenReturn(places);
		assertEquals(true,
				(service.findByName(PLACE_NAME).containsAll(places)&& places.containsAll(service.findByName(PLACE_NAME))));
	}

	@Test
	public void testSearchPlaceByNameThatDoesNotExistAndByEmptyName() {
		when(service.findByName(PLACE_NAME)).thenReturn(new ArrayList<Place>());
		assertNotEquals(places, service.findByName(PLACE_NAME));
	}

}
