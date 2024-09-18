package ai.shreds.adapter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
class AdapterExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdapterExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception exception) {
        logger.error("Exception caught in AdapterDocumentController: ", exception);
        return new ResponseEntity<>("Error occurred: " + (exception.getMessage() != null ? exception.getMessage() : "Unknown error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<String> handleNullPointerException(NullPointerException exception) {
        logger.error("NullPointerException caught: ", exception);
        return new ResponseEntity<>("A null value was encountered where it is not allowed.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        logger.error("IllegalArgumentException caught: ", exception);
        return new ResponseEntity<>("An illegal argument was provided: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}