package com.keyin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationTest {

  @Test
  void testRegistration() {
    Event event = new Event("Tech Summit", 10);
    Attendee attendee = new Attendee("John Doe", "john@example.com");

    event.register(attendee);

    assertTrue(event.isRegistered(attendee));
    assertEquals(1, event.getRegisteredCount());
  }

  @Test
  void testRegistrationFull() {
    Event event = new Event("Tech Summit", 1);
    Attendee attendee1 = new Attendee("John Doe", "john@example.com");
    Attendee attendee2 = new Attendee("Jane Doe", "jane@example.com");

    event.register(attendee1);

    assertThrows(EventFullException.class, () -> event.register(attendee2));
  }
}
