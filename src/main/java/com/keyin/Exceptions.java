package com.keyin;

/**
 * Custom runtime exceptions used by the event booking domain.
 */
class DuplicateRegistrationException extends RuntimeException {
  DuplicateRegistrationException(String message) {
    super(message);
  }
}

/**
 * Thrown when an attendee tries to register for an event that is already full.
 */
class EventFullException extends RuntimeException {
  EventFullException(String message) {
    super(message);
  }
}