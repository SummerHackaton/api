package org.example.summerhackaton.dao;


import org.example.summerhackaton.domain.model.user.RolesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository  extends MongoRepository<RolesEntity, String> { }
