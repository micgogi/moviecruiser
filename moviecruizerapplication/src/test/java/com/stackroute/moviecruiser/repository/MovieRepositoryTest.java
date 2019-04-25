package com.stackroute.moviecruiser.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiser.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepo;
   
    Movie movie;
    @Before 
    public void set() {
     movie = new Movie();
		//movie.setId(1);
		movie.setTitle("ABc");
		movie.setPoster_path("www.abc.com/abc.png");
		movie.setReleaseDate("07mar2019");
		movie.setComments("abc");
		movie.setUserId("abc");
		movie.setMovieId("abc");
		movie.setVoteAverage(45.5);
		movie.setVoteCount(112);
    }
    @After
    public void tearDown(){
    	movieRepo.deleteAllInBatch();
    }
	@Test
	public void testSaveMovie() throws Exception{
		
		movieRepo.save(movie);
		boolean fetch=movieRepo.existsById(movie.getId());
		assertThat(fetch).isEqualTo(true);
	}
   
	@Test
	public void testUpdateMovie() throws Exception{
		
		movieRepo.save(movie);
		Movie getMovie = movieRepo.save(movie);
		assertEquals(getMovie.getTitle(),"ABc");
		 getMovie.setComments("edf");
		 Movie getMovie1=movieRepo.save(getMovie);
		Movie movie2 = movieRepo.save(getMovie1);
		assertEquals(movie2.getComments(),"edf");
		
	}	
	@Test
	public void testDeleteMovie() throws Exception {
		
		 Movie movie2 = movieRepo.save(movie);
		assertEquals("ABc",movie2.getTitle());
		movieRepo.delete(movie2);
		assertEquals(Optional.empty(),movieRepo.findById(1));
	}
	@Test
	public void testGetMovie() throws Exception  {
		
		
		final Movie movie1 = movieRepo.save(movie);
		assertEquals(movie1.getTitle(),"ABc");
		//movieRepo.delete(movie);
	}
	
	
	@Test
	public void testGetAllMovies() throws Exception {
		
		Movie movie2 = new Movie();
		movie2.setId(2);
		movie2.setTitle("ABcde");
		movie2.setPoster_path("www.abce.com/abcde.png");
		movie2.setReleaseDate("05mar2019");
		movie2.setComments("abcde");
		movie2.setUserId("abc");
		movie2.setMovieId("xyz");
		movie2.setVoteAverage(45.5);
		movie2.setVoteCount(45);
		movieRepo.save(movie);
		movieRepo.save(movie2);
		List<Movie> movieList =movieRepo.findByUserId("abc");
		
		assertEquals(movieList.size(),2);
		//movieRepo.deleteAllInBatch();
	}
	
}
