package atividades;

import users.Utilizador;

public class ADistanciaAltimetria extends Atividade{

    private float distancia;
    private float altimetria;
    private int tempoAExecutar;
    
    public ADistanciaAltimetria(int id, String nome, boolean isHard){
        super(id, nome, isHard);
        this.distancia = -1;
        this.altimetria = -1;
        this.tempoAExecutar = -1;
    }

    public ADistanciaAltimetria(int id,String nome, boolean isHard, float distancia, float altimetria, int tempoAExecutar){
        super(id, nome, isHard);
        this.distancia = distancia;
        this.altimetria = altimetria;
        this.tempoAExecutar = tempoAExecutar;
    }

    @Override
    public float caloriasGastas(Utilizador utilizador){
        return (float)((utilizador.getPeso() * (distancia + altimetria)/1000) * utilizador.fatorMultiplicativo() * (getMediaFreqCardiaca()/120)) ;
    }

    // SET E GETS

    public float getDistancia(){
        return distancia;
    }

    public void setDistancia(float distancia){
        this.distancia = distancia;
    }

    public float getAltimetria(){
        return altimetria;
    }

    public void setAltimetria(float altimetria){
        this.altimetria = altimetria;
    }

    public int getTempoAExecutar(){
        return tempoAExecutar;
    }

    public void setTempoAExecutar(int tempoAExecutar){
        this.tempoAExecutar = tempoAExecutar;
    }

}
