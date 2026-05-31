import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.keyin.Attendee;

class AttendeeTest {
  @Test
  void testAttendeeCreation() {
    Attendee attendee = new Attendee("Johnny Lightning", "johnny@example.com");
    assertEquals("Johnny Lightning", attendee.getName());
    assertEquals("johnny@example.com", attendee.getEmail());

  }
}
