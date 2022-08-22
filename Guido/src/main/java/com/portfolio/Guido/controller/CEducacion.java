
package com.portfolio.Guido.controller;

import com.portfolio.Guido.Dto.DtoEducacion;
import com.portfolio.Guido.entity.Educacion;
import com.portfolio.Guido.service.Seducacion;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://login-33ab0.web.app")
public class CEducacion {
    @Autowired
    Seducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
       List<Educacion> list = sEducacion.list();
       return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id")int id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("Id no existe"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
      if(!sEducacion.existsById(id)){
          return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
      }
      sEducacion.delete(id);
      return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoeducacion){
        if (StringUtils.isBlank(dtoeducacion.getNombreEdu())){
            return new ResponseEntity(new Mensaje("Nombre obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existsByNombreEdu(dtoeducacion.getNombreEdu())){
            return new ResponseEntity(new Mensaje("El nombre ya existe"),HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoeducacion.getDescripcionEdu())){
            return new ResponseEntity(new Mensaje("Descripcion obligatoria"),HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoeducacion.getFechaEdu())){
            return new ResponseEntity(new Mensaje("Fecha obligatoria"),HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = new Educacion(dtoeducacion.getNombreEdu(), dtoeducacion.getDescripcionEdu(), dtoeducacion.getFechaEdu());
        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoeducacion){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity(new Mensaje("Id no existe"), HttpStatus.NOT_FOUND);
        }
        if(sEducacion.existsByNombreEdu(dtoeducacion.getNombreEdu()) && sEducacion.getByNombreEdu(dtoeducacion.getNombreEdu()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducacion.getNombreEdu())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducacion.getDescripcionEdu())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducacion.getFechaEdu())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        
        
        Educacion educacion = sEducacion.getOne(id).get();
        
        educacion.setNombreEdu(dtoeducacion.getNombreEdu());
        educacion.setDescripcionEdu(dtoeducacion.getDescripcionEdu());
        educacion.setFechaEdu(dtoeducacion.getFechaEdu());
        
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }
}
