package com.poc.deliveryapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EntityJiraHolder {

	@Id
	private String id;
	private String story;
	private String usage;
	private String business;
	private String effort;

	public EntityJiraHolder() {
	}

	public EntityJiraHolder(String story, String usage, String business, String effort) {
		this.story = story;
		this.usage = usage;
		this.business = business;
		this.effort = effort;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}

	@Override
	public String toString() {
		return "JiraHolder{" + "id=" + id + ", story='" + story + '\'' + ", usage=" + usage + ", business=" + business
				+ ", effort='" + effort + '\'' + '}';
	}
}
