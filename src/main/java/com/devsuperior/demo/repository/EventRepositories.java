package com.devsuperior.demo.repository;

import com.devsuperior.demo.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepositories extends JpaRepository<Event, Long> {

}
