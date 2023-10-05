package com.algoverse.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class to advice the Controller how to handle certain exceptions.
 */
@SuppressWarnings("unused")
@ControllerAdvice
public class ExceptionHandling {

  @ResponseBody
  @ExceptionHandler(WrongCoordinateInputException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String wrongCoordinateInput(WrongCoordinateInputException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(WrongWallInputException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String wrongWallInput(WrongWallInputException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(WrongInputOfBoardSizeAndPoints.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String wrongInputOfBoardAndPoints(WrongInputOfBoardSizeAndPoints ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(WrongInputOfBoardSizeAndWalls.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String wrongInputOfBoardAndWalls(WrongInputOfBoardSizeAndWalls ex) {
    return ex.getMessage();
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

}
