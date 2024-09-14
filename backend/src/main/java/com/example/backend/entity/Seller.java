package com.example.backend.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import java.util.Set;

/**
 * Created by Binh
 * Date : 7/30/2023 - 2:32 PM
 * Description :
 */

@Entity(name = "Seller")
@Table(name = "\"seller\"")
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sellerId;

    private String sellerName;

    private String sellerFirstName;

    private String sellerLastName;

    private String sellerPassword;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "SELL_ROLE",
            joinColumns = {
                @JoinColumn(name = "SELLER_ID")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_seller",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "seller_id")
            }
    )
    private Set<Product> product;

    public Set<Product> getProduct() {
        return product;
    }


}
