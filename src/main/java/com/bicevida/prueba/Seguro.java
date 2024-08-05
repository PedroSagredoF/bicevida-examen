package com.bicevida.prueba;

import lombok.Data;

@Data
public class Seguro {
    private int id;
    private String name;

    public Seguro(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
