package com.portfolio.Guido.controller;

import com.portfolio.Guido.Dto.DtoProyectos;
import com.portfolio.Guido.entity.Proyectos;
import com.portfolio.Guido.service.SProyectos;
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
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://login-33ab0.web.app")
public class CProyectos {

    @Autowired
    SProyectos sProyectos;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("Id no existe"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
      if(!sProyectos.existsById(id)){
          return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
      }
      sProyectos.delete(id);
      return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoproyectos){
        if (StringUtils.isBlank(dtoproyectos.getNombreP())){
            return new ResponseEntity(new Mensaje("Nombre obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if (sProyectos.existsByNombreP(dtoproyectos.getNombreP())){
            return new ResponseEntity(new Mensaje("El nombre ya existe"),HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoproyectos.getDescripcionP())){
            return new ResponseEntity(new Mensaje("Descripción obligatoria"),HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = new Proyectos(dtoproyectos.getNombreP(), dtoproyectos.getDescripcionP(), dtoproyectos.getImgP(), dtoproyectos.getUrlP());
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyecto creado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoproyectos){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("Id no existe"), HttpStatus.NOT_FOUND);
        }
        if(sProyectos.existsByNombreP(dtoproyectos.getNombreP()) && sProyectos.getByNombreP(dtoproyectos.getNombreP()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoproyectos.getNombreP())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtoproyectos.getDescripcionP())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyectos proyectos = sProyectos.getOne(id).get();
        
        proyectos.setNombreP(dtoproyectos.getNombreP());
        proyectos.setDescripcionP(dtoproyectos.getDescripcionP());
        proyectos.setImgP(dtoproyectos.getImgP());
        proyectos.setUrlP(dtoproyectos.getUrlP());
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }

}
