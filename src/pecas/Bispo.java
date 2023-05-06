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
public class Bispo extends Peca{

    public Bispo(Cor cor, Posicao casa) {
        super(cor, casa);
    }

    
    @Override
    public void desenha() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/bispoBranco-casaBranca.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/bispoBranco-casaPreta.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/bispoPreto-casaBranca.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/bispoPreto-casaPreta.jpg")));
                }
            }
    }

    @Override
    public ArrayList<Posicao> casasPossiveis() {
        ArrayList<Posicao> lista = new ArrayList<Posicao>();
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        int i, j;
        i = j = 0;
        //>/\
        for (i = x+1, j = y+1;  i<8 && j <8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i++, j++) {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
        if((i<8) && (j<8)&&(xadrez.getMatrizTabuleiro()[i][j].getPeca() != null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor()))
        {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
        //<\/
        for (i = x-1, j = y-1;  i>=0 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i--, j--) {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
        if (i>=0 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor())) {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
        //</\
        for (i = x+1, j = y-1;  i<8 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i++, j--) {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
        if(i<8 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor()))
        {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
        //>\/
        for (i = x-1, j = y+1;  i>=0 && j<8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i--, j++) {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
        if(i>=0 && j<8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor()))
        {
            lista.add(xadrez.getMatrizTabuleiro()[i][j]);
        }
            
        return lista;
        
        
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
        //>/\
        for (i = x+1, j = y+1;  i<8 && j <8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i++, j++) {
            this.desenhaPecaSelecionada(i, j);
        }
        //<\/
        for (i = x-1, j = y-1;  i>=0 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i--, j--) {
            this.desenhaPecaSelecionada(i, j);
        }
        
        //</\
        for (i = x+1, j = y-1;  i<8 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i++, j--) {
           this.desenhaPecaSelecionada(i, j);
        }
        
        //>\/
        for (i = x-1, j = y+1;  i>=0 && j<8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i--, j++) {
            this.desenhaPecaSelecionada(i, j);
        }
        
    }
    
    public void desenhaAtaque() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/bispoBranco-casaBrancaA.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/bispoBranco-casaPretaA.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/bispoPreto-casaBrancaA.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/bispoPreto-casaPretaA.jpg")));
                }
            }
    }

    @Override
    public void casaPossiveisAtaque() {
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        int i, j;
        i = j = 0;
        //>/\
        for (i = x+1, j = y+1;  i<8 && j <8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i++, j++) {
            
        }
        if((i<8) && (j<8)&&(xadrez.getMatrizTabuleiro()[i][j].getPeca() != null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[i][j].getPeca().desenhaAtaque();
        }
        //<\/
        for (i = x-1, j = y-1;  i>=0 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i--, j--) {

        }
        if (i>=0 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor())) {
            xadrez.getMatrizTabuleiro()[i][j].getPeca().desenhaAtaque();
        }
        //</\
        for (i = x+1, j = y-1;  i<8 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i++, j--) {

        }
        if(i<8 && j>=0 && (xadrez.getMatrizTabuleiro()[i][j].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[i][j].getPeca().desenhaAtaque();
        }
        //>\/
        for (i = x-1, j = y+1;  i>=0 && j<8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca() == null); i--, j++) {
            
        }
        if(i>=0 && j<8 && (xadrez.getMatrizTabuleiro()[i][j].getPeca()!= null) && (xadrez.getMatrizTabuleiro()[i][j].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[i][j].getPeca().desenhaAtaque();
        }
    }
    
}
