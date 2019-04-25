package com.stackroute.moviecruiser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
@Service
public interface MovieService {

	
	boolean saveMovie(Movie movie) throws MovieAlreadyExistException;
	
	Movie updateMovie(int id,Movie movie) throws MovieNotFoundException;
	
	boolean deleteMovieById(int id) throws MovieNotFoundException;
	
	Movie getMovieByid(int id) throws MovieNotFoundException;
	
	List<Movie> getMyMovies(String userId);
}
