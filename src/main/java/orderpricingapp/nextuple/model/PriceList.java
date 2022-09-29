package orderpricingapp.nextuple.model;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pricelisttable")
@AllArgsConstructor
@NoArgsConstructor

public class PriceList {

    @Id
    @Column(name = "pricelistkey")
    private String priceListKey;

    @Column(name = "name")
    private String priceListName;

    @Column(name = "active")
    private String active;

    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "organizationcode")
    private String organizationCode;



}
