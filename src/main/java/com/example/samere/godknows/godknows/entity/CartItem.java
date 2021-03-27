package com.example.samere.godknows.godknows.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ITEM")
public class CartItem {

    @Id
    @Column(name = "item_id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "item_images", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "img_url")
    private Set<String> imgUrls = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(Set<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id) && Objects.equals(name, cartItem.name) && Objects.equals(price, cartItem.price) && Objects.equals(description, cartItem.description) && Objects.equals(imgUrls, cartItem.imgUrls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, imgUrls);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imgUrls='" + imgUrls + '\'' +
                '}';
    }

}
