
package com.portfolio.Guido.controller;

import com.portfolio.Guido.entity.Persona;
import com.portfolio.Guido.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PersonaController {
    @Autowired
    private IPersonaService persoServ;
       
    @PostMapping ("/new/persona")
    public void agregarPersona(@RequestBody Persona pers) {
        persoServ.crearPersona(pers);
    }
    
    @GetMapping ("/ver/personas")
    @ResponseBody
    public List<Persona> verPersonas () {
        return persoServ.verPersonas();
    }
    
    @DeleteMapping ("/delete/{id}")
    public void borrarPersonas(@PathVariable Long id){
        persoServ.borrarPersona(id);
    }
    
    @PutMapping ("/persona/editar/{id}")
    public Persona editPersona (@PathVariable Long id,
                                @RequestParam("nombre") String nuevoNombre,
                                @RequestParam("apellido") String nuevoApellido,
                                @RequestParam("img") String nuevaImg,
                                @RequestParam("puesto") String nuevoPuesto,
                                @RequestParam("descripcion") String nuevaDescripcion){
        Persona persona = persoServ.buscarPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevaImg);
        persona.setPuesto(nuevoPuesto);
        persona.setDescripcion(nuevaDescripcion);
        
        persoServ.crearPersona(persona);
        return persona;
    }
    
    @GetMapping("/persona/encontrar/{id}")
    public Persona buscarPersona(@PathVariable Long id){
        return persoServ.buscarPersona(id);
    }
}
