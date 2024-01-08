package com.devsuperior.demo.service;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repository.CityRepositories;
import com.devsuperior.demo.repository.EventRepositories;
import com.devsuperior.demo.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventServices {

    @Autowired
    private EventRepositories repositories;

    @Autowired
    private CityRepositories cityRepositories;

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event entity = repositories.getReferenceById(id);

            if (dto.getCityId() != null) {
                City city = cityRepositories.getReferenceById(dto.getCityId());
                entity.setCity(city);
            }
            copyEntity(dto, entity);
            entity = repositories.save(entity);

            return new EventDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void copyEntity(EventDTO dto, Event entity) {

        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());

        if (dto.getCityId() != null) {
            City city = cityRepositories.getReferenceById(dto.getCityId());
            entity.setCity(city);
        }

    }
}
