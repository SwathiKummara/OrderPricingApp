package orderpricingapp.nextuple.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pricesres {

    private Date fromdate;
    private Date todate;
    private Double unitPrice;
    private Double listPrice;
    private String pricelistName;



    }
