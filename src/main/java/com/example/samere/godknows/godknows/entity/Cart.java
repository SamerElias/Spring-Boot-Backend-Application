package com.example.samere.godknows.godknows.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false, unique = true)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart")
    private Set<CartItem> cartItems;

}
