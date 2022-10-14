package orderpricingapp.nextuple.business;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
@Data
public class Prices {
    private String itemid;
    private String itemDescription;
    public  List<Pricesres> prices;

    }
