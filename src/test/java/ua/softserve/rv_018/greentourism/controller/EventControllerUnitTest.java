package ua.softserve.rv_018.greentourism.controller;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.softserve.rv_018.greentourism.model.CommentItem;
import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Gallery;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.repository.CommentItemRepository;
import ua.softserve.rv_018.greentourism.repository.GalleryRepository;
import ua.softserve.rv_018.greentourism.service.EventService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EventControllerUnitTest {
	
	public static final Event EVENT = new Event();
	public static final String HEADER_LOCATION = "http://localhost/api/event/1";
	public static final String POINTS = "[{\"id\":1,\"latitude\":1.0,\"longitude\":1.0},{\"id\":2,\"latitude\":2.0,\"longitude\":2.0}]";
	private static final String EVENT_URL = "/api/event";
	private static final String EMPTY_COLLECTION = "";
	public static final String VALUE ="{\"id\":1,\"category\":null,\"dateStart\":null,\"dateEnd\":null,\"description\":\"AwesomeEvent\","
			+ "\"name\":\"NewEventInOurCity\",\"point\":null,\"user\":null,\"attachments\":[],\"comments\":[]}";
	public static final String EMPTY_VALUE = "";
	private static final String COLLECTION = "[{\"id\":1,\"category\":null,\"dateStart\":null,\"dateEnd\":null,\"description\":null,"
			+ "\"name\":null,\"point\":{\"id\":1,\"latitude\":1.0,\"longitude\":1.0},\"user\":null,\"attachments\":[],\"comments\":[]},"
			+ "{\"id\":2,\"category\":null,\"dateStart\":null,\"dateEnd\":null,\"description\":null,\"name\":null,"
			+ "\"point\":{\"id\":2,\"latitude\":2.0,\"longitude\":2.0},\"user\":null,\"attachments\":[],\"comments\":[]}]";

	private List<Event> events;
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EventController eventController;
	
	@Mock
	private EventService eventService;

	@Mock
	private GalleryRepository galleryRepository;
	
	@Mock
	private CommentItemRepository commentItemRepository;
	
	@Mock
	private HttpHeaders httpHeaders;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(this.eventController).build();

		Point point1 = new Point();
		point1.setId(1);
		point1.setLatitude(1);
		point1.setLongitude(1);

		Point point2 = new Point();
		point2.setId(2);
		point2.setLatitude(2);
		point2.setLongitude(2);

		Event event1 = new Event();
		event1.setId(1);
		event1.setPoint(point1);

		Event event2 = new Event();
		event2.setId(2);
		event2.setPoint(point2);

		events = Arrays.asList(event1, event2);

		EVENT.setId(1);
		EVENT.setName("NewEventInOurCity");
		EVENT.setDescription("AwesomeEvent");
	}
	
	@Test
	public void testCreateEvent() throws Exception {
		Mockito.when(eventService.create(Mockito.any(Event.class))).thenReturn(EVENT);

		Gson gson = new Gson();
		String json = gson.toJson(EVENT);

		mockMvc.perform(post("/api/event").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(header().string("Location", HEADER_LOCATION));
	}
	
	@Test
	public void testFindEventPointsBetweenTwoDates_ShouldReturnTwoPointsAsJSON() throws Exception {
		Point point1 = new Point();
		point1.setId(1);
		point1.setLatitude(1);
		point1.setLongitude(1);
		
		Point point2 = new Point();
		point2.setId(2);
		point2.setLatitude(2);
		point2.setLongitude(2);
		
		List<Point> points = Arrays.asList(point1, point2);
		
		//Two random dates used here, only thing that matters is accordance between dates in URL and dates sent to service
		Mockito.when(eventService.findEventPointsBetweenTwoDates(java.sql.Date.valueOf("2016-09-04"), java.sql.Date.valueOf("2016-09-20")))
		       .thenReturn(points);
		
		mockMvc.perform(get("/api/event/pointBetweenDates?start-date=Sun Sep 04 2016 00:00:00&end-date=Tue Sep 20 2016 00:00:00"))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		       .andExpect(content().string(POINTS));
	}
	
	@Test
	public void testGetEvents_ShouldReturnEmptyString() throws Exception {
		Mockito.when(eventService.findAll()).thenReturn(null);
		
		mockMvc.perform(get(EVENT_URL))
		        .andExpect(status().isOk())
		        .andExpect(content().string(EMPTY_COLLECTION));
	}
	
	@Test
	public void testGetEvent() throws Exception {
		Mockito.when(eventService.findOne(1)).thenReturn(EVENT);
		Mockito.when(galleryRepository.findByEvent(EVENT)).thenReturn(new ArrayList<Gallery>());
	    Mockito.when(commentItemRepository.findByEvent(EVENT)).thenReturn(new ArrayList<CommentItem>());

		mockMvc.perform(get("/api/event/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string(VALUE));
	}
	
	@Test
	public void testGetEventThatDoesNotExist() throws Exception {
		mockMvc.perform(get("/api/event/-1"))
				.andExpect(status().isNotFound())
				.andExpect(content().string(EMPTY_VALUE));
	}

	@Test
	public void testGetEventByUser() throws Exception {
		Mockito.when(eventService.findByUserIdWithAttachments((long) 1)).thenReturn(events);

		mockMvc.perform(get("/api/event/user/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content().string(COLLECTION));
	}
	
	@Test
    public void testUpdateEvent() throws Exception {
    	Mockito.when(eventService.findOne(1)).thenReturn(new Event());
    	Mockito.when(eventService.update(Mockito.any(Event.class))).thenReturn(EVENT);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(EVENT);
        
    	mockMvc.perform(put("/api/event/1").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isOk())
		        .andExpect(content().string(VALUE));
    }
    
    @Test
    public void testUpdateEventThatDoesNotExist() throws Exception {
    	Mockito.when(eventService.findOne(1)).thenReturn(null);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(EVENT);
        
    	mockMvc.perform(put("/api/event/1").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isNotFound());
    }
}
