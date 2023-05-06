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
public class RelogioJogador extends Thread implements Serializable{
    private int minutos, segundos, jogador, milissegundos;
    private String tempo;
    private JTextField relogio;
    

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    
    public RelogioJogador(JTextField relogio, int tempo, int jogador){
        minutos = tempo;
        segundos = 0;
        milissegundos = 1000;
        this.jogador = jogador;
        this.relogio = relogio;
    }
    
    public void run(){
        while(true){
            try {
                if (minutos == -1) {
                    if (jogador == 1) {
                    minutos = 0;
                    segundos = 0;
                    tempo = (minutos + ":" + segundos);
                    relogio.setText(tempo);
                    JOptionPane.showMessageDialog(null, "TEMPO ESGOTADO!  Jogador Preto ganhou.");
                    System.exit(0);
                    }
                    else
                    {
                        minutos = 0;
                        segundos = 0;
                        tempo = (minutos + ":" + segundos);
                        relogio.setText(tempo);
                        JOptionPane.showMessageDialog(null, "TEMPO ESGOTADO!  Jogador Branco ganhou.");
                        System.exit(0);
                    }
                }
                Relogio.sleep(1);
                milissegundos--;
                if(milissegundos == 0 || milissegundos == -1)
                {
                    segundos --;
                    milissegundos = 1000;
                    if(segundos == 0 || segundos == -1)
                    {
                        minutos--;
                        segundos = 59;
                    }
                    
                }
                tempo = (minutos + ":" + segundos + ":" + milissegundos);
                relogio.setText(tempo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro no relogio!");
            }
        }
    }
}
