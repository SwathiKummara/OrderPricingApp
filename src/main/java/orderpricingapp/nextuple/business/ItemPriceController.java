package orderpricingapp.nextuple.business;

import orderpricingapp.nextuple.exception.ItemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")

public class ItemPriceController {
    @Autowired
    ItemPriceService itemPriceService;

    @Autowired
    CurrentyearPrices currentyearPrices;

@GetMapping("/itemprice")
    public ItemPriceRes getItemPrice(@RequestBody ItemPriceReq itemPriceReq) throws ItemException, com.orderPricingApp.springboot.exception.PricelistException, com.orderPricingApp.springboot.exception.PricelinelistException {
    return  itemPriceService.getItemPrice(itemPriceReq);

}

@GetMapping("/currentyearprices/{organizationcode}/{itemId}")
    public Prices getItemlist(@PathVariable String organizationcode, @PathVariable String itemId, Prices prices, Pricesres pricesres) throws ItemException, com.orderPricingApp.springboot.exception.PricelinelistException, com.orderPricingApp.springboot.exception.PricelistException {
    return currentyearPrices.getCurrentYearprices(organizationcode,itemId);
}

}
