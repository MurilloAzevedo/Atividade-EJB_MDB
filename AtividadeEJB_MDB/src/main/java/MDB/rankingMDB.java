/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MDB;

/**
 *
 * @author Murilo
 */

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java/Ranking"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class rankingMDB implements MessageListener{
    
    private static final Logger logger = Logger.getLogger(rankingMDB.class.getName());
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String rankingList = ((TextMessage) message).getText();
                logger.info("Ranking: " + rankingList);
            } catch (JMSException e) {
                logger.severe("Erro" + e.getMessage());
            }
        }
    }
    
    
}
