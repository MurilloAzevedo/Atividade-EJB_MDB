/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Murilo
 */
@Data
public class Ranking {

    @Getter @Setter
    private String nome;
    
    @Getter @Setter
    private int pontos;

    public Ranking() {
    }

    public Ranking(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
    }
    
}
