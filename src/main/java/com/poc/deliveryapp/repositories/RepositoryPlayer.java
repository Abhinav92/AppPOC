package com.poc.deliveryapp.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.poc.deliveryapp.pojo.Player;

@Repository
public interface RepositoryPlayer extends MongoRepository<Player, String> {

}
