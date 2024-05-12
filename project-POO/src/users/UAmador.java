package users;

public class UAmador extends Utilizador{

    private float fatorMultiplicativo;

    //Constructor
    public UAmador(int idUtilizador, String nome, float peso, int idade, float altura, String morada, String email, float freqCardiacaMedia) {
        super(idUtilizador, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
        this.fatorMultiplicativo = fatorMultiplicativo();
    }

    @Override
    public float fatorMultiplicativo() {
        return (float)((66 + (13.7 * getPeso()) + (5 * getAltura()) - (6.8 * getIdade())) * 1.55) / 2000;
    }
    
    // GETs e SETs
    public float getFatorMultiplicativo() {
        return fatorMultiplicativo;
    }

    public void setFatorMultiplicativo(float fatorMultiplicativo) {
        this.fatorMultiplicativo = fatorMultiplicativo;
    }

}
