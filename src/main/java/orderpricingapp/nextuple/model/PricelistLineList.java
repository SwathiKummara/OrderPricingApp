package orderpricingapp.nextuple.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "pricelistlinelist")

public class PricelistLineList {

    @Id
    @Column(name = "pllkey")
    private String pricelistLinekey;

    @Column(name = "pricelistkey")
    private String pricelistkey;

    @Column(name = "itemkey")
    private String itemkey;

    @Column(name = "unitprice")
    private Double unitPrice;

    @Column(name = "listprice")
    private Double listPrice;

    @Column(name = "organizationcode")
    private String organizationCode;




}
