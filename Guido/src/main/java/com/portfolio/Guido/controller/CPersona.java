
package com.portfolio.Guido.controller;

import com.portfolio.Guido.Dto.DtoPersona;
import com.portfolio.Guido.entity.Persona;
import com.portfolio.Guido.service.SPersona;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("perfil")
@CrossOrigin(origins = "http://localhost:4200")
public class CPersona {
    @Autowired
    SPersona sPersona;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
       List<Persona> list = sPersona.list();
       return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sPersona.existsByNombre(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje("esa persona existe"), HttpStatus.BAD_REQUEST);
        Persona pers = new Persona(dtopersona.getNombre(), dtopersona.getApellido(), dtopersona.getImgPerfil(), dtopersona.getPuesto(), dtopersona.getDescripcionPuesto());
        sPersona.save(pers);
            
        return new ResponseEntity (new Mensaje("Perfil agregada"), HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody DtoPersona dtopersona){
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        if(sPersona.existsByNombre(dtopersona.getNombre()) && sPersona.getByNombre(dtopersona.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje ("El perfil ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Persona pers = sPersona.getOne(id).get();
        pers.setNombre(dtopersona.getNombre());
        pers.setApellido(dtopersona.getApellido());
        pers.setImgPerfil(dtopersona.getImgPerfil());
        pers.setPuesto(dtopersona.getPuesto());
        pers.setDescripcionPuesto(dtopersona.getDescripcionPuesto());
        
        sPersona.save(pers);
        return new ResponseEntity(new Mensaje("Perfil actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sPersona.existsById(id))
            return new ResponseEntity (new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        
        sPersona.delete(id);
        
        return new ResponseEntity (new Mensaje ("Perfil eliminado"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!sPersona.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Persona pers = sPersona.getOne(id).get();
        return new ResponseEntity(pers, HttpStatus.OK);
    }
    
}
