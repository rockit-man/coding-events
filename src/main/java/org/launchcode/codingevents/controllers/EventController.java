package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
//        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    // lives at /events/create
    @PostMapping("create")
    public String createEventForm(@ModelAttribute Event newEvent) {
        EventData.addEvent(newEvent);
        return "redirect:";
    }

    // displays form
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.removeEvent(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event selectedEvent = EventData.getById(eventId);
        model.addAttribute("selectedEvent", selectedEvent);
        String title = "Edit Event" + selectedEvent.getName() + "(id" + selectedEvent.getId() + ")";
        model.addAttribute("title", title);
        return "events/edit";
    }

    @PostMapping("edit/{eventId}")
//    @RequestMapping(value = "eventsedit", method = {RequestMethod.GET, RequestMethod.POST})
    public String processEditForm(int eventId, String name, String description) {
        Event selectedEvent = EventData.getById(eventId);
        selectedEvent.setName(name);
        selectedEvent.setDescription(description);
        return "redirect:";
    }
}
