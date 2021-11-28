package com.groupinsy.omr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupinsy.omr.model.Movie;

@Mapper
public interface MovieMapper {
	
	
	List<Movie> getMovies();

	void insertMovie(Movie movie);
	
	void updateMovie(Movie movie);
	
	void deleteMovie(Integer id);
	
	Movie getMovieById(Integer id);

}
