package com.algoverse.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class to advice the Controller how to handle certain exceptions.
 */
@SuppressWarnings("unused")
@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

  /**
   * Method to create the error message.
   *
   * @param ex The exception that is thrown
   * @return Return the ResponseEntity
   */
  @ResponseBody
  @ExceptionHandler(WrongCoordinateInputException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> wrongCoordinateInput(WrongCoordinateInputException ex) {
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp", LocalDateTime.now());
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  /**
   * Method to create the error message.
   *
   * @param ex The exception that is thrown
   * @return Return the ResponseEntity
   */
  @ResponseBody
  @ExceptionHandler(WrongWallInputException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> wrongWallInput(WrongWallInputException ex) {
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp", LocalDateTime.now());
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  /**
   * Method to create the error message.
   *
   * @param ex The exception that is thrown
   * @return Return the ResponseEntity
   */
  @ResponseBody
  @ExceptionHandler(WrongInputOfBoardSizeAndPoints.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> wrongInputOfBoardAndPoints(WrongInputOfBoardSizeAndPoints ex) {
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp", LocalDateTime.now());
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  /**
   * Method to create the error message.
   *
   * @param ex The exception that is thrown
   * @return Return the ResponseEntity
   */
  @ResponseBody
  @ExceptionHandler(WrongInputOfBoardSizeAndWalls.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> wrongInputOfBoardAndWalls(WrongInputOfBoardSizeAndWalls ex) {
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp", LocalDateTime.now());
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  /**
   * Method to create the error message.
   *
   * @param ex The exception that is thrown
   * @return Return the ResponseEntity
   */
  @ResponseBody
  @ExceptionHandler(IllegalBoundForBoardSize.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> wrongInputOfBoardSize(IllegalBoundForBoardSize ex) {
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp", LocalDateTime.now());
    map.put("message", ex.getMessage());
    map.put("status", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
  }

  /**
   * Exception for handling the wrong input for coordinates.
   */
  public static class WrongCoordinateInputException extends IllegalArgumentException {
    public WrongCoordinateInputException() {
      super("The input array for the coordinates should only contain two integers.");
    }
  }

  /**
   * Exception for handling the wrong input for wall coordinates.
   */
  public static class WrongWallInputException extends IllegalArgumentException {
    /**
     * The constructor.
     */
    public WrongWallInputException() {
      super(
          "The input of the 2d array for the wall should only "
              + "contain two integers in the nested array.");
    }
  }

  /**
   * Exception for handling wrong input in terms of starting or ending node is not set on the board.
   */
  public static class WrongInputOfBoardSizeAndPoints extends IllegalArgumentException {
    /**
     * The constructor.
     */
    public WrongInputOfBoardSizeAndPoints() {
      super("The starting/end points are not in the field.");
    }
  }

  /**
   * Exception for handling wrong input in terms of coordinates
   * of the wall are not set on the board.
   */
  public static class WrongInputOfBoardSizeAndWalls extends IllegalArgumentException {
    /**
     * The constructor.
     */
    public WrongInputOfBoardSizeAndWalls() {
      super("Some coordinates of the wall are not in the field.");
    }
  }

  /**
   * Exception for handling wrong input for the board size.
   */
  public static class IllegalBoundForBoardSize extends IllegalArgumentException {
    /**
     * The constructor.
     */
    public IllegalBoundForBoardSize() {
      super("Size of the board must be at least 1x2 or 2x1.");
    }
  }
}
