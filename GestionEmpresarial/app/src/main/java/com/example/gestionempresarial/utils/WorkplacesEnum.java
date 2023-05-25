package com.example.gestionempresarial.utils;

public enum WorkplacesEnum {

    CABA (1, "CABA"),
    ZONA_NORTE (2, "Zona Norte"),
    ZONA_OESTE (3, "Zona Oeste");

    public int idLocation;
    public String location;

    WorkplacesEnum(int i, String location) {
        idLocation  = i;
        this.location = location;
    }

    public int getIdLocation(){

        return idLocation;
    }

    public String getLocation(){

        return location;
    }
}
