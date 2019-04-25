package com.stackroute.moviecruiser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecruiser.domain.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

	
	
	List<Movie> findByUserId(String userId);
}
