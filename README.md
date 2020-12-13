# Introduction

When maintaining a calendar of events, it is important to know if an event overlaps with another event. This application helps you finding the overlapping events from the given set of calender events.

## Prerequisites

This project requires the following prerequisites to be installed on the system.
* Java 8

## Building the application
  * Mac -  ```./gradlew build```
  * Windows - ```./gradlew build```

## Running the application

Input can be provided in two ways:
  1. Update `src\main\resources\events.json` file and then use ```./gradlew run``` to execute the program
  2. Add file in `src\main\resources` folder and provide file name using command line argument ```gradlew run --args="FILE_NAME"```

**Note: The event start and end time should be provided in `yyyy-MM-dd HH:mm:ss` format.**
