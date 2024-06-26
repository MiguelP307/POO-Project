package manager;

import atividades.ADistancia;
import atividades.ADistanciaAltimetria;
import atividades.ARepeticoes;
import atividades.ARepeticoesPeso;
import atividades.Atividade;
import plano_treino.PlanoTreino;
import users.UAmador;
import users.UOcasional;
import users.UProfissional;
import users.Utilizador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        criarDiretorios();
    }

    public void criarUtilizador(String nome, float peso, int idade, float altura, String morada, String email, float freqCardiacaMedia, int tipoUser){

        Utilizador user = null;

        switch (tipoUser) {
            case 1:
                user = new UOcasional(idUtilizadores, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
                break;
            
            case 2:
                user = new UAmador(idUtilizadores, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
                break;

            case 3:
                user = new UProfissional(idUtilizadores, nome, peso, idade, altura, morada, email, freqCardiacaMedia);
                break;
        
            default:
                System.out.println("Tipo de user invalido");
                break;
        }

        if(user != null){
            utilizadores.add(user);
            idUtilizadores++;
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

    public void criarAtividade(String nome, boolean isHard, int tipoAtividade){
        
        Atividade atv = null;

        switch (tipoAtividade) {
            case 1:
                atv = new ADistancia(idAtividades, nome, isHard);
                break;

            case 2:
                atv = new ADistanciaAltimetria(idAtividades, nome, isHard);
                break;
        
            case 3:
                atv = new ARepeticoes(idAtividades, nome, isHard);
                break;
        
            case 4:
                atv = new ARepeticoesPeso(idAtividades, nome, isHard);
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
    public void criarPlanoTreino(LocalDateTime date, Utilizador user, List<Atividade> atvs){
        PlanoTreino plano = new PlanoTreino(idPlanos, date);
        for(int i = 0; i < atvs.size(); i++){
            plano.addAtividadeRealizada(atvs.get(i));
        }
        user.addPlanoTreino(plano);
        idPlanos++;
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

    /* public Optional<Atividade> displayActivities() {

    } */

    public Utilizador getUtilizadorPorId(int idUser){
        Utilizador utilizadorR = null;

        for(Utilizador utilizador : utilizadores){
            if(utilizador.getIdUtilizador() == idUser){
                utilizadorR = utilizador;
                break;
            }
        }
        return utilizadorR;
    }

    public Optional<Utilizador> fetchUserByID(int idUtilizador) {
        return this.utilizadores
            .stream()
            .filter(u -> u.getIdUtilizador() == idUtilizador).findAny();
    }

    public void mostrarUtilizadores(){

        for(Utilizador user : utilizadores){
        
            System.out.println("ID: " + user.getIdUtilizador() + 
                               "\n - Nome: " + user.getNome() + 
                               "\n - Peso: " + user.getPeso() + 
                               "\n - Idade: " + user.getIdade() + 
                               "\n - Altura: " + user.getAltura() + 
                               "\n - Morada: " + user.getMorada() + 
                               "\n - Email: " + user.getEmail() + 
                               "\n - Frequencia Cardiaca Media: " + user.getFreqCardiacaMedia());

            mostrarAtividades(user.getAtividadesRealizadas());
            System.out.println();
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
                             "\n - Nome: " + atividade.getNome() + 
                             "\n - Dificuldade: " + (atividade.getIsHard() ? "Hard" : "Nao Hard") +
                             "\n - Calorias gastas: " + atividade.getCaloriasGastas() +
                             "\n - Tempo de Execucao: " + atividade.getTempoDeExecucao()
                             );
    
            
            if(atividade instanceof ADistancia){
                ADistancia aDistancia = (ADistancia) atividade;
                System.out.println("\n - Distancia: " + aDistancia.getDistancia());

                if(aDistancia.getTempoAExecutar() >= 0)
                    System.out.println("\n - Tempo a executar: " + aDistancia.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
                
            }
            else if(atividade instanceof ADistanciaAltimetria){

                ADistanciaAltimetria aDistanciaAltimetria = (ADistanciaAltimetria) atividade;
                System.out.println("\n - Distancia: " + aDistanciaAltimetria.getDistancia() + 
                                   "\n - Altitude: " + aDistanciaAltimetria.getAltimetria());

                if(aDistanciaAltimetria.getTempoAExecutar() >= 0)
                    System.out.println("\n - Tempo a executar: " + aDistanciaAltimetria.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
            }
            else if(atividade instanceof ARepeticoes){
                ARepeticoes aRepeticoes = (ARepeticoes) atividade;
                System.out.println("\n - Repeticoes: " + aRepeticoes.getRepeticoes());

                if(aRepeticoes.getTempoAExecutar() >= 0)
                    System.out.println("\n - Tempo a executar: " + aRepeticoes.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
            }
            else if(atividade instanceof ARepeticoesPeso){

                ARepeticoesPeso aRepeticoesPeso = (ARepeticoesPeso) atividade;
                System.out.println("\n - Repeticoes: " + aRepeticoesPeso.getRepeticoes() + 
                                   "\n - Peso: " + aRepeticoesPeso.getPeso());

                if(aRepeticoesPeso.getTempoAExecutar() >= 0)
                    System.out.println("\n - Tempo a executar: " + aRepeticoesPeso.getTempoAExecutar());
                else
                    System.out.println("Tempo a executar: Nenhum");
            }
            else {
                System.out.println("\n - Tipo desconhecido");
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

                    System.out.println("ID do Plano: " + plano.getID() + "\n - Data: " + plano.getData());
                    System.out.println("Atividades:");
                    
                    for(Atividade atividade : plano.getAtividades()){
                        System.out.println("- ID: " + atividade.getID() + "\n - Nome: " + atividade.getNome() + "\n");
                    }
                }
            }
        }else{
            System.out.println("Utilizador com ID " + idUtilizador + " nao encontrado...");
        }
    }

    public Atividade getAtividadePorId(int id, List<Atividade> ativs) {
        for (Atividade atividade : ativs) {
            if (atividade.getID() == id) {
                return atividade;
            }
        }
        return null; 
    }

    public PlanoTreino getPlanoTreinoPorId(int id, Utilizador user) {
        for (PlanoTreino planoTreino : user.getPlanosDeTreino()) {
            if (planoTreino.getID() == id) {
                return planoTreino;
            }
        }
        return null; 
    }

    public void saveData() {
        try {
            // Salvar dados dos utilizadores em arquivos individuais dentro da pasta "utilizadores"
            for (Utilizador utilizador : utilizadores) {
                String fileName = "saves/utilizadores/" + utilizador.getIdUtilizador() + ".dat";
                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(utilizador);
                objectOut.close();
                fileOut.close();
            }

            // Salvar lista de atividades em "atividades.txt"
            FileOutputStream atividadesFileOut = new FileOutputStream("saves/atividades.txt");
            ObjectOutputStream atividadesObjectOut = new ObjectOutputStream(atividadesFileOut);
            atividadesObjectOut.writeObject(atividadesDisponiveis);
            atividadesObjectOut.close();
            atividadesFileOut.close();

            // Salvar IDs em "ids.txt"
            FileOutputStream idsFileOut = new FileOutputStream("saves/ids.txt");
            ObjectOutputStream idsObjectOut = new ObjectOutputStream(idsFileOut);
            idsObjectOut.writeInt(idUtilizadores);
            idsObjectOut.writeInt(idAtividades);
            idsObjectOut.writeInt(idPlanos);
            idsObjectOut.close();
            idsFileOut.close();

            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    

    public void loadData(){
        try {
            // Carrega os dados dos utilizadores
            File utilizadoresFolder = new File("saves/utilizadores");
            File[] utilizadoresFiles = utilizadoresFolder.listFiles();

            if (utilizadoresFiles != null) {
                for (File file : utilizadoresFiles) {
                    FileInputStream fileIn = new FileInputStream(file);
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    Utilizador utilizador = (Utilizador) objectIn.readObject();
                    utilizadores.add(utilizador);
                    objectIn.close();
                    fileIn.close();
                }
            }

            // Carrega a lista de atividades
            FileInputStream atividadesFileIn = new FileInputStream("saves/atividades.txt");
            ObjectInputStream atividadesObjectIn = new ObjectInputStream(atividadesFileIn);
            atividadesDisponiveis = (List<Atividade>) atividadesObjectIn.readObject();
            atividadesObjectIn.close();
            atividadesFileIn.close();

            // Carrega os IDs
            FileInputStream idsFileIn = new FileInputStream("saves/ids.txt");
            ObjectInputStream idsObjectIn = new ObjectInputStream(idsFileIn);
            idUtilizadores = idsObjectIn.readInt();
            idAtividades = idsObjectIn.readInt();
            idPlanos = idsObjectIn.readInt();
            idsObjectIn.close();
            idsFileIn.close();

            System.out.println("Data loaded successfully.");
        } 
        catch(IOException | ClassNotFoundException e){
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public void apagarFicheirosSalvos() {
        try {
            // Apaga os arquivos de utilizadores
            File utilizadoresFolder = new File("saves/utilizadores");
            if(utilizadoresFolder.exists()){
                File[] utilizadoresFiles = utilizadoresFolder.listFiles();
                if(utilizadoresFiles != null){
                    for(File file : utilizadoresFiles){
                        file.delete();
                    }
                }
            }

            // Apaga os arquivo de atividades
            File atividadesFile = new File("saves/atividades.txt");
            if(atividadesFile.exists()){
                atividadesFile.delete();
            }

            // Apaga oa arquivo de IDs
            File idsFile = new File("saves/ids.txt");
            if(idsFile.exists()){
                idsFile.delete();
            }

            System.out.println("Files deleted with sucess!");
        } catch (Exception e) {
            System.out.println("Error while deleting files: " + e.getMessage());
        }
    }

    private void criarDiretorios() {
        
        File savesFolder = new File("saves");
        if(!savesFolder.exists()){
            savesFolder.mkdir();
        }

        File utilizadoresFolder = new File("saves/utilizadores");
        if(!utilizadoresFolder.exists()){
            utilizadoresFolder.mkdir();
        }

        File atividadesFile = new File("saves/atividades.txt");
        if(!atividadesFile.exists()){
            try{
                atividadesFile.createNewFile();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        File idsFile = new File("saves/ids.txt");
        if (!idsFile.exists()){
            try{
                idsFile.createNewFile();
            } 
            catch(IOException e){
                e.printStackTrace();
            }
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

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public void setUtilizadores(List<Utilizador> utilizadores) {
        this.utilizadores = utilizadores;
    }

    
    public List<Atividade> getAtividadesDisponiveis() {
        return atividadesDisponiveis;
    }

    public void setAtividadesDisponiveis(List<Atividade> atividadesDisponiveis) {
        this.atividadesDisponiveis = atividadesDisponiveis;
    }

   
}
