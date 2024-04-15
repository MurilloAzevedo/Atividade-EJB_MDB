/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.jsf;

import br.data.model.Ranking;
import br.ejb.EjbLogicNegocio;
import br.ejb.EjbRanking;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.Queue;
import javax.persistence.Entity;
import lombok.Getter;
import  lombok.Setter;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
/**
 *
 * @author Murilo
 */
@Named(value = "jsfRanking")
@Entity
@Stateless
//@RequestScoped
public class JsfRanking implements Serializable{

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "java/Ranking")
    private Queue fila;
    
    @EJB
    private EjbLogicNegocio ejbLogicNegocio;

    public JsfRanking() {
    }
    
    @Getter @Setter
    private String nome;
    
    @Getter @Setter
    private int resultado = 0;
    
    @Getter @Setter
    private boolean tentativa = false;
    
    private Ranking userRank;
    
    @EJB
    private EjbRanking ejbRanking;
    
    @Getter @Setter
    int[] valor = new int[2];
    
    public void send(){
        
        try{
            JMSContext context = connectionFactory.createContext();
        context.createProducer().send(fila, ejbRanking.getAll().toString());
        } catch(Exception e){
            System.out.println("erro");
            System.err.println(e.getMessage());
        }
    }
    
    @Inject
    public JsfRanking(Ranking ranking) {
        this.userRank = ranking;
    }
    
    public ArrayList<Ranking> getAll(){
        return ejbRanking.getAll();
    }
    
    public void update(){
        ejbRanking.update(userRank, nome, tentativa);
        valor = ejbLogicNegocio.geraNum();
    }
    
    public void calculo(){
        //valor = new int[]{0,0};
        tentativa = ejbLogicNegocio.Resultado(resultado, valor[0], valor[1]);
        ejbRanking.update(userRank, nome, tentativa);
        resultado = 0;
        tentativa = false;
        valor = ejbLogicNegocio.geraNum();
        send();
    }
}
