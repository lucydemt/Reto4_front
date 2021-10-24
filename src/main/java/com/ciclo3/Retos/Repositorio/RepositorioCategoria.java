/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo3.Retos.Repositorio;

import com.ciclo3.Retos.Interface.InterfaceCategoria;
import com.ciclo3.Retos.Modelo.Categoria;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andres Cardona
 */
@Repository
public class RepositorioCategoria {
    @Autowired
    private InterfaceCategoria crud1;
    
    public List<Categoria> getAll(){
        return (List<Categoria>) crud1.findAll();
    }
    
    public Optional<Categoria> getCategoria(int id){
        return crud1.findById(id);
    }
    
    public Categoria save(Categoria categoria){
        return crud1.save(categoria);
    }
    
    public void delete(Categoria categoria){
        crud1.delete(categoria);
    }
}
