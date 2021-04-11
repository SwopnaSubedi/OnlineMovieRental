package com.groupinsy.omr.controller.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupinsy.omr.controller.model.Movie;

@Mapper
public interface MovieMapper {
	
	
	List<Movie> getMovies();
	
	

}
