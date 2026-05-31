package com.keyin;

/**
 * Coordinates events and registrations.
 * Acts as a small in-memory service layer over {@link Event}.
 */
public class Registration {

  private Event[] events = new Event[10];
  private int eventCount;

  /**
   * Creates and stores a new event.
   *
   * @throws IllegalStateException if an event with the same name already exists.
   */
  public Event createEvent(String name, int capacity) {
    Event event = new Event(name, capacity);
    if (findEventIndex(event.getName()) >= 0) {
      throw new IllegalStateException("Event '" + event.getName() + "' already exists");
    }
    if (eventCount == events.length) {
      Event[] bigger = new Event[events.length * 2];
      for (int i = 0; i < eventCount; i++) {
        bigger[i] = events[i];
      }
      events = bigger;
    }
    events[eventCount] = event;
    eventCount++;
    return event;
  }

  public Event getEvent(String name) {
    String lookupName = name == null ? null : name.trim();
    int index = findEventIndex(lookupName);
    if (index < 0) {
      throw new IllegalArgumentException("No event found with name: " + name);
    }
    return events[index];
  }

  public Event[] getEvents() {
    Event[] copy = new Event[eventCount];
    for (int i = 0; i < eventCount; i++) {
      copy[i] = events[i];
    }
    return copy;
  }

  public void registerAttendee(String eventName, Attendee attendee) {
    getEvent(eventName).register(attendee);
  }

  public boolean cancelRegistration(String eventName, Attendee attendee) {
    return getEvent(eventName).cancelRegistration(attendee);
  }

  private int findEventIndex(String eventName) {
    if (eventName == null) {
      return -1;
    }
    for (int i = 0; i < eventCount; i++) {
      if (events[i].getName().equals(eventName)) {
        return i;
      }
    }
    return -1;
  }

}