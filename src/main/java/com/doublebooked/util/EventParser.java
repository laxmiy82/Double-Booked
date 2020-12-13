package com.doublebooked.util;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.doublebooked.model.Event;

public class EventParser {

  private static final Logger LOGGER = Logger.getLogger(EventParser.class.getName());

  private EventParser() {
  }

  public static List<Event> parseEvents(String fileName) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      InputStream eventStream = EventParser.class.getClassLoader().getResourceAsStream(fileName);

      if (eventStream == null) {
        LOGGER.log(Level.SEVERE, "{0} file not found", fileName);
        return null;
      } else {
        List<Event> eventList = objectMapper.readValue(eventStream, new TypeReference<List<Event>>() {
        });

        eventStream.close();
        return eventList;
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error occured while parsing the file: {0}", e.getMessage());
      return null;
    }
  }
}