package com.example.libraryapp.entity.Cart;

import com.example.libraryapp.entity.Client.Client;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Shopping_Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private Client client;

    private double total;

    public Shopping_Cart() {
    }

    public Shopping_Cart(Client client, double total) {
        this.client = client;
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shopping_Cart that = (Shopping_Cart) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
