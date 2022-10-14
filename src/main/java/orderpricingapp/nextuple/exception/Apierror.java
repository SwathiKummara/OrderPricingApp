package orderpricingapp.nextuple.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apierror {
    private  String message;
    private HttpStatus status;
   private LocalDate timeStamp;

}
