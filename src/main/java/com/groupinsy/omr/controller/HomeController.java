package com.groupinsy.omr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.groupinsy.omr.model.Movie;
import com.groupinsy.omr.model.UserDto;
import com.groupinsy.omr.service.MovieService;

@Controller
public class HomeController {
	
	@Autowired
	MovieService movieService;
	
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<Movie> movies = movieService.getMovies();
		
		model.addAttribute("movies", movies);
		
		return "homepage";
	}
	
	
	@GetMapping("/signup")
	public String signUp(Model model) {
		
		
		return "signup";
	}
	
	
	@PostMapping("/signup")
	public String signUpPost(@ModelAttribute("user") UserDto userDto, Model model) {
		
		return "signup";
	}
	
	@GetMapping("/login")
	public String signIn(Model model) {
		
		
		return "login";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		
		
		return "cart";
	}
	
	@GetMapping("/checkout")
	public String checkOut(Model model) {
		
		
		return "checkout";
	}
	

}
