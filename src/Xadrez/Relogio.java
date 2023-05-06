/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904

package Xadrez;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Allan
 */
public class Relogio extends Thread implements Serializable{
    
    private String tempo;
    int minuto, segundo;
    int milisegundo;
    private JTextField relogio;
    
    public void salvarJogador(int minutos, int segundos){
           try {
                FileOutputStream minutosRelogio = new FileOutputStream("Minutos");     
                ObjectOutputStream objMinutos = new ObjectOutputStream(minutosRelogio);
            
                objMinutos.writeObject(minutos);
                objMinutos.close();
                
                FileOutputStream segundosRelogio = new FileOutputStream("Segundos");
                ObjectOutputStream objSegundos = new ObjectOutputStream(segundosRelogio);
            
                objSegundos.writeObject(segundos);
                objSegundos.close();              
    
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + ex);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
        }           
    }
/*    
    public void desfazerJogador(int minutos, int segundos){
           try {
                FileOutputStream minutosRelogio = new FileOutputStream("desfazer/Minutos");     
                ObjectOutputStream objMinutos = new ObjectOutputStream(minutosRelogio);
            
                objMinutos.writeObject(minutos);
                objMinutos.close();
                
                FileOutputStream segundosRelogio = new FileOutputStream("desfazer/Segundos");
                ObjectOutputStream objSegundos = new ObjectOutputStream(segundosRelogio);
            
                objSegundos.writeObject(segundos);
                objSegundos.close();              
    
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + ex);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
        }           
    }
    
    public void refazerJogador(int minutos, int segundos){
           try {
                FileOutputStream minutosRelogio = new FileOutputStream("refazer/Minutos");     
                ObjectOutputStream objMinutos = new ObjectOutputStream(minutosRelogio);
            
                objMinutos.writeObject(minutos);
                objMinutos.close();
                
                FileOutputStream segundosRelogio = new FileOutputStream("refazer/Segundos");
                ObjectOutputStream objSegundos = new ObjectOutputStream(segundosRelogio);
            
                objSegundos.writeObject(segundos);
                objSegundos.close();              
    
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + ex);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
        }           
    }
*/
    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }
    
    public Relogio(JTextField relogio){
        minuto = segundo = 0;
        this.relogio = relogio;
    }
    
    public void run(){
        while(true){
            try {
                Relogio.sleep(1);
                milisegundo++;
                if(milisegundo == 1000){
                   segundo++;
                   milisegundo = 0;
                    if (segundo == 60) {
                        segundo = 0;
                        minuto = minuto +1;
                    }
                }
                tempo = (minuto + ":" + segundo +":"+milisegundo);
                relogio.setText(tempo);
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Erro no relogio!");
            }
        }
    }
    
}
