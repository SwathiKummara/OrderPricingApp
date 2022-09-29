package orderpricingapp.nextuple.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(ItemException.class)
    public ResponseEntity<Object> handleNotFoundException(ItemException itemException, WebRequest webRequest){

    return new ResponseEntity<>(new Apierror(itemException.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()),HttpStatus.NOT_FOUND);
}
    @ExceptionHandler(com.orderPricingApp.springboot.exception.PricelistException.class)
    public ResponseEntity<Object> handleNotFoundException(com.orderPricingApp.springboot.exception.PricelistException pricelistException, WebRequest webRequest) {

        return new ResponseEntity<>(new Apierror(pricelistException.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(com.orderPricingApp.springboot.exception.PricelinelistException.class)
    public ResponseEntity<Object> handleNotFound(com.orderPricingApp.springboot.exception.PricelinelistException pricelinelistException, WebRequest webRequest) {

        return new ResponseEntity<>(new Apierror(pricelinelistException.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()), HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new Apierror(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()),HttpStatus.NOT_FOUND);

    }
}
