package orderpricingapp.nextuple.business;

import orderpricingapp.nextuple.exception.AlreadyExistsException;
import orderpricingapp.nextuple.exception.DoesNotMatchException;
import orderpricingapp.nextuple.exception.NotFoundException;
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

    public Prices getCurrentYearprices(@RequestParam String organizationCode, @RequestParam String itemId) throws NotFoundException,  DoesNotMatchException, AlreadyExistsException {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        Prices prices = new Prices();


        Optional<Item> itemIdcheck = itemService.getbyitemId(itemId);
        ArrayList priceslist = new ArrayList();


        if (itemIdcheck.isPresent()) {
            prices.setItemid(itemIdcheck.get().getItemId());
            prices.setItemDescription(itemIdcheck.get().getItemDescription());
            String itemKey = itemIdcheck.get().getItemKey();

            List<PricelistLineList> pricelistLineList = (priceListLineListService.getByitemKey(itemKey));
            for (var i = 0; i < pricelistLineList.size(); i++) {
                Optional<PricelistLineList> pricelistLineList1 = Optional.ofNullable(pricelistLineList.get(i));
                Pricesres pricesres = new Pricesres();
                if (itemKey.equals(pricelistLineList1.get().getItemkey())) {

                    Optional<PriceList> priceList = Optional.ofNullable(priceListService.getByKey(pricelistLineList1.get().getPricelistkey()));

                    if (Year.now().equals(Year.of(priceList.get().getStartDate().getYear())) &&Year.now().equals (Year.of(priceList.get().getEndDate().getYear()))) {

                        pricesres.setFromdate(priceList.get().getStartDate());
                        pricesres.setTodate(priceList.get().getEndDate());
                        pricesres.setPricelistName(priceList.get().getPriceListName());
                        pricesres.setListPrice(pricelistLineList1.get().getListPrice());
                        pricesres.setUnitPrice(pricelistLineList1.get().getUnitPrice());
                        priceslist.add(pricesres);
                        prices.setPrices(priceslist);


                    }
                    if (!Year.now().equals(Year.of(priceList.get().getStartDate().getYear())) && !Year.now().equals (Year.of(priceList.get().getEndDate().getYear()))){
                        throw new AlreadyExistsException("itemId "+itemId+" do not have any prices in the currentyear with organization code "+ organizationCode);
                    }
                }

            }
        }
        if (itemIdcheck.isEmpty()) {
            throw new NotFoundException("item id does not exists with id " + itemId);
        }
        Optional<List<Item>> itemOrgCheck = itemService.getbyOrganizationcode(organizationCode);
        if (itemOrgCheck.isEmpty()) {
            throw new NotFoundException("item did not found with organization code " + organizationCode);
        }

        Optional<Item> itemidAndOrgCheck = itemService.getbycodeandid(organizationCode, itemId);
        if (itemidAndOrgCheck.isEmpty()) {
            throw new DoesNotMatchException("itemid " + itemId + " with organizationcode " + organizationCode + " doesn't match.");

        }

        return prices;
    }
}
