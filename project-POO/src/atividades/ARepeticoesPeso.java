package atividades;

import users.Utilizador;

public class ARepeticoesPeso extends Atividade{
    
    int numeroRepeticoes;
    int peso;
    int tempoAExecutar;

    public ARepeticoesPeso(int id, String nome, boolean isHard){
        super(id, nome, isHard);
        this.numeroRepeticoes = -1;
        this.peso = -1;
        this.tempoAExecutar = -1;
    }

    public ARepeticoesPeso(int id, String nome, boolean isHard, int numeroRepeticoes, int peso, int tempoAExecutar){
        super(id ,nome, isHard);
        this.numeroRepeticoes = numeroRepeticoes;
        this.peso = peso;
        this.tempoAExecutar = tempoAExecutar;
    }


    @Override
    public float caloriasGastas(Utilizador utilizador){

        return (float)((((utilizador.getPeso() + peso)/70) * utilizador.getFatorMultiplicativo())* numeroRepeticoes);
        
    }

    // SETs E GETs
    public int getNumeroRepeticoes() {
        return numeroRepeticoes;
    }

    public void setNumeroRepeticoes(int numeroRepeticoes) {
        this.numeroRepeticoes = numeroRepeticoes;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getTempoAExecutar() {
        return tempoAExecutar;
    }

    public void setTempoAExecutar(int tempoAExecutar) {
        this.tempoAExecutar = tempoAExecutar;
    }
}
