package com.poc.deliveryapp.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.deliveryapp.exceptionhandlers.ExceptionHandlerReportAlreadyExists;
import com.poc.deliveryapp.pojo.Player;
import com.poc.deliveryapp.repositories.RepositoryPlayer;
import com.poc.deliveryapp.resttemplate.RestTemplateClass;

@RestController
public class ControllerRestTemplate {

	HttpHeaders headers = new HttpHeaders();
	Player newplayer = null;
			
	@Value("${url}")
	String url;
	
	@Autowired
	RestTemplateClass restTemplate;
	
	@Autowired
	RepositoryPlayer playerRepo;


	@GetMapping("/getPlayerInfo/{playerID}")
	public ResponseEntity<Player> getPlayerInfo(@PathVariable String playerID) throws ConfigurationException, IOException, URISyntaxException {
		String endpoint = url + "/" + playerID;
		System.out.println("Trying to access the endpoint" + endpoint);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		final ResponseEntity<Player> responseBody = restTemplate.exchange(endpoint, HttpMethod.GET, entity,
				Player.class);
		System.out.println(responseBody.toString());
		// assigning the player params to the new body for persistence
		newplayer=responseBody.getBody();
		persistPlayerInfo();
		return responseBody;
	}
	
	// exposure to this endpoint will be removed
	@PostMapping("/createInfo")
	public ResponseEntity<Player> persistPlayerInfo()
			throws IOException, ConfigurationException, URISyntaxException {
		try {
			Player savedReport = playerRepo.save(newplayer);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedReport.getProfile().getAccount_id()).toUri();
			return ResponseEntity.created(location).build();
		} catch (RuntimeException e) {
			System.out.println("A player details with same parameters already exists " + e);
			throw new ExceptionHandlerReportAlreadyExists();
		}
	}

}
