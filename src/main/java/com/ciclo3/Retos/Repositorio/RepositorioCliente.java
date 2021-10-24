/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo3.Retos.Repositorio;

import com.ciclo3.Retos.Interface.InterfaceCliente;
import com.ciclo3.Retos.Modelo.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andres Cardona
 */
@Repository
public class RepositorioCliente {
    @Autowired
    private InterfaceCliente crud2;
    
    public List<Cliente> getAll(){
        return (List<Cliente>) crud2.findAll();
    }
    
    public Optional<Cliente> getCliente(int id){
        return crud2.findById(id);
    }
    
    public Cliente save(Cliente cliente){
        return crud2.save(cliente);
    }
    
    public void delete(Cliente cliente){
        crud2.delete(cliente);
    }
}
