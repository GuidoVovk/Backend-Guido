
package com.portfolio.Guido.Dto;

import javax.validation.constraints.NotBlank;


public class DtoProyectos {
    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String imgP;
    private String urlP;

    public DtoProyectos() {
    }

    public DtoProyectos(String nombreP, String descripcionP, String imgP, String urlP) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.imgP = imgP;
        this.urlP = urlP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }

    public String getUrlP() {
        return urlP;
    }

    public void setUrlP(String urlP) {
        this.urlP = urlP;
    }
    
    
}
