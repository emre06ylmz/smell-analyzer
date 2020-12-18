package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.Event;
import com.eylmz.master.sonar.client.repository.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService implements IEventService{

    @Autowired
    private IEventRepository eventRepository;

    @Override
    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}
