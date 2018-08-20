package com.example.controller;

import com.example.model.Client;
import com.example.repository.ClientRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientRepository clientRepository;
    private UserRepository userRepository;

    @Autowired
    public ClientController(ClientRepository clientRepository, UserRepository userRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> findById(@PathVariable Long id) {
        return clientRepository.findById(id);
    }

    @PutMapping("/{clientId}")
    public Client newClient(@PathVariable Long clientId, @RequestBody @Valid Client client) {
        client.setUser(userRepository.findById(clientId).get());
        return clientRepository.save(client);
    }

    @PatchMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody @Valid Client client) {
        client.setId(id);
        return  clientRepository.save(client);
    }









}
