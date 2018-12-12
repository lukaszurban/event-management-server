package com.management.server.core.service;

import com.management.server.core.exception.UserNotFoundException;
import com.management.server.core.model.Event;
import com.management.server.core.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Łukasz on 29.11.2018
 */
@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<Event> getUsersEvents(Long id) {
        return eventRepository.findByUsers_Id(id);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteEvent(id);
    }

    @Transactional
    public Event updateEvent(Event event, Long id) {
        Event e = eventRepository.getOne(id);
        e.setName(event.getName());
        e.setDate(event.getDate());
        e.setDescription(event.getDescription());
        e.setLocation(event.getLocation());
        return e;
    }
}
