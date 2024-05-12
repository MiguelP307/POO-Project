package atividades;

import users.Utilizador;

public class ARepeticoes extends Atividade{

    int numeroRepeticoes;
    int tempoAExecutar;

    public ARepeticoes(int id, String nome, boolean isHard){
        super(id, nome, isHard);
        this.numeroRepeticoes = -1;
        this.tempoAExecutar = -1;
    }

    public ARepeticoes(int id, String nome, boolean isHard, int numeroRepeticoes, int tempoAExecutar){
        super(id, nome, isHard);
        this.numeroRepeticoes = numeroRepeticoes;
        this.tempoAExecutar = tempoAExecutar;
    }

    @Override
    public float caloriasGastas(Utilizador utilizador){
        return (float)((((utilizador.getPeso())/70) * utilizador.getFatorMultiplicativo())* numeroRepeticoes);

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
