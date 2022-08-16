package com.portfolio.Guido.controller;

import com.portfolio.Guido.Dto.DtoSkills;
import com.portfolio.Guido.entity.Skills;
import com.portfolio.Guido.service.SSkills;
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
@RequestMapping("/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class CSkills {

    @Autowired
    SSkills sSkills;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = sSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id")int id){
        if(!sSkills.existsById(id)){
            return new ResponseEntity(new Mensaje("Id no existe"), HttpStatus.BAD_REQUEST);
        }
        
        Skills skills = sSkills.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
      if(!sSkills.existsById(id)){
          return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
      }
      sSkills.delete(id);
      return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoSkills dtoskills){
        if (StringUtils.isBlank(dtoskills.getNombreSkill())){
            return new ResponseEntity(new Mensaje("Nombre obligatorio"),HttpStatus.BAD_REQUEST);
        }
        if (sSkills.existsByNombreSkill(dtoskills.getNombreSkill())){
            return new ResponseEntity(new Mensaje("El nombre ya existe"),HttpStatus.BAD_REQUEST);
        }
        
        Skills skills = new Skills(dtoskills.getNombreSkill(), dtoskills.getPorcentaje());
        sSkills.save(skills);
        return new ResponseEntity(new Mensaje("Skill creada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoSkills dtoskills){
        if(!sSkills.existsById(id)){
            return new ResponseEntity(new Mensaje("Id no existe"), HttpStatus.NOT_FOUND);
        }
        if(sSkills.existsByNombreSkill(dtoskills.getNombreSkill()) && sSkills.getByNombreSkill(dtoskills.getNombreSkill()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoskills.getNombreSkill())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Skills skills = sSkills.getOne(id).get();
        
        skills.setNombreSkill(dtoskills.getNombreSkill());
        skills.setPorcentaje(dtoskills.getPorcentaje());
        
        sSkills.save(skills);
        
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
}
