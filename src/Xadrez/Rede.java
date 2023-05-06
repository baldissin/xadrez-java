/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//ALLAN BALDISSIN  8657904

package Xadrez;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Allan
 */
public class Rede {
    Socket cliente;
    ServerSocket servidor;
    ObjectOutputStream ou;
    ObjectInputStream in;
    
    public Rede(Socket cliente, ServerSocket servidor){
        if(cliente == null && servidor == null){
            
        }
        else try {
            this.cliente = cliente;
            this.servidor = servidor;
            if(servidor == null){
                ou = new ObjectOutputStream(cliente.getOutputStream());
                in = new ObjectInputStream(cliente.getInputStream());
            }
            else{
                ou = new ObjectOutputStream(cliente.getOutputStream());
                in = new ObjectInputStream(cliente.getInputStream());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Queda da conexção!");
            System.exit(1);
        }
    }
    
    public void send(Salvar sav){
        try {
            ou.writeObject(sav);
            ou.reset();
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar!");
        }
    }
    
    public Salvar receive(){
        Salvar sav = null;
        try {
            sav = (Salvar) in.readObject();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao receber!");
        } catch (ClassNotFoundException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao receber!");
        }
        catch(NullPointerException e){
            //JOptionPane.showMessageDialog(null, "Erro na conexão!");
            //System.exit(1);
        }
        return sav;
    }
}