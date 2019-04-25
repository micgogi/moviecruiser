package com.stackroute.moviecruiser.controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.service.MovieServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@Autowired
	private transient MockMvc mockMvc;
	
	@MockBean
	private MovieServiceImpl movieService;
	
	private transient Movie movie;
	
	@InjectMocks
	private MovieController movieController;
	static List<Movie> movies;
	
	//Optional<Movie> options;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		movies=new ArrayList<>();
		mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
		movie=new Movie(1, "xyz", "abc", "abc", "abc", "abc",
				45.5, 40,"100");
		movies.add(movie);
		movie=new Movie(2, "xyz", "abc", "abc", "abc", "abc",
				45.5, 40, "100");
		movies.add(movie);
		
		//options=Optional.of(movie);
	}
	
	@Test
	public void testSaveNewMovieSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NTI4ODM1MDh9.MyXS7jbfrQOkK0iUtgH3RddaK0ZIYIDV-a_XLknmJ_o";

		when(movieService.saveMovie(movie)).thenReturn(true);
		mockMvc.perform(post("/api/v1/movieservice/movie").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie))).andExpect(status().isCreated());
		verify(movieService, times(1)).saveMovie(Mockito.any(Movie.class));
	}
	
//	@Test
//	public void testUpdateMovieSuccess() throws Exception{
//		movie.setComments("not so good");
////		when(movieService.updateMovie(movie)).thenReturn(movies.get(0));
//		mockMvc.perform(put("/api/movie/{id}",1).contentType(MediaType.APPLICATION_JSON).content(jsonToString(movie)))
//		.andExpect(status().isOk());
//		verify(movieService,times(1)).updateMovie(Mockito.any(Movie.class));
//		verifyNoMoreInteractions(movieService);
//	}
	@Test
	public void testDeleteMovieById()throws Exception{
		when(movieService.deleteMovieById(1)).thenReturn(true);
		mockMvc.perform(delete("/api/v1/movieservice/movie/{id}",1)).andExpect(status().isOk());
		verify(movieService,times(1)).deleteMovieById(1);
		verifyNoMoreInteractions(movieService);
	}
	
	
	@Test
	public void testGetMyMovies() throws Exception{
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NTI4ODM1MDh9.MyXS7jbfrQOkK0iUtgH3RddaK0ZIYIDV-a_XLknmJ_o";
		when(movieService.getMyMovies("100")).thenReturn(movies);
		mockMvc.perform(get("/api/v1/movieservice/movies")
				.header("authorization","Bearer "+token)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andDo(print());
		verify(movieService,times(1)).getMyMovies("100");
		verifyNoMoreInteractions(movieService);
	}
	
//	@Test
//	public void testFetchMovieById()throws Exception{
//		when(movieService.getMovieByid(1)).thenReturn(movies.get(0));
//		mockMvc.perform(get("/api/movie/{id}",1)).andExpect(status().isOk());
//		verify(movieService,times(1)).getMovieByid(1);
//		verifyNoMoreInteractions(movieService);
//	}
//	
//	@Test
//	public void testFetchAllMovies() throws Exception {
//		when(movieService.getAllMovie()).thenReturn(null);
//		mockMvc.perform(get("/api/movie")).andExpect(status().isOk());
//		verify(movieService,times(1)).getAllMovie();
//		verifyNoMoreInteractions(movieService);
//	}
	private static String jsonToString(final Object obj) throws JsonProcessingException{
		String result;
		try {
			final ObjectMapper mapper=new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);
			result =jsonContent;
					
		}catch(JsonProcessingException e) {
			result="json processing error";
		}
		return result;
	}

}
