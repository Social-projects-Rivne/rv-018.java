package ua.softserve.rv_018.greentourism.placeTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
	public static final String PLACE_NAME = "Name of place";
	public static final String EMPTY_NAME = "";

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSearchPlaceByNameThatExists() {
		when(place.getName()).thenReturn(PLACE_NAME);
		when(service.findByName(PLACE_NAME)).thenReturn(place);
		assertEquals(place.getName(), service.findByName(PLACE_NAME)
				.getName());
		assertEquals(place, service.findByName(PLACE_NAME));
	}

	@Test
	public void testSearchPlaceByNameThatDoesNotExist() {
		when(service.findByName(PLACE_NAME)).thenReturn(null);
		assertNotEquals(place, service.findByName(PLACE_NAME));
	}

	@Test
	public void testSearchPlaceByEmptyName() {
		when(service.findByName(EMPTY_NAME)).thenReturn(null);
		assertNotEquals(place, service.findByName(PLACE_NAME));
	}
}
