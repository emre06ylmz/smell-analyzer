package com.eylmz.master.sonar.client.service;

import com.eylmz.master.sonar.client.dto.github.Event;

public interface IEventService {

    void addEvent(Event event);

}
