package com.example.demo.repository;

import com.example.demo.Entity.entityofmail;
import com.example.demo.Service.EmailService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailMessagesRepo extends MongoRepository<entityofmail, String> {
}
