package ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import ifpr.paranavai.jogo.principal.Principal;

public class FaseUm extends Fase {

    public FaseUm() { // Linha adicionada (+)
        super(); // Chamada do construtor da classe super
        emJogo = true;
        ImageIcon carregando = new ImageIcon("recursos\\fundo.jpg");
        fundo = carregando.getImage();

        personagem = new Personagem(); // + Criação do objeto Personagem

        this.inicializaInimigos();

        timer = new Timer(DELAY, this); // + Criação do objeto Timer
        timer.start(); // + Iniciando o nosso jogo

    }

    @Override
    public void inicializaInimigos() {
        inimigos = new ArrayList<Inimigo>();

        for (int i = 0; i < QTDE_DE_INIMIGOS; i++) {
            int x = (int) ((Math.random() * 8000) + Principal.LARGURA_DA_JANELA);
            int y = (int) (Math.random() * Principal.ALTURA_DA_JANELA);
            Inimigo inimigo = new Inimigo(x, y);
            inimigos.add(inimigo);
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);

            ArrayList<Tiro> tiros = personagem.getTiros();
            ArrayList<TiroSuper> especiais = personagem.getEspeciais();

            for (Tiro tiro : tiros) {
                
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), this);
            }

            for (TiroSuper especial : especiais) {
                graficos.drawImage(especial.getImagem(), especial.getPosicaoEmX(), especial.getPosicaoEmY(), this);
            }
            
            for (Inimigo inimigo : inimigos) {
                
                graficos.drawImage(inimigo.getImagem(), inimigo.getPosicaoEmX(), inimigo.getPosicaoEmY(), this);
            }
        } else {
            ImageIcon fimDeJogo = new ImageIcon("recursos\\fimdejogo.jpg");
            graficos.drawImage(fimDeJogo.getImage(), 0, 0, this);
        }
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            personagem.atirar();
        else
            personagem.mover(e);
        
        if (e.getKeyCode() == KeyEvent.VK_Q)
            personagem.soltar();
        else
            personagem.mover(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        personagem.parar(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();

        ArrayList<Tiro> tiros = personagem.getTiros();
        ArrayList<TiroSuper> especiais = personagem.getEspeciais();

        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.get(i);
            if (tiro.getPosicaoEmX() > Principal.LARGURA_DA_JANELA || !tiro.getEhVisivel())
                tiros.remove(tiro);
            else
                tiro.atualizar();
        }

        for (int j = 0; j < especiais.size(); j++) {
            TiroSuper especial = especiais.get(j);
            if (especial.getPosicaoEmX() > Principal.LARGURA_DA_JANELA || !especial.getEhVisivel())
                especiais.remove(especial);
            else
                especial.atualizar();
        }

        // Criando um laço de repetição (for). Iremos percorrer toda a lista.
        for (int i = 0; i < this.inimigos.size(); i++) {
            // Obter o objeto inimigo da posicao i do ArrayList
            Inimigo inimigo = this.inimigos.get(i);
            // Verificar se (if) a posição do x (inimigo.getPosicaoEmX()) é maior do que a
            // largura da nossa janela
            if (inimigo.getPosicaoEmX() < 0 || !inimigo.getEhVisivel())
                // Remover da lista se estiver fora do campo de visão (0)
                inimigos.remove(inimigo);
            else
                // Atualizar a posição do inimigo.
                inimigo.atualizar();
        }
        this.verificarColisoes();
        repaint();
    }

    @Override
    public void verificarColisoes() {
        Rectangle formaPersonagem = this.personagem.getRectangle();

        for (int i = 0; i < this.inimigos.size(); i++) {
            Inimigo inimigo = inimigos.get(i);
            Rectangle formaInimigo = inimigo.getRectangle();
            if (formaInimigo.intersects(formaPersonagem)) {
                this.personagem.setEhVisivel(false);
                inimigo.setEhVisivel(false);
                emJogo = false;
            }
            ArrayList<Tiro> tiros = this.personagem.getTiros();
            for (int j = 0; j < tiros.size(); j++) {
                Tiro tiro = tiros.get(j);
                Rectangle formaTiro = tiro.getRectangle();
                if (formaInimigo.intersects(formaTiro)) {
                    inimigo.setEhVisivel(false);
                    tiro.setEhVisivel(false);
                }
            }
            ArrayList<TiroSuper> especiais = this.personagem.getEspeciais();
            for (int k = 0; k < especiais.size(); k++) {
                TiroSuper especial = especiais.get(k);
                Rectangle formaEspecial = especial.getRectangle();
                
            }
        }
    }
}
