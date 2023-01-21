package com.kapsi.exceptions;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    /* --------------------------------------   Login Exception    ----------------------------------------------*/
    @ExceptionHandler(LogInException.class)
    public ResponseEntity<ErrorDetails> logInException(LogInException loginException, WebRequest request){

        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), loginException.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }


    /*--------------------------------------------  Admin Exception  --------------------------------------------------*/
    @ExceptionHandler(AdminException.class)
    public ResponseEntity<ErrorDetails> AdminException(AdminException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Cab Exception  --------------------------------------------------*/
    @ExceptionHandler(CabException.class)
    public ResponseEntity<ErrorDetails> CabException(CabException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Customer Exception  --------------------------------------------------*/
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDetails> CustomerException(CustomerException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Driver Exception  --------------------------------------------------*/
    @ExceptionHandler(DriverException.class)
    public ResponseEntity<ErrorDetails> DriverException(DriverException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  TripBooking Exception  --------------------------------------------------*/
    @ExceptionHandler(TripBookingException.class)
    public ResponseEntity<ErrorDetails> TripBookingException(TripBookingException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Exception  --------------------------------------------------*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> Exception(Exception exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Illegal Argument Exception  --------------------------------------------------*/
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> IllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Null Pointer Exception  --------------------------------------------------*/
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetails> NullPointerException(NullPointerException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Json Parse Exception  --------------------------------------------------*/
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorDetails> BeanCreationException(JsonParseException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Validation Exception  --------------------------------------------------*/
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetails> BeanCreationException(ValidationException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }

}
