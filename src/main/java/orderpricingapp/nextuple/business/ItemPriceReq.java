package orderpricingapp.nextuple.business;


import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ItemPriceReq {

    private String itemId;

    private String organizationCode;
    private LocalDate currentdate;


}
