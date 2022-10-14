package orderpricingapp.nextuple.controller;



import orderpricingapp.nextuple.exception.AlreadyExistsException;
import orderpricingapp.nextuple.exception.DoesNotMatchException;
import orderpricingapp.nextuple.exception.NotFoundException;
import orderpricingapp.nextuple.model.PricelistLineList;
import orderpricingapp.nextuple.service.PriceListLineListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pricelistlinelist")
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
    public PricelistLineList savePriceList(@RequestBody PricelistLineList pricelistLineList) throws NotFoundException, DoesNotMatchException, AlreadyExistsException {
        return  priceListLineListService.add(pricelistLineList);

    }

    @GetMapping("{pricelistlinekey}")
    public PricelistLineList getlist(@PathVariable ("pricelistlinekey") String key) throws NotFoundException, AlreadyExistsException {
        return priceListLineListService.getByKey(key);
    }

    @PutMapping("{pricelistlinekey}")
    public PricelistLineList updatelist(@RequestBody PricelistLineList pricelistLineList, @PathVariable ("pricelistlinekey") String key) throws  NotFoundException {
        return priceListLineListService.update(key,pricelistLineList);
    }

    @PatchMapping("{pricelistlinekey}")
    public PricelistLineList patch(@RequestBody PricelistLineList p,@PathVariable("pricelistlinekey") String key) throws NotFoundException {
        return priceListLineListService.patch(key,p);

    }
    @DeleteMapping("{pricelistlinekey}")
    public String delete(@PathVariable("pricelistlinekey") String key) throws NotFoundException {

        return priceListLineListService.delete(key);
    }

}
