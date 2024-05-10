package Atividades;

import Users.Utilizador;

public abstract class Atividade {

    private String nome;
    private int tempoDeExecucao;
    private boolean isHard;

    private int repeticoes;

    // Constructor
    public Atividade(String nome, boolean isHard){
        this.nome = nome;
        this.isHard = isHard;
        this.tempoDeExecucao = -1;
        this.repeticoes = -1;
    }

    public abstract float caloriasGastas(Utilizador utilizador);

    public void addTempo(int tempo){
        this.tempoDeExecucao = tempo;
    }

    public void addRepeticoes(int repeticoes){
        this.repeticoes = repeticoes;
    }

    // SETS E GETS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoDeExecucao() {
        return tempoDeExecucao;
    }

    public void setTempoDeExecucao(int tempoDeExecucao) {
        this.tempoDeExecucao = tempoDeExecucao;
    }

    public boolean getIsHard() {
        return isHard;
    }

    public void setHard(boolean isHard) {
        this.isHard = isHard;
    }
    
    public int getRepeticoes(){
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes){
        this.repeticoes = repeticoes;
    }
}
