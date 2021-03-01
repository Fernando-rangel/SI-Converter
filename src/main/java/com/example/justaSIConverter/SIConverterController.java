package com.example.justaSIConverter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SIConverterController {
	
	@GetMapping("/si")
	public SIConverter convertUnits(@RequestParam(value = "units") String units) {
		return new SIConverter(units);
	}
	
}
