package com.enp.skytracker.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enp.skytracker.api.dto.UserDto;
import com.enp.skytracker.api.model.ErrorDetails;
import com.enp.skytracker.api.model.ResponseDetails;
import com.enp.skytracker.api.model.WeatherData;
import com.enp.skytracker.api.service.WeatherService;

/*
 * @(#)WeatherController.java 1.0 23/10/2024
 * 
 * El código implementado en este formulario esta protegido
 * bajo las leyes internacionales del Derecho de Autor, sin embargo
 * se entrega bajo las condiciones de la General Public License (GNU GPLv3)
 * descrita en https://www.gnu.org/licenses/gpl-3.0.html
 */

/**
 * @author eliezer.navarro
 * @version 1.0
 * @since 1.0
 */

@RestController
@RequestMapping("/api/v1/skytracker/weather")
public class WeatherController {
	
	@Autowired
    private WeatherService weatherService;

    @GetMapping("/forecast/{cityName}")
    public ResponseDetails<?> getWeatherCity(@PathVariable String cityName) {
    	WeatherData data;
    	try{
    		data = weatherService.fetchWeatherData(cityName);
			if(data == null) {
				ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.NOT_FOUND.toString(),"Wather for city <"+cityName+"> not found");
				return new ResponseDetails<String>("ERROR",new Date(),new ResponseEntity<String>("NOT_FOUND", HttpStatus.NOT_FOUND));
			}
			return new ResponseDetails<WeatherData>("OK",new Date(),new ResponseEntity<WeatherData>(data, HttpStatus.OK));
		}catch(Exception e){
			ErrorDetails err = new ErrorDetails(new Date(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),e.getMessage());
			return new ResponseDetails<ErrorDetails>("ERROR",new Date(),new ResponseEntity<ErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR));
		}
    }
    
    /*
    public ResponseEntity<WeatherData> getWeatherByCity(@PathVariable String cityName) {
        WeatherData data = weatherService.fetchWeatherData(cityName);
        return ResponseEntity.ok(data);
    }
	*/
}
