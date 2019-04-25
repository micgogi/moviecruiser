package com.stackroute.moviecruiser.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.repository.MovieRepository;

public class MovieServiceImplTest {

	
	@Mock
	private MovieRepository movieRepo;
	
	
	private Movie movie;
	
	@InjectMocks
	private MovieServiceImpl movieServiceImpl;
	
	Optional<Movie> options;
	
	@Before
	public void setupMock(){
		MockitoAnnotations.initMocks(this);
		movie = new Movie();
		movie.setId(1);
		movie.setTitle("abc");
		movie.setComments("abc");
		movie.setPoster_path("abc.com/abc.png");
		movie.setReleaseDate("23mar2019");
		movie.setUserId("xyz");
		movie.setMovieId("abc");
		movie.setVoteAverage(45.5);
		movie.setVoteCount(45);
		options=Optional.of(movie);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(movie);
		assertNotNull(movieRepo);
	}
	@Test
	public void testsaveMovieSuccess() throws MovieAlreadyExistException{
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag = movieServiceImpl.saveMovie(movie);
		assertEquals("Saving movie failed",true,flag);
		verify(movieRepo,times(1)).save(movie);
		verify(movieRepo,times(1)).findById(movie.getId());
		
	}
	
	@Test(expected=MovieAlreadyExistException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistException{
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag = movieServiceImpl.saveMovie(movie);
		assertFalse("Saving movie failed",flag);
		verify(movieRepo,times(1)).findById(movie.getId());
	}
	
	@Test
	public void testUpdateMovie() throws MovieNotFoundException{
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		movie.setComments("Not so good movie");
		Movie movie1= movieServiceImpl.updateMovie(1,movie);
		assertEquals("updating movie failed","Not so good movie",movie1.getComments());
		verify(movieRepo,times(1)).save(movie);
		verify(movieRepo,times(1)).findById(movie.getId());
		
	}
	
	@Test
	public void testDeleteMovieById() throws MovieNotFoundException{
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).delete(movie);
		boolean flag = movieServiceImpl.deleteMovieById(1);
		assertEquals("deleting movie failed",true,flag);
		verify(movieRepo,times(1)).delete(movie);
		verify(movieRepo,times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetMovieById() throws MovieNotFoundException{
		when(movieRepo.findById(1)).thenReturn(options);
		Movie movie1 = movieServiceImpl.getMovieByid(1);
		assertEquals("fetching movie failed",movie1,movie);
		verify(movieRepo,times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetMoviesById() throws Exception{
//		List<Movie> movieList= new ArrayList<>(1);
//		when(movieRepo.findAll()).thenReturn(movieList);
//		List<Movie> movie1List=movieServiceImpl.getAllMovie();
//		assertEquals(movieList,movie1List);
//		verify(movieRepo,times(1)).findAll();
		when(movieRepo.findById(1)).thenReturn(options);
		Movie movie1 = movieServiceImpl.getMovieByid(1);
		assertEquals("fetching movie failed",movie1,movie);
		verify(movieRepo,times(1)).findById(movie.getId());
		
	}
	
	@Test
	public void testGetMyMovies() throws Exception{
		List<Movie> movieList = new ArrayList<>();
		movieList.add(movie);
		when(movieRepo.findByUserId("xyz")).thenReturn(movieList);
		List<Movie> movieList1 = movieServiceImpl.getMyMovies("xyz");
		assertEquals(movieList,movieList1);
		verify(movieRepo,times(1)).findByUserId("xyz");
	}
	
}
