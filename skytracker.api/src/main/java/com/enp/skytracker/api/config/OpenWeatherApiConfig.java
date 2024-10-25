package com.enp.skytracker.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * @(#)OpenWeatherApiConfig.java 1.0 23/10/2024
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

@Configuration
public class OpenWeatherApiConfig {
	
	@Value("${openweather.api.key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getWeatherApiUrl(String cityName) {
        return "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=" + apiKey;
    }

}
