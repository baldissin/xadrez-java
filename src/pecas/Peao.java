/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904

package pecas;

import gui.Posicao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Allan
 */
public class Peao extends Peca{

    public Peao(Cor cor, Posicao casa) {
        super(cor, casa);
    }

    @Override
    public void desenha() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/peaoBranco-casaBranca.jpg")));

                }
                
                else
                {                    
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/peaoBranco-casaPreta.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {                    
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/peaoPreto-casaBranca.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/peaoPreto-casaPreta.jpg")));
                }
            }
            
    }
    
    public void desenhaAtaque() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/peaoBranco-casaBrancaA.jpg")));

                }
                
                else
                {                    
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/peaoBranco-casaPretaA.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {                    
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/peaoPreto-casaBrancaA.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/peaoPreto-casaPretaA.jpg")));
                }
            }
            
    }

    @Override
    public ArrayList<Posicao> casasPossiveis() {
        //errado
        ArrayList<Posicao> lista = new ArrayList<Posicao>();
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        if (xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Peca.Cor.branca) {
            if (x == 1) {
                if ((xadrez.getMatrizTabuleiro()[x+2][y].getPeca() == null) && (xadrez.getMatrizTabuleiro()[x+1][y].getPeca() == null)) {
                    lista.add(xadrez.getMatrizTabuleiro()[x+2][y]);
                    //this.desenhaPecaSelecionada(x+2,y);
                }
            }
            if ((x+1<8)&&(xadrez.getMatrizTabuleiro()[x+1][y].getPeca() == null) ) {
                lista.add(xadrez.getMatrizTabuleiro()[x+1][y]);
                //this.desenhaPecaSelecionada(x+1, y);
            }
        }
        else if(xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Peca.Cor.preta){
            if (x == 6) {
                if ((xadrez.getMatrizTabuleiro()[x-2][y].getPeca() == null) && (xadrez.getMatrizTabuleiro()[x-1][y].getPeca() == null)) {
                    lista.add(xadrez.getMatrizTabuleiro()[x-2][y]);
                    //this.desenhaPecaSelecionada(x-2,y);
                }
            }
            if ((x-1>=0)&&(xadrez.getMatrizTabuleiro()[x-1][y].getPeca() == null)) {
                lista.add(xadrez.getMatrizTabuleiro()[x-1][y]);
                //this.desenhaPecaSelecionada(x-1,y);
            }
        }
        
        return lista;
    }
    
    public ArrayList<Posicao> casasParaComer(){
        ArrayList<Posicao> lista = new ArrayList<Posicao>();
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        
        if (xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Peca.Cor.branca) {
            if (x+1<8 && y+1<8 && (xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca() != null && xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca().getCor() == Peca.Cor.preta)) {
                lista.add(xadrez.getMatrizTabuleiro()[x+1][y+1]);
            }
            if (x+1<8 && y-1>=0 && (xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca() != null && xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca().getCor() == Peca.Cor.preta)) {
                lista.add(xadrez.getMatrizTabuleiro()[x+1][y-1]);
            }
        }
        else{
            if (x-1>=0 && y-1>0 && ((xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca() != null)&&(xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca().getCor() == Peca.Cor.branca))) {
                lista.add(xadrez.getMatrizTabuleiro()[x-1][y-1]);
            }
            if (x-1>=0 && y+1<8 && ((xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca() != null)&&(xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca().getCor() == Peca.Cor.branca))) {
                lista.add(xadrez.getMatrizTabuleiro()[x-1][y+1]);
            }
        }
        
        return lista;
    }
        public void casasPossiveisSelecionadas() {
        //errado
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        if (xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Peca.Cor.branca) {
            if (x == 1) {
                if ((xadrez.getMatrizTabuleiro()[x+2][y].getPeca() == null) && (xadrez.getMatrizTabuleiro()[x+1][y].getPeca() == null)) {
                    
                    this.desenhaPecaSelecionada(x+2,y);
                }
            }
            if ((x+1<8)&&(xadrez.getMatrizTabuleiro()[x+1][y].getPeca() == null) ) {
                
                this.desenhaPecaSelecionada(x+1, y);
            }
        }
        else if(xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Peca.Cor.preta){
            if (x == 6) {
                if ((xadrez.getMatrizTabuleiro()[x-2][y].getPeca() == null) && (xadrez.getMatrizTabuleiro()[x-1][y].getPeca() == null)) {
                    this.desenhaPecaSelecionada(x-2,y);
                }
            }
            if ((x-1>=0)&&(xadrez.getMatrizTabuleiro()[x-1][y].getPeca() == null)) {
                this.desenhaPecaSelecionada(x-1,y);
            }
        }
    }

    @Override
    public void casaPossiveisAtaque() {
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        
        if (xadrez.getMatrizTabuleiro()[x][y].getPeca().getCor() == Peca.Cor.branca) {
            if (x+1<8 && y+1<8 && (xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca() != null && xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca().getCor() == Peca.Cor.preta)) {
                xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca().desenhaAtaque();
            }
            if (x+1<8 && y-1>=0 && (xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca() != null && xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca().getCor() == Peca.Cor.preta)) {
                xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca().desenhaAtaque();
            }
        }
        else{
            if (x-1>=0 && y-1>0 && ((xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca() != null)&&(xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca().getCor() == Peca.Cor.branca))) {
                xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca().desenhaAtaque();
            }
            if (x-1>=0 && y+1<8 && ((xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca() != null)&&(xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca().getCor() == Peca.Cor.branca))) {
                xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca().desenhaAtaque();
            }
        }
    }
    

   
    
}
