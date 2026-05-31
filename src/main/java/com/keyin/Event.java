package com.keyin;

/**
 * Represents a single event with a fixed capacity.
 * Tracks the set of registered attendees and enforces capacity / duplicate
 * rules.
 */
public class Event {

  private final String name;
  private final int capacity;
  private final Attendee[] attendees;
  private int registeredCount;

  public Event(String name, int capacity) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Event name must not be blank");
    }
    if (capacity <= 0) {
      throw new IllegalArgumentException("Event capacity must be greater than zero");
    }
    this.name = name.trim();
    this.capacity = capacity;
    this.attendees = new Attendee[capacity];
    this.registeredCount = 0;
  }

  public String getName() {
    return name;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getRegisteredCount() {
    return registeredCount;
  }

  public int getAvailableSeats() {
    return capacity - registeredCount;
  }

  public boolean isFull() {
    return registeredCount >= capacity;
  }

  public Attendee[] getAttendees() {
    Attendee[] copy = new Attendee[registeredCount];
    for (int i = 0; i < registeredCount; i++) {
      copy[i] = attendees[i];
    }
    return copy;
  }

  /**
   * Registers an attendee for this event.
   *
   * @throws EventFullException             if the event is at capacity
   * @throws DuplicateRegistrationException if the attendee is already registered
   */
  public void register(Attendee attendee) {
    if (attendee == null) {
      throw new IllegalArgumentException("Attendee must not be null");
    }
    if (isRegistered(attendee)) {
      throw new DuplicateRegistrationException(
          "Attendee " + attendee.getEmail() + " is already registered for " + name);
    }
    if (isFull()) {
      throw new EventFullException("Event '" + name + "' is full");
    }
    attendees[registeredCount] = attendee;
    registeredCount++;
  }

  /**
   * Cancels an attendee's registration.
   *
   * @return true if the attendee was registered and has been removed; false
   *         otherwise.
   */
  public boolean cancelRegistration(Attendee attendee) {
    if (attendee == null) {
      return false;
    }
    for (int i = 0; i < registeredCount; i++) {
      if (attendees[i].equals(attendee)) {
        for (int j = i; j < registeredCount - 1; j++) {
          attendees[j] = attendees[j + 1];
        }
        attendees[registeredCount - 1] = null;
        registeredCount--;
        return true;
      }
    }
    return false;
  }

  public boolean isRegistered(Attendee attendee) {
    if (attendee == null) {
      return false;
    }
    for (int i = 0; i < registeredCount; i++) {
      if (attendees[i].equals(attendee)) {
        return true;
      }
    }
    return false;
  }
}
