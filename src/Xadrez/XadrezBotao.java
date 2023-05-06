/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904

package Xadrez;

import gui.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import pecas.*;



/**
 *
 * @author Allan
 */
public class XadrezBotao implements Serializable{
    
    private ArrayList<Peca> Branco, Preto;
    private static XadrezBotao XadrezSingleton = null;
    int turno;
    private Posicao matrizTabuleiro[][];

    public Posicao[][] getMatrizTabuleiro() {
        return matrizTabuleiro;
    }

    public void setMatrizTabuleiro(Posicao[][] matrizTabuleiro) {
        this.matrizTabuleiro = matrizTabuleiro;
    }
    
    public void mudaLabel(JLabel label, Peca p)
    {
        if ((turno%2 == 0) && (this.getBranco().contains(p))) {
            label.setText("Branco");
        }
        
        else if((turno%2==1)&&(this.getPreto().contains(p)))
        {
            label.setText("Preto");
        }
       
    }
    
    public String retornaTurno()
    {
        String t;
        if (turno%2 == 0) {
            t = "Branco";
            return t;
        }
        else{
            t = "Preto";
            return t;
        }
    }
    
    public void proxTurno()
    {
        turno++;
    }
    
    public boolean verifTurno(Peca p)
    {
        if ((turno%2 == 0) && p.getCor() == Peca.Cor.branca) {
            return true;
        }
        
        else if((turno%2==1)&& p.getCor() == Peca.Cor.preta)
        {
            return true;
        }
        
        else{
            return false;
        }
    }
    
    public void setTurno(int turno)
    {
        this.turno = turno;
    }
    
    public int getTurno()
    {
        return this.turno;
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

    public static XadrezBotao getXadrezSingleton() {
        return XadrezSingleton;
    }
    
    public void fimDoJogoBranco()
    {
        for (Peca p: this.Branco) {
            if (p instanceof Rei) {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Fim de jogo, preta venceu.");
        System.exit(0);
    }
    
    public void remove(Peca p){
        if (p != null) {
            
            if (p.getCor() == Peca.Cor.branca) {
                Branco.remove(p);
            }
            else{
                Preto.remove(p);
            }
            
        }
    }
    
    public void fimDoJogoPreto()
    {
        for (Peca p: this.Preto) {
            if (p instanceof Rei) {
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Fim de jogo, branca venceu.");
        System.exit(0);
    }
    
    private static Socket socket;
    private static ServerSocket serversocket;
    private static int time;
    
    public XadrezBotao(Inicio inicio, int time, Socket socket, ServerSocket serversocket)
    {
        this.time = time;
        this.socket = socket;
        this.serversocket = serversocket;
        this.inicio = inicio;
        XadrezSingleton = this;
        Branco = new ArrayList<Peca>();
        Preto = new ArrayList<Peca>();
        Tabuleiro tab = new Tabuleiro(matrizTabuleiro, time, socket, serversocket, inicio);
        
        this.turno = 0;
        
        FactoryPeca factory = new FactoryPeca();
        
        Branco.add(factory.getPeca("Torre", Peca.Cor.branca, matrizTabuleiro[0][0]));
        Branco.add(factory.getPeca("Cavalo",Peca.Cor.branca, matrizTabuleiro[0][1]));
        Branco.add(factory.getPeca("Bispo",Peca.Cor.branca, matrizTabuleiro[0][2]));
        Branco.add(factory.getPeca("Rainha", Peca.Cor.branca, matrizTabuleiro[0][3]));
        Branco.add(factory.getPeca("Rei",Peca.Cor.branca, matrizTabuleiro[0][4]));
        Branco.add(factory.getPeca("Bispo",Peca.Cor.branca, matrizTabuleiro[0][5]));
        Branco.add(factory.getPeca("Cavalo",Peca.Cor.branca, matrizTabuleiro[0][6]));
        Branco.add(factory.getPeca("Torre",Peca.Cor.branca, matrizTabuleiro[0][7]));
        for(int i = 0; i<8; i++)
        {
            Branco.add(factory.getPeca("Peao",Peca.Cor.branca, matrizTabuleiro[1][i]));        
        }
        
        Preto.add(factory.getPeca("Torre",Peca.Cor.preta, matrizTabuleiro[7][0]));
        Preto.add(factory.getPeca("Cavalo",Peca.Cor.preta, matrizTabuleiro[7][1]));
        Preto.add(factory.getPeca("Bispo",Peca.Cor.preta, matrizTabuleiro[7][2]));
        Preto.add(factory.getPeca("Rainha",Peca.Cor.preta, matrizTabuleiro[7][3]));
        Preto.add(factory.getPeca("Rei",Peca.Cor.preta, matrizTabuleiro[7][4]));
        Preto.add(factory.getPeca("Bispo",Peca.Cor.preta, matrizTabuleiro[7][5]));
        Preto.add(factory.getPeca("Cavalo",Peca.Cor.preta, matrizTabuleiro[7][6]));
        Preto.add(factory.getPeca("Torre",Peca.Cor.preta, matrizTabuleiro[7][7]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][0]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][1]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][2]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][3]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][4]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][5]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][6]));
        Preto.add(factory.getPeca("Peao",Peca.Cor.preta, matrizTabuleiro[6][7]));
        
        tab.show();
    }
    private static Inicio inicio;
    /**
     * @param args the command line arguments
    public static void main(String[] args) {
        // TODO code application logic here
        XadrezBotao xadrez = new XadrezBotao(inicio, time, socket, serversocket);
    }
    */
    public void salvaBrancoPreto(ArrayList<Peca> Branco, ArrayList<Peca> Preto){
        try{
            FileOutputStream out1 = new FileOutputStream("Peca");
            ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
            
            objOut1.writeObject(Branco);
            objOut1.close();
            
            FileOutputStream out2 = new FileOutputStream("Peca2");
            ObjectOutputStream objOut2 = new ObjectOutputStream(out2);
            
            objOut2.writeObject(Preto);
            objOut2.close();
            
            FileOutputStream matriz = new FileOutputStream("MatrizTabuleiro");
            ObjectOutputStream objMatriz = new ObjectOutputStream(matriz);
            
            objMatriz.writeObject(this.getMatrizTabuleiro());
            objMatriz.close();
            
            FileOutputStream turno = new FileOutputStream("Turno");
            ObjectOutputStream objTurno = new ObjectOutputStream(turno);
            
            objTurno.writeObject(this.getTurno());
            objTurno.close();
            
        } catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
        } catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
        }
    }
 /*   
    public void desfazer(ArrayList<Peca> Branco, ArrayList<Peca> Preto){
        try{
            FileOutputStream out1 = new FileOutputStream("desfazer/Peca");
            ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
            
            objOut1.writeObject(Branco);
            objOut1.close();
            
            FileOutputStream out2 = new FileOutputStream("desfazer/Peca2");
            ObjectOutputStream objOut2 = new ObjectOutputStream(out2);
            
            objOut2.writeObject(Preto);
            objOut2.close();
            
            FileOutputStream matriz = new FileOutputStream("desfazer/MatrizTabuleiro");
            ObjectOutputStream objMatriz = new ObjectOutputStream(matriz);
            
            objMatriz.writeObject(this.getMatrizTabuleiro());
            objMatriz.close();
            
            FileOutputStream turno = new FileOutputStream("desfazer/Turno");
            ObjectOutputStream objTurno = new ObjectOutputStream(turno);
            
            objTurno.writeObject(this.getTurno());
            objTurno.close();
            
        } catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
        } catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
        }
    }
    
     public void refazer(ArrayList<Peca> Branco, ArrayList<Peca> Preto){
        try{
            FileOutputStream out1 = new FileOutputStream("refazer/Peca");
            ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
            
            objOut1.writeObject(Branco);
            objOut1.close();
            
            FileOutputStream out2 = new FileOutputStream("refazer/Peca2");
            ObjectOutputStream objOut2 = new ObjectOutputStream(out2);
            
            objOut2.writeObject(Preto);
            objOut2.close();
            
            FileOutputStream matriz = new FileOutputStream("refazer/MatrizTabuleiro");
            ObjectOutputStream objMatriz = new ObjectOutputStream(matriz);
            
            objMatriz.writeObject(this.getMatrizTabuleiro());
            objMatriz.close();
            
            FileOutputStream turno = new FileOutputStream("refazer/Turno");
            ObjectOutputStream objTurno = new ObjectOutputStream(turno);
            
            objTurno.writeObject(this.getTurno());
            objTurno.close();
            
        } catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
        } catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
        }
    }
    */
    
}
