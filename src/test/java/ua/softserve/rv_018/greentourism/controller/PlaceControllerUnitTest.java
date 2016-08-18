package ua.softserve.rv_018.greentourism.controller;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.service.PlaceService;

import java.util.List;
import java.util.Arrays;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlaceControllerUnitTest {
	
	private static final String EMPTY_COLLECTION = "";
	private static final String COLLECTION = 
			"[{\"id\":1,\"name\":null,\"description\":null,\"point\":"
			+ "{\"id\":1,\"langtitude\":1.0,\"longtitude\":1.0},"
			+ "\"user\":null,\"category\":null},"
			+ "{\"id\":2,\"name\":null,\"description\":null,\"point\":"
			+ "{\"id\":2,\"langtitude\":2.0,\"longtitude\":2.0},"
			+ "\"user\":null,\"category\":null}]";
	
	private List<Place> places;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private PlaceController placeController;
	
	@Mock
	private PlaceService placeService;
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(this.placeController).build();
        
        Point point1 = new Point();
        point1.setId(1);
        point1.setLangtitude(1);
        point1.setLongtitude(1);
        
        Point point2 = new Point();
        point2.setId(2);
        point2.setLangtitude(2);
        point2.setLongtitude(2);
        
        Place place1 = new Place();
        place1.setId(1);
        place1.setPoint(point1);
        
        Place place2 = new Place();
        place2.setId(2);
        place2.setPoint(point2);
        
        places = Arrays.asList(place1, place2);
    }
	
	@Test
	public void getPlaces_ShouldReturnEmptyString() throws Exception {
		Mockito.when(placeService.findAll()).thenReturn(null);
		
		mockMvc.perform(get("/api/place"))
		        .andExpect(status().isOk())
		        .andExpect(content().string(EMPTY_COLLECTION));
		
	}
	
	@Test
	public void getPlaces_ShouldReturnStringWithTwoPlacesAsJSON() throws Exception {
		Mockito.when(placeService.findAll()).thenReturn(places);
		
		mockMvc.perform(get("/api/place"))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(content().string(COLLECTION));
		
	}
}
