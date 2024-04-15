/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package br.ejb;

import br.data.model.Ranking;
import java.util.ArrayList;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Murilo
 */
@Singleton
@Stateful
public class EjbRanking {
    
    private final ArrayList<Ranking> lrank;
    
    public EjbRanking(){
        lrank = new ArrayList<>();
    }

    public void update(Ranking rank, String nome, boolean acertou){
        boolean achou = false;

        for(Ranking ranking : lrank){
            if (ranking.getNome().equals(nome)){
                if(acertou){
                    ranking.setPontos(ranking.getPontos()+1);
                    FacesContext.getCurrentInstance()
                                .addMessage(null, new FacesMessage( "Parabens! Você acertou!!"));
                }
                else{
                    FacesContext.getCurrentInstance()
                                .addMessage(null, new FacesMessage( "Que pena. Você errou ='("));
                }
                achou = true;
                break;
            }

        }
        if(!achou){
            if(acertou){
                lrank.add(new Ranking(nome, 1));
            }
            else{
                lrank.add(new Ranking(nome, 0));
            }
            
        }    
    }
    
    public ArrayList<Ranking> getAll(){
        return lrank;
    }
}
