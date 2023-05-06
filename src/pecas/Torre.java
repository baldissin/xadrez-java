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
public class Torre extends Peca{

    public Torre(Cor cor, Posicao posicao) {
        super(cor, posicao);
    }
    
    
    public ArrayList<Posicao> casasPossiveis()
    {
        
        ArrayList<Posicao> lista = new ArrayList<Posicao>();
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        int i, j;
        i = j = 0;
        
        
        //para /\
        for (i = x+1; ((i < 8) && (xadrez.getMatrizTabuleiro()[i][y].getPeca() == null)); i++) {
            lista.add(xadrez.getMatrizTabuleiro()[i][y]);
            
        }
        if((i<8) &&(xadrez.getMatrizTabuleiro()[i][y].getPeca() != null) && (xadrez.getMatrizTabuleiro()[i][y].getPeca().getCor() != this.getCor()))
        {
            lista.add(xadrez.getMatrizTabuleiro()[i][y]);
        }
        //para \/
        for(i = x-1; (i>=0)&&(xadrez.getMatrizTabuleiro()[i][y].getPeca() == null);i--)
        {
            lista.add(xadrez.getMatrizTabuleiro()[i][y]);
        }
        if((i >= 0) &&(xadrez.getMatrizTabuleiro()[i][y].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][y].getPeca().getCor() != this.getCor()))
        {
            lista.add(xadrez.getMatrizTabuleiro()[i][y]);
        }
        //para >>
        for (j = y+1; (j < 8) && (xadrez.getMatrizTabuleiro()[x][j].getPeca() == null); j++) {
            lista.add(xadrez.getMatrizTabuleiro()[x][j]);
        }
        if((j<8) && (xadrez.getMatrizTabuleiro()[x][j].getPeca() != null) && (xadrez.getMatrizTabuleiro()[x][j].getPeca().getCor() != this.getCor()))
        {
            lista.add(xadrez.getMatrizTabuleiro()[x][j]);
        }
        //para <<
        for (j = y-1; (j>=0) && (xadrez.getMatrizTabuleiro()[x][j].getPeca()== null); j--) {
            lista.add(xadrez.getMatrizTabuleiro()[x][j]);
        }
        if ((j>=0)&& (xadrez.getMatrizTabuleiro()[x][j] != null) && (xadrez.getMatrizTabuleiro()[x][j].getPeca().getCor() != this.getCor())) {
            lista.add(xadrez.getMatrizTabuleiro()[x][j]);
        }
        
        return lista;
    }

    @Override
    public void desenha() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/torreBranca-casaBranca.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/torreBranca-casaPreta.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/torrePreta-casaBranca.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/torrePreta-casaPreta.jpg")));
                }
            }
    }
    
    public void desenhaAtaque() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/torreBranca-casaBrancaA.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/torreBranca-casaPretaA.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/torrePreta-casaBrancaA.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/torrePreta-casaPretaA.jpg")));
                }
            }
    }

    @Override
    public ArrayList<Posicao> casasParaComer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void casasPossiveisSelecionadas() {
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        int i, j;
        i = j = 0;
        
        
        //para /\
        for (i = x+1; ((i < 8) && (xadrez.getMatrizTabuleiro()[i][y].getPeca() == null)); i++) {
            this.desenhaPecaSelecionada(i, y);
            
        }
        //para \/
        for(i = x-1; (i>=0)&&(xadrez.getMatrizTabuleiro()[i][y].getPeca() == null);i--)
        {
            this.desenhaPecaSelecionada(i, y);
        }
        
        //para >>
        for (j = y+1; (j < 8) && (xadrez.getMatrizTabuleiro()[x][j].getPeca() == null); j++) {
            this.desenhaPecaSelecionada(x, j);
        }
        
        //para <<
        for (j = y-1; (j>=0) && (xadrez.getMatrizTabuleiro()[x][j].getPeca()== null); j--) {
            this.desenhaPecaSelecionada(x, j);
        }
    }

    @Override
    public void casaPossiveisAtaque() {
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        int i, j;
        i = j = 0;
        
        
        //para /\
        for (i = x+1; ((i < 8) && (xadrez.getMatrizTabuleiro()[i][y].getPeca() == null)); i++) {
            
        }
        if((i<8) &&(xadrez.getMatrizTabuleiro()[i][y].getPeca() != null) && (xadrez.getMatrizTabuleiro()[i][y].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[i][y].getPeca().desenhaAtaque();
        }
        //para \/
        for(i = x-1; (i>=0)&&(xadrez.getMatrizTabuleiro()[i][y].getPeca() == null);i--)
        {

        }
        if((i >= 0) &&(xadrez.getMatrizTabuleiro()[i][y].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][y].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[i][y].getPeca().desenhaAtaque();
        }
        //para >>
        for (j = y+1; (j < 8) && (xadrez.getMatrizTabuleiro()[x][j].getPeca() == null); j++) {
            
        }
        if((j<8) && (xadrez.getMatrizTabuleiro()[x][j].getPeca() != null) && (xadrez.getMatrizTabuleiro()[x][j].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[x][j].getPeca().desenhaAtaque();
        }
        //para <<
        for (j = y-1; (j>=0) && (xadrez.getMatrizTabuleiro()[x][j].getPeca()== null); j--) {

        }
        if ((j>=0)&& (xadrez.getMatrizTabuleiro()[x][j] != null) && (xadrez.getMatrizTabuleiro()[x][j].getPeca().getCor() != this.getCor())) {
            xadrez.getMatrizTabuleiro()[x][j].getPeca().desenhaAtaque();
        }
        
    }
    
}
