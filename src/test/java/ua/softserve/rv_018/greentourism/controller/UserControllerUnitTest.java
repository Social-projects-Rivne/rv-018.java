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

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


public class UserControllerUnitTest {
    public static final String EMPTY_COLLECTION = "[]";
    public static final String EMPTY_VALUE = "";
    public static final String VALUE ="{\"id\":1,\"username\":\"username\",\"email\":\"some@gmail.com\",\"firstName\":null,\"lastName\":null,\"password\":\"password\",\"active\":false}";
    public static final User USER = new User(1l, "username", "some@gmail.com", "password");
    public static final String HEADER_LOCATION = "http://localhost/user/1";

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
    
    @Mock
    private HttpHeaders httpHeaders;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(EMPTY_COLLECTION));
    }
    
    @Test
    public void testGetUser() throws Exception {
    	Mockito.when(userService.findOne(1l)).thenReturn(USER);
        
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(VALUE));
    }
    
    @Test
    public void testGetUserThatDoesNotExist() throws Exception {
        mockMvc.perform(get("/user/-1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(EMPTY_VALUE));
    }
    
    @Test
    public void testCreateUser() throws Exception {
    	Mockito.when(userService.create(Mockito.any(User.class))).thenReturn(USER);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(USER);
        
    	mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isOk())
		        .andExpect(header().string("Location", HEADER_LOCATION));
    }
    
    @Test
    public void testUpdateUser() throws Exception {
    	Mockito.when(userService.findOne(1l)).thenReturn(new User());
    	Mockito.when(userService.update(Mockito.any(User.class))).thenReturn(USER);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(USER);
        
    	mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isOk())
		        .andExpect(content().string(VALUE));
    }
    
    @Test
    public void testUpdateUserThatDoesNotExist() throws Exception {
    	Mockito.when(userService.findOne(1l)).thenReturn(null);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(USER);
        
    	mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON).content(json))
		        .andExpect(status().isNotFound());
    }
}
