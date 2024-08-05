package com.bicevida.prueba;

import lombok.Data;

@Data
public class Cuenta {
    private int clientId;

    private int insuranceId;
    private int balance;

    public Cuenta(int clientId, int insuranceId, int balance) {
        this.clientId = clientId;
        this.insuranceId = insuranceId;
        this.balance = balance;
    }
}
