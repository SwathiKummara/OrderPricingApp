package orderpricingapp.nextuple.business;

import com.orderPricingApp.springboot.exception.PricelinelistException;
import orderpricingapp.nextuple.exception.ItemException;
import orderpricingapp.nextuple.model.Item;
import orderpricingapp.nextuple.model.PriceList;
import orderpricingapp.nextuple.model.PricelistLineList;
import orderpricingapp.nextuple.repository.PriceListRepository;
import orderpricingapp.nextuple.service.ItemService;
import orderpricingapp.nextuple.service.PriceListLineListService;
import orderpricingapp.nextuple.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

@Service
public class CurrentyearPrices {

    @Autowired
    ItemService itemService;

    @Autowired
    PriceListService priceListService;

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    PriceListLineListService priceListLineListService;

    public Prices getCurrentYearprices(@RequestParam String organizationCode, @RequestParam String itemId) throws ItemException, com.orderPricingApp.springboot.exception.PricelinelistException, com.orderPricingApp.springboot.exception.PricelistException {
        Calendar calendar = Calendar.getInstance();
       Prices prices = new Prices();
//       Pricesres pricesres = new Pricesres();
//        Pricesres pricesres = null;

        Optional<Item> itemIdcheck = itemService.getbyitemId(itemId);
        ArrayList priceslist = new ArrayList();


        if (itemIdcheck.isPresent()) {
            prices.setItemid(itemIdcheck.get().getItemId());
            prices.setItemDescription(itemIdcheck.get().getItemDescription());
            String itemKey = itemIdcheck.get().getItemKey();

            List<PricelistLineList> pricelistLineList = (priceListLineListService.getByitemKey(itemKey));
            for (int i = 0; i < pricelistLineList.size(); i++) {
                Optional<PricelistLineList> pricelistLineList1 = Optional.ofNullable(pricelistLineList.get(i));
                Pricesres pricesres = new Pricesres();

                if (itemKey.equals(pricelistLineList1.get().getItemkey())) {
                    Optional<PriceList> priceList = Optional.ofNullable(priceListService.getByKey(pricelistLineList1.get().getPricelistkey()));
                    calendar.setTime(priceList.get().getStartDate());
                    Year startyear = Year.of(calendar.get(Calendar.YEAR));
                    calendar.setTime(priceList.get().getEndDate());
                    Year endyear = Year.of(calendar.get(Calendar.YEAR));

                    if (Year.now().equals(startyear) && Year.now().equals(endyear)) {

                        pricesres.setFromdate(priceList.get().getStartDate());
                        pricesres.setTodate(priceList.get().getEndDate());
                        pricesres.setPricelistName(priceList.get().getPriceListName());
                        pricesres.setListPrice(pricelistLineList1.get().getListPrice());
                        pricesres.setUnitPrice(pricelistLineList1.get().getUnitPrice());
                        priceslist.add(pricesres);
                        prices.setPrices(priceslist);



                    }
                    if (!Year.now().equals(startyear) || !Year.now().equals(endyear)){
                        throw new PricelinelistException("itemId "+itemId+" do not have any prices in the currenyear with organization code"+ organizationCode);
                    }
                }

            }
        }
        if (itemIdcheck.isEmpty()) {
            throw new ItemException("item id does not exists with id " + itemId);
        }
        Optional<List<Item>> itemOrgCheck = itemService.getbyOrganizationcode(organizationCode);
        if (itemOrgCheck.isEmpty()) {
            throw new ItemException("item did not found with orgaization code " + organizationCode);
        }

        Optional<Item> itemidAndOrgCheck = itemService.getbycodeandid(organizationCode, itemId);
        if (itemidAndOrgCheck.isEmpty()) {
            throw new ItemException("itemid " + itemId + " with organizationcode " + organizationCode + " doesn't match.");

        }

        return prices;
    }
}
