package com.estantedigital.model;

import java.time.LocalDate;

public class Emprestimo {
    private long id;
    private long  usuarioId;
    private long itemId;
    private LocalDate dataRetirada;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoReal = null;
    private String justificativaAtraso;


    public Emprestimo(long id, long usuarioId, long itemId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.itemId = itemId;
        this.dataRetirada = LocalDate.now();
        this.dataPrevistaDevolucao = this.dataRetirada.plusDays(7);
    }

    public boolean isAtrasado() {
        return dataDevolucaoReal == null &&
                LocalDate.now().isAfter(dataPrevistaDevolucao);
    }

    public long getId() { return id; }

}
