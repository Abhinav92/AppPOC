package com.poc.deliveryapp.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.poc.deliveryapp.entity.EntityJiraHolder;
import com.poc.deliveryapp.exceptionhandlers.ExceptionHandlerReleaseNotFound;
import com.poc.deliveryapp.exceptionhandlers.ExceptionHandlerReportAlreadyExists;
import com.poc.deliveryapp.repositories.RepositoryJiraHolder;

@RestController
public class ControllerJiraHolder {

	@Autowired
	RepositoryJiraHolder jiraRepo;

	@GetMapping("/getAllJiraInfo")
	public List<EntityJiraHolder> retreiveAllJiraInfo() {
		return (List<EntityJiraHolder>) jiraRepo.findAll();
	}

	@GetMapping("/getSpecificJira/{id}")
	public EntityJiraHolder retrieveSpecificJira(@PathVariable String id) throws ExceptionHandlerReleaseNotFound {
		Optional<EntityJiraHolder> jiraHolderParam = jiraRepo.findById(id);
		if (!jiraHolderParam.isPresent())
			throw new ExceptionHandlerReleaseNotFound();
		return jiraHolderParam.get();
	}

	/*
	 * @GetMapping("/getAllSpecificJira/{reportId}") public JiraHolder
	 * retrieveAllSpecificJira(@PathVariable String reportId) throws
	 * ReleaseNotFoundException { Optional<JiraHolder> jiraHolderParam =
	 * jiraRepo.findByReportID(reportId); if (!jiraHolderParam.isPresent()) throw
	 * new ReleaseNotFoundException(); return jiraHolderParam.get(); }
	 */
	/*
	 * @DeleteMapping("/deleteReport/{id}") public void deleteReportID(@PathVariable
	 * long id) throws ReleaseNotFoundException { releaseRepo.deleteById(id); }
	 */

	// create report based on the Object
	@PostMapping("/createJiraItems")
	public ResponseEntity<EntityJiraHolder> createNewReport(@RequestBody EntityJiraHolder jiraholderParam) {
		try {
			EntityJiraHolder savedReport = jiraRepo.save(jiraholderParam);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedReport.getID()).toUri();
			return ResponseEntity.created(location).build();
		} catch (RuntimeException e) {
			System.out.println("A report with same parameters already exists " + e);
			throw new ExceptionHandlerReportAlreadyExists();
		}
	}
	/*
	 * @PutMapping("/updateJiraItems/{id}") public ResponseEntity<ReleaseParameters>
	 * updateReport(@PathVariable long id, @RequestBody JiraHolder jiraParam) { try
	 * { JiraHolder existing = jiraRepo.findById(id).get();
	 * UtilityClass.copyNonNullProperties(jiraParam, existing); JiraHolder
	 * savedReport = jiraRepo.save(existing); URI location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	 * .buildAndExpand(savedReport.getID()).toUri(); return
	 * ResponseEntity.created(location).build(); } catch (RuntimeException e) {
	 * System.out.println("A report with same parameters already exists " + e);
	 * throw new ReportAlreadyExistsException(); } }
	 */
}
