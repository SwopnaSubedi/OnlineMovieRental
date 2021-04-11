package com.groupinsy.omr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupinsy.omr.model.Movie;

@Mapper
public interface MovieMapper {
	
	
	List<Movie> getMovies();
	
	

}
