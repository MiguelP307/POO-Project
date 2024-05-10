package Atividades;

import Users.Utilizador;

public class ARepeticoes extends Atividade{

    int numeroRepeticoes;
    int tempoAExecutar;

    public ARepeticoes(String nome, boolean isHard){
        super(nome, isHard);
        this.numeroRepeticoes = -1;
        this.tempoAExecutar = -1;
    }

    public ARepeticoes(String nome, boolean isHard, int numeroRepeticoes, int tempoAExecutar){
        super(nome, isHard);
        this.numeroRepeticoes = numeroRepeticoes;
        this.tempoAExecutar = tempoAExecutar;
    }

    @Override
    public float caloriasGastas(Utilizador utilizador){
        throw new UnsupportedOperationException("Unimplemented method 'fatorMultiplicativo'");
    }

    // SETs E GETs
    public int getNumeroRepeticoes() {
        return numeroRepeticoes;
    }

    public void setNumeroRepeticoes(int numeroRepeticoes) {
        this.numeroRepeticoes = numeroRepeticoes;
    }

    public int getTempoAExecutar() {
        return tempoAExecutar;
    }

    public void setTempoAExecutar(int tempoAExecutar) {
        this.tempoAExecutar = tempoAExecutar;
    }
}   
