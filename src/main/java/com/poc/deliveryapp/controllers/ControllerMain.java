package com.poc.deliveryapp.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.deliveryapp.entity.EntityReleaseParameters;
import com.poc.deliveryapp.exceptionhandlers.ExceptionHandlerReleaseNotFound;
import com.poc.deliveryapp.exceptionhandlers.ExceptionHandlerReportAlreadyExists;
import com.poc.deliveryapp.repositories.RepositoryReleaseParameters;
import com.poc.deliveryapp.util.Utility;

@RestController
public class ControllerMain {

	static int counter = 1;

	@Autowired
	RepositoryReleaseParameters releaseRepo;

	@Autowired(required = false)
	DataSource dataSource;
	@Autowired(required = false)
	MongoDbFactory mongoDbFactory;

	@GetMapping("/getAllReports")
	public List<EntityReleaseParameters> retreiveAllReports() {
		return releaseRepo.findAll();
	}

	@GetMapping("/getOneReport/{id}")
	public EntityReleaseParameters retrieveOneReport(@PathVariable String id) throws ExceptionHandlerReleaseNotFound {
		Optional<EntityReleaseParameters> releaseParam = releaseRepo.findById(id);
		if (!releaseParam.isPresent())
			throw new ExceptionHandlerReleaseNotFound();
		return releaseParam.get();
	}

	// require this when using Criteria Query
	/*
	 * @GetMapping("/getSpecificReport/{lob}/{year}/{quarter}/{release}") public
	 * EntityReleaseParameters retrieveParamReport(@PathVariable String
	 * lob, @PathVariable String year,
	 * 
	 * @PathVariable String quarter, @PathVariable String release) throws
	 * ExceptionHandlerReleaseNotFound { Optional<EntityReleaseParameters>
	 * releaseParam = releaseRepo.findByParam(lob, year, quarter, release); if
	 * (!releaseParam.isPresent()) throw new ExceptionHandlerReleaseNotFound();
	 * return releaseParam.get(); }
	 */

	@DeleteMapping("/deleteReport/{id}")
	public void deleteReportID(@PathVariable String id) throws ExceptionHandlerReleaseNotFound {
		releaseRepo.deleteById(id);
	}

	// create a new entry in the DB
	@PostMapping("/createReport")
	public ResponseEntity<EntityReleaseParameters> createReport(
			@Valid @RequestBody EntityReleaseParameters releaseParam)
			throws IOException, ConfigurationException, URISyntaxException {
		try {
			releaseParam.setID(String.valueOf(counter));
			counter++;
			// System.out.println(String.valueOf(Utility.getPropValue()));
			// Utility.setPropValue("120");
			EntityReleaseParameters savedReport = releaseRepo.save(releaseParam);
			// System.out.println("saving report");
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedReport.getID()).toUri();
			// System.out.println("returning hateoas");
			return ResponseEntity.created(location).build();
		} catch (RuntimeException e) {
			System.out.println("A report with same parameters already exists " + e);
			throw new ExceptionHandlerReportAlreadyExists();
		}
	}

	// update an existing entry in the DB
	@PutMapping("/updateReport/{id}")
	public ResponseEntity<EntityReleaseParameters> updateReport(@PathVariable String id,
			@RequestBody EntityReleaseParameters releaseParam) {
		EntityReleaseParameters existing = releaseRepo.findById(id).get();
		Utility.copyNonNullProperties(releaseParam, existing);
		EntityReleaseParameters savedReport = releaseRepo.save(existing);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedReport.getID()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/generateTextFile")
	public ResponseEntity<Resource> retreiveDataForText() throws IOException {
		List<EntityReleaseParameters> result = releaseRepo.findAll();
		Utility.insertAndSave(result);

		File file = new File("persistData.txt");
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=persistDataFile.txt");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}
}
