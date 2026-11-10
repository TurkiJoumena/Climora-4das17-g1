package com.climora.alertesnotificationsservice.repository;

import com.climora.alertesnotificationsservice.domain.AlerteDeclenchee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAlerteDeclencheeRepository extends MongoRepository<AlerteDeclenchee, String> {
}