package com.bah.spring.Customer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bah.spring.domain.EventObj;

@RestController
@RequestMapping("/allevents")
public class EventAPI {
    ArrayList<EventObj> eventList = new ArrayList<EventObj>();

    public EventAPI() {
        EventObj e1 = new EventObj(1, "EVNT01", "BAH/WebAge Pizza Party", "Booz Allen and WebAge are both great, so they both get pizza.");
        EventObj e2 = new EventObj(2, "EVNT02", "React Training", "Time to learn React.js");
        EventObj e3 = new EventObj(3, "EVNT03", "WebGL/Three.js Training", "Neat 3D computer graphics training");
        EventObj e4 = new EventObj(4, "EVNT04", "Another Pizza Party", "This is the only kind of office party I can think of at the moment");

        eventList.add(e1);
        eventList.add(e2);
        eventList.add(e3);
        eventList.add(e4);

    }

    @GetMapping
    public Collection<EventObj> getAll() {
        return this.eventList;
    }

    @GetMapping("/{eventId}")
    public EventObj getEventById(@PathVariable("customerId") long id) {
        EventObj event = null;
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getId() == id) {
                event = eventList.get(i);
            }
        }
        return event;
    }

}