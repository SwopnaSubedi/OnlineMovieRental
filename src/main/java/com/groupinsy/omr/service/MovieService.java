package com.groupinsy.omr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupinsy.omr.mapper.MovieMapper;
import com.groupinsy.omr.model.Movie;

@Service
public class MovieService {
	
	
	@Autowired
	MovieMapper movieMapper;
		
	
	public List<Movie> getMovies(){
		return movieMapper.getMovies();
	}
	
	public void saveMovie(Movie movie) {
		if(movie.getId() != null)
		{
			movieMapper.updateMovie(movie);
		}else {
			movieMapper.insertMovie(movie);
		}
		
	}
	
	public void deleteMovie(Integer id)
	{
		movieMapper.deleteMovie(id);
	}
	

	public Movie getMovieById(Integer id) {
		return movieMapper.getMovieById(id);
	}

}
