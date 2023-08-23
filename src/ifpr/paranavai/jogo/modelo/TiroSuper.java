package ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;

public class TiroSuper extends ElementoGrafico{

    private static int VELOCIDADE = 2;

    public TiroSuper(int posicaoPersonagemEmX, int posicaoPersonagemEmY){
        this.carregar();
        super.setPosicaoEmX(posicaoPersonagemEmY);
        super.setPosicaoEmY(posicaoPersonagemEmY - (this.getAlturaImagem() / 2));
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Especial.png");
        super.setImagem(carregando.getImage());
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE);
    }
    
}
