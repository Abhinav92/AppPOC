package com.poc.deliveryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.poc.deliveryapp.entity.EntityJiraHolder;

@Repository
public interface RepositoryJiraHolder extends MongoRepository<EntityJiraHolder, String> {

	
}
