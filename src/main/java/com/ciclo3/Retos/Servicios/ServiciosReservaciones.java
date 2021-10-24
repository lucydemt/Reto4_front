/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo3.Retos.Servicios;

import com.ciclo3.Retos.Modelo.Reservaciones;
import com.ciclo3.Retos.Repositorio.RepositorioReservaciones;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andres Cardona
 */
@Service
public class ServiciosReservaciones {
    @Autowired
    private RepositorioReservaciones metodosCrud;
    
    public List<Reservaciones> getAll(){
        return metodosCrud.getAll();
    }
    
    public Optional<Reservaciones> getReservaciones(int reservacionesId){
        return metodosCrud.getReservation(reservacionesId);
    }
    
    public Reservaciones save(Reservaciones reservaciones){
        if(reservaciones.getIdReservation()==null){
            return metodosCrud.save(reservaciones);
        }else{
            Optional<Reservaciones> e=metodosCrud.getReservation(reservaciones.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservaciones);
            }else{
                return reservaciones;
            }
        }
    }
    public Reservaciones update(Reservaciones reservaciones){
	if(reservaciones.getIdReservation()!=null){
            Optional<Reservaciones> e=metodosCrud.getReservation(reservaciones.getIdReservation());
            if(!e.isEmpty()){
		if(reservaciones.getStartDate()!=null){
                    e.get().setStartDate(reservaciones.getStartDate());
		}
                if(reservaciones.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservaciones.getDevolutionDate());
		}
                if(reservaciones.getStatus()!=null){
                    e.get().setStatus(reservaciones.getStatus());
		}
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservaciones;                
            }
	}else{
            return reservaciones;
        }
    }
    
    public boolean deleteReservaciones(int reservacionesId){
        Boolean aBoolean = getReservaciones(reservacionesId).map(reservaciones -> {
            metodosCrud.delete(reservaciones);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
