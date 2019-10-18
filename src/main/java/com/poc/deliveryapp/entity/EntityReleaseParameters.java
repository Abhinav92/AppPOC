package com.poc.deliveryapp.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

public class EntityReleaseParameters {

	@Id
	private String id;

	@NotNull(message="Line of Business is a mandatory parameter and cannot be null")
	private String lob;

	@NotNull(message="Year is a mandatory parameter and cannot be null")
	private String year;

	@NotNull(message="Quarter is a mandatory parameter and cannot be null")
	private String quarter;

	@NotNull(message="RD/FD delivery is a mandatory parameter and cannot be null")
	private String releases;

	private String title;
	private String description;
	private String name;
	private String businessContact;
	private List<EntityJiraHolder> jiraholder;
	private String additionalInfo;
	private String progressBar;
	private String attachement;
	private String risk;
	private String assumption;
	private String issues;
	private String dependency;

	public EntityReleaseParameters() {

	}

	public EntityReleaseParameters(String lob, String year, String quarter, String releases, String title, String description,
			String name, String businessContact, List<EntityJiraHolder> jiraholder, String additionalInfo, String progressBar,
			String attachement,String risk, String assumption,String issues,String dependency) {
		this.lob = lob;
		this.year = year;
		this.quarter = quarter;
		this.releases = releases;
		this.title = title;
		this.description = description;
		this.name = name;
		this.businessContact = businessContact;
		this.jiraholder = jiraholder;
		this.additionalInfo = additionalInfo;
		this.progressBar = progressBar;
		this.attachement = attachement;
		this.risk=risk;
		this.assumption=assumption;
		this.issues=issues;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getReleases() {
		return releases;
	}

	public void setReleases(String releases) {
		this.releases = releases;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessContact() {
		return businessContact;
	}

	public void setBusinessContact(String businessContact) {
		this.businessContact = businessContact;
	}

	public List<EntityJiraHolder> getJiraHolder() {
		return jiraholder;
	}

	public void setJiraHolder(List<EntityJiraHolder> jiraholder) {
		this.jiraholder = jiraholder;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getAssumption() {
		return assumption;
	}

	public void setAssumption(String assumption) {
		this.assumption = assumption;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}
	

}
