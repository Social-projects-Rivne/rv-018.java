package ua.softserve.rv_018.greentourism.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import ua.softserve.rv_018.greentourism.service.EventService;

public class EventControllerUnitTest {
	
	public static final Event EVENT = new Event();
	public static final String HEADER_LOCATION = "http://localhost/api/event/1";
	
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
}
