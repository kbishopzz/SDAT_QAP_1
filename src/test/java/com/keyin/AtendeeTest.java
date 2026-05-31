package com.keyin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttendeeTest {

  @Test
  @DisplayName("Attendee normalizes email to lowercase and trims whitespace")
  void attendeeNormalizesEmail() {
    Attendee attendee = new Attendee("Alice", "  Alice@Example.COM ");
    assertEquals("alice@example.com", attendee.getEmail());
    assertEquals("Alice", attendee.getName());
  }

  @Test
  @DisplayName("Invalid email is rejected")
  void invalidEmailIsRejected() {
    assertThrows(IllegalArgumentException.class,
        () -> new Attendee("Alice", "not-an-email"));
  }
}
