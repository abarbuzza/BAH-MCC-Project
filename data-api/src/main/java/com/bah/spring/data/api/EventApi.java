package com.bah.spring.data.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bah.spring.data.model.Event;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class EventApi {
    ArrayList<Event> eventList = new ArrayList<Event>();

    public EventApi() {
        Event e1 = new Event(1, "EVNT01", "BAH/WebAge Pizza Party", "Booz Allen and WebAge are both great, so they both get pizza.");
        Event e2 = new Event(2, "EVNT02", "React Training", "Time to learn React.js");
        Event e3 = new Event(3, "EVNT03", "WebGL/Three.js Training", "Neat 3D computer graphics training");
        Event e4 = new Event(4, "EVNT04", "Another Pizza Party", "This is the only kind of office party I can think of at the moment");

        eventList.add(e1);
        eventList.add(e2);
        eventList.add(e3);
        eventList.add(e4);

    }

    @GetMapping("/allevents")
    public Collection<Event> getAll() {
        return this.eventList;
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable("eventId") long id) {
        Event event = null;
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getId() == id) {
                event = eventList.get(i);
            }
        }
        return event;
    }

}