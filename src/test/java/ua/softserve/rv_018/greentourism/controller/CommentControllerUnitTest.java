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

import ua.softserve.rv_018.greentourism.model.Comment;
import ua.softserve.rv_018.greentourism.service.CommentService;

public class CommentControllerUnitTest {
	
	public static final Comment COMMENT = new Comment();
	public static final String HEADER_LOCATION = "http://localhost/api/comment/1";
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private CommentController commentController;
	
	@Mock
	private CommentService commentService;
	
	@Mock
	private HttpHeaders httpHeaders;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(this.commentController).build();
		
		COMMENT.setId(1);
		COMMENT.setBody("It's a really cool place for weekend");
	}
	
	@Test
	public void testCreateComment() throws Exception {
		Mockito.when(commentService.create(Mockito.any(Comment.class))).thenReturn(COMMENT);

		Gson gson = new Gson();
		String json = gson.toJson(COMMENT);

		mockMvc.perform(post("/api/comment").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(header().string("Location", HEADER_LOCATION));
		
	}
}
