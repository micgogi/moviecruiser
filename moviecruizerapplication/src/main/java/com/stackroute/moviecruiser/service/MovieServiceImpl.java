package com.stackroute.moviecruiser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.repository.MovieRepository;
@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
	MovieRepository movieRepo;
	
	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistException {
		// TODO Auto-generated method stub
		
		Optional<Movie> object=movieRepo.findById(movie.getId());
		if(object.isPresent()){
			throw new MovieAlreadyExistException("Movie already exist");
		}
		movieRepo.save(movie);
		return true;
	}
    
	
//	@Override
//	public boolean deleteMovieById(Integer id) throws MovieNotFoundException {
//		Movie deleteMovie = movieRepo.findById(id).orElse(null);
//		if(deleteMovie==null)
//			throw new MovieNotFoundException("Movie Not Found");
//		
//		movieRepo.delete(deleteMovie);
//		
//		return true;
//	}
//	@Override
//	public Movie getMovieByid(Integer id) throws MovieNotFoundException {
//	   Movie movie = movieRepo.findById(id).orElse(null);
//	   if(movie==null)
//		   throw new MovieNotFoundException("Movie Not Found");
//		return movie;
//	}
	@Override
	public List<Movie> getMyMovies(String userId) {
		
		return movieRepo.findByUserId(userId);
	}

	@Override
	public boolean deleteMovieById(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Movie deleteMovie = movieRepo.findById(id).orElse(null);
		if(deleteMovie==null)
			throw new MovieNotFoundException("Movie Not Found");
		movieRepo.delete(deleteMovie);
		return true;
	}

	@Override
	public Movie getMovieByid(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		 Movie movie = movieRepo.findById(id).orElse(null);
		 if(movie==null)
			 throw new MovieNotFoundException("Movie Not Found");
		 return movie;
		
	}

	@Override
	public Movie updateMovie(int id, Movie movie) throws MovieNotFoundException {
		Movie updatedMovie = movieRepo.findById(movie.getId()).orElse(null);
		if(updatedMovie==null)
			throw new MovieNotFoundException("Movie Not Found");
		
		updatedMovie.setComments(movie.getComments());
		movieRepo.save(updatedMovie);
		return updatedMovie;
	}

}
