package com.management.server.web.controller;

import com.management.server.core.model.Event;
import com.management.server.core.service.EventService;
import com.management.server.web.dto.EventDto;
import com.management.server.web.dto.mapper.EventMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Łukasz on 03.12.2018
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/events")
public class EventController extends AbstractController {

    private final EventService eventService;
    private final EventMapper mapper;

    @GetMapping
    public ResponseEntity getEvents() {
        final Set<EventDto> events = mapper.mapToDtoSet(new HashSet<>(eventService.getEvents()));
        return new ResponseEntity(events, HttpStatus.OK);
    }

    @GetMapping(value = "{eventId}")
    public ResponseEntity getEvent(@PathVariable Long eventId) {
        EventDto event = mapper.mapToDto(eventService.getEventById(eventId));
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto) {
        Event event = eventService.addEvent(mapper.mapToEntity(eventDto));
        return ResponseEntity.created(getLocation(event.getId())).build();
    }

    @PutMapping("{eventId}")
    public ResponseEntity updateEvent(@PathVariable Long eventId, @RequestBody EventDto eventDto) {
        Event event = eventService.updateEvent(mapper.mapToEntity(eventDto), eventId);
        return ResponseEntity.created(getLocation(event.getId())).build();
    }
}
