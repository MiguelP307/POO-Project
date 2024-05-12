package users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import atividades.Atividade;
import plano_treino.PlanoTreino;

public abstract class Utilizador implements Serializable{

    private int idUtilizador;
    private String nome;
    private float peso;
    private int idade;
    private float altura;
    private String morada;
    private String email;
    private float freqCardiacaMedia;
    private List<Atividade> atividadesRealizadas;
    private List<PlanoTreino> planoTreinos;


    /*
    Ocasional = 1.375
    Amador = 1.55 
    Profissional = 1.9 
     */

    // Construtor
    public Utilizador(int idUtilizador, String nome, float peso, int idade, float altura,  String morada, String email, float freqCardiacaMedia) {
        this.idUtilizador = idUtilizador;
        this.nome = nome;
        this.peso = peso;
        this.idade = idade;
        this.altura = altura;
        this.morada = morada;
        this.email = email;
        this.freqCardiacaMedia = freqCardiacaMedia;
        this.atividadesRealizadas = new ArrayList<>();
        this.planoTreinos = new ArrayList<>();
    }

    public abstract float fatorMultiplicativo();
    public abstract float getFatorMultiplicativo();
    public abstract void setFatorMultiplicativo(float fatorMultiplicativo);


    public void addAtividadeRealizada(Atividade atividade, int tempo, float freqMediaCardiaca){
        int i = 0;
        for(; i < atividadesRealizadas.size(); i++){

            Atividade atv = atividadesRealizadas.get(i);
            if(atv.getID() == atividade.getID())
                return;
        }
        
        atividade.preencherAtividadeRealizada(this, tempo, freqMediaCardiaca);
        atividadesRealizadas.add(atividade);
    }

    public void removeAtividadeRealizada(int idAtividade){
        for(int i = 0; i < atividadesRealizadas.size(); i++){
            Atividade atv = atividadesRealizadas.get(i);

            if(atv.getID() == idAtividade){
                atividadesRealizadas.remove(i);
                break;
            }
        }
    }

    public void addPlanoTreino(PlanoTreino plano){
        int i = 0;
        for(; i < planoTreinos.size(); i++){

            PlanoTreino pln = planoTreinos.get(i);
            if(pln.getID() == plano.getID())
                return;
        }
        planoTreinos.add(plano);
    }

    public void removePlanoTreino(int idPlano){
        int i = 0;
        for(; i < planoTreinos.size(); i++){

            PlanoTreino pln = planoTreinos.get(i);
            if(pln.getID() == idPlano){

                planoTreinos.remove(i);
                break;
            }
        }
    }

    // SET E GETS
    public int getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public float getAltura(){
        return altura;
    }

    public void setAltura(float altura){
        this.altura = altura;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getFreqCardiacaMedia() {
        return freqCardiacaMedia;
    }

    public void setFreqCardiacaMedia(Float freqCardiacaMedia) {
        this.freqCardiacaMedia = freqCardiacaMedia;
    }

    public List<Atividade> getAtividadesRealizadas() {
        return atividadesRealizadas;
    }

    public void setAtividadesRealizadas(List<Atividade> atividadesRealizadas) {
        this.atividadesRealizadas = atividadesRealizadas;
    }

    public List<PlanoTreino> getPlanosDeTreino() {
        return planoTreinos;
    }

    public void setPlanosDeTreino(List<PlanoTreino> planoTreinos) {
        this.planoTreinos = planoTreinos;
    }
}
