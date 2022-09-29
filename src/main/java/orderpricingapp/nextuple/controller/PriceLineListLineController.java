package orderpricingapp.nextuple.controller;



import orderpricingapp.nextuple.model.PricelistLineList;
import orderpricingapp.nextuple.service.PriceListLineListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pricelistline")
public  class PriceLineListLineController {
    @Autowired
    PriceListLineListService priceListLineListService;

    @GetMapping
    public List<PricelistLineList> getAlllist(int no,int size){

        return priceListLineListService.getAllListsWithPagination(no,size);

    }

    @GetMapping("getAllWithPagination")
    public List<PricelistLineList> getAllListWithPagination(@RequestParam int pageNo , @RequestParam int pageSize){
       List<PricelistLineList > p = priceListLineListService.getAllListsWithPagination(pageNo,pageSize);
        return p;
    }

    @PostMapping
    public PricelistLineList savePriceList(@RequestBody PricelistLineList pricelistLineList) throws com.orderPricingApp.springboot.exception.PricelinelistException {
        return  priceListLineListService.add(pricelistLineList);

    }

    @GetMapping("{pricelistlinekey}")
    public PricelistLineList getlist(@PathVariable ("pricelistlinekey") String key) throws com.orderPricingApp.springboot.exception.PricelinelistException {
        return priceListLineListService.getByKey(key);
    }

    @PutMapping("{pricelistlinekey}")
    public PricelistLineList updatelist(@RequestBody PricelistLineList pricelistLineList, @PathVariable ("pricelistlinekey") String key) throws com.orderPricingApp.springboot.exception.PricelinelistException {
        return priceListLineListService.update(key,pricelistLineList);
    }

    @PatchMapping("{pricelistlinekey}")
    public PricelistLineList patch(@RequestBody PricelistLineList p,@PathVariable("pricelistlinekey") String key) throws com.orderPricingApp.springboot.exception.PricelinelistException {
        return priceListLineListService.patch(key,p);

    }
    @DeleteMapping("{pricelistlinekey}")
    public String delete(@PathVariable("pricelistlinekey") String key) throws com.orderPricingApp.springboot.exception.PricelinelistException {

        return priceListLineListService.delete(key);
    }

}
