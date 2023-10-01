package com.example.libraryapp.Controllers.Client;

import com.example.libraryapp.entity.Client.Client;
import com.example.libraryapp.repositories.ClientRepository;
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

    @GetMapping("")
    public List<Client> getAllBooks() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getBookById(@PathVariable("id") Long id) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id){
        Client client = clientRepository.findById(id).orElse(null);

        if (client == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clientRepository.deleteById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}