package com.enp.skytracker.api.service;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.enp.skytracker.api.config.OpenWeatherApiConfig;
import com.enp.skytracker.api.model.data.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
	
	@Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private OpenWeatherApiConfig apiConfig;

    public Root fetchWeatherData(String cityName) {
        String url = apiConfig.getWeatherApiUrl(cityName);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        // Procesar la respuesta JSON de la API
        return parseWeatherResponse(response.getBody());
    }

    private Root parseWeatherResponse(String response) {
        // Convertir JSON a WeatherData
        ObjectMapper mapper = new ObjectMapper();
        Root weatherData = null;
		try {
			weatherData = mapper.readValue(response, Root.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return weatherData;
    }

}
