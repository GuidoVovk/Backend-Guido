
package com.portfolio.Guido.service;

import com.portfolio.Guido.entity.Persona;
import java.util.List;


public interface IPersonaService {
    public List<Persona> verPersonas ();
    public void crearPersona (Persona per);
    public void borrarPersona (Long id);
    public Persona buscarPersona (Long id);
}
