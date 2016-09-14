package ua.softserve.rv_018.greentourism.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Arrays;

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

import com.google.gson.Gson;

import ua.softserve.rv_018.greentourism.model.Event;
import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.service.EventService;

public class EventControllerUnitTest {
	
	public static final Event EVENT = new Event();
	public static final String HEADER_LOCATION = "http://localhost/api/event/1";
	public static final String POINTS = "[{\"id\":1,\"latitude\":1.0,\"longitude\":1.0},{\"id\":2,\"latitude\":2.0,\"longitude\":2.0}]";
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EventController eventController;
	
	@Mock
	private EventService eventService;
	
	@Mock
	private HttpHeaders httpHeaders;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(this.eventController).build();
		
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
		
		//Two random dates used here, only think that matters is accordance between dates in URL and dates sent to service
		Mockito.when(eventService.findEventPointsBetweenTwoDates(java.sql.Date.valueOf("2016-09-04"), java.sql.Date.valueOf("2016-09-20")))
		       .thenReturn(points);
		
		mockMvc.perform(get("/api/event/pointBetweenDates?start-date=Sun Sep 04 2016 00:00:00&end-date=Tue Sep 20 2016 00:00:00"))
		       .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(content().string(POINTS));
	}
}
