package atividades;

import users.Utilizador;

public abstract class Atividade {

    private int id;
    private String nome;
    private boolean isHard;

    private float caloriasGastas;
    private int tempoDeExecucao;
    private int repeticoes;
    private float mediaFreqCardiaca;

    // Constructor
    public Atividade(int id, String nome, boolean isHard){
        this.id = id;
        this.nome = nome;
        this.isHard = isHard;
        
        // Vao ser alterados quando forem adicionado a um plano de treino
        this.repeticoes = -1;

        // Vao ser alterados quando a atividade for realizada
        this.caloriasGastas = -1;
        this.tempoDeExecucao = -1;
        this.mediaFreqCardiaca = -1;
    }

    public abstract float caloriasGastas(Utilizador utilizador);
    
    public void addRepeticoes(int repeticoes){
        this.repeticoes = repeticoes;
    }

    public void preencherAtividadeRealizada(Utilizador user, int tempo, float mediaFreqCardiaca){
        this.tempoDeExecucao = tempo;
        this.mediaFreqCardiaca = mediaFreqCardiaca;
        this.caloriasGastas = caloriasGastas(user);
    }

    // SETS E GETS
    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCaloriasGastas(){
        return caloriasGastas;
    }

    public void setCaloriasGastas(float caloriasGastas){
        this.caloriasGastas = caloriasGastas;
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

    public float getMediaFreqCardiaca(){
        return mediaFreqCardiaca;
    }

    public void setMediaFreqCardiaca(float mediaFreqCardiaca){
        this.mediaFreqCardiaca = mediaFreqCardiaca;
    }
}
