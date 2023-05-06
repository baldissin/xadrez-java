/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// ALLAN BALDISSIN 8657904

package gui;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.*;
import pecas.Peca;
import pecas.Peca.Cor;

/**
 *
 * @author Allan
 */
public class Posicao implements Serializable{
    
    private Cor cor;
    private int x, y;
    private BufferedImage imagem;
    private JButton botao;
    private Peca peca;
    
    public Posicao(Cor cor, int x, int y)
    {
        this.cor = cor;
        this.x = x;
        this.y = y;
        this.peca = null;
    }
    
    public void setPeca(Peca peca)
    {
        this.peca = peca;
    }
    
    public Peca getPeca()
    {
        return peca;
    }
    
    public BufferedImage getImag() {
        return imagem;
    }

    public void setImag(BufferedImage imagem) {
        this.imagem = imagem;
    }


    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public JButton getBotao() {
        return botao;
    }

    public void setBotao(JButton botao) {
        this.botao = botao;
    }
    
    public void desenha() {

            if(this.getCor() == Peca.Cor.branca)
            {
                botao.setIcon(new ImageIcon(getClass().getResource("/imagens/Branca/casaBranca.jpg")));
            }
            else
            {
                botao.setIcon(new ImageIcon(getClass().getResource("/imagens/Preta/casaPreta.jpg")));
            }

        
    }
    
    
    
}
