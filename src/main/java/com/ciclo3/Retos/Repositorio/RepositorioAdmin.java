/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo3.Retos.Repositorio;

import com.ciclo3.Retos.Interface.InterfaceAdmin;
import com.ciclo3.Retos.Modelo.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author temporal
 */
@Repository
public class RepositorioAdmin {
    @Autowired
    private InterfaceAdmin crud;
    
    public List<Admin> getAll(){
        return (List<Admin>) crud.findAll();
    }
    
    public Optional<Admin> getAdmin(int id){
        return crud.findById(id);
    }
    
    public Admin save(Admin admin){
        return crud.save(admin);
    }
    
    public void delete(Admin admin){
        crud.delete(admin);
    }
}
