/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904



package gui;

import Xadrez.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import pecas.Peca;
import pecas.*;
import pecas.Peca.Cor;

/**
 *
 * @author Allan
 */
public class Tabuleiro extends javax.swing.JFrame {

    XadrezBotao xadrez;
    Salvar salvar, desfazer, refazer, aux;
    private Thread online;
    
    private RelogioJogador relogioJogador1, relogioJogador2;
    private AutoSave save;
    Relogio relogio;
   
    /**
     * Creates new form Tabuleiro
     */
    
    private Socket cliente;
    private ServerSocket servidor;
    private boolean cli, serv, lan;
    private Inicio inicio;
    private Rede rede;
     
    
     public Tabuleiro(Posicao [][]matrizTabuleiro, int time, Socket socket, ServerSocket serversocket, Inicio inicio) {
        cliente = socket;
        servidor = serversocket;
        cli = false;
        serv = false;
        this.inicio = inicio;
        
        if(cliente == null && servidor == null){
            lan = false;
        }
        else if(servidor != null){
            serv = true;
            lan = true;
        }
        else{
            cli = true;
            lan = true;
        }
        rede = new Rede(cliente, servidor);
        if(lan){
        online = new Thread(new Runnable() {     
            public void run() {       
                while(true){
                        
                        Salvar load = rede.receive();
                        if(load == null){
                
                            JOptionPane.showMessageDialog(rootPane, "Queda na conexão!");
                            dispose();
                        }
                xadrez.setBranco(load.getBranco());
                xadrez.setPreto(load.getPreto());
                xadrez.setMatrizTabuleiro(load.getMatrizTabuleiro());
                xadrez.setTurno(load.getTurno());
                relogioJogador1.setMinutos(load.getMinutos1());
                relogioJogador1.setSegundos(load.getSegundos1());
                relogioJogador2.setMinutos(load.getMinutos2());
                relogioJogador2.setSegundos(load.getSegundos2());
                txtLog.setText(load.getLog());
                
                if (xadrez.getTurno() % 2 == 0) {
                    lblJogador.setText("Branco");
                    //relogioJogador1.resume();
                    txtJogadorPreto.setText(null);
                }
                else
                {
                    lblJogador.setText("Preto");
                    //relogioJogador2.resume();
                    txtJogadorBranco.setText(null);
               }
                
                relogio.setMinuto(load.getMinutos3());
                relogio.setSegundo(load.getSegundos3());
                
                //relogio.resume();
                
                xadrez.getMatrizTabuleiro()[0][0].setBotao(btn70);
                xadrez.getMatrizTabuleiro()[0][1].setBotao(btn71);
                xadrez.getMatrizTabuleiro()[0][2].setBotao(btn72);
                xadrez.getMatrizTabuleiro()[0][3].setBotao(btn73);
                xadrez.getMatrizTabuleiro()[0][4].setBotao(btn74);
                xadrez.getMatrizTabuleiro()[0][5].setBotao(btn75);
                xadrez.getMatrizTabuleiro()[0][6].setBotao(btn76);
                xadrez.getMatrizTabuleiro()[0][7].setBotao(btn77);
                xadrez.getMatrizTabuleiro()[1][0].setBotao(btn60);
                xadrez.getMatrizTabuleiro()[1][1].setBotao(btn61);
                xadrez.getMatrizTabuleiro()[1][2].setBotao(btn62);
                xadrez.getMatrizTabuleiro()[1][3].setBotao(btn63);
                xadrez.getMatrizTabuleiro()[1][4].setBotao(btn64);
                xadrez.getMatrizTabuleiro()[1][5].setBotao(btn65);
                xadrez.getMatrizTabuleiro()[1][6].setBotao(btn66);
                xadrez.getMatrizTabuleiro()[1][7].setBotao(btn67);
                xadrez.getMatrizTabuleiro()[2][0].setBotao(btn50);
                xadrez.getMatrizTabuleiro()[2][1].setBotao(btn51);
                xadrez.getMatrizTabuleiro()[2][2].setBotao(btn52);
                xadrez.getMatrizTabuleiro()[2][3].setBotao(btn53);
                xadrez.getMatrizTabuleiro()[2][4].setBotao(btn54);
                xadrez.getMatrizTabuleiro()[2][5].setBotao(btn55);
                xadrez.getMatrizTabuleiro()[2][6].setBotao(btn56);
                xadrez.getMatrizTabuleiro()[2][7].setBotao(btn57);
                xadrez.getMatrizTabuleiro()[3][0].setBotao(btn40);
                xadrez.getMatrizTabuleiro()[3][1].setBotao(btn41);
                xadrez.getMatrizTabuleiro()[3][2].setBotao(btn42);
                xadrez.getMatrizTabuleiro()[3][3].setBotao(btn43);
                xadrez.getMatrizTabuleiro()[3][4].setBotao(btn44);
                xadrez.getMatrizTabuleiro()[3][5].setBotao(btn45);
                xadrez.getMatrizTabuleiro()[3][6].setBotao(btn46);
                xadrez.getMatrizTabuleiro()[3][7].setBotao(btn47);
                xadrez.getMatrizTabuleiro()[4][0].setBotao(btn30);
                xadrez.getMatrizTabuleiro()[4][1].setBotao(btn31);
                xadrez.getMatrizTabuleiro()[4][2].setBotao(btn32);
                xadrez.getMatrizTabuleiro()[4][3].setBotao(btn33);
                xadrez.getMatrizTabuleiro()[4][4].setBotao(btn34);
                xadrez.getMatrizTabuleiro()[4][5].setBotao(btn35);
                xadrez.getMatrizTabuleiro()[4][6].setBotao(btn36);
                xadrez.getMatrizTabuleiro()[4][7].setBotao(btn37);
                xadrez.getMatrizTabuleiro()[5][0].setBotao(btn20);
                xadrez.getMatrizTabuleiro()[5][1].setBotao(btn21);
                xadrez.getMatrizTabuleiro()[5][2].setBotao(btn22);
                xadrez.getMatrizTabuleiro()[5][3].setBotao(btn23);
                xadrez.getMatrizTabuleiro()[5][4].setBotao(btn24);
                xadrez.getMatrizTabuleiro()[5][5].setBotao(btn25);
                xadrez.getMatrizTabuleiro()[5][6].setBotao(btn26);
                xadrez.getMatrizTabuleiro()[5][7].setBotao(btn27);
                xadrez.getMatrizTabuleiro()[6][0].setBotao(btn10);
                xadrez.getMatrizTabuleiro()[6][1].setBotao(btn11);
                xadrez.getMatrizTabuleiro()[6][2].setBotao(btn12);
                xadrez.getMatrizTabuleiro()[6][3].setBotao(btn13);
                xadrez.getMatrizTabuleiro()[6][4].setBotao(btn14);
                xadrez.getMatrizTabuleiro()[6][5].setBotao(btn15);
                xadrez.getMatrizTabuleiro()[6][6].setBotao(btn16);
                xadrez.getMatrizTabuleiro()[6][7].setBotao(btn17);
                xadrez.getMatrizTabuleiro()[7][0].setBotao(btn00);
                xadrez.getMatrizTabuleiro()[7][1].setBotao(btn01);
                xadrez.getMatrizTabuleiro()[7][2].setBotao(btn02);
                xadrez.getMatrizTabuleiro()[7][3].setBotao(btn03);
                xadrez.getMatrizTabuleiro()[7][4].setBotao(btn04);
                xadrez.getMatrizTabuleiro()[7][5].setBotao(btn05);
                xadrez.getMatrizTabuleiro()[7][6].setBotao(btn06);
                xadrez.getMatrizTabuleiro()[7][7].setBotao(btn07);
                
                for(int i = 7; i >= 0; i--)
                {
                    for(int j = 0; j < 8; j++)
                    {                
                        
                        if (xadrez.getMatrizTabuleiro()[i][j].getPeca() != null) {
                            xadrez.getMatrizTabuleiro()[i][j].getPeca().desenha();
                        }
                        else
                        {
                            xadrez.getMatrizTabuleiro()[i][j].desenha();
                        }
                        
                    }
                }
                        }
                        }
        });
        online.start();
        }
        initComponents();
        txtLog.setText("Cor - Peca - Casa");
        xadrez = XadrezBotao.getXadrezSingleton();
        matrizTabuleiro = new Posicao[8][8];
        int cont = 0;
        Refazer.hide();
        relogio = new Relogio(txtRelogio);
        int tempo = time;
        
        relogioJogador1 = new RelogioJogador(txtJogadorBranco, tempo, 1);
        relogioJogador2 = new RelogioJogador(txtJogadorPreto, tempo, 2);
        
        for(int i = 7; i >= 0; i--)
        {
            for(int j = 0; j < 8; j++)
            {
                if((cont % 2) == 0)
                {
                    matrizTabuleiro[i][j] = new Posicao(Peca.Cor.branca, i, j);
                }
            
                else
                {
                    matrizTabuleiro[i][j] = new Posicao(Peca.Cor.preta, i, j);
                }
                cont++;
            }
            
            cont++;
        }
        
        
        
        matrizTabuleiro[0][0].setBotao(btn70);
        matrizTabuleiro[0][1].setBotao(btn71);
        matrizTabuleiro[0][2].setBotao(btn72);
        matrizTabuleiro[0][3].setBotao(btn73);
        matrizTabuleiro[0][4].setBotao(btn74);
        matrizTabuleiro[0][5].setBotao(btn75);
        matrizTabuleiro[0][6].setBotao(btn76);
        matrizTabuleiro[0][7].setBotao(btn77);
        matrizTabuleiro[1][0].setBotao(btn60);
        matrizTabuleiro[1][1].setBotao(btn61);
        matrizTabuleiro[1][2].setBotao(btn62);
        matrizTabuleiro[1][3].setBotao(btn63);
        matrizTabuleiro[1][4].setBotao(btn64);
        matrizTabuleiro[1][5].setBotao(btn65);
        matrizTabuleiro[1][6].setBotao(btn66);
        matrizTabuleiro[1][7].setBotao(btn67);
        matrizTabuleiro[2][0].setBotao(btn50);
        matrizTabuleiro[2][1].setBotao(btn51);
        matrizTabuleiro[2][2].setBotao(btn52);
        matrizTabuleiro[2][3].setBotao(btn53);
        matrizTabuleiro[2][4].setBotao(btn54);
        matrizTabuleiro[2][5].setBotao(btn55);
        matrizTabuleiro[2][6].setBotao(btn56);
        matrizTabuleiro[2][7].setBotao(btn57);
        matrizTabuleiro[3][0].setBotao(btn40);
        matrizTabuleiro[3][1].setBotao(btn41);
        matrizTabuleiro[3][2].setBotao(btn42);
        matrizTabuleiro[3][3].setBotao(btn43);
        matrizTabuleiro[3][4].setBotao(btn44);
        matrizTabuleiro[3][5].setBotao(btn45);
        matrizTabuleiro[3][6].setBotao(btn46);
        matrizTabuleiro[3][7].setBotao(btn47);
        matrizTabuleiro[4][0].setBotao(btn30);
        matrizTabuleiro[4][1].setBotao(btn31);
        matrizTabuleiro[4][2].setBotao(btn32);
        matrizTabuleiro[4][3].setBotao(btn33);
        matrizTabuleiro[4][4].setBotao(btn34);
        matrizTabuleiro[4][5].setBotao(btn35);
        matrizTabuleiro[4][6].setBotao(btn36);
        matrizTabuleiro[4][7].setBotao(btn37);
        matrizTabuleiro[5][0].setBotao(btn20);
        matrizTabuleiro[5][1].setBotao(btn21);
        matrizTabuleiro[5][2].setBotao(btn22);
        matrizTabuleiro[5][3].setBotao(btn23);
        matrizTabuleiro[5][4].setBotao(btn24);
        matrizTabuleiro[5][5].setBotao(btn25);
        matrizTabuleiro[5][6].setBotao(btn26);
        matrizTabuleiro[5][7].setBotao(btn27);
        matrizTabuleiro[6][0].setBotao(btn10);
        matrizTabuleiro[6][1].setBotao(btn11);
        matrizTabuleiro[6][2].setBotao(btn12);
        matrizTabuleiro[6][3].setBotao(btn13);
        matrizTabuleiro[6][4].setBotao(btn14);
        matrizTabuleiro[6][5].setBotao(btn15);
        matrizTabuleiro[6][6].setBotao(btn16);
        matrizTabuleiro[6][7].setBotao(btn17);
        matrizTabuleiro[7][0].setBotao(btn00);
        matrizTabuleiro[7][1].setBotao(btn01);
        matrizTabuleiro[7][2].setBotao(btn02);
        matrizTabuleiro[7][3].setBotao(btn03);
        matrizTabuleiro[7][4].setBotao(btn04);
        matrizTabuleiro[7][5].setBotao(btn05);
        matrizTabuleiro[7][6].setBotao(btn06);
        matrizTabuleiro[7][7].setBotao(btn07);
        
        for(int i = 7; i >= 0; i--)
        {
            for(int j = 0; j < 8; j++)
            {
                matrizTabuleiro[i][j].desenha();
            }
        }
        
        save = new AutoSave(relogio, relogioJogador1, relogioJogador2, xadrez, txtLog.getText());
        
        xadrez.setMatrizTabuleiro(matrizTabuleiro);
        relogio.start();
        if(!lan)
        {
            relogioJogador1.start();
            relogioJogador2.start();
            save.start();
        }
        else{
            jMenu1.disable();
            jMenu2.disable();
        }
        relogioJogador2.suspend();
        save.suspend();
        
        refazer = new Salvar(xadrez.getBranco(), xadrez.getPreto(), xadrez.getTurno(), xadrez.getMatrizTabuleiro(), relogioJogador1.getMinutos(),relogioJogador1.getSegundos(), relogioJogador2.getMinutos(), relogioJogador2.getSegundos(), relogio.getMinuto(), relogio.getSegundo(), txtLog.getText());
        desfazer = new Salvar(xadrez.getBranco(), xadrez.getPreto(), xadrez.getTurno(), xadrez.getMatrizTabuleiro(), relogioJogador1.getMinutos(),relogioJogador1.getSegundos(), relogioJogador2.getMinutos(), relogioJogador2.getSegundos(), relogio.getMinuto(), relogio.getSegundo(), txtLog.getText());
        
    }
     
     
     int jogada = 0, x, y;
     private Peca pecaAux;
     
     public void escreveLog(Peca p, int x, int y, JLabel jogador){
         char peca = 0, letra = 0, numero = 0;
         String cor, aux = txtLog.getText();
         
         if(p instanceof Peao){
            peca = 'P';
         }
         if(p instanceof Bispo){
            peca = 'B';
         }
         if(p instanceof Cavalo){
            peca = 'P';
         }
         if(p instanceof Dama){
            peca = 'D';
         }
         if(p instanceof Rei){
            peca = 'R';
         }
         if(p instanceof Torre){
            peca = 'T';
         }
        if(x == 7){
            numero = '8';
        }
        if(x == 6){
            numero = '7';
        }
        if(x == 5){
            numero = '6';
        }
        if(x == 4){
            numero = '5';
        }
        if(x == 3){
            numero = '4';
        }
        if(x == 2){
            numero = '3';
        }
        if(x == 1){
            numero = '2';
        }
        if(x == 0){
            numero = '1';
        }
        if(y == 0){
            letra = 'A';
        }
        if(y == 1){
            letra = 'B';
        }
        if(y == 2){
            letra = 'C';
        }
        if(y == 3){
            letra = 'D';
        }
        if(y == 4){
            letra = 'E';
        }
        if(y == 5){
            letra = 'F';
        }
        if(y == 6){
            letra = 'G';
        }
        if(y == 7){
            letra = 'H';
        }
        
        cor = jogador.getText();
        
        txtLog.setText(aux + "\n"+cor+" "+ peca +" "+ letra+"" + numero);
     }
     
         
    public void retiraPecasSelecionadas(){
        for(int x = 0; x<8; x++){
            for(int y = 0; y < 8; y++){
                if (xadrez.getMatrizTabuleiro()[x][y].getPeca() == null) {
                    if (xadrez.getMatrizTabuleiro()[x][y].getCor() == Peca.Cor.branca) {
                        xadrez.getMatrizTabuleiro()[x][y].getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/casaBranca.jpg")));
                    }
                    else
                    {
                        xadrez.getMatrizTabuleiro()[x][y].getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/casaPreta.jpg")));
                    }
                }
            }
        }
    }
    
    public void retiraAtaque(){
        for(int x = 0; x<8; x++){
            for(int y = 0; y < 8; y++){
                if (xadrez.getMatrizTabuleiro()[x][y].getPeca() != null) {
                    xadrez.getMatrizTabuleiro()[x][y].getPeca().desenha();
                }
            }
        }
    }
     
     public void movimento(int x,int y)
     { 
         if(!lan){
               if(jogada == 0)
               {
                     desfazer.setBranco(xadrez.getBranco());
                     desfazer.setPreto(xadrez.getPreto());
                     desfazer.setTurno(xadrez.getTurno());
                     desfazer.setMatrizTabuleiro(xadrez.getMatrizTabuleiro());
                     desfazer.setMinutos1(relogioJogador1.getMinutos());
                     desfazer.setSegundos1(relogioJogador1.getSegundos());
                     desfazer.setMinutos2(relogioJogador2.getMinutos());
                     desfazer.setSegundos2(relogioJogador2.getSegundos());
                     desfazer.setMinutos3(relogio.getMinuto());
                     desfazer.setSegundos3(relogio.getSegundo());
                     desfazer.setLog(txtLog.getText());
                   if(xadrez.getMatrizTabuleiro()[x][y].getPeca() != null)
                   {
                       if(xadrez.verifTurno(xadrez.getMatrizTabuleiro()[x][y].getPeca())){
                                pecaAux = xadrez.getMatrizTabuleiro()[x][y].getPeca();
                                pecaAux.casasPossiveisSelecionadas();
                                pecaAux.casaPossiveisAtaque();
                                jogada = 1;
                           }

                       }
                   }
         
                
               else if(jogada == 1)
               {
                   if(pecaAux != null)
                   {
                       if(pecaAux.getClass() == Peao.class)
                       {
                           try {
                               
                                pecaAux.verifMovimento(xadrez.getMatrizTabuleiro()[x][y]);
                                xadrez.getMatrizTabuleiro()[pecaAux.getCasa().getX()][pecaAux.getCasa().getY()].setPeca(null);
                                xadrez.remove(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                xadrez.getMatrizTabuleiro()[x][y].setPeca(pecaAux);
                                pecaAux.getCasa().desenha();
                                pecaAux.setCasa(xadrez.getMatrizTabuleiro()[x][y]);
                                if(x == 7 || x == 0){
                                    Promocao promocao = new Promocao(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                    promocao.show();
                                }
                                else
                                    xadrez.getMatrizTabuleiro()[x][y].getPeca().desenha();
                                
                                escreveLog(xadrez.getMatrizTabuleiro()[x][y].getPeca(), x, y, lblJogador);
                                xadrez.proxTurno();
                                lblJogador.setText(xadrez.retornaTurno());
                                if (lblJogador.getText() == "Branco") {
                                        relogioJogador2.suspend();
                                        relogioJogador1.resume();
                                    }
                                else{
                                    relogioJogador1.suspend();
                                    relogioJogador2.resume();
                                }
                                retiraPecasSelecionadas();
                                retiraAtaque();
                                xadrez.fimDoJogoBranco();
                                xadrez.fimDoJogoPreto();
                                jogada = 0;
                                
                           }
                           
                               catch(IlegalChessMoviment e) {
                               try {
                                    pecaAux.verifComer(xadrez.getMatrizTabuleiro()[x][y]);
                                    xadrez.getMatrizTabuleiro()[pecaAux.getCasa().getX()][pecaAux.getCasa().getY()].setPeca(null);
                                    xadrez.remove(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                    xadrez.getMatrizTabuleiro()[x][y].setPeca(pecaAux);
                                    pecaAux.getCasa().desenha();
                                    pecaAux.setCasa(xadrez.getMatrizTabuleiro()[x][y]);
                                    xadrez.getMatrizTabuleiro()[x][y].getPeca().desenha();
                                    xadrez.proxTurno();
                                    escreveLog(xadrez.getMatrizTabuleiro()[x][y].getPeca(), x, y, lblJogador);
                                    lblJogador.setText(xadrez.retornaTurno());
                                    if (lblJogador.getText() == "Branco") {
                                        relogioJogador2.suspend();
                                        relogioJogador1.resume();
                                    }
                                    else{
                                        relogioJogador1.suspend();
                                        relogioJogador2.resume();
                                    }
                                    jogada = 0;
                                    retiraPecasSelecionadas();
                                    retiraAtaque();
                                    xadrez.fimDoJogoBranco();
                                    xadrez.fimDoJogoPreto();
                                    } catch (IlegalChessMoviment ex) {
                                        JOptionPane.showMessageDialog(null, "Movimento Ilegal");
                                        jogada =0;
                                        retiraPecasSelecionadas();
                                        retiraAtaque();
                                    }
                                    
                               }
                               
                       
                           jogada =0;
                           retiraPecasSelecionadas();
                           retiraAtaque();
                       }
                       else{
                           
                           try{
                               pecaAux.verifMovimento(xadrez.getMatrizTabuleiro()[x][y]);
                                xadrez.getMatrizTabuleiro()[pecaAux.getCasa().getX()][pecaAux.getCasa().getY()].setPeca(null);
                                xadrez.remove(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                xadrez.getMatrizTabuleiro()[x][y].setPeca(pecaAux);
                                pecaAux.getCasa().desenha();
                                pecaAux.setCasa(xadrez.getMatrizTabuleiro()[x][y]);
                                xadrez.getMatrizTabuleiro()[x][y].getPeca().desenha();
                                jogada = 0;
                                xadrez.proxTurno();
                                escreveLog(xadrez.getMatrizTabuleiro()[x][y].getPeca(), x, y, lblJogador);
                                lblJogador.setText(xadrez.retornaTurno());
                                if (lblJogador.getText() == "Branco") {
                                        relogioJogador2.suspend();
                                        relogioJogador1.resume();
                                    }
                                    else{
                                        relogioJogador1.suspend();
                                        relogioJogador2.resume();
                                    }
                                retiraPecasSelecionadas();
                                retiraAtaque();
                                xadrez.fimDoJogoBranco();
                                xadrez.fimDoJogoPreto();
                           }
                           catch(IlegalChessMoviment e){
                               JOptionPane.showMessageDialog(null, "Movimento Ilegal");
                               jogada =0;
                               retiraPecasSelecionadas();
                               retiraAtaque();
                               xadrez.fimDoJogoBranco();
                               xadrez.fimDoJogoPreto();
                           }
                       }
                        
                        Refazer.hide();
                        Desfazer.show();
                   
                   }
                   
                   xadrez.fimDoJogoBranco();
                   xadrez.fimDoJogoPreto();
                   
               }
               xadrez.fimDoJogoBranco();
               xadrez.fimDoJogoPreto();
     }
         else if(lan){
             if(jogada == 0)
               {
                   if(xadrez.getMatrizTabuleiro()[x][y].getPeca() != null)
                   {
                       if((serv && xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Cor.preta && xadrez.getTurno()%2 == 1) || (!serv && xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Cor.branca && xadrez.getTurno()%2 == 0)){
                                pecaAux = xadrez.getMatrizTabuleiro()[x][y].getPeca();
                                pecaAux.casasPossiveisSelecionadas();
                                pecaAux.casaPossiveisAtaque();
                                jogada = 1;
                           }

                       }
                   }
         
                
               else if(jogada == 1)
               {
                   if(pecaAux != null)
                   {
                       if(pecaAux.getClass() == Peao.class)
                       {
                           try {
                               
                                pecaAux.verifMovimento(xadrez.getMatrizTabuleiro()[x][y]);
                                xadrez.getMatrizTabuleiro()[pecaAux.getCasa().getX()][pecaAux.getCasa().getY()].setPeca(null);
                                xadrez.remove(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                xadrez.getMatrizTabuleiro()[x][y].setPeca(pecaAux);
                                pecaAux.getCasa().desenha();
                                pecaAux.setCasa(xadrez.getMatrizTabuleiro()[x][y]);
                                if(x == 7 || x == 0){
                                    Promocao promocao = new Promocao(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                    promocao.show();
                                }
                                else
                                    xadrez.getMatrizTabuleiro()[x][y].getPeca().desenha();
                                
                                escreveLog(xadrez.getMatrizTabuleiro()[x][y].getPeca(), x, y, lblJogador);
                                xadrez.proxTurno();
                                lblJogador.setText(xadrez.retornaTurno());
                                if (lblJogador.getText() == "Branco") {
                                        relogioJogador2.suspend();
                                        relogioJogador1.resume();
                                    }
                                else{
                                    relogioJogador1.suspend();
                                    relogioJogador2.resume();
                                }
                                retiraPecasSelecionadas();
                                retiraAtaque();
                                xadrez.fimDoJogoBranco();
                                xadrez.fimDoJogoPreto();
                                jogada = 0;
                                //desfazer
                                
                           }
                           
                               catch(IlegalChessMoviment e) {
                               try {
                                   pecaAux.verifComer(xadrez.getMatrizTabuleiro()[x][y]);
                                   xadrez.getMatrizTabuleiro()[pecaAux.getCasa().getX()][pecaAux.getCasa().getY()].setPeca(null);
                                    xadrez.remove(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                    xadrez.getMatrizTabuleiro()[x][y].setPeca(pecaAux);
                                    pecaAux.getCasa().desenha();
                                    pecaAux.setCasa(xadrez.getMatrizTabuleiro()[x][y]);
                                    xadrez.getMatrizTabuleiro()[x][y].getPeca().desenha();
                                    xadrez.proxTurno();
                                    escreveLog(xadrez.getMatrizTabuleiro()[x][y].getPeca(), x, y, lblJogador);
                                    lblJogador.setText(xadrez.retornaTurno());
                                    if (lblJogador.getText() == "Branco") {
                                        relogioJogador2.suspend();
                                        relogioJogador1.resume();
                                    }
                                    else{
                                        relogioJogador1.suspend();
                                        relogioJogador2.resume();
                                    }
                                    jogada = 0;
                                    retiraPecasSelecionadas();
                                    retiraAtaque();
                                    xadrez.fimDoJogoBranco();
                                    xadrez.fimDoJogoPreto();
                                    } catch (IlegalChessMoviment ex) {
                                        JOptionPane.showMessageDialog(null, "Movimento Ilegal");
                                        jogada =0;
                                        retiraPecasSelecionadas();
                                        retiraAtaque();
                                    }
                                    
                               }
                               
                       
                           jogada =0;
                           retiraPecasSelecionadas();
                           retiraAtaque();
                       }
                       else{
                           
                           try{
                               pecaAux.verifMovimento(xadrez.getMatrizTabuleiro()[x][y]);
                                xadrez.getMatrizTabuleiro()[pecaAux.getCasa().getX()][pecaAux.getCasa().getY()].setPeca(null);
                                xadrez.remove(xadrez.getMatrizTabuleiro()[x][y].getPeca());
                                xadrez.getMatrizTabuleiro()[x][y].setPeca(pecaAux);
                                pecaAux.getCasa().desenha();
                                pecaAux.setCasa(xadrez.getMatrizTabuleiro()[x][y]);
                                xadrez.getMatrizTabuleiro()[x][y].getPeca().desenha();
                                jogada = 0;
                                xadrez.proxTurno();
                                escreveLog(xadrez.getMatrizTabuleiro()[x][y].getPeca(), x, y, lblJogador);
                                lblJogador.setText(xadrez.retornaTurno());
                                if (lblJogador.getText() == "Branco") {
                                        relogioJogador2.suspend();
                                        relogioJogador1.resume();
                                    }
                                    else{
                                        relogioJogador1.suspend();
                                        relogioJogador2.resume();
                                    }
                                retiraPecasSelecionadas();
                                retiraAtaque();
                                xadrez.fimDoJogoBranco();
                                xadrez.fimDoJogoPreto();
                           }
                           catch(IlegalChessMoviment e){
                               JOptionPane.showMessageDialog(null, "Movimento Ilegal");
                               jogada =0;
                               retiraPecasSelecionadas();
                               retiraAtaque();
                               xadrez.fimDoJogoBranco();
                               xadrez.fimDoJogoPreto();
                           }
                       }
                        
                        Refazer.hide();
                        Desfazer.show();
                   
                   }
                   rede.send(setSave());
                   
                   xadrez.fimDoJogoBranco();
                   xadrez.fimDoJogoPreto();
                   
               }
               
               xadrez.fimDoJogoBranco();
               xadrez.fimDoJogoPreto();
         }
     }
     
     public Salvar setSave(){
         Salvar salv = new Salvar(xadrez.getBranco(), xadrez.getPreto(), xadrez.getTurno(), xadrez.getMatrizTabuleiro(), relogioJogador1.getMinutos(),relogioJogador1.getSegundos(), relogioJogador2.getMinutos(), relogioJogador2.getSegundos(), relogio.getMinuto(), relogio.getSegundo(), txtLog.getText());
         return salv;
     }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn00 = new javax.swing.JButton();
        btn01 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn30 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn02 = new javax.swing.JButton();
        btn40 = new javax.swing.JButton();
        btn03 = new javax.swing.JButton();
        btn50 = new javax.swing.JButton();
        btn04 = new javax.swing.JButton();
        btn70 = new javax.swing.JButton();
        btn05 = new javax.swing.JButton();
        btn60 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn06 = new javax.swing.JButton();
        btn07 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn41 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn61 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        btn71 = new javax.swing.JButton();
        btn51 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn42 = new javax.swing.JButton();
        btn43 = new javax.swing.JButton();
        btn52 = new javax.swing.JButton();
        btn53 = new javax.swing.JButton();
        btn62 = new javax.swing.JButton();
        btn63 = new javax.swing.JButton();
        btn72 = new javax.swing.JButton();
        btn73 = new javax.swing.JButton();
        btn64 = new javax.swing.JButton();
        btn35 = new javax.swing.JButton();
        btn75 = new javax.swing.JButton();
        btn74 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btn44 = new javax.swing.JButton();
        btn45 = new javax.swing.JButton();
        btn65 = new javax.swing.JButton();
        btn55 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn54 = new javax.swing.JButton();
        btn66 = new javax.swing.JButton();
        btn37 = new javax.swing.JButton();
        btn77 = new javax.swing.JButton();
        btn76 = new javax.swing.JButton();
        btn36 = new javax.swing.JButton();
        btn46 = new javax.swing.JButton();
        btn47 = new javax.swing.JButton();
        btn67 = new javax.swing.JButton();
        btn57 = new javax.swing.JButton();
        btn26 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        btn56 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblJogador = new javax.swing.JLabel();
        txtRelogio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJogadorBranco = new javax.swing.JTextField();
        txtJogadorPreto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Salvar = new javax.swing.JMenuItem();
        Abrir = new javax.swing.JMenuItem();
        AutoSave = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Desfazer = new javax.swing.JMenuItem();
        Refazer = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btn00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn00MouseClicked(evt);
            }
        });

        btn01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn01MouseClicked(evt);
            }
        });

        btn10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn10MouseClicked(evt);
            }
        });

        btn20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn20MouseClicked(evt);
            }
        });

        btn30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn30MouseClicked(evt);
            }
        });

        btn11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn11MouseClicked(evt);
            }
        });

        btn02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn02MouseClicked(evt);
            }
        });

        btn40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn40MouseClicked(evt);
            }
        });

        btn03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn03MouseClicked(evt);
            }
        });

        btn50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn50MouseClicked(evt);
            }
        });

        btn04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn04MouseClicked(evt);
            }
        });

        btn70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn70MouseClicked(evt);
            }
        });

        btn05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn05MouseClicked(evt);
            }
        });

        btn60.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn60MouseClicked(evt);
            }
        });

        btn12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn12MouseClicked(evt);
            }
        });

        btn06.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn06MouseClicked(evt);
            }
        });

        btn07.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn07MouseClicked(evt);
            }
        });

        btn13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn13MouseClicked(evt);
            }
        });

        btn14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn14MouseClicked(evt);
            }
        });

        btn21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn21MouseClicked(evt);
            }
        });

        btn15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn15MouseClicked(evt);
            }
        });

        btn41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn41MouseClicked(evt);
            }
        });

        btn16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn16MouseClicked(evt);
            }
        });

        btn61.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn61MouseClicked(evt);
            }
        });

        btn31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn31MouseClicked(evt);
            }
        });

        btn71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn71MouseClicked(evt);
            }
        });

        btn51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn51MouseClicked(evt);
            }
        });

        btn23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn23MouseClicked(evt);
            }
        });

        btn22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn22MouseClicked(evt);
            }
        });

        btn32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn32MouseClicked(evt);
            }
        });

        btn33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn33MouseClicked(evt);
            }
        });

        btn42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn42MouseClicked(evt);
            }
        });

        btn43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn43MouseClicked(evt);
            }
        });

        btn52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn52MouseClicked(evt);
            }
        });

        btn53.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn53MouseClicked(evt);
            }
        });

        btn62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn62MouseClicked(evt);
            }
        });

        btn63.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn63MouseClicked(evt);
            }
        });

        btn72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn72MouseClicked(evt);
            }
        });

        btn73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn73MouseClicked(evt);
            }
        });

        btn64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn64MouseClicked(evt);
            }
        });

        btn35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn35MouseClicked(evt);
            }
        });

        btn75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn75MouseClicked(evt);
            }
        });

        btn74.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn74MouseClicked(evt);
            }
        });

        btn34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn34MouseClicked(evt);
            }
        });

        btn44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn44MouseClicked(evt);
            }
        });

        btn45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn45MouseClicked(evt);
            }
        });

        btn65.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn65MouseClicked(evt);
            }
        });

        btn55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn55MouseClicked(evt);
            }
        });

        btn24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn24MouseClicked(evt);
            }
        });

        btn25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn25MouseClicked(evt);
            }
        });

        btn54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn54MouseClicked(evt);
            }
        });

        btn66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn66MouseClicked(evt);
            }
        });

        btn37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn37MouseClicked(evt);
            }
        });

        btn77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn77MouseClicked(evt);
            }
        });

        btn76.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn76MouseClicked(evt);
            }
        });

        btn36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn36MouseClicked(evt);
            }
        });

        btn46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn46MouseClicked(evt);
            }
        });

        btn47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn47MouseClicked(evt);
            }
        });

        btn67.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn67MouseClicked(evt);
            }
        });

        btn57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn57MouseClicked(evt);
            }
        });

        btn26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn26MouseClicked(evt);
            }
        });

        btn27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn27MouseClicked(evt);
            }
        });

        btn56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn56MouseClicked(evt);
            }
        });

        btn17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn17MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("8");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("7");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("6");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("5");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("4");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("3");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("2");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addComponent(jLabel7)
                        .addComponent(jLabel8))
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btn00, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn01, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn03, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn04, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn05, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn06, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn07, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn51, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn70, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn71, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn60, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn61, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn42, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn52, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn53, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn72, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn73, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn62, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn63, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn44, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn45, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn55, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn74, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn75, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn64, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn65, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn46, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn47, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn56, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn57, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn76, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn77, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn66, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn67, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn03, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn04, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn05, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn06, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn07, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn01, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(btn02, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn00, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel7)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel8)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn51, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(7, 7, 7))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn70, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(26, 26, 26))))
                            .addComponent(btn71, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn42, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn52, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn53, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(btn72, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn44, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn45, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn55, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(btn74, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn75, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn46, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn47, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn56, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn57, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(btn76, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn77, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setText("Xadrez");

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 0, 18)); // NOI18N
        jLabel2.setText("Vez do jogador");

        lblJogador.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        lblJogador.setText("Branco");

        txtRelogio.setEditable(false);

        jLabel3.setText("Tempo Total:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Relogio Branco              Relogio Preto");

        txtJogadorBranco.setEditable(false);

        txtJogadorPreto.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("A");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("B");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("C");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("D");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("E");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("F");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("G");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("H");

        jButton1.setText("Desistir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        txtLog.setEditable(false);
        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        jMenu1.setText("Arquivo");

        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/images.jpg"))); // NOI18N
        Salvar.setText("Salvar");
        Salvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalvarMouseClicked(evt);
            }
        });
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });
        jMenu1.add(Salvar);

        Abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/abrir.jpg"))); // NOI18N
        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        jMenu1.add(Abrir);

        AutoSave.setText("Habilitar Auto-Save (10 seg)");
        AutoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AutoSaveActionPerformed(evt);
            }
        });
        jMenu1.add(AutoSave);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Desfazer/Refazer");

        Desfazer.setText("Desfazer");
        Desfazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesfazerActionPerformed(evt);
            }
        });
        jMenu2.add(Desfazer);

        Refazer.setText("Refazer");
        Refazer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefazerActionPerformed(evt);
            }
        });
        jMenu2.add(Refazer);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel13)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel14)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel15)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel16)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel17)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel18)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel19)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(txtJogadorBranco, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtJogadorPreto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblJogador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblJogador)
                            .addComponent(jButton1))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJogadorBranco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJogadorPreto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18)
                        .addComponent(jLabel19)
                        .addComponent(jLabel20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn00MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn00MouseClicked
        x = 7;
        y = 0;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn00MouseClicked

    private void btn20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn20MouseClicked
        x = 5;
        y = 0;
        
        this.movimento(x, y);
    }//GEN-LAST:event_btn20MouseClicked

    private void btn01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn01MouseClicked
         x = 7;
        y = 1;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn01MouseClicked

    private void btn02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn02MouseClicked
         x = 7;
        y = 2;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn02MouseClicked

    private void btn03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn03MouseClicked
         x = 7;
        y = 3;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn03MouseClicked

    private void btn04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn04MouseClicked
         x = 7;
        y = 4;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn04MouseClicked

    private void btn05MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn05MouseClicked
         x = 7;
        y = 5;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn05MouseClicked

    private void btn06MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn06MouseClicked
         x = 7;
        y = 6;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn06MouseClicked

    private void btn07MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn07MouseClicked
         x = 7;
        y = 7;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn07MouseClicked

    private void btn10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn10MouseClicked
        x = 6;
        y = 0;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn10MouseClicked

    private void btn11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn11MouseClicked
         x = 6;
        y = 1;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn11MouseClicked

    private void btn12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn12MouseClicked
        x = 6;
        y = 2;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn12MouseClicked

    private void btn13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn13MouseClicked
         x = 6;
        y = 3;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn13MouseClicked

    private void btn14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn14MouseClicked
         x = 6;
        y = 4;
          
        this.movimento(x, y);
    }//GEN-LAST:event_btn14MouseClicked

    private void btn15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn15MouseClicked
         x = 6;
        y = 5;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn15MouseClicked

    private void btn16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn16MouseClicked
         x = 6;
        y = 6;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn16MouseClicked

    private void btn17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn17MouseClicked
         x = 6;
        y = 7;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn17MouseClicked

    private void btn21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn21MouseClicked
         x = 5;
        y = 1;
                
        this.movimento(x, y);
    }//GEN-LAST:event_btn21MouseClicked

    private void btn22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn22MouseClicked
         x = 5;
        y = 2;
                
        this.movimento(x, y);
    }//GEN-LAST:event_btn22MouseClicked

    private void btn23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn23MouseClicked
        x = 5;
        y = 3;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn23MouseClicked

    private void btn24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn24MouseClicked
         x = 5;
        y = 4;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn24MouseClicked

    private void btn25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn25MouseClicked
         x = 5;
        y = 5;
                
        this.movimento(x, y);
    }//GEN-LAST:event_btn25MouseClicked

    private void btn26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn26MouseClicked
         x = 5;
        y = 6;
                
        this.movimento(x, y);
    }//GEN-LAST:event_btn26MouseClicked

    private void btn27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn27MouseClicked
         x = 5;
        y = 7;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn27MouseClicked

    private void btn30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn30MouseClicked
        x = 4;
        y = 0;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn30MouseClicked

    private void btn31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn31MouseClicked
         x = 4;
        y = 1;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn31MouseClicked

    private void btn32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn32MouseClicked
         x = 4;
        y = 2;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn32MouseClicked

    private void btn33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn33MouseClicked
         x = 4;
        y = 3;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn33MouseClicked

    private void btn34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn34MouseClicked
        x = 4;
        y = 4;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn34MouseClicked

    private void btn35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn35MouseClicked
         x = 4;
        y = 5;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn35MouseClicked

    private void btn36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn36MouseClicked
         x = 4;
        y = 6;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn36MouseClicked

    private void btn37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn37MouseClicked
         x = 4;
        y = 7;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn37MouseClicked

    private void btn40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn40MouseClicked
         x = 3;
        y = 0;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn40MouseClicked

    private void btn41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn41MouseClicked
         x = 3;
        y = 1;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn41MouseClicked

    private void btn42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn42MouseClicked
         x = 3;
        y = 2;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn42MouseClicked

    private void btn43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn43MouseClicked
         x = 3;
        y = 3;
        
        this.movimento(x, y);
    }//GEN-LAST:event_btn43MouseClicked

    private void btn44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn44MouseClicked
         x = 3;
        y = 4;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn44MouseClicked

    private void btn45MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn45MouseClicked
         x = 3;
        y = 5;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn45MouseClicked

    private void btn46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn46MouseClicked
         x = 3;
        y = 6;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn46MouseClicked

    private void btn47MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn47MouseClicked
         x = 3;
        y = 7;
                 
        this.movimento(x, y);
    }//GEN-LAST:event_btn47MouseClicked

    private void btn50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn50MouseClicked
         x = 2;
        y = 0;
 
        this.movimento(x, y);
    }//GEN-LAST:event_btn50MouseClicked

    private void btn51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn51MouseClicked
         x = 2;
        y = 1;
        
        this.movimento(x, y);
    }//GEN-LAST:event_btn51MouseClicked

    private void btn52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn52MouseClicked
         x = 2;
        y = 2;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn52MouseClicked

    private void btn53MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn53MouseClicked
         x = 2;
        y = 3;
        
        
        this.movimento(x, y);
    }//GEN-LAST:event_btn53MouseClicked

    private void btn54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn54MouseClicked
         x = 2;
        y = 4;
        
        this.movimento(x, y);
    }//GEN-LAST:event_btn54MouseClicked

    private void btn55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn55MouseClicked
         x = 2;
        y = 5;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn55MouseClicked

    private void btn56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn56MouseClicked
         x = 2;
        y = 6;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn56MouseClicked

    private void btn57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn57MouseClicked
         x = 2;
        y = 7;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn57MouseClicked

    private void btn60MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn60MouseClicked
         x = 1;
        y = 0;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn60MouseClicked

    private void btn61MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn61MouseClicked
         x = 1;
        y = 1;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn61MouseClicked

    private void btn62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn62MouseClicked
         x = 1;
        y = 2;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn62MouseClicked

    private void btn63MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn63MouseClicked
         x = 1;
        y = 3;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn63MouseClicked

    private void btn64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn64MouseClicked
         x = 1;
        y = 4;
 
        this.movimento(x, y);
    }//GEN-LAST:event_btn64MouseClicked

    private void btn65MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn65MouseClicked
         x = 1;
        y = 5;
        
        this.movimento(x, y);
    }//GEN-LAST:event_btn65MouseClicked

    private void btn66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn66MouseClicked
         x = 1;
        y = 6;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn66MouseClicked

    private void btn67MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn67MouseClicked
         x = 1;
        y = 7;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn67MouseClicked

    private void btn70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn70MouseClicked
         x = 0;
        y = 0;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn70MouseClicked

    private void btn71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn71MouseClicked
         x = 0;
        y = 1;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn71MouseClicked

    private void btn72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn72MouseClicked
         x = 0;
        y = 2;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn72MouseClicked

    private void btn73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn73MouseClicked
         x = 0;
        y = 3;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn73MouseClicked

    private void btn75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn75MouseClicked
         x = 0;
        y = 5;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn75MouseClicked

    private void btn77MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn77MouseClicked
         x = 0;
        y = 7;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn77MouseClicked

    private void btn74MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn74MouseClicked
         x = 0;
        y = 4;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn74MouseClicked

    private void btn76MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn76MouseClicked
         x = 0;
        y = 6;
         
        this.movimento(x, y);
    }//GEN-LAST:event_btn76MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (lblJogador.getText().equals("Branco")) {
            JOptionPane.showMessageDialog(null, "Fim de jogo! Preta venceu.");
        }
        else{
            JOptionPane.showMessageDialog(null, "Fim de jogo! Branca venceu.");
        }
        
        System.exit(0);
    }//GEN-LAST:event_jButton1MouseClicked

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        salvar = new Salvar(xadrez.getBranco(), xadrez.getPreto(), xadrez.getTurno(), xadrez.getMatrizTabuleiro(), relogioJogador1.getMinutos(),relogioJogador1.getSegundos(), relogioJogador2.getMinutos(), relogioJogador2.getSegundos(), relogio.getMinuto(), relogio.getSegundo(), txtLog.getText());
        salvar.salvarComo();
    }//GEN-LAST:event_SalvarActionPerformed

    private void SalvarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalvarMouseClicked
        
    }//GEN-LAST:event_SalvarMouseClicked

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        relogioJogador1.suspend();
        relogioJogador2.suspend();
        relogioJogador1.setMinutos(0);
        relogioJogador1.setSegundos(0);
        relogioJogador2.setMinutos(0);
        relogioJogador2.setSegundos(0);
        relogio.suspend();
        relogio.setMinuto(0);
        relogio.setSegundo(0);
        
        JFileChooser jf = new JFileChooser();
        int resultado = jf.showOpenDialog(null);
        
        if(resultado == JFileChooser.APPROVE_OPTION){
            try{
                save.suspend();
                FileInputStream inc = new FileInputStream(jf.getSelectedFile());
                ObjectInputStream objInc = new ObjectInputStream(inc);                
                Salvar salv = (Salvar) objInc.readObject();
                xadrez.setBranco(salv.getBranco());
                xadrez.setPreto(salv.getPreto());
                xadrez.setMatrizTabuleiro(salv.getMatrizTabuleiro());
                xadrez.setTurno(salv.getTurno());
                relogioJogador1.setMinutos(salv.getMinutos1());
                relogioJogador1.setSegundos(salv.getSegundos1());
                relogioJogador2.setMinutos(salv.getMinutos2());
                relogioJogador2.setSegundos(salv.getSegundos2());
                txtLog.setText(salv.getLog());
                
                if (xadrez.getTurno() % 2 == 0) {
                    lblJogador.setText("Branco");
                    relogioJogador1.resume();
                    txtJogadorPreto.setText(null);
                }
                else
                {
                    lblJogador.setText("Preto");
                    relogioJogador2.resume();
                    txtJogadorBranco.setText(null);
               }
                
                relogio.setMinuto(salv.getMinutos3());
                relogio.setSegundo(salv.getSegundos3());
                
                relogio.resume();
                
                xadrez.getMatrizTabuleiro()[0][0].setBotao(btn70);
                xadrez.getMatrizTabuleiro()[0][1].setBotao(btn71);
                xadrez.getMatrizTabuleiro()[0][2].setBotao(btn72);
                xadrez.getMatrizTabuleiro()[0][3].setBotao(btn73);
                xadrez.getMatrizTabuleiro()[0][4].setBotao(btn74);
                xadrez.getMatrizTabuleiro()[0][5].setBotao(btn75);
                xadrez.getMatrizTabuleiro()[0][6].setBotao(btn76);
                xadrez.getMatrizTabuleiro()[0][7].setBotao(btn77);
                xadrez.getMatrizTabuleiro()[1][0].setBotao(btn60);
                xadrez.getMatrizTabuleiro()[1][1].setBotao(btn61);
                xadrez.getMatrizTabuleiro()[1][2].setBotao(btn62);
                xadrez.getMatrizTabuleiro()[1][3].setBotao(btn63);
                xadrez.getMatrizTabuleiro()[1][4].setBotao(btn64);
                xadrez.getMatrizTabuleiro()[1][5].setBotao(btn65);
                xadrez.getMatrizTabuleiro()[1][6].setBotao(btn66);
                xadrez.getMatrizTabuleiro()[1][7].setBotao(btn67);
                xadrez.getMatrizTabuleiro()[2][0].setBotao(btn50);
                xadrez.getMatrizTabuleiro()[2][1].setBotao(btn51);
                xadrez.getMatrizTabuleiro()[2][2].setBotao(btn52);
                xadrez.getMatrizTabuleiro()[2][3].setBotao(btn53);
                xadrez.getMatrizTabuleiro()[2][4].setBotao(btn54);
                xadrez.getMatrizTabuleiro()[2][5].setBotao(btn55);
                xadrez.getMatrizTabuleiro()[2][6].setBotao(btn56);
                xadrez.getMatrizTabuleiro()[2][7].setBotao(btn57);
                xadrez.getMatrizTabuleiro()[3][0].setBotao(btn40);
                xadrez.getMatrizTabuleiro()[3][1].setBotao(btn41);
                xadrez.getMatrizTabuleiro()[3][2].setBotao(btn42);
                xadrez.getMatrizTabuleiro()[3][3].setBotao(btn43);
                xadrez.getMatrizTabuleiro()[3][4].setBotao(btn44);
                xadrez.getMatrizTabuleiro()[3][5].setBotao(btn45);
                xadrez.getMatrizTabuleiro()[3][6].setBotao(btn46);
                xadrez.getMatrizTabuleiro()[3][7].setBotao(btn47);
                xadrez.getMatrizTabuleiro()[4][0].setBotao(btn30);
                xadrez.getMatrizTabuleiro()[4][1].setBotao(btn31);
                xadrez.getMatrizTabuleiro()[4][2].setBotao(btn32);
                xadrez.getMatrizTabuleiro()[4][3].setBotao(btn33);
                xadrez.getMatrizTabuleiro()[4][4].setBotao(btn34);
                xadrez.getMatrizTabuleiro()[4][5].setBotao(btn35);
                xadrez.getMatrizTabuleiro()[4][6].setBotao(btn36);
                xadrez.getMatrizTabuleiro()[4][7].setBotao(btn37);
                xadrez.getMatrizTabuleiro()[5][0].setBotao(btn20);
                xadrez.getMatrizTabuleiro()[5][1].setBotao(btn21);
                xadrez.getMatrizTabuleiro()[5][2].setBotao(btn22);
                xadrez.getMatrizTabuleiro()[5][3].setBotao(btn23);
                xadrez.getMatrizTabuleiro()[5][4].setBotao(btn24);
                xadrez.getMatrizTabuleiro()[5][5].setBotao(btn25);
                xadrez.getMatrizTabuleiro()[5][6].setBotao(btn26);
                xadrez.getMatrizTabuleiro()[5][7].setBotao(btn27);
                xadrez.getMatrizTabuleiro()[6][0].setBotao(btn10);
                xadrez.getMatrizTabuleiro()[6][1].setBotao(btn11);
                xadrez.getMatrizTabuleiro()[6][2].setBotao(btn12);
                xadrez.getMatrizTabuleiro()[6][3].setBotao(btn13);
                xadrez.getMatrizTabuleiro()[6][4].setBotao(btn14);
                xadrez.getMatrizTabuleiro()[6][5].setBotao(btn15);
                xadrez.getMatrizTabuleiro()[6][6].setBotao(btn16);
                xadrez.getMatrizTabuleiro()[6][7].setBotao(btn17);
                xadrez.getMatrizTabuleiro()[7][0].setBotao(btn00);
                xadrez.getMatrizTabuleiro()[7][1].setBotao(btn01);
                xadrez.getMatrizTabuleiro()[7][2].setBotao(btn02);
                xadrez.getMatrizTabuleiro()[7][3].setBotao(btn03);
                xadrez.getMatrizTabuleiro()[7][4].setBotao(btn04);
                xadrez.getMatrizTabuleiro()[7][5].setBotao(btn05);
                xadrez.getMatrizTabuleiro()[7][6].setBotao(btn06);
                xadrez.getMatrizTabuleiro()[7][7].setBotao(btn07);
                
                for(int i = 7; i >= 0; i--)
                {
                    for(int j = 0; j < 8; j++)
                    {                
                        
                        if (xadrez.getMatrizTabuleiro()[i][j].getPeca() != null) {
                            xadrez.getMatrizTabuleiro()[i][j].getPeca().desenha();
                        }
                        else
                        {
                            xadrez.getMatrizTabuleiro()[i][j].desenha();
                        }
                        
                    }
                }
                


            } catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }catch(ClassNotFoundException e){ 
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Erro: " + e);
            }
        }
    }//GEN-LAST:event_AbrirActionPerformed

    private void DesfazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesfazerActionPerformed
       refazer.setBranco(xadrez.getBranco());
       refazer.setPreto(xadrez.getPreto());
       refazer.setTurno(xadrez.getTurno());
       refazer.setMatrizTabuleiro(xadrez.getMatrizTabuleiro());
       refazer.setMinutos1(relogioJogador1.getMinutos());
       refazer.setSegundos1(relogioJogador1.getSegundos());
       refazer.setMinutos2(relogioJogador2.getMinutos());
       refazer.setSegundos2(relogioJogador2.getSegundos());
       refazer.setMinutos3(relogio.getMinuto());
       refazer.setSegundos3(relogio.getSegundo());
       refazer.setLog(txtLog.getText());
        relogioJogador1.suspend();
       relogioJogador2.suspend();
       relogioJogador1.setMinutos(0);
       relogioJogador1.setSegundos(0);
       relogioJogador2.setMinutos(0);
       relogioJogador2.setSegundos(0);
       relogio.suspend();
       relogio.setMinuto(0);
       relogio.setSegundo(0);
       Desfazer.hide();
       Refazer.show();
       xadrez.setTurno(desfazer.getTurno());
       xadrez.setBranco(desfazer.getBranco());
       xadrez.setPreto(desfazer.getPreto());
       xadrez.setMatrizTabuleiro(desfazer.getMatrizTabuleiro());
       relogioJogador1.setMinutos(desfazer.getMinutos1());
       relogioJogador2.setMinutos(desfazer.getMinutos2());
       relogio.setMinuto(desfazer.getMinutos3());
       relogioJogador1.setSegundos(desfazer.getSegundos1());
       relogioJogador2.setSegundos(desfazer.getSegundos2());
       relogio.setSegundo(desfazer.getSegundos3());
       
       if (xadrez.getTurno() % 2 == 0) {
                    lblJogador.setText("Branco");
                    relogioJogador1.resume();
                    txtJogadorPreto.setText(null);
                }
                else
                {
                    lblJogador.setText("Preto");
                    relogioJogador2.resume();
                    txtJogadorBranco.setText(null);
               }
                
                
                relogio.resume();
                
                xadrez.getMatrizTabuleiro()[0][0].setBotao(btn70);
                xadrez.getMatrizTabuleiro()[0][1].setBotao(btn71);
                xadrez.getMatrizTabuleiro()[0][2].setBotao(btn72);
                xadrez.getMatrizTabuleiro()[0][3].setBotao(btn73);
                xadrez.getMatrizTabuleiro()[0][4].setBotao(btn74);
                xadrez.getMatrizTabuleiro()[0][5].setBotao(btn75);
                xadrez.getMatrizTabuleiro()[0][6].setBotao(btn76);
                xadrez.getMatrizTabuleiro()[0][7].setBotao(btn77);
                xadrez.getMatrizTabuleiro()[1][0].setBotao(btn60);
                xadrez.getMatrizTabuleiro()[1][1].setBotao(btn61);
                xadrez.getMatrizTabuleiro()[1][2].setBotao(btn62);
                xadrez.getMatrizTabuleiro()[1][3].setBotao(btn63);
                xadrez.getMatrizTabuleiro()[1][4].setBotao(btn64);
                xadrez.getMatrizTabuleiro()[1][5].setBotao(btn65);
                xadrez.getMatrizTabuleiro()[1][6].setBotao(btn66);
                xadrez.getMatrizTabuleiro()[1][7].setBotao(btn67);
                xadrez.getMatrizTabuleiro()[2][0].setBotao(btn50);
                xadrez.getMatrizTabuleiro()[2][1].setBotao(btn51);
                xadrez.getMatrizTabuleiro()[2][2].setBotao(btn52);
                xadrez.getMatrizTabuleiro()[2][3].setBotao(btn53);
                xadrez.getMatrizTabuleiro()[2][4].setBotao(btn54);
                xadrez.getMatrizTabuleiro()[2][5].setBotao(btn55);
                xadrez.getMatrizTabuleiro()[2][6].setBotao(btn56);
                xadrez.getMatrizTabuleiro()[2][7].setBotao(btn57);
                xadrez.getMatrizTabuleiro()[3][0].setBotao(btn40);
                xadrez.getMatrizTabuleiro()[3][1].setBotao(btn41);
                xadrez.getMatrizTabuleiro()[3][2].setBotao(btn42);
                xadrez.getMatrizTabuleiro()[3][3].setBotao(btn43);
                xadrez.getMatrizTabuleiro()[3][4].setBotao(btn44);
                xadrez.getMatrizTabuleiro()[3][5].setBotao(btn45);
                xadrez.getMatrizTabuleiro()[3][6].setBotao(btn46);
                xadrez.getMatrizTabuleiro()[3][7].setBotao(btn47);
                xadrez.getMatrizTabuleiro()[4][0].setBotao(btn30);
                xadrez.getMatrizTabuleiro()[4][1].setBotao(btn31);
                xadrez.getMatrizTabuleiro()[4][2].setBotao(btn32);
                xadrez.getMatrizTabuleiro()[4][3].setBotao(btn33);
                xadrez.getMatrizTabuleiro()[4][4].setBotao(btn34);
                xadrez.getMatrizTabuleiro()[4][5].setBotao(btn35);
                xadrez.getMatrizTabuleiro()[4][6].setBotao(btn36);
                xadrez.getMatrizTabuleiro()[4][7].setBotao(btn37);
                xadrez.getMatrizTabuleiro()[5][0].setBotao(btn20);
                xadrez.getMatrizTabuleiro()[5][1].setBotao(btn21);
                xadrez.getMatrizTabuleiro()[5][2].setBotao(btn22);
                xadrez.getMatrizTabuleiro()[5][3].setBotao(btn23);
                xadrez.getMatrizTabuleiro()[5][4].setBotao(btn24);
                xadrez.getMatrizTabuleiro()[5][5].setBotao(btn25);
                xadrez.getMatrizTabuleiro()[5][6].setBotao(btn26);
                xadrez.getMatrizTabuleiro()[5][7].setBotao(btn27);
                xadrez.getMatrizTabuleiro()[6][0].setBotao(btn10);
                xadrez.getMatrizTabuleiro()[6][1].setBotao(btn11);
                xadrez.getMatrizTabuleiro()[6][2].setBotao(btn12);
                xadrez.getMatrizTabuleiro()[6][3].setBotao(btn13);
                xadrez.getMatrizTabuleiro()[6][4].setBotao(btn14);
                xadrez.getMatrizTabuleiro()[6][5].setBotao(btn15);
                xadrez.getMatrizTabuleiro()[6][6].setBotao(btn16);
                xadrez.getMatrizTabuleiro()[6][7].setBotao(btn17);
                xadrez.getMatrizTabuleiro()[7][0].setBotao(btn00);
                xadrez.getMatrizTabuleiro()[7][1].setBotao(btn01);
                xadrez.getMatrizTabuleiro()[7][2].setBotao(btn02);
                xadrez.getMatrizTabuleiro()[7][3].setBotao(btn03);
                xadrez.getMatrizTabuleiro()[7][4].setBotao(btn04);
                xadrez.getMatrizTabuleiro()[7][5].setBotao(btn05);
                xadrez.getMatrizTabuleiro()[7][6].setBotao(btn06);
                xadrez.getMatrizTabuleiro()[7][7].setBotao(btn07);
                
                for(int i = 7; i >= 0; i--)
                {
                    for(int j = 0; j < 8; j++)
                    {                
                        
                        if (xadrez.getMatrizTabuleiro()[i][j].getPeca() != null) {
                            xadrez.getMatrizTabuleiro()[i][j].getPeca().desenha();
                        }
                        else
                        {
                            xadrez.getMatrizTabuleiro()[i][j].desenha();
                        }
                        
                    }
                }
       //salvar = new Salvar(xadrez.getBranco(), xadrez.getPreto(), xadrez.getTurno(), xadrez.getMatrizTabuleiro(),
       //relogioJogador1.getMinutos(),relogioJogador1.getSegundos(), relogioJogador2.getMinutos(), relogioJogador2.getSegundos(), 
       //relogio.getMinuto(), relogio.getSegundo());
       
    }//GEN-LAST:event_DesfazerActionPerformed

    private void RefazerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefazerActionPerformed
/*       relogioJogador1.suspend();
       relogioJogador2.suspend();
       relogioJogador1.setMinutos(0);
       relogioJogador1.setSegundos(0);
       relogioJogador2.setMinutos(0);
       relogioJogador2.setSegundos(0);
       relogio.suspend();
       relogio.setMinuto(0);
       relogio.setSegundo(0);
       //Desfazer.hide();
       //Refazer.show();
       xadrez.setTurno(aux.getTurno());
       xadrez.setBranco(aux.getBranco());
       xadrez.setPreto(aux.getPreto());
       xadrez.setMatrizTabuleiro(aux.getMatrizTabuleiro());
       relogioJogador1.setMinutos(aux.getMinutos1());
       relogioJogador2.setMinutos(aux.getMinutos2());
       relogio.setMinuto(aux.getMinutos3());
       relogioJogador1.setSegundos(aux.getSegundos1());
       relogioJogador2.setSegundos(aux.getSegundos2());
       relogio.setSegundo(aux.getSegundos3());
       
       if (xadrez.getTurno() % 2 == 0) {
            lblJogador.setText("Branco");
            relogioJogador1.resume();
            txtJogadorPreto.setText(null);
        }
        else
        {
            lblJogador.setText("Preto");
            relogioJogador2.resume();
            txtJogadorBranco.setText(null);
       }


        relogio.resume();
                
        xadrez.getMatrizTabuleiro()[0][0].setBotao(btn70);
        xadrez.getMatrizTabuleiro()[0][1].setBotao(btn71);
        xadrez.getMatrizTabuleiro()[0][2].setBotao(btn72);
        xadrez.getMatrizTabuleiro()[0][3].setBotao(btn73);
        xadrez.getMatrizTabuleiro()[0][4].setBotao(btn74);
        xadrez.getMatrizTabuleiro()[0][5].setBotao(btn75);
        xadrez.getMatrizTabuleiro()[0][6].setBotao(btn76);
        xadrez.getMatrizTabuleiro()[0][7].setBotao(btn77);
        xadrez.getMatrizTabuleiro()[1][0].setBotao(btn60);
        xadrez.getMatrizTabuleiro()[1][1].setBotao(btn61);
        xadrez.getMatrizTabuleiro()[1][2].setBotao(btn62);
        xadrez.getMatrizTabuleiro()[1][3].setBotao(btn63);
        xadrez.getMatrizTabuleiro()[1][4].setBotao(btn64);
        xadrez.getMatrizTabuleiro()[1][5].setBotao(btn65);
        xadrez.getMatrizTabuleiro()[1][6].setBotao(btn66);
        xadrez.getMatrizTabuleiro()[1][7].setBotao(btn67);
        xadrez.getMatrizTabuleiro()[2][0].setBotao(btn50);
        xadrez.getMatrizTabuleiro()[2][1].setBotao(btn51);
        xadrez.getMatrizTabuleiro()[2][2].setBotao(btn52);
        xadrez.getMatrizTabuleiro()[2][3].setBotao(btn53);
        xadrez.getMatrizTabuleiro()[2][4].setBotao(btn54);
        xadrez.getMatrizTabuleiro()[2][5].setBotao(btn55);
        xadrez.getMatrizTabuleiro()[2][6].setBotao(btn56);
        xadrez.getMatrizTabuleiro()[2][7].setBotao(btn57);
        xadrez.getMatrizTabuleiro()[3][0].setBotao(btn40);
        xadrez.getMatrizTabuleiro()[3][1].setBotao(btn41);
        xadrez.getMatrizTabuleiro()[3][2].setBotao(btn42);
        xadrez.getMatrizTabuleiro()[3][3].setBotao(btn43);
        xadrez.getMatrizTabuleiro()[3][4].setBotao(btn44);
        xadrez.getMatrizTabuleiro()[3][5].setBotao(btn45);
        xadrez.getMatrizTabuleiro()[3][6].setBotao(btn46);
        xadrez.getMatrizTabuleiro()[3][7].setBotao(btn47);
        xadrez.getMatrizTabuleiro()[4][0].setBotao(btn30);
        xadrez.getMatrizTabuleiro()[4][1].setBotao(btn31);
        xadrez.getMatrizTabuleiro()[4][2].setBotao(btn32);
        xadrez.getMatrizTabuleiro()[4][3].setBotao(btn33);
        xadrez.getMatrizTabuleiro()[4][4].setBotao(btn34);
        xadrez.getMatrizTabuleiro()[4][5].setBotao(btn35);
        xadrez.getMatrizTabuleiro()[4][6].setBotao(btn36);
        xadrez.getMatrizTabuleiro()[4][7].setBotao(btn37);
        xadrez.getMatrizTabuleiro()[5][0].setBotao(btn20);
        xadrez.getMatrizTabuleiro()[5][1].setBotao(btn21);
        xadrez.getMatrizTabuleiro()[5][2].setBotao(btn22);
        xadrez.getMatrizTabuleiro()[5][3].setBotao(btn23);
        xadrez.getMatrizTabuleiro()[5][4].setBotao(btn24);
        xadrez.getMatrizTabuleiro()[5][5].setBotao(btn25);
        xadrez.getMatrizTabuleiro()[5][6].setBotao(btn26);
        xadrez.getMatrizTabuleiro()[5][7].setBotao(btn27);
        xadrez.getMatrizTabuleiro()[6][0].setBotao(btn10);
        xadrez.getMatrizTabuleiro()[6][1].setBotao(btn11);
        xadrez.getMatrizTabuleiro()[6][2].setBotao(btn12);
        xadrez.getMatrizTabuleiro()[6][3].setBotao(btn13);
        xadrez.getMatrizTabuleiro()[6][4].setBotao(btn14);
        xadrez.getMatrizTabuleiro()[6][5].setBotao(btn15);
        xadrez.getMatrizTabuleiro()[6][6].setBotao(btn16);
        xadrez.getMatrizTabuleiro()[6][7].setBotao(btn17);
        xadrez.getMatrizTabuleiro()[7][0].setBotao(btn00);
        xadrez.getMatrizTabuleiro()[7][1].setBotao(btn01);
        xadrez.getMatrizTabuleiro()[7][2].setBotao(btn02);
        xadrez.getMatrizTabuleiro()[7][3].setBotao(btn03);
        xadrez.getMatrizTabuleiro()[7][4].setBotao(btn04);
        xadrez.getMatrizTabuleiro()[7][5].setBotao(btn05);
        xadrez.getMatrizTabuleiro()[7][6].setBotao(btn06);
        xadrez.getMatrizTabuleiro()[7][7].setBotao(btn07);

        for(int i = 7; i >= 0; i--)
        {
            for(int j = 0; j < 8; j++)
            {                

                if (xadrez.getMatrizTabuleiro()[i][j].getPeca() != null) {
                    xadrez.getMatrizTabuleiro()[i][j].getPeca().desenha();
                }
                else
                {
                    xadrez.getMatrizTabuleiro()[i][j].desenha();
                }

            }
        }
//aux = desfazer;
        Refazer.hide();
        Desfazer.show();*/
    }//GEN-LAST:event_RefazerActionPerformed
    int autosave = 0;
    private void AutoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AutoSaveActionPerformed
        if(autosave == 0){
            save.resume();
            AutoSave.setText("Desabilitar Auto-Save");
            autosave = 1;
        }
        else if(autosave == 1){
            save.suspend();
            AutoSave.setText("Habilitar Auto-Save (10 seg)");
            autosave = 0;
        }
    }//GEN-LAST:event_AutoSaveActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        inicio.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JMenuItem AutoSave;
    private javax.swing.JMenuItem Desfazer;
    private javax.swing.JMenuItem Refazer;
    private javax.swing.JMenuItem Salvar;
    private javax.swing.JButton btn00;
    private javax.swing.JButton btn01;
    private javax.swing.JButton btn02;
    private javax.swing.JButton btn03;
    private javax.swing.JButton btn04;
    private javax.swing.JButton btn05;
    private javax.swing.JButton btn06;
    private javax.swing.JButton btn07;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn26;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn30;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn35;
    private javax.swing.JButton btn36;
    private javax.swing.JButton btn37;
    private javax.swing.JButton btn40;
    private javax.swing.JButton btn41;
    private javax.swing.JButton btn42;
    private javax.swing.JButton btn43;
    private javax.swing.JButton btn44;
    private javax.swing.JButton btn45;
    private javax.swing.JButton btn46;
    private javax.swing.JButton btn47;
    private javax.swing.JButton btn50;
    private javax.swing.JButton btn51;
    private javax.swing.JButton btn52;
    private javax.swing.JButton btn53;
    private javax.swing.JButton btn54;
    private javax.swing.JButton btn55;
    private javax.swing.JButton btn56;
    private javax.swing.JButton btn57;
    private javax.swing.JButton btn60;
    private javax.swing.JButton btn61;
    private javax.swing.JButton btn62;
    private javax.swing.JButton btn63;
    private javax.swing.JButton btn64;
    private javax.swing.JButton btn65;
    private javax.swing.JButton btn66;
    private javax.swing.JButton btn67;
    private javax.swing.JButton btn70;
    private javax.swing.JButton btn71;
    private javax.swing.JButton btn72;
    private javax.swing.JButton btn73;
    private javax.swing.JButton btn74;
    private javax.swing.JButton btn75;
    private javax.swing.JButton btn76;
    private javax.swing.JButton btn77;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblJogador;
    private javax.swing.JTextField txtJogadorBranco;
    private javax.swing.JTextField txtJogadorPreto;
    private javax.swing.JTextArea txtLog;
    private javax.swing.JTextField txtRelogio;
    // End of variables declaration//GEN-END:variables
    
}
