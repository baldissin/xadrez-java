/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// //ALLAN BALDISSIN  8657904
package Xadrez;

import gui.Posicao;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;
import pecas.Peca;

/**
 *
 * @author Allan
 */
public class Salvar implements Serializable{
    
    private ArrayList<Peca> Branco, Preto;
    private int turno, minutos1, minutos2, minutos3, segundos1, segundos2, segundos3;
    private File salvaArquivo;
    private String log;
    
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getMinutos1() {
        return minutos1;
    }

    public void setMinutos1(int minutos1) {
        this.minutos1 = minutos1;
    }

    public int getMinutos2() {
        return minutos2;
    }

    public void setMinutos2(int minutos2) {
        this.minutos2 = minutos2;
    }

    public int getMinutos3() {
        return minutos3;
    }

    public void setMinutos3(int minutos3) {
        this.minutos3 = minutos3;
    }

    public int getSegundos1() {
        return segundos1;
    }

    public void setSegundos1(int segundos1) {
        this.segundos1 = segundos1;
    }

    public int getSegundos2() {
        return segundos2;
    }

    public void setSegundos2(int segundos2) {
        this.segundos2 = segundos2;
    }

    public int getSegundos3() {
        return segundos3;
    }

    public void setSegundos3(int segundos3) {
        this.segundos3 = segundos3;
    }
    private Posicao matrizTabuleiro[][];
    
    public Salvar(ArrayList<Peca> Branco,ArrayList<Peca> Preto, int turno, Posicao matrizTabuleiro[][], int minutos1, int segundos1, int minutos2, int segundos2, int minutos3, int segundos3, String log){
        this.Branco = Branco;
        this.Preto = Preto;
        this.turno = turno;
        this.matrizTabuleiro = matrizTabuleiro;
        this.minutos1 = minutos1;
        this.minutos2 = minutos2;
        this.minutos3 = minutos3;
        this.segundos1 = segundos1;
        this.segundos2 = segundos2;
        this.segundos3 = segundos3;
        this.salvaArquivo = null;
        this.log = log;
        
    }
    
    public ArrayList<Peca> getBranco() {
        return Branco;
    }

    public void setBranco(ArrayList<Peca> Branco) {
        this.Branco = Branco;
    }

    public ArrayList<Peca> getPreto() {
        return Preto;
    }

    public void setPreto(ArrayList<Peca> Preto) {
        this.Preto = Preto;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Posicao[][] getMatrizTabuleiro() {
        return matrizTabuleiro;
    }

    public void setMatrizTabuleiro(Posicao[][] matrizTabuleiro) {
        this.matrizTabuleiro = matrizTabuleiro;
    }
    
    
    public void salvarComo(){
        JFileChooser salvandoArquivo = new JFileChooser();  
        int resultado = salvandoArquivo.showSaveDialog(null);  
  
        FileFilter filefilter = new FileNameExtensionFilter("Arquivo",  
                ".arq");  
        salvandoArquivo.addChoosableFileFilter(filefilter);  
        if (resultado == JFileChooser.APPROVE_OPTION) {  
  
            File salvarArquivoEscolhido = salvandoArquivo.getSelectedFile();  
            this.salvaArquivo = salvarArquivoEscolhido;  
  
            if (salvarArquivoEscolhido.exists() == true) {  
  
                int selecionaOpcao = JOptionPane.showConfirmDialog(null,  
                        " O arquivo já existe, deseja sobrescreve-lo? ", null,  
                        JOptionPane.OK_CANCEL_OPTION);  
                if (selecionaOpcao == JOptionPane.OK_OPTION) {  
                    try{
                    FileOutputStream out1 = new FileOutputStream(salvaArquivo);  
                    ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
                    objOut1.writeObject(this);
                    objOut1.close();
                    } catch(FileNotFoundException e)
                    {
                        JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
                    } catch(IOException e)
                    {
                        JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
                    }
  
                }  
  
            } else {
                try{
                FileOutputStream out1 = new FileOutputStream(salvaArquivo);  
                ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
                objOut1.writeObject(this);
                objOut1.close();
                }catch(FileNotFoundException e)
                {
                    JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
                } catch(IOException e)
                {
                    JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
                }
            }  
  
        } 
    }
    
public File autoSalvar(){
        JFileChooser salvandoArquivo = new JFileChooser();  
        int resultado = salvandoArquivo.showSaveDialog(null);  
  
        FileFilter filefilter = new FileNameExtensionFilter("Arquivo",  
                ".arq");  
        salvandoArquivo.addChoosableFileFilter(filefilter);  
        if (resultado == JFileChooser.APPROVE_OPTION) {  
  
            File salvarArquivoEscolhido = salvandoArquivo.getSelectedFile();  
            this.salvaArquivo = salvarArquivoEscolhido;  
  
            if (salvarArquivoEscolhido.exists() == true) {  
  
                int selecionaOpcao = JOptionPane.showConfirmDialog(null,  
                        " O arquivo já existe, deseja sobrescreve-lo? ", null,  
                        JOptionPane.OK_CANCEL_OPTION);  
                if (selecionaOpcao == JOptionPane.OK_OPTION) {  
                    try{
                    FileOutputStream out1 = new FileOutputStream(salvaArquivo);  
                    ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
                    objOut1.writeObject(this);
                    objOut1.close();
                    } catch(FileNotFoundException e)
                    {
                        JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
                    } catch(IOException e)
                    {
                        JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
                    }
  
                }  
  
            } else {
                try{
                FileOutputStream out1 = new FileOutputStream(salvaArquivo);  
                ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
                objOut1.writeObject(this);
                objOut1.close();
                }catch(FileNotFoundException e)
                {
                    JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
                } catch(IOException e)
                {
                    JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
                }
            }  
  
        }
        
        return salvaArquivo;
    }
    
    
    
}
