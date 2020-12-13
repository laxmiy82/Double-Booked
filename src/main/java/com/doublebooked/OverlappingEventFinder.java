package com.doublebooked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.doublebooked.model.Event;
import com.doublebooked.util.EventParser;

public class OverlappingEventFinder {
  private static final Logger LOGGER = Logger.getLogger(OverlappingEventFinder.class.getName());

  public static void main(String[] args) {

    String fileName = (args.length > 0) ? args[0] : "events.json";
    List<Event> eventList = EventParser.parseEvents(fileName);

    if (eventList == null || eventList.isEmpty()) {
      LOGGER.log(Level.SEVERE, "Invalid or empty event list provided");
    } else {
      List<List<Event>> overlappingEventPairs = findOverlappingEvents(eventList);

      if (overlappingEventPairs.isEmpty()) {
        LOGGER.log(Level.INFO, "None of the events are overlapping");
      } else {
        printOverlappingEvents(overlappingEventPairs);
      }
    }
  }

  public static List<List<Event>> findOverlappingEvents(List<Event> eventList) {
    try {
      int eventCount = eventList.size();
      List<List<Event>> overlappingEventPairs = new ArrayList<>();
      Event currentEvent, nextEvent;

      for (int i = 0; i < eventCount - 1; i++) {
        currentEvent = eventList.get(i);

        for (int j = i + 1; j < eventCount; j++) {
          nextEvent = eventList.get(j);
          Boolean startsBeforeNextEnds = currentEvent.getStartTime().before(nextEvent.getEndTime());
          Boolean endsBeforeNextStarts = currentEvent.getEndTime().after(nextEvent.getStartTime());

          if (startsBeforeNextEnds && endsBeforeNextStarts) {
            overlappingEventPairs.add(new ArrayList<>(Arrays.asList(currentEvent, nextEvent)));
          }
        }
      }

      return overlappingEventPairs;
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Event cannot have null start or end time");
    }
  }

  private static void printOverlappingEvents(List<List<Event>> overlappingEventPairs) {
    int numberOfPairs = overlappingEventPairs.size();
    List<Event> overlappingPair;

    for (int i = 0; i < numberOfPairs; i++) {
      overlappingPair = overlappingEventPairs.get(i);
      System.out.printf("Event %s overlaps with %s\n", overlappingPair.get(0), overlappingPair.get(1));
    }
  }
}
