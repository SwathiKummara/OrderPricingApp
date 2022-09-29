package orderpricingapp.nextuple.repository;


import orderpricingapp.nextuple.model.PricelistLineList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceLineListRepository extends JpaRepository<PricelistLineList,String>{

    Optional<PricelistLineList> findByOrganizationCodeAndItemkeyAndPricelistkey(String code,String itemkey, String pricelistkey);
    List<PricelistLineList> findByItemkey(String key);
    List<PricelistLineList> findByPricelistkey(String key);

}
