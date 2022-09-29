package orderpricingapp.nextuple.business;

import lombok.Data;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemPriceService {

    @Autowired
    ItemService itemService;

    @Autowired
    PriceListService priceListService;

    @Autowired
    PriceListRepository priceListRepository;

    @Autowired
    PriceListLineListService priceListLineListService;

    public ItemPriceRes getItemPrice(ItemPriceReq itemPriceReq) throws ItemException, com.orderPricingApp.springboot.exception.PricelistException, com.orderPricingApp.springboot.exception.PricelinelistException {

        Optional<Item> itemIdcheck = itemService.getbyitemId(itemPriceReq.getItemId());

        ItemPriceRes itemPriceRes = new ItemPriceRes();

        if (itemIdcheck.isPresent()) {

            String itemKey = itemIdcheck.get().getItemKey();
            itemPriceRes.setItemId(itemIdcheck.get().getItemId());


            List<PricelistLineList> pricelistLineList = priceListLineListService.getByitemKey(itemKey);
            for (int i = 0; i < pricelistLineList.size(); i++) {
                Optional<PricelistLineList> pricelistLineList1 = Optional.ofNullable(pricelistLineList.get(i));
                Optional<PriceList> priceList = Optional.ofNullable(priceListService.getByKey(pricelistLineList1.get().getPricelistkey()));
                if (priceList.get().getActive().equalsIgnoreCase("yes")) {

                    if (itemPriceReq.getCurrentdate() != null && priceList.get().getEndDate() != null && priceList.get().getStartDate() != null) {
                        if (itemPriceReq.getCurrentdate().after(priceList.get().getStartDate()) && itemPriceReq.getCurrentdate().before(priceList.get().getEndDate())) {
                            itemPriceRes.setPricingDate(itemPriceReq.getCurrentdate());
                            itemPriceRes.setPricelistName(priceList.get().getPriceListName());
                            itemPriceRes.setUnitPrice(pricelistLineList1.get().getUnitPrice());
                            itemPriceRes.setListPrice(pricelistLineList1.get().getListPrice());
                        }
                    }
                }

            }
        }

        if (itemIdcheck.isEmpty()) {
            throw new ItemException("item id does not exists with id " + itemPriceReq.getItemId());
        }
        Optional<List<Item>> itemOrgCheck = itemService.getbyOrganizationcode(itemPriceReq.getOrganizationCode());
        if (itemOrgCheck.isEmpty()) {
            throw new ItemException("item did not found with orgaization code " + itemPriceReq.getOrganizationCode());
        }

        Optional<Item> itemidAndOrgCheck = itemService.getbycodeandid(itemPriceReq.getOrganizationCode(), itemPriceReq.getItemId());
        if (itemidAndOrgCheck.isEmpty()) {
            throw new ItemException("itemid " + itemPriceReq.getItemId() + " with organizationcode " + itemPriceReq.getOrganizationCode() + " doesn't match.");

        }

        return itemPriceRes;
    }

}