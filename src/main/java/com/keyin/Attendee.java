package com.keyin;

/**
 * Represents a person who can register for events.
 * Equality is based on email so the same person cannot register twice.
 */
public class Attendee {

  private final String name;
  private final String email;

  public Attendee(String name, String email) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Attendee name must not be blank");
    }
    if (email == null || !email.contains("@")) {
      throw new IllegalArgumentException("Attendee email must be a valid email address");
    }
    this.name = name.trim();
    this.email = email.trim().toLowerCase();
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other)
      return true;
    if (!(other instanceof Attendee))
      return false;
    Attendee attendee = (Attendee) other;
    return email.equals(attendee.email);
  }

  @Override
  public int hashCode() {
    return email.hashCode();
  }

  @Override
  public String toString() {
    return name + " <" + email + ">";
  }
}
