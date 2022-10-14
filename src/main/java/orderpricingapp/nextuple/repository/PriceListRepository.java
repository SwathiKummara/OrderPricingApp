package orderpricingapp.nextuple.repository;

import orderpricingapp.nextuple.model.PriceList;
import orderpricingapp.nextuple.model.PricelistLineList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList,String> {
    List<PriceList> findByPriceListKey(String priceListKey);
    List<PriceList> findByOrganizationCodeAndPriceListName(String code, String name);
    List<PriceList> findByActiveAndOrganizationCode(String active, String code);

    Date findByStartDate(Date date);
     Date findByEndDate(Date date);

}
