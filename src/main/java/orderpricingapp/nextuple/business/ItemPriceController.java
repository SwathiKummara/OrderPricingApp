package orderpricingapp.nextuple.business;

import orderpricingapp.nextuple.exception.AlreadyExistsException;
import orderpricingapp.nextuple.exception.DoesNotMatchException;
import orderpricingapp.nextuple.exception.NotFoundException;
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
    public ItemPriceRes getItemPrice(@RequestBody ItemPriceReq itemPriceReq) throws NotFoundException, Exception{
    return  itemPriceService.getItemPrice(itemPriceReq);

}

@GetMapping("/currentyearprices/{organizationcode}/{itemId}")
    public Prices getItemlist(@PathVariable String organizationcode, @PathVariable String itemId, Prices prices, Pricesres pricesres) throws NotFoundException,  DoesNotMatchException, AlreadyExistsException {
    return currentyearPrices.getCurrentYearprices(organizationcode,itemId);
}

}
