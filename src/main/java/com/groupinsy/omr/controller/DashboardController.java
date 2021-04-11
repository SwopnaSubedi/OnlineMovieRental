package com.groupinsy.omr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.groupinsy.omr.controller.model.Movie;
import com.groupinsy.omr.controller.service.MovieService;

@Controller
public class DashboardController {
	
	
	@Autowired
	MovieService movieService;
	
	
	@GetMapping("/management-json")
	public @ResponseBody List<Movie> hometest() {
			
		return movieService.getMovies();
	}
	
	@GetMapping("/management")
	public String home(Model model) {
		
		model.addAttribute("movies", movieService.getMovies());
		
		return "dashboard";
	}
	
	@GetMapping("/add-movie")
	public String addMovie(Model model) {
		
		model.addAttribute("movie", new Movie());
		
		return "add-movie";
	}
	
	@PostMapping("/save-movie")
	public String saveMovie(@ModelAttribute("movie") Movie movie, Model model) {
		
		
		
		
		model.addAttribute("movie", movie);
		
		return "redirect:/management";
	}

}
