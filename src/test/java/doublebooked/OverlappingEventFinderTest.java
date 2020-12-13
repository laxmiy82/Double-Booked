package doublebooked;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.doublebooked.OverlappingEventFinder;
import com.doublebooked.model.Event;
import com.doublebooked.util.EventParser;

public class OverlappingEventFinderTest {

  @Test
  public void testForOverlappingEvents() {
    List<Event> eventList = EventParser.parseEvents("overlappingEvents.json");
    List<List<Event>> actualValue = OverlappingEventFinder.findOverlappingEvents(eventList);

    assertEquals(5, actualValue.size());
  }
}
