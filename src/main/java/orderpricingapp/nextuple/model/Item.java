package orderpricingapp.nextuple.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @Column(name = "itemkey")
    private String itemKey;

    @Column(name = "itemid" , unique = true)
    private String itemId;

    @Column(name = "itemdescription")
    private String itemDescription;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "organizationcode")
    private String organizationCode;

    @Column(name = "unitofmeasure",unique = true)
    private String unitOfMeasure;


//TODO @Builder noargs and allargs, how to use different profiles.

}
