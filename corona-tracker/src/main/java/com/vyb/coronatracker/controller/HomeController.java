package com.vyb.coronatracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vyb.coronatracker.models.LocationStats;
import com.vyb.coronatracker.services.CoronaVirusService;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusService coronoVirusService;
	
	@GetMapping("/coronatracker")
	public String home(Model model) {
		List<LocationStats> allStats = coronoVirusService.getAllStats();
		int totlaCasesGlobe = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
		int totaDiffFromPrevious = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevious()).sum();
		model.addAttribute("allStats",allStats);
		model.addAttribute("totlaCasesGlobe",totlaCasesGlobe);
		model.addAttribute("totaDiffFromPrevious",totaDiffFromPrevious);
		return "home";
	}

}
