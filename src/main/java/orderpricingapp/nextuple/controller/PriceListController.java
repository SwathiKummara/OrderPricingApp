package orderpricingapp.nextuple.controller;

import orderpricingapp.nextuple.exception.AlreadyExistsException;
import orderpricingapp.nextuple.exception.DoesNotMatchException;
import orderpricingapp.nextuple.exception.NotFoundException;
import orderpricingapp.nextuple.model.PriceList;
import orderpricingapp.nextuple.repository.PriceListRepository;
import orderpricingapp.nextuple.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/pricelist")
public class PriceListController {

    @Autowired
    PriceListService priceListService;
    PriceListRepository priceListRepository;


    @GetMapping
    public List<PriceList> getAll ()
    {
        return priceListService.getAll();
    }

    @GetMapping("{pricelistkey}")
    public PriceList getByName(@PathVariable("pricelistkey") String key) throws  DoesNotMatchException {
        return priceListService.getByKey(key);
    }
//    @GetMapping("get/{organizationcode}/{name}")
//    public List<PriceList> getBycodeAndname(@PathVariable ("organizationcode") String code, @PathVariable("name") String name) {
//        return priceListService.getByNameandCode(code,name);
//    }
//
//    @GetMapping("get/{active}/{organizationcode}")
//    public List<PriceList> getBycodeAndstatus(@PathVariable ("active") String status, @PathVariable("organizationcode") String code) {
//        return priceListService.getBystatusandCode(status,code);
//    }
//
    @PostMapping
    private String savePriceList(@RequestBody PriceList priceList) throws AlreadyExistsException {
        return  priceListService.saveList(priceList);

    }


    @PutMapping("/{pricelistkey}")
    public PriceList updatelist(@RequestBody PriceList priceList, @PathVariable ("pricelistkey") String key) throws NotFoundException {
       return priceListService.update(key,priceList);

    }

    @PatchMapping("/{pricelistkey}")
    public PriceList patch(@RequestBody PriceList p,@PathVariable("pricelistkey") String key) throws NotFoundException {
        return priceListService.patch(key,p);
    }


//    @DeleteMapping("/{pricelistkey}")
//    public String deletePricelistByName(@PathVariable("pricelistkey") String key) throws Exception {
//
//        return priceListService.delete(key);
//    }

}
