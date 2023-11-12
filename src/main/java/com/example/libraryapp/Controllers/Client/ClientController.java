package com.example.libraryapp.Controllers.Client;

import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.entity.Client.CreditCard;
import com.example.libraryapp.repositories.ClientRepository;
import com.example.libraryapp.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @GetMapping("")
    public ResponseEntity<List<Client>> getAllUsers() {
        List<Client> clients = clientRepository.findAll();

        if(clients == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getClientById(@PathVariable("username") String username) {
        Client client = clientRepository.findByUsername(username);

        if (client == null) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        if (!client.validate()){
            return ResponseEntity.badRequest().body("Both username and password must be entered.");
        }
        if(clientRepository.findByUsername(client.getUsername()) != null){
            return ResponseEntity.badRequest().body("This username is taken, please enter a new one.");
        }

        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateClient(@PathVariable("username") String username, @RequestBody Client client){
        Client clientToUpdate = clientRepository.findByUsername(username);

        if (clientToUpdate == null){
            return ResponseEntity.badRequest().body("User not found.");
        }
        if(client.getHomeAddress() != null){
            clientRepository.updateHomeAddress(client.getHomeAddress(), username);
        }
        if(client.getName() != null){
            clientRepository.updateName(client.getName(), username);
        }

        if(client.getUsername() != null){
            clientRepository.updateUsername(client.getUsername(), username);
        }

        if(client.getPassword() != null){
            clientRepository.updatePassword(client.getPassword(), username);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add-credit-card/{username}")
    public ResponseEntity<?> addCreditCard(@PathVariable("username") String username, @RequestBody CreditCard creditCard){
        Client client = clientRepository.findByUsername(username);

        if (client == null){
            return ResponseEntity.badRequest().body("User not found.");
        }

        if(creditCardRepository.findByCardNumber(creditCard.getCardNumber()) != null){
            return ResponseEntity.badRequest().body("This credit card number is already in use.");
        }

        int cardNumberLength = creditCard.getCardNumber().length();
        int securityCodeLength = creditCard.getSecurityCode().length();

        if(cardNumberLength != 16){
            return ResponseEntity.badRequest().body("The credit card number must be 16 digits long.");
        }
        if(securityCodeLength != 3){
            return ResponseEntity.badRequest().body("The security code must be 3 digits long.");
        }

        creditCard.setClient(client);
        creditCardRepository.save(creditCard);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null){
            return ResponseEntity.badRequest().body("User not found.");
        }

        clientRepository.deleteById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}