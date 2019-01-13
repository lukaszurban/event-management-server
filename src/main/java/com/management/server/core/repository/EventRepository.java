package com.management.server.core.repository;

import com.management.server.core.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz on 28.11.2018
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrderById();

    Optional<Event> findById(Long personId);

    List<Event> findByUsers_Id(Long personId);

    @Query("delete from event e where e.id=:id")
    void deleteEvent(@Param("id") Long id);
}
