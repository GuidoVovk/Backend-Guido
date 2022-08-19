
package com.portfolio.Guido.Dto;

import javax.validation.constraints.NotBlank;


public class DtoPersona {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String imgPerfil;
    @NotBlank
    private String puesto;
    @NotBlank
    private String descripcionPuesto;

    public DtoPersona() {
    }

    public DtoPersona(String nombre, String apellido, String imgPerfil, String puesto, String descripcionPuesto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.imgPerfil = imgPerfil;
        this.puesto = puesto;
        this.descripcionPuesto = descripcionPuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDescripcionPuesto() {
        return descripcionPuesto;
    }

    public void setDescripcionPuesto(String descripcionPuesto) {
        this.descripcionPuesto = descripcionPuesto;
    }
    
    
    
    
}
