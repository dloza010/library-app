package com.example.libraryapp.entity.Cart;

import com.example.libraryapp.entity.Book.Book_Details;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cart_Item {
    // Instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name="shopping_cart_id")
    private Shopping_Cart shopping_cart_id;

    @OneToOne
    @JoinColumn(name="ISBN")
    private Book_Details ISBN;
    private Long quantity;

    public Cart_Item() {
    }

    public Cart_Item(Shopping_Cart shopping_cart_id, Book_Details ISBN, long quantity){
        this.shopping_cart_id = shopping_cart_id;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart_Item cart_item = (Cart_Item) o;
        return id.equals(cart_item.id) && Objects.equals(shopping_cart_id, cart_item.shopping_cart_id) && Objects.equals(ISBN, cart_item.ISBN) && Objects.equals(quantity, cart_item.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shopping_cart_id, ISBN, quantity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shopping_Cart getShopping_cart_id() {
        return shopping_cart_id;
    }

    public void setShopping_cart_id(Shopping_Cart shopping_cart_id) {
        this.shopping_cart_id = shopping_cart_id;
    }

    public Book_Details getISBN() {
        return ISBN;
    }

    public void setISBN(Book_Details ISBN) {
        this.ISBN = ISBN;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
