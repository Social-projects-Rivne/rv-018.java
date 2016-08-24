package ua.softserve.rv_018.greentourism.placeTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.repository.PlaceRepository;
import ua.softserve.rv_018.greentourism.service.PlaceServiceImpl;

/**
 * Class to test possibility of editing Place information.
 * 
 * @version 1.0 24 Aug 2016
 * @author Andrey Kluyuko
 * 
 */

public class PlaceEditUnitTest {

	@Mock
	private Place newPlace;
	@Mock
	private Place oldPlace;
	@Mock
	private PlaceRepository placeRepository;
	private PlaceServiceImpl placeService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		placeService = new PlaceServiceImpl();
		placeService.setPlaceRepository(placeRepository);
	}

	/*
	 * 
	 * 
	 */

	@Test
	public void testEditPlaceThatExists() {
		when(placeRepository.update(newPlace)).thenReturn(newPlace);
		assertEquals(newPlace, placeService.update(newPlace));
	}

	/*
	 * 
	 * 
	 */
	@Test
	public void testEditPlaceThatDoesNotExistOrByEmptyPlace() {
		when(placeRepository.update(newPlace)).thenReturn(null);
		assertEquals(null, placeService.update(newPlace));
	}
}
