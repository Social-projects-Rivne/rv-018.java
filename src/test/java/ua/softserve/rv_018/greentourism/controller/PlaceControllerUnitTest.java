package ua.softserve.rv_018.greentourism.controller;

import ua.softserve.rv_018.greentourism.model.CommentItem;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.CommentItemRepository;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;
import ua.softserve.rv_018.greentourism.service.PlaceService;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlaceControllerUnitTest {
	
	private static final String EMPTY_COLLECTION = "";
	public static final String EMPTY_VALUE = "";
	private static final String COLLECTION = 
			"[{\"id\":1,\"name\":null,\"description\":null,\"point\":"
			+ "{\"id\":1,\"latitude\":1.0,\"longitude\":1.0},"
			+ "\"user\":null,\"category\":null,\"attachments\":[],\"comments\":[]},"
			+ "{\"id\":2,\"name\":null,\"description\":null,\"point\":"
			+ "{\"id\":2,\"latitude\":2.0,\"longitude\":2.0},"
			+ "\"user\":null,\"category\":null,\"attachments\":[],\"comments\":[]}]";
	private static final String PLACE_URL = "/api/place";
	public static final String VALUE ="{\"id\":1,\"name\":\"Name1\",\"description\":\"Description1\",\"point\":null,\"user\":null,\"category\":null,\"attachments\":[],\"comments\":[]}";

    public static final Place PLACE = new Place();
    public static final String HEADER_LOCATION = "http://localhost/api/place/1";
	
	private List<Place> places;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private PlaceController placeController;
	
	@Mock
	private PlaceService placeService;
	
	@Mock
	private GalleryRepository galleryRepository;
	
	@Mock
	private CommentItemRepository commentItemRepository;
	
	@Mock

    private HttpHeaders httpHeaders;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(this.placeController).build();
		
		Point point1 = new Point();
		point1.setId(1);
		point1.setLatitude(1);
		point1.setLongitude(1);
		
		Point point2 = new Point();
		point2.setId(2);
		point2.setLatitude(2);
		point2.setLongitude(2);
		
		Place place1 = new Place();
		place1.setId(1);
		place1.setPoint(point1);
		
		Place place2 = new Place();
		place2.setId(2);
		place2.setPoint(point2);
		
		places = Arrays.asList(place1, place2);
		
		PLACE.setId(1);
		PLACE.setName("Name1");
		PLACE.setDescription("Description1");
        
	}
	
	@Test
	public void testGetPlaces_ShouldReturnEmptyString() throws Exception {
		Mockito.when(placeService.findAll()).thenReturn(null);
		
		mockMvc.perform(get(PLACE_URL))
		        .andExpect(status().isOk())
		        .andExpect(content().string(EMPTY_COLLECTION));
		
	}
	
	@Test
	public void testGetPlaces_ShouldReturnStringWithTwoPlacesAsJSON() throws Exception {
		Mockito.when(placeService.findAll()).thenReturn(places);
		
		mockMvc.perform(get(PLACE_URL))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(content().string(COLLECTION));
		
	}
	
	@Test
	public void testCreatePlace() throws Exception {
		Mockito.when(placeService.create(Mockito.any(Place.class))).thenReturn(PLACE);

		Gson gson = new Gson();
		String json = gson.toJson(PLACE);

		mockMvc.perform(post("/api/place").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(header().string("Location", HEADER_LOCATION));
		
	}
	
	@Test
	 public void testGetPlace() throws Exception {
	     Mockito.when(placeService.findOne(1)).thenReturn(PLACE);
	     Mockito.when(galleryRepository.findByPlace(PLACE)).thenReturn(new ArrayList<Gallery>());
	     Mockito.when(commentItemRepository.findByPlace(PLACE)).thenReturn(new ArrayList<CommentItem>());

	     mockMvc.perform(get("/api/place/1"))
	             .andExpect(status().isOk())
	             .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	             .andExpect(content().string(VALUE));
    }
	
	@Test
    public void testGetPlaceThatDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/place/-1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(EMPTY_VALUE));
    }
	
	@Test
    public void testUpdatePlace() throws Exception {
    	Mockito.when(placeService.findOne(1)).thenReturn(new Place());
    	Mockito.when(placeService.update(Mockito.any(Place.class))).thenReturn(PLACE);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(PLACE);
        
    	mockMvc.perform(put("/api/place/1").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isOk())
		        .andExpect(content().string(VALUE));
    }
    
    @Test
    public void testUpdatePlaceThatDoesNotExist() throws Exception {
    	Mockito.when(placeService.findOne(1)).thenReturn(null);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(PLACE);
        
    	mockMvc.perform(put("/api/place/1").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isNotFound());
    }
}