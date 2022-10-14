package orderpricingapp.nextuple.business;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ItemPriceRes {
    private String itemId;
    private LocalDate pricingDate;
    private Double unitPrice;
    private Double listPrice;
    private String pricelistName;
}
