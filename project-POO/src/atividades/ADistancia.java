package atividades;

import users.Utilizador;

public class ADistancia extends Atividade{
    
    private float distancia;
    private int tempoAExecutar;

    public ADistancia(int id, String nome, boolean isHard){
        super(id, nome, isHard);
        this.distancia = -1;
        this.tempoAExecutar = -1;
    }

    public ADistancia(int id, String nome, boolean isHard, float distancia, int tempoAExecutar){
        super(id, nome, isHard);
        this.distancia = distancia;
        this.tempoAExecutar = tempoAExecutar;
    }

    @Override
    public float caloriasGastas(Utilizador utilizador){
        return (float)((utilizador.getPeso() * distancia/1000) * utilizador.fatorMultiplicativo() * (getMediaFreqCardiaca()/120)) ;
    }

    // SET E GETS
    public float getDistancia(){
        return distancia;
    }

    public void setDistancia(float distancia){
        this.distancia = distancia;
    }

    public int getTempoAExecutar(){
        return tempoAExecutar;
    }

    public void setTempoAExecutar(int tempoAExecutar){
        this.tempoAExecutar = tempoAExecutar;
    }
}
