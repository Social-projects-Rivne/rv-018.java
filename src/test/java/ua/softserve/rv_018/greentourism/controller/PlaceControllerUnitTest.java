package ua.softserve.rv_018.greentourism.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import ua.softserve.rv_018.greentourism.model.Place;
import ua.softserve.rv_018.greentourism.service.PlaceService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


public class PlaceControllerUnitTest {
    public static final String VALUE ="{\"id\":1,\"placename\":\"placename\",\"somedescription\":\"goodPlaceForVocation\",\"point\":null,\"user\":null,\"category\":\"null}";
    public static final Place PLACE = new Place(1, "placename", "somedescription");
    public static final String HEADER_LOCATION = "http://localhost/place/1";

    private MockMvc mockMvc;

    @InjectMocks
    private PlaceController placeController;

    @Mock
    private PlaceService placeService;
    
    @Mock
    private HttpHeaders httpHeaders;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(this.placeController).build();
    }

    @Test
    public void testCreatePlace() throws Exception {
    	Mockito.when(placeService.create(Mockito.any(Place.class))).thenReturn(PLACE);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(PLACE);
        
    	mockMvc.perform(post("/place").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isOk())
		        .andExpect(header().string("Location", HEADER_LOCATION));
    }
    
}
