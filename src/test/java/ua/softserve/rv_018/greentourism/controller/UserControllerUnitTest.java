package ua.softserve.rv_018.greentourism.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import ua.softserve.rv_018.greentourism.model.User;
import ua.softserve.rv_018.greentourism.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerUnitTest {
    public static final String EMPTY_COLLECTION = "[]";
    public static final String EMPTY_VALUE = "";
    public static final String VALUE ="{\"id\":1,\"login\":\"login\",\"email\":\"some@gmail.com\",\"firstName\":null,\"lastName\":null,\"password\":\"password\",\"active\":false}"; 

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        User user = new User(1l, "login", "some@gmail.com", "password");
        
        Mockito.when(userService.findOne(1l)).thenReturn(user);
        mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(EMPTY_COLLECTION));
    }
    
    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(VALUE));
    }
    
    @Test
    public void testGetUserThatDoesNotExist() throws Exception {
        mockMvc.perform(get("/user/-1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(EMPTY_VALUE));
    }
}
