/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904


package Xadrez;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Allan
 */
public class AutoSave extends Thread implements Serializable{
    Salvar salvar;
    private Relogio rel;
    private RelogioJogador j1, j2;
    private XadrezBotao xadrez;
    private int cont;
    private File nome;
    private String log;
    
    public AutoSave(Relogio rel, RelogioJogador j1, RelogioJogador j2, XadrezBotao xadrez, String log){
        this.rel = rel;
        this.j1 = j1;
        this.j2 = j2;
        this.xadrez = xadrez;
        cont = 0;
        nome = null;
        this.log = log;
        
    }
    int conter = 0;
    public void run(){ 
        while (true){
            try{
                if(conter == 0)
                {
                    salvar = new Salvar(xadrez.getBranco(), xadrez.getPreto(), xadrez.getTurno(), xadrez.getMatrizTabuleiro(), j1.getMinutos(),j1.getSegundos(), j2.getMinutos(), j2.getSegundos(), rel.getMinuto(), rel.getSegundo(), log);
                    nome = salvar.autoSalvar();
                    conter = 1;
                }
                AutoSave.sleep(1000);
            
                if (cont == 10) {
                    try{
                    FileOutputStream out1 = new FileOutputStream(nome);  
                    ObjectOutputStream objOut1 = new ObjectOutputStream(out1);
                    objOut1.writeObject(this);
                    objOut1.close();
                    cont = 0;
                    }catch(FileNotFoundException e)
                    {
                        JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n"  + e);
                    } catch(IOException e)
                    {
                        JOptionPane.showMessageDialog(null, "Erro no salvar: \n\n " + e);
                    }
                }
                
                cont++;
            }catch(InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Erro no auto save!");
            }
        }
    }
    
}
