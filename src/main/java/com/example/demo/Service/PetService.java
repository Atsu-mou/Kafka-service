package com.example.demo.Service;


import com.example.demo.Model.Pet;

public interface PetService {

    void kafkaSend(String topicName, Pet pet);


}
