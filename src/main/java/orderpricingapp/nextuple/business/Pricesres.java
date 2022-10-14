package orderpricingapp.nextuple.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pricesres {

    private LocalDate fromdate;
    private LocalDate todate;
    private Double unitPrice;
    private Double listPrice;
    private String pricelistName;



    }
