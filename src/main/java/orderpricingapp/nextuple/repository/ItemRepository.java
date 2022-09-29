package orderpricingapp.nextuple.repository;

import orderpricingapp.nextuple.model.Item;
import orderpricingapp.nextuple.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

Item findByItemKey(String itemKey);

Item findByItemId(String itemId);
Item findByOrganizationCodeAndItemKey(String code,String key);

Item findByOrganizationCodeAndItemId(String code,String id);

Item deleteByOrganizationCodeAndItemId(String code,String id);
Optional<List<Item>> findByOrganizationCode(String code);


}
