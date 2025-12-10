package com.songlify.advices;

import com.songlify.exceptions.AlbumAlreadyExistsException;
import com.songlify.exceptions.SingerAlreadyExistsException;
import com.songlify.exceptions.SingerNotFoundException;
import com.songlify.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // PSA some of these have been built with the help of GPT so, sorry.
    /* ------------------ Helper method (avoid repetition) ------------------ */
    private ResponseEntity<ErrorResponse> buildError(
            HttpStatus status,
            String message,
            WebRequest req
    ) {
        ErrorResponse res = new ErrorResponse();
        res.setTimestamp(new Date());
        res.setMessage(message);
        res.setStatusCode(status.value());
        res.setPath(req.getDescription(false));
        return new ResponseEntity<>(res, status);
    }


    /* ------------------ Custom Exceptions ------------------ */

    @ExceptionHandler(SingerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSingerNotFound(
            SingerNotFoundException ex, WebRequest req
    ) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler(SingerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleSingerAlreadyExists(
            SingerAlreadyExistsException ex, WebRequest req
    ) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), req);
    }

    @ExceptionHandler(AlbumAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlbumAlreadyExists(AlbumAlreadyExistsException ex, WebRequest req) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), req);
    }


    /* ------------------ Standard / Common Exceptions ------------------ */

    // Bad JSON body, unreadable request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadJson(
            HttpMessageNotReadableException ex, WebRequest req
    ) {
        return buildError(HttpStatus.BAD_REQUEST, "Malformed JSON request", req);
    }

    // @Valid validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest req
    ) {
        String msg = ex.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .orElse("Validation error");

        return buildError(HttpStatus.BAD_REQUEST, msg, req);
    }

    // @ModelAttribute validation errors
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindErrors(
            BindException ex, WebRequest req
    ) {
        String msg = ex.getFieldErrors()
                .stream()
                .findFirst()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .orElse("Binding error");

        return buildError(HttpStatus.BAD_REQUEST, msg, req);
    }

    // Missing required query parameter
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(
            MissingServletRequestParameterException ex, WebRequest req
    ) {
        return buildError(
                HttpStatus.BAD_REQUEST,
                "Missing request parameter: " + ex.getParameterName(),
                req
        );
    }

    // IllegalArgumentException → your logic errors
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest req
    ) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }


    /* ------------------ Fallback (generic) ------------------ */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest req
    ) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", req);
    }
}
