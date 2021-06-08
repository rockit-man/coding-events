package org.launchcode.codingevents.models;


import javax.validation.constraints.*;
import java.util.Objects;

public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank ( message = "Name is required.")
    @Size (min = 3, max = 50, message = "Name must be between 3 and 50 characters long.")
    private String name;

    @Size (max = 500, message = "Description too long!")
    private String description;

    @NotBlank ( message = "Email is required.")
    @Email ( message = "Invalid email. Please try again.")
    private String contactEmail;

    @NotBlank ( message = "Location field cannot be left blank.")
    private String location;

    @AssertTrue ( message = "Registration must be required at this time.")
    private boolean registrationRequired;

    @Positive (message = "Number of attendees must be one or more.")
    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    private int numberOfAttendees;

    private EventType type;

    public Event(String name, String description, String contactEmail, EventType type, String location, int numberOfAttendees) {
        this();
        this.type = type;
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.numberOfAttendees = getNumberOfAttendees();
        this.registrationRequired = registrationRequired;
    }

    public Event() {
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() { return contactEmail; }

    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }

    public int getId() {
        return id;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public boolean isRegistrationRequired() { return registrationRequired; }

    public void setRegistrationRequired(boolean registrationRequired) { this.registrationRequired = registrationRequired; }

    public EventType getType() { return type; }

    public void setType(EventType type) { this.type = type; }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return getId() == event.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
