package com.groupinsy.omr.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupinsy.omr.controller.mapper.MovieMapper;
import com.groupinsy.omr.controller.model.Movie;

@Service
public class MovieService {
	
	
	@Autowired
	MovieMapper movieMapper;
		
	
	public List<Movie> getMovies(){
		return movieMapper.getMovies();
	}


}
