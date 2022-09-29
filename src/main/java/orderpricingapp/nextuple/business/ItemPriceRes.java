package orderpricingapp.nextuple.business;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemPriceRes {
    private String itemId;
    private Date pricingDate;
    private Double unitPrice;
    private Double listPrice;
    private String pricelistName;
}
