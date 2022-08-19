
package com.portfolio.Guido.repository;

import com.portfolio.Guido.entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPersona extends JpaRepository<Persona, Integer> {
    public Optional<Persona> findByNombre (String nombre);
    public boolean existsByNombre (String nombre);
}
