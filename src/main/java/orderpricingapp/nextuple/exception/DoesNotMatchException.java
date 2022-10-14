package orderpricingapp.nextuple.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)

public class DoesNotMatchException extends Exception {
    public DoesNotMatchException() {super();}

    public DoesNotMatchException(String exception)
    {
        super(exception);

    }
}
