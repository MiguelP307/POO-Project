package Manager;

import Users.UAmador;
import Users.UOcasional;
import Users.UProfissional;
import Users.Utilizador;
import PlanoTreino.PlanoTreino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Atividades.ADistancia;
import Atividades.ADistanciaAltimetria;
import Atividades.ARepeticoes;
import Atividades.ARepeticoesPeso;
import Atividades.Atividade;

public class Manager {
    private List<Utilizador> utilizadores;
    private List<Atividade> atividadesDisponiveis;

    private int idUtilizadores;
    private int idAtividades;
    private int idPlanos;

    public Manager() {
        this.utilizadores = new ArrayList<>();
        this.atividadesDisponiveis = new ArrayList<>();

        this.idUtilizadores = 1;
        this.idAtividades = 1;
        this.idPlanos = 1;
    }

    public void criarUtilizador(int idUser, String nome, float peso, int idade, float altura, String morada, String email, float freqCardiacaMedia, int tipoUser){

        Utilizador user = null;

        switch (tipoUser) {
            case 1:
                user = new UOcasional(idUser, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
                break;
            
            case 2:
                user = new UAmador(idUser, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
                break;

            case 3:
                user = new UProfissional(idUser, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
                break;
        
            default:
                System.out.println("Tipo de user invalido");
                break;
        }

        if(user != null){
            utilizadores.add(user);
            idUser++;
        }
        else{
            System.out.println("Error adicionar utilizador");
        }

    }

    public void removerUtilizador(int id){

        Utilizador utilizadorToRemove = null;

        for(Utilizador utilizador : utilizadores){
            if(utilizador.getIdUtilizador() == id){
                utilizadorToRemove = utilizador;
                break;
            }
        }

        if(utilizadorToRemove != null){
            utilizadores.remove(utilizadorToRemove);
            System.out.println("Utilizador removido com sucesso!");
        }else {
            System.out.println("Utilizador com ID " + id + " nao encontrado...");
        }
    }

    public void criarAtividade(int id, String nome, boolean isHard, int tipoAtividade){
        
        Atividade atv = null;

        switch (tipoAtividade) {
            case 1:
                atv = new ADistancia(id, nome, isHard);
                break;

            case 2:
                atv = new ADistanciaAltimetria(id, nome, isHard);
                break;
        
            case 3:
                atv = new ARepeticoes(id, nome, isHard);
                break;
        
            case 4:
                atv = new ARepeticoesPeso(id, nome, isHard);
                break;
        
            default:
                System.out.println("Tipo de ativiadade invalida");
                break;
        }

        if(atv != null){
            atividadesDisponiveis.add(atv);
            idAtividades++;
        }
        else{
            System.out.println("Erro ao adicionar atividade");
        }

    }


    public void removerAtividade(int id){

        Atividade atividadeToRemove = null;
        for(Atividade atividade : atividadesDisponiveis){
            
            if(atividade.getID() == id){
                atividadeToRemove = atividade;
                break;
            }
        }
        if(atividadeToRemove != null){

            atividadesDisponiveis.remove(atividadeToRemove);
            System.out.println("Atividade removida com sucesso!");
        }else{

            System.out.println("Atividade com ID " + id + " nao encontrada...");
        }
    }


    // Geral um plano de treino
    public void criarPlanoTreino(){
    }

    // Instancia um plano de treino e adiciona as atividades escolhidas pelo user
    public void criarPlanoTreino(int id, LocalDate date, Utilizador user, List<Atividade> atvs){
        PlanoTreino plano = new PlanoTreino(id, date);
        for(int i = 0; i < atvs.size(); i++){
            plano.addAtividadeRealizada(atvs.get(i));
        }
        user.addPlanoTreino(plano);
    }


    public void removerPlanoTreino(int idUtilizador, int idPlano){

        Utilizador utilizador = null;
        for(Utilizador user : utilizadores){

            if(user.getIdUtilizador() == idUtilizador){
                utilizador = user;
                break;
            }
        }
        if(utilizador != null){

            utilizador.removePlanoTreino(idPlano);
            System.out.println("Plano de treino removido com sucesso!");
        }else{
            System.out.println("Utilizador com ID " + idUtilizador + " nao encontrado...");
        }
    }


    // Metodos de visualizacao

    public void mostrarUtilizadores(){

        for(Utilizador user : utilizadores){
        
            System.out.println("ID: " + user.getIdUtilizador() + 
                               " - Nome: " + user.getNome() + 
                               " - Peso: " + user.getPeso() + 
                               " - Idade: " + user.getIdade() + 
                               " - Altura: " + user.getAltura() + 
                               " - Morada: " + user.getMorada() + 
                               " - Email: " + user.getEmail() + 
                               " - Frequencia Cardiaca Mrdia: " + user.getFreqCardiacaMedia());

            mostrarAtividades(user.getAtividadesRealizadas());
        }
    }

    public void mostrarAtividades(){

        System.out.println("Atividades Disponíveis:");

        for(Atividade atividade : atividadesDisponiveis){

            System.out.println("ID: " + atividade.getID() + " - Nome: " + atividade.getNome() + " - Dificuldade: " + (atividade.getIsHard() ? "Hard" : "Nao Hard"));
        }
    }

    public void mostrarAtividades(List<Atividade> atvs){

        System.out.println("Atividades Realizadas:");

        for(Atividade atividade : atvs){
            System.out.print("ID: " + atividade.getID() + 
                             " - Nome: " + atividade.getNome() + 
                             " - Dificuldade: " + (atividade.getIsHard() ? "Hard" : "Nao Hard") +
                             " - Calorias gastas: " + atividade.getCaloriasGastas() +
                             " - Tempo de Execucao: " + atividade.getTempoDeExecucao()
                             );
    
            
            if(atividade instanceof ADistancia){
                ADistancia aDistancia = (ADistancia) atividade;
                System.out.println(" - Distancia: " + aDistancia.getDistancia());

                if(aDistancia.getTempoAExecutar() >= 0)
                    System.out.println(" - Tempo a executar: " + aDistancia.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
                
            }
            else if(atividade instanceof ADistanciaAltimetria){

                ADistanciaAltimetria aDistanciaAltimetria = (ADistanciaAltimetria) atividade;
                System.out.println(" - Distancia: " + aDistanciaAltimetria.getDistancia() + 
                                   " - Altitude: " + aDistanciaAltimetria.getAltimetria());

                if(aDistanciaAltimetria.getTempoAExecutar() >= 0)
                    System.out.println(" - Tempo a executar: " + aDistanciaAltimetria.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
            }
            else if(atividade instanceof ARepeticoes){
                ARepeticoes aRepeticoes = (ARepeticoes) atividade;
                System.out.println(" - Repeticoes: " + aRepeticoes.getRepeticoes());

                if(aRepeticoes.getTempoAExecutar() >= 0)
                    System.out.println(" - Tempo a executar: " + aRepeticoes.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
            }
            else if(atividade instanceof ARepeticoesPeso){

                ARepeticoesPeso aRepeticoesPeso = (ARepeticoesPeso) atividade;
                System.out.println(" - Repeticoes: " + aRepeticoesPeso.getRepeticoes() + 
                                   " - Peso: " + aRepeticoesPeso.getPeso());

                if(aRepeticoesPeso.getTempoAExecutar() >= 0)
                    System.out.println(" - Tempo a executar: " + aRepeticoesPeso.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
            }
            else {
                System.out.println(" - Tipo desconhecido");
            }
        }
    }

    public void mostrarPlanosTreinoUtilizador(int idUtilizador){
        
        Utilizador utilizador = null;
        for(Utilizador user : utilizadores){

            if(user.getIdUtilizador() == idUtilizador){
                utilizador = user;
                break;
            }
        }
        if(utilizador != null)
        {
            List<PlanoTreino> planos = utilizador.getPlanosDeTreino();

            if(planos.isEmpty()){

                System.out.println("Este utilizador nao possui planos de treino.");

            }else{

                System.out.println("Planos de Treino do Utilizador " + utilizador.getNome() + ":");
                for(PlanoTreino plano : planos){

                    System.out.println("ID do Plano: " + plano.getID() + " - Data: " + plano.getData());
                    System.out.println("Atividades:");
                    
                    for(Atividade atividade : plano.getAtividades()){
                        System.out.println("    - ID: " + atividade.getID() + " - Nome: " + atividade.getNome());
                    }
                }
            }
        }else{
            System.out.println("Utilizador com ID " + idUtilizador + " nao encontrado...");
        }
    }

    // SETs e GETs

    public int getIDUtilizadores(){
        return idUtilizadores;
    }

    public void setIDUtilizadores(int id){
        this.idUtilizadores = id;
    }

    public int getIDPlanos(){
        return idPlanos;
    }

    public void setIDPlanos(int id){
        this.idPlanos = id;
    }

    public int getIDAtividades(){
        return idAtividades;
    }

    public void setIDAtividades(int id){
        this.idAtividades = id;
    }
}
