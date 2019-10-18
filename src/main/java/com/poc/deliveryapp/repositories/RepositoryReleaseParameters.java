package com.poc.deliveryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.poc.deliveryapp.entity.EntityReleaseParameters;

@Repository
public interface RepositoryReleaseParameters extends MongoRepository<EntityReleaseParameters, String> {

}
