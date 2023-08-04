package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

/**
 * Created by Binh
 * Date : 7/30/2023 - 2:32 PM
 * Description :
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Seller {
    @Id
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
