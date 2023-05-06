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
public class Rei extends Peca{

    public Rei(Cor cor, Posicao casa) {
		super(cor, casa);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void desenha() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/reiBranco-casaBranca.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/reiBranco-casaPreta.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/reiPreto-casaBranca.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/reiPreto-casaPreta.jpg")));
                }
            }
    }
    
    public void desenhaAtaque() {

            if(this.getCor() == Peca.Cor.branca)
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/reiBranco-casaBrancaA.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/BrancaAtaque/reiBranco-casaPretaA.jpg")));
                }
            }
            else
            {
                if(this.getCasa().getCor() == Peca.Cor.branca)
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/reiPreto-casaBrancaA.jpg")));
                }
                
                else
                {
                    this.getCasa().getBotao().setIcon(new ImageIcon(getClass().getResource("/imagens/PretaAtaque/reiPreto-casaPretaA.jpg")));
                }
            }
    }

    @Override
    public ArrayList<Posicao> casasPossiveis() {
        
        ArrayList<Posicao> lista = new ArrayList<Posicao>();
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        
        if(((x+1)<8) && ((y+1)<8) &&((xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca().getCor()!= this.getCor())))
        {
            lista.add(xadrez.getMatrizTabuleiro()[x+1][y+1]);
        }
        if(((x+1)<8) && ((xadrez.getMatrizTabuleiro()[x+1][y].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x+1][y].getPeca().getCor()!= this.getCor()))){
            lista.add(xadrez.getMatrizTabuleiro()[x+1][y]);
        }
        if(((x+1)<8) && ((y-1)>=0) && ((xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca().getCor()!= this.getCor())))
        {
            lista.add(xadrez.getMatrizTabuleiro()[x+1][y-1]);
        }
        if(((y+1)<8) && ((xadrez.getMatrizTabuleiro()[x][y+1].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x][y+1].getPeca().getCor()!= this.getCor()))){
            lista.add(xadrez.getMatrizTabuleiro()[x][y+1]);
        }
        if(((y-1)>=0) && ((xadrez.getMatrizTabuleiro()[x][y-1].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x][y-1].getPeca().getCor()!= this.getCor()))){
            lista.add(xadrez.getMatrizTabuleiro()[x][y-1]);
        }
        if(((x-1)>=0) && y+1<8 && ((xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca().getCor()!= this.getCor()))){
            lista.add(xadrez.getMatrizTabuleiro()[x-1][y+1]);
        }
        if(((x-1)>=0) && ((xadrez.getMatrizTabuleiro()[x-1][y].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x-1][y].getPeca().getCor()!= this.getCor()))){
            lista.add(xadrez.getMatrizTabuleiro()[x-1][y]);
        }
        if(((x-1)>=0) && ((y-1)>=0) && ((xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca() == null) || (xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca().getCor()!= this.getCor()))){
            lista.add(xadrez.getMatrizTabuleiro()[x-1][y-1]);
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
        if(((x+1)<8) && ((y+1)<8) &&((xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca() == null)))
        {
            desenhaPecaSelecionada(x+1, y+1);
        }
        if(((x+1)<8) && ((xadrez.getMatrizTabuleiro()[x+1][y].getPeca() == null))){
            desenhaPecaSelecionada(x+1, y);
        }
        if(((x+1)<8) && ((y-1)>=0) && ((xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca() == null)))
        {
            desenhaPecaSelecionada(x+1, y-1);
        }
        if(((y+1)<8) && ((xadrez.getMatrizTabuleiro()[x][y+1].getPeca() == null))){
            desenhaPecaSelecionada(x, y+1);
        }
        if(((y-1)>=0) && ((xadrez.getMatrizTabuleiro()[x][y-1].getPeca() == null))){
            desenhaPecaSelecionada(x, y-1);
        }
        if(((x-1)>=0) && y+1<8 && ((xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca() == null))){
            desenhaPecaSelecionada(x-1, y+1);
        }
        if(((x-1)>=0) && ((xadrez.getMatrizTabuleiro()[x-1][y].getPeca() == null))){
            desenhaPecaSelecionada(x-1, y);
        }
        if(((x-1)>=0) && ((y-1)>=0) && ((xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca() == null))){
            desenhaPecaSelecionada(x-1, y-1);
        }
    }

    @Override
    public void casaPossiveisAtaque() {
        int x = this.getCasa().getX();
        int y = this.getCasa().getY();
        
        if(((x+1)<8) && (xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca() != null)&& ((y+1)<8) &&(xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[x+1][y+1].getPeca().desenhaAtaque();
        }
        if(((x+1)<8) && (xadrez.getMatrizTabuleiro()[x+1][y].getPeca() != null)&& (xadrez.getMatrizTabuleiro()[x+1][y].getPeca().getCor() != this.getCor()) ){
            xadrez.getMatrizTabuleiro()[x+1][y].getPeca().desenhaAtaque();
        }
        if(((x+1)<8) && ((y-1)>=0) && (xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca() != null) && (xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca().getCor() != this.getCor()))
        {
            xadrez.getMatrizTabuleiro()[x+1][y-1].getPeca().desenhaAtaque();
        }
        if(((y+1)<8) && (xadrez.getMatrizTabuleiro()[x][y+1].getPeca() != null)&& (xadrez.getMatrizTabuleiro()[x][y+1].getPeca().getCor()!= this.getCor())){
            xadrez.getMatrizTabuleiro()[x][y+1].getPeca().desenhaAtaque();
        }
        if(((y-1)>=0) && (xadrez.getMatrizTabuleiro()[x][y-1].getPeca() != null)&& (xadrez.getMatrizTabuleiro()[x][y-1].getPeca().getCor()!= this.getCor())){
            xadrez.getMatrizTabuleiro()[x][y-1].getPeca().desenhaAtaque();
        }
        if(((x-1)>=0) && y+1<8 && (xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca() != null)&& (xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca().getCor()!= this.getCor())){
            xadrez.getMatrizTabuleiro()[x-1][y+1].getPeca().desenhaAtaque();
        }
        if(((x-1)>=0) && (xadrez.getMatrizTabuleiro()[x-1][y].getPeca() != null)&& (xadrez.getMatrizTabuleiro()[x-1][y].getPeca().getCor()!= this.getCor())){
            xadrez.getMatrizTabuleiro()[x-1][y].getPeca().desenhaAtaque();
        }
        if(((x-1)>=0) && ((y-1)>=0) && (xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca() != null)&& (xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca().getCor()!= this.getCor())){
            xadrez.getMatrizTabuleiro()[x-1][y-1].getPeca().desenhaAtaque();
        }
    }
    
    
}
