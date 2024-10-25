package com.enp.skytracker.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.enp.skytracker.api.config.OpenWeatherApiConfig;
import com.enp.skytracker.api.model.WeatherData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @(#)WeatherService.java 1.0 23/10/2024
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

@Service
public class WeatherService {
	
	@Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private OpenWeatherApiConfig apiConfig;

    public WeatherData fetchWeatherData(String cityName) {
        String url = apiConfig.getWeatherApiUrl(cityName);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        // Procesar la respuesta JSON de la API
        return parseWeatherResponse(response.getBody());
    }

    private WeatherData parseWeatherResponse(String response) {
        // Convertir JSON a WeatherData
        ObjectMapper mapper = new ObjectMapper();
        WeatherData weatherData = null;
		try {
			weatherData = mapper.readValue(response, WeatherData.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return weatherData;
    }

}
