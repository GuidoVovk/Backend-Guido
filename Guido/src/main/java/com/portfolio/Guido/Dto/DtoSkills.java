
package com.portfolio.Guido.Dto;

import javax.validation.constraints.NotBlank;


public class DtoSkills {
    @NotBlank
    private String nombreSkill;
    @NotBlank
    private int porcentaje;

    public DtoSkills() {
    }

    public DtoSkills(String nombreSkill, int porcentaje) {
        this.nombreSkill = nombreSkill;
        this.porcentaje = porcentaje;
    }

    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
