package orderpricingapp.nextuple.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class NotFoundException extends  Exception{


    public NotFoundException() {super();}

    public NotFoundException(String exception)
    {
        super(exception);

    }
}
