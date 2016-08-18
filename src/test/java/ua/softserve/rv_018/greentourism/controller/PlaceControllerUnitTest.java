package ua.softserve.rv_018.greentourism.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import ua.softserve.rv_018.greentourism.model.Point;
import ua.softserve.rv_018.greentourism.service.PlaceService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class PlaceControllerUnitTest {
    public static final String EMPTY_COLLECTION = "[]";
    public static final String EMPTY_VALUE = "";
    public static final String VALUE ="{\"id\":1,\"langtitude\":\"langtitude\",\"longtitude\":\"longtitude\"}";
    public static final Point PLACE = new Point(1l, "langtitude", "longtitude");
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
    public void testGetPlace() throws Exception {
    	Mockito.when(placeService.findOnePlace(1l)).thenReturn(PLACE);
        
        mockMvc.perform(get("/place/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(VALUE));
    }
}

