package com.codegym.repository;

import com.codegym.model.Receptionist;

import java.util.ArrayList;
import java.util.List;

public interface ReceptionistRepository {
    List<Receptionist> findAll();

    Receptionist findById(int id);

    void addElement(Receptionist element);

    void updateElement(int id, Receptionist element);

    void removeElement(int id);

    List<Receptionist> findByName(String name);
}
