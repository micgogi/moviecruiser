package com.stackroute.moviecruiser.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiser.domain.Movie;
import com.stackroute.moviecruiser.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiser.exception.MovieNotFoundException;
import com.stackroute.moviecruiser.service.MovieService;

import io.jsonwebtoken.Jwts;


@CrossOrigin
@RestController
@RequestMapping(path="/api/v1/movieservice")
public class MovieController {
    @Autowired
	private MovieService movieService;
    
    @PostMapping("/movie")
    public ResponseEntity<?> saveNewMovie(@RequestBody Movie movie,HttpServletRequest request,
    		HttpServletResponse response){
    	System.out.println("In Movie");
    	final String authHeader = request.getHeader("authorization");
    	final String token = authHeader.substring(7);
    	String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
    	System.out.println("userId:"+userId);
    	try{
    		movie.setUserId(userId);
    		movieService.saveMovie(movie);
    	
    	}catch(MovieAlreadyExistException e){
    		return new ResponseEntity<String>("({message}"+e.getMessage()+"\"+);",HttpStatus.CONFLICT);
    	}
    	return 	new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
    }
    
    
    
    @PutMapping(path="/movie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable("id") Integer id, @RequestBody Movie movie){
    	ResponseEntity<?> responseEntity;
    	
    	try{
    		Movie fetchedMovie = movieService.updateMovie(id, movie);
    		responseEntity=new ResponseEntity<Movie>(fetchedMovie,HttpStatus.OK);
    	}catch(MovieNotFoundException e){
    		responseEntity=new ResponseEntity<String>("({message}"+e.getMessage()+"\"+);",HttpStatus.CONFLICT);
    	}
    	return responseEntity;
    }
    
    @DeleteMapping(path="/movie/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable("id") Integer id){
    	ResponseEntity<?> responseEntity;
    	
    	try{
    		movieService.deleteMovieById(id);
    		
    	}catch(MovieNotFoundException e){
    		System.out.println("hiiiii");
    		responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    	}
    	responseEntity=new ResponseEntity<String>("Movie Deletd Successfully",HttpStatus.OK);
    	return responseEntity;
    }
    @GetMapping(path="/movie/{id}")
    public ResponseEntity<?> fetchMovieById(@PathVariable("id") Integer id){
    	ResponseEntity<?> responseEntity;
    	Movie thisMovie=null;
    	try{
    		thisMovie= movieService.getMovieByid(id);
    	}catch(MovieNotFoundException e){
    		responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    	}
    	responseEntity=new ResponseEntity<Movie>(thisMovie,HttpStatus.OK);
    	return responseEntity;
    }
    
    @GetMapping("/movies")
    public @ResponseBody ResponseEntity<List<Movie>> fetchMyMovies(final ServletRequest req,
    		final ServletResponse res){
    	final HttpServletRequest request = (HttpServletRequest) req;
    	final String authHeader =  request.getHeader("authorization");
    	final String token = authHeader.substring(7);
    	String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
    	System.out.println("userId"+userId);
   return new ResponseEntity<List<Movie>>(movieService.getMyMovies(userId),HttpStatus.OK);
    }
}
