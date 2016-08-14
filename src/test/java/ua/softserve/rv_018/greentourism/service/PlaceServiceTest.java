package ua.softserve.rv_018.greentourism.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import ua.softserve.rv_018.greentourism.model.Place;

public class PlaceServiceTest {

	@Test
	public void testPlaceService(){
			
		Place place = new Place();
		place.setId(1);
		place.setName("White Lake");
		place.setDescription("Huge lake near Varash town");
		
		assertEquals(1, place.getId());
		assertEquals("White Lake", place.getName());
		assertEquals("Huge lake near Varash town", place.getDescription());
	}

}
