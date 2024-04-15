/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package br.ejb;

import java.util.Random;
import javax.ejb.Stateless;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Murilo
 */
@Stateless
public class EjbLogicNegocio {
    
    @Getter @Setter
    private int[] numrandom = new int[2];

    public int Calculo(int a, int b){
        return a + b;
    }
    
    public boolean Resultado(int tentativa, int a, int b){
        return tentativa == Calculo(a,b);
    }
    
    public int[] geraNum(){
        Random random = new Random();
        numrandom[0] = random.nextInt(101);
        numrandom[1] = random.nextInt(101);
        return numrandom;
    }
}
