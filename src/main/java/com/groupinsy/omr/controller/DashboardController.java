package com.groupinsy.omr.controller;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.groupinsy.omr.model.Movie;
import com.groupinsy.omr.service.MovieService;

@Controller
@SessionAttributes("movie")
public class DashboardController {

	@Autowired
	MovieService movieService;

	@Value("${movielocation}")
	String movieLocation;

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

	@GetMapping("/edit-movie/{id}")
	public String editMovie(@PathVariable Integer id, Model model) {

		model.addAttribute("movie", movieService.getMovieById(id));

		return "add-movie";
	}

	@GetMapping("/delete-movie/{id}")
	public String deleteMovie(@PathVariable Integer id, Model model) {

		movieService.deleteMovie(id);

		return "redirect:/management";
	}

	@PostMapping("/save-movie")
	public String saveMovie(@ModelAttribute("movie") Movie movie, 
			@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("movieFile") MultipartFile movieFile, Model model) {

		try {
			if(movie.getMovieLink() == null)
			{
				String movieLink = RandomStringUtils.randomAlphanumeric(40);
				movie.setMovieLink(movieLink);
			}
			
			if(!thumbnail.isEmpty()) {
				
				
				File f1 = new File(movieLocation +"/thumbnails/"+movie.getMovieLink()+".jpg");
				
				thumbnail.transferTo(f1);
				
			}
			
			if(!movieFile.isEmpty()) {
				
				File f1 = new File(movieLocation +"/"+movie.getMovieLink()+".mp4");
				
				movieFile.transferTo(f1);
				
			}
			
			movieService.saveMovie(movie);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("movie", movie);
			return "add-movie";
		}

		return "redirect:/management";

	}
	
	@GetMapping("/reports")
	public String reports(Model model) {


		return "reports";
	}
	
	

}
