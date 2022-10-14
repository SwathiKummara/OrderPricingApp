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

import java.time.LocalDate;
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

    public ItemPriceRes getItemPrice(ItemPriceReq itemPriceReq) throws NotFoundException,  DoesNotMatchException, AlreadyExistsException {

        Optional<Item> itemIdcheck = itemService.getbyitemId(itemPriceReq.getItemId());

        ItemPriceRes itemPriceRes = new ItemPriceRes();
        LocalDate currentDate = itemPriceReq.getCurrentdate();
        LocalDate date = LocalDate.now();

        if (itemIdcheck.isPresent()) {

            String itemKey = itemIdcheck.get().getItemKey();
            itemPriceRes.setItemId(itemIdcheck.get().getItemId());


            List<PricelistLineList> pricelistLineList = priceListLineListService.getByitemKey(itemKey);

            for (int i = 0; i < pricelistLineList.size(); i++) {


                Optional<PricelistLineList> pricelistLineList1 = Optional.ofNullable(pricelistLineList.get(i));
                Optional<PriceList> priceList = Optional.ofNullable(priceListService.getByKey(pricelistLineList1.get().getPricelistkey()));



                if (priceList.get().getActive().equalsIgnoreCase("yes")) {
                    System.out.println(pricelistLineList.get(i).getPricelistkey());
                    if (currentDate != null) {
                        if (itemPriceReq.getCurrentdate() != null && priceList.get().getEndDate() != null && priceList.get().getStartDate() != null) {
                            if (itemPriceReq.getCurrentdate().isAfter(priceList.get().getStartDate()) && itemPriceReq.getCurrentdate().isBefore(priceList.get().getEndDate())) {
                                itemPriceRes.setPricingDate(itemPriceReq.getCurrentdate());
                                itemPriceRes.setPricelistName(priceList.get().getPriceListName());
                                itemPriceRes.setUnitPrice(pricelistLineList1.get().getUnitPrice());
                                itemPriceRes.setListPrice(pricelistLineList1.get().getListPrice());
                            }
                            else {
                                throw new AlreadyExistsException("Currently the product is not available");
                            }

                        }

                    }
                   else if (currentDate == null) {

                        if (date.isAfter(priceList.get().getStartDate()) && date.isBefore(priceList.get().getEndDate())) {
                            itemPriceRes.setPricingDate(date);
                            itemPriceRes.setPricelistName(priceList.get().getPriceListName());
                            itemPriceRes.setUnitPrice(pricelistLineList1.get().getUnitPrice());
                            itemPriceRes.setListPrice(pricelistLineList1.get().getListPrice());
                        }
                        else {
                            throw new AlreadyExistsException("Currently the product is not available");
                        }

                    }

                }
             else if (priceList.get().getActive().equalsIgnoreCase("no")) {
                    System.out.println(priceList.get().getPriceListKey());
                    throw new NotFoundException("Currently the product is not available With this time period");
                }
            }


        }
        if (itemIdcheck.isEmpty()) {
            throw new NotFoundException("item id does not exists with id " + itemPriceReq.getItemId());
        }
        Optional<List<Item>> itemOrgCheck = itemService.getbyOrganizationcode(itemPriceReq.getOrganizationCode());
        if (itemOrgCheck.isEmpty()) {
            throw new NotFoundException("item did not found with orgaization code " + itemPriceReq.getOrganizationCode());
        }

        Optional<Item> itemidAndOrgCheck = itemService.getbycodeandid(itemPriceReq.getOrganizationCode(), itemPriceReq.getItemId());
        if (itemidAndOrgCheck.isEmpty()) {
            throw new DoesNotMatchException("itemid " + itemPriceReq.getItemId() + " with organizationcode " + itemPriceReq.getOrganizationCode() + " doesn't match.");

        }

        return itemPriceRes;
    }

}
// TODO active status,restcontrolleradvice vs controlleradvice vs restcontroller