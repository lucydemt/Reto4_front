/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo3.Retos.Servicios;

import com.ciclo3.Retos.Modelo.Quadbike;
import com.ciclo3.Retos.Repositorio.RepositorioQuadbike;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author temporal
 */
@Service
public class ServiciosQuadbike {
    @Autowired
    private RepositorioQuadbike metodosCrud;
    
    public List<Quadbike> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Quadbike> getQuadbike(int quadbikeId){
        return metodosCrud.getQuadbike(quadbikeId);
    }
    
    public Quadbike save(Quadbike quadbike){
        if(quadbike.getId()==null){
            return metodosCrud.save(quadbike);
        }else{
            Optional<Quadbike> e=metodosCrud.getQuadbike(quadbike.getId());
            if(e.isEmpty()){
                return metodosCrud.save(quadbike);
            }else{
                return quadbike;
            }
        }
    }
    public Quadbike update(Quadbike quadbike){
	if(quadbike.getId()!=null){
            Optional<Quadbike> e=metodosCrud.getQuadbike(quadbike.getId());
            if(!e.isEmpty()){
		if(quadbike.getName()!=null){
                    e.get().setName(quadbike.getName());
		}
                if(quadbike.getBrand()!=null){
                    e.get().setBrand(quadbike.getBrand());
		}
                if(quadbike.getYear()!=null){
                    e.get().setYear(quadbike.getYear());
		}
                if(quadbike.getDescription()!=null){
                    e.get().setDescription(quadbike.getDescription());
		}
                if(quadbike.getCategory()!=null){
                    e.get().setCategory(quadbike.getCategory());
		}
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return quadbike;                
            }
	}else{
            return quadbike;
        }
    }
    
    public boolean deleteQuadbike(int quadbikeId){
        Boolean aBoolean = getQuadbike(quadbikeId).map(quadbike -> {
            metodosCrud.delete(quadbike);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
