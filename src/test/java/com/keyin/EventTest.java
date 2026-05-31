package com.keyin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

  private Event event;
  private Attendee alice;
  private Attendee bob;

  @BeforeEach
  void setUp() {
    event = new Event("Java Conf", 2);
    alice = new Attendee("Alice", "alice@example.com");
    bob = new Attendee("Bob", "bob@example.com");
  }

  @Test
  @DisplayName("Registering an attendee increases the registered count")
  void registerAddsAttendee() {
    event.register(alice);
    assertEquals(1, event.getRegisteredCount());
    assertTrue(event.isRegistered(alice));
    assertEquals(1, event.getAvailableSeats());
  }

  @Test
  @DisplayName("Event becomes full when capacity is reached")
  void eventBecomesFull() {
    event.register(alice);
    event.register(bob);
    assertTrue(event.isFull());
    assertEquals(0, event.getAvailableSeats());
  }

  @Test
  @DisplayName("Registering past capacity throws EventFullException")
  void registeringPastCapacityThrows() {
    event.register(alice);
    event.register(bob);
    Attendee carol = new Attendee("Carol", "carol@example.com");

    assertThrows(EventFullException.class, () -> event.register(carol));
  }

  @Test
  @DisplayName("Duplicate registration is rejected")
  void duplicateRegistrationIsRejected() {
    event.register(alice);
    Attendee aliceAgain = new Attendee("Alice Smith", "ALICE@example.com");

    assertThrows(DuplicateRegistrationException.class,
        () -> event.register(aliceAgain));
    assertEquals(1, event.getRegisteredCount());
  }

  @Test
  @DisplayName("Cancelling a registered attendee frees a seat")
  void cancelRegistrationFreesSeat() {
    event.register(alice);
    event.register(bob);

    boolean removed = event.cancelRegistration(alice);

    assertTrue(removed);
    assertEquals(1, event.getRegisteredCount());
    assertFalse(event.isRegistered(alice));
    assertFalse(event.isFull());
  }

  @Test
  @DisplayName("Cancelling an attendee that was never registered returns false")
  void cancelNonRegisteredReturnsFalse() {
    event.register(alice);
    assertFalse(event.cancelRegistration(bob));
    assertEquals(1, event.getRegisteredCount());
  }

  @Test
  @DisplayName("Invalid event data is rejected")
  void invalidEventDataIsRejected() {
    assertThrows(IllegalArgumentException.class, () -> new Event(" ", 1));
    assertThrows(IllegalArgumentException.class, () -> new Event("X", 0));
    assertThrows(IllegalArgumentException.class, () -> new Event("X", -5));
  }
}
