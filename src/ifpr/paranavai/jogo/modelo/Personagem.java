package ifpr.paranavai.jogo.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Personagem extends ElementoGrafico {
    private static final int DESLOCAMENTO = 3;
    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 100;

    private int deslocamentoEmX;
    private int deslocamentoEmY;

    private ArrayList<Tiro> tiros;
    private ArrayList<TiroSuper> especiais;

    public Personagem() {
        this.carregar();
        super.setPosicaoEmX(POSICAO_INICIAL_EM_X);
        super.setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        this.tiros = new ArrayList<Tiro>();
        this.especiais = new ArrayList<TiroSuper>();
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\espaconave.png");
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + this.deslocamentoEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + this.deslocamentoEmY);
    }

    public void atirar() {
        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
        TiroSuper especial = new TiroSuper(frenteDaNave, meioDaNave);
        this.especiais.add(especial);
    }

    public void soltar() {
        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);
        TiroSuper especial = new TiroSuper(frenteDaNave, meioDaNave);
        this.especiais.add(especial);
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            case KeyEvent.VK_W:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_S:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_A:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_D:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            default:
                break;
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT:
                deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_W:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_S:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_A:
                deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_D:
                deslocamentoEmX = 0;
                break;
            default:
                break;
        }
    }

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public ArrayList<TiroSuper> getEspeciais() {
        return this.especiais;
    }

    public void setEspeciais(ArrayList<TiroSuper> especiais) {
        this.especiais = especiais;
    }
}