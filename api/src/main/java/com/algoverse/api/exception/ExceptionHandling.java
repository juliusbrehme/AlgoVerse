package com.algoverse.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

  public static class WrongCoordinateInputException extends IllegalArgumentException {
    public WrongCoordinateInputException() {
      super("The input array for the coordinates should only contain two integers.");
    }
  }

  public static class WrongWallInputException extends IllegalArgumentException {
    public WrongWallInputException() {
      super("The input of the 2d array for the wall should only contain two integers in the nested array.");
    }
  }

}
