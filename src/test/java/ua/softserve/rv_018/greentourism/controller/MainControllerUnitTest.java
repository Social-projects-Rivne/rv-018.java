package ua.softserve.rv_018.greentourism.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.softserve.rv_018.greentourism.controller.MainController;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MainControllerUnitTest {
	
	private MockMvc mockMvc;
	
	@Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
	
	@InjectMocks
    private MainController mainController;
	
	@Mock
	private SecurityContextLogoutHandler securityContextLogoutHandler;
	
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);
        
        mockMvc = MockMvcBuilders.standaloneSetup(this.mainController).build();
    }
	
	@Test
    public void testLogout_ShouldReturnRedirectView() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/login?logout"))
        		.andExpect(redirectedUrl("/login?logout"));
    }
	
	@Test
    public void testLogout_LogoutHandlerGetsNullAndMethodsReturnsRedirectView() throws Exception {
		HttpServletRequest request =  Mockito.mock(HttpServletRequest.class);     
	    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
		
		Mockito.doNothing().when(securityContextLogoutHandler).logout(request, response, null);
	    
		mockMvc.perform(get("/logout"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/login?logout"))
                .andExpect(redirectedUrl("/login?logout"));
    }
}

