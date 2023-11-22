package com.example.libraryapp.entity.Client;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
public class CreditCard {

    //INSTANCE VARIABLES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private Client client;
    @NotNull
    private String cardNumber;
    @NotNull
    private String cardName;
    @NotNull
    private String expirationDate;
    @NotNull
    private String securityCode;

    //CONSTRUCTOR
    public CreditCard(){

    }
    public CreditCard(Client client,
                      @NotNull String cardNumber,
                      @NotNull String cardName,
                      @NotNull String expirationDate,
                      @NotNull String securityCode)
    {
        this.client = client;
        this.cardNumber = cardNumber;
        this.cardName = cardName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //SETTERS AND GETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @NotNull
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(@NotNull String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @NotNull
    public String getCardName() {
        return cardName;
    }

    public void setCardName(@NotNull String cardName) {
        this.cardName = cardName;
    }

    @NotNull
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(@NotNull String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @NotNull
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(@NotNull String securityCode) {
        this.securityCode = securityCode;
    }
}
