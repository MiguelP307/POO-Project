package Users;

import Atividades.Atividade;

public class UOcasional extends Utilizador{

    private float fatorMultiplicativo;

    //Constructor
    public UOcasional(int idUtilizador, String nome, float peso, int idade, float altura, String morada, String email, float freqCardiacaMedia) {
        super(idUtilizador, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
        this.fatorMultiplicativo = fatorMultiplicativo();
    }

    @Override
    public float fatorMultiplicativo() {
        // TODO FAZER A FORMULA PARA CALCULAR O FATOR MULTIPLICATIVO
        throw new UnsupportedOperationException("Unimplemented method 'fatorMultiplicativo'");
    }
    
    // Getters e Setters
    public float getFatorMultiplicativo() {
        return fatorMultiplicativo;
    }

    public void setFatorMultiplicativo(float fatorMultiplicativo) {
        this.fatorMultiplicativo = fatorMultiplicativo;
    }

    @Override
    public void addAtividade(Atividade atividade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAtividade'");
    }

    @Override
    public void removeAtividade(int idAtividade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAtividade'");
    }
}
