package cli;

import manager.Manager;
import plano_treino.PlanoTreino;
import users.Utilizador;

import java.util.*;
import java.io.IOException;
import java.time.LocalDateTime;

import atividades.ADistancia;
import atividades.ADistanciaAltimetria;
import atividades.ARepeticoes;
import atividades.ARepeticoesPeso;
import atividades.Atividade;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Frontend {

    private Manager manager;

    private LocalDateTime currentTime;

    private DateTimeFormatter timeFormat;

    public Frontend(Manager manager) {
        this.manager = manager;
        this.timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in); 

        this.currentTime = LocalDateTime.now();
  
        while(true) {
            clearScreen();
            this.printHelp();
            System.out.print("> ");
            String line = scanner.nextLine().strip();
            clearScreen();

            int lineInt = Integer.parseInt(line);

            switch (lineInt) {
                case 1:
                    this.createUser(scanner);
                    break;

                case 2:
                    this.createActivity(scanner);
                    break;

                case 3:
                    this.searchUserByID(scanner);
                    break;

                case 4:
                    this.showAvailableActivities(scanner);
                    break;
                
                case 5:
                    this.showAllUsers(scanner);
                    break;

                case 6:
                    this.advanceTime(scanner);
                    break;

                case 7:
                    this.saveAndLoad(scanner);
                    break;

                case 8:
                    this.printHelp();
                    break;

                case 0:
                    return;
                default:
                    System.err.printf("Unknown command '%s'\n", line);
                    pressToContinue(scanner);
                    break;
            }
        }

        //scanner.close(); 
    }

    private void createUser(Scanner scanner) {

        boolean inputValid = false;

        while(!inputValid){

            try {  
                System.out.print("Name: ");
                String username = scanner.nextLine();
        
                System.out.printf("1. Ocasional\n2. Amateur\n3. Profissional\nType: ");
                String type = scanner.nextLine();
        
                System.out.print("Email: ");
                String email = scanner.nextLine();
        
                System.out.print("City: ");
                String city = scanner.nextLine();
        
                System.out.print("Age: ");
                String age = scanner.nextLine();
        
                System.out.print("Height(cm): ");
                String height = scanner.nextLine();
        
                System.out.print("Weight (kg): ");
                String weight = scanner.nextLine();
        
                System.out.print("Avarage Heart Rate: ");
                String heartRate = scanner.nextLine();
        
        
                switch (Integer.parseInt(type)) {
                    case 1:
                        manager.criarUtilizador(username, Float.parseFloat(weight), Integer.parseInt(age) , Float.parseFloat(height), city, email, Float.parseFloat(heartRate), Integer.parseInt(type));
                        break;
        
                    case 2:
                        manager.criarUtilizador(username, Float.parseFloat(weight), Integer.parseInt(age) , Float.parseFloat(height), city, email, Float.parseFloat(heartRate), Integer.parseInt(type));
                        break;
        
                    case 3:
                        manager.criarUtilizador(username, Float.parseFloat(weight), Integer.parseInt(age) , Float.parseFloat(height), city, email, Float.parseFloat(heartRate), Integer.parseInt(type));
                        break;
                
                    default:
                        System.err.println("Invalid type!");
                        break;
                }
                System.out.println("\n\n\nUser added with sucess!");
                inputValid = true;
                pressToContinue(scanner);
                clearScreen();

            } catch (NumberFormatException | InputMismatchException e) {
                clearScreen();
                System.out.println("Invalid input! Please enter valid input.");
                scanner.nextLine();
            }
        }
    }

    private void createActivity(Scanner scanner) {

        boolean inputValid = false;

        while(!inputValid){

            try {
                System.out.print("Name: ");
                String activityName = scanner.nextLine();
        
                System.out.printf("1. Distance\n2. Distance and Altimetry\n3. Repetitions\n4. Repetitions with weigths\nType: ");
        
                String type = scanner.nextLine();
        
                System.out.print("Difficulty (Normal / Hard): ");
                String difficulty = scanner.nextLine();
                boolean dif;
        
                if(difficulty.compareTo("Normal") == 0){
                    dif = false;
                }
                else if(difficulty.compareTo("Hard") == 0){
                    dif = true;
                }
                else{
                    throw new IllegalArgumentException("Invalid dificulty type!");
                }
        
                switch (Integer.parseInt(type)) {
                    case 1:
                        manager.criarAtividade(activityName, dif, Integer.parseInt(type));
                        break;
        
                    case 2:
                        manager.criarAtividade(activityName, dif, Integer.parseInt(type));
                        break;
        
                    case 3:
                        manager.criarAtividade(activityName, dif, Integer.parseInt(type));
                        break;
        
                    case 4:
                        manager.criarAtividade(activityName, dif, Integer.parseInt(type));
                        break;
                
                    default:
                        throw new IllegalArgumentException("Invalid activity type!");
    
                }
                System.out.println("\n\nActivity added with sucess!");
                inputValid = true;
                pressToContinue(scanner);
                clearScreen();

            } catch (IllegalArgumentException e) {
                clearScreen();
                System.out.println(e.getMessage());
            }
        }

    }

    private void searchUserByID(Scanner scanner) {

        boolean inputValid = false;

        System.out.print("ID: ");
        int userID = Integer.parseInt(scanner.nextLine());

        clearScreen();
        
        Optional<Utilizador> u = this.manager.fetchUserByID(userID);
        if (u.isEmpty()) {
            System.out.println("User does not exist.");
            pressToContinue(scanner);
            return;
        }
    
        while(!inputValid){

            try {
                System.out.println("1. Display all activities");
                System.out.println("2. Display training plan");
                System.out.println("3. Create training plan");
                System.out.println("4. Do the activity (Test)");
                System.out.println("0. Go back");
                System.out.print("> ");
                int line = Integer.parseInt(scanner.nextLine());
                
                clearScreen();
        
                switch (line) {

                    case 0:
                        return;

                    case 1:    
                        manager.mostrarAtividades(manager.getUtilizadorPorId(userID).getAtividadesRealizadas());
                        
                        break;
        
                    case 2:
                        manager.mostrarPlanosTreinoUtilizador(userID);
                        break;
        
                    case 3:

                        boolean inputValid2 = false;

                        while(!inputValid2){
                            try {
                                Atividade atividade2;
                                System.out.print("Enter the date and time for the training plan (dd/MM/yyyy HH:mm:ss): ");
                                String dateString = scanner.nextLine();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                                LocalDateTime date = LocalDateTime.parse(dateString, formatter);
                    
                                manager.mostrarAtividades();
                                
                                List<Integer> selectedActivityIDs = new ArrayList<>();
                                System.out.println("Enter the IDs of the activities you want to add to the training plan (separated by commas): ");
                                String input = scanner.nextLine();
                                String[] idStrings = input.split(",");

                                for (String idString : idStrings) {
                                    selectedActivityIDs.add(Integer.parseInt(idString.trim()));
                                }
                                
                                for(int id : selectedActivityIDs){
                                    Atividade atividade = manager.getAtividadePorId(id, manager.getAtividadesDisponiveis());
                                    if(atividade == null){
                                        throw new IllegalArgumentException("Activity with ID " + id + " does not exist.");
                                    }
                                }

                                List<Atividade> selectedActivities = new ArrayList<>();
                                for (int id : selectedActivityIDs) {
                                    Atividade atividade = manager.getAtividadePorId(id, manager.getAtividadesDisponiveis());

                                    System.out.printf("For activity " + atividade.getNome() + " e ID " + atividade.getID() + ":\n");
                                    if (atividade != null) {
                                        if (atividade instanceof ARepeticoesPeso) {
                
                                            System.out.print("Enter the number of repetitions: ");
                                            int numeroRepeticoes = Integer.parseInt(scanner.nextLine());
                                            
                                            System.out.print("Enter the weight(kg): ");
                                            int peso = Integer.parseInt(scanner.nextLine());
                
                                            System.out.print("Enter the execution time (-1 if doesnt matter): ");
                                            int tempoAExecutar = Integer.parseInt(scanner.nextLine());
                
                                            atividade2 = new ARepeticoesPeso(atividade.getID(), atividade.getNome(), atividade.getIsHard(), numeroRepeticoes, peso, tempoAExecutar);
                                            selectedActivities.add(atividade2);
                                        } 
                                        else if(atividade instanceof ARepeticoes){
                                            
                                            System.out.print("Enter the number of repetitions: ");
                                            int numeroRepeticoes = Integer.parseInt(scanner.nextLine());
                
                                            System.out.print("Enter the execution time (-1 if doesnt matter): ");
                                            int tempoAExecutar = Integer.parseInt(scanner.nextLine());
                                            
                                            atividade2 = new ARepeticoes(atividade.getID(), atividade.getNome(), atividade.getIsHard(), numeroRepeticoes, tempoAExecutar);
                                            selectedActivities.add(atividade2);
                                        } 
                                        else if(atividade instanceof ADistanciaAltimetria){
                                            
                                            System.out.print("Enter the distance(m): ");
                                            float distancia = Float.parseFloat(scanner.nextLine());
                
                                            System.out.print("Enter the altimetry(m): ");
                                            float altimetria = Float.parseFloat(scanner.nextLine());
                
                                            System.out.print("Enter the execution time (-1 if doesnt matter): ");
                                            int tempoAExecutar = Integer.parseInt(scanner.nextLine());
                
                                            atividade2 = new ADistanciaAltimetria(atividade.getID(), atividade.getNome(), atividade.getIsHard(), distancia, altimetria, tempoAExecutar);
                                            selectedActivities.add(atividade2);
                                        }
                                        else if(atividade instanceof ADistancia){
                                            
                                            System.out.print("Enter the distance(m): ");
                                            float distancia = Float.parseFloat(scanner.nextLine());
                
                                            System.out.print("Enter the execution time (-1 if doesnt matter): ");
                                            int tempoAExecutar = Integer.parseInt(scanner.nextLine());
                                        
                                            atividade2 = new ADistancia(atividade.getID(), atividade.getNome(), atividade.getIsHard(), distancia, tempoAExecutar);
                                            selectedActivities.add(atividade2);
                                        }
                                    }
                                }
                
                                manager.criarPlanoTreino(date, manager.getUtilizadorPorId(userID), selectedActivities);
                                System.out.println("Training plan created successfully!");
                                break;
                            } 
                            catch (DateTimeParseException e) {
                                clearScreen();
                                System.err.println("Invalid input! Please enter valid input.");
                                scanner.nextLine();
                            }
                            catch(NullPointerException e){
                                clearScreen();
                                System.err.println("Empty list of activities.");
                                break;
                            }
                            catch (IllegalArgumentException e) {
                                clearScreen();
                                System.err.println(e.getMessage());
                                scanner.nextLine();
                            }
                        }
                        break;
        
                    case 4:
                        manager.mostrarPlanosTreinoUtilizador(userID);
                
                        System.out.print("Choose a training plan ID: ");
                        int trainingPlanID = Integer.parseInt(scanner.nextLine());
                    
                        PlanoTreino planoTreino = manager.getPlanoTreinoPorId(trainingPlanID,manager.getUtilizadorPorId(userID));
                        if(planoTreino == null){
                            System.out.println("Training plan not found.");
                            pressToContinue(scanner);
                            clearScreen();
                            break;
                        }
                        
                        clearScreen();
                        manager.mostrarAtividades(planoTreino.getAtividades());
        
                        System.out.print("Choose an activity ID: ");
                        int activityID = Integer.parseInt(scanner.nextLine());
                    
                        Atividade atividade = manager.getAtividadePorId(activityID,planoTreino.getAtividades());
                        if (atividade == null) {
                            System.out.println("Activity not found.");
                            pressToContinue(scanner);
                            clearScreen();
                            break;
                        }
        
                        clearScreen();
        
                        System.out.println();
                        System.out.print("Enter the time needed to finish the activity(s): ");
                        int tempoAExecutar = Integer.parseInt(scanner.nextLine());
        
                        System.out.print("Enter the average heart rate during the activity: ");
                        float freqMediaCardiaca = Float.parseFloat(scanner.nextLine());
        
                        manager.getUtilizadorPorId(userID).addAtividadeRealizada(atividade, tempoAExecutar,freqMediaCardiaca);
                        System.out.println();
                        System.out.println("Activity added to your history.");
                        break;
                    
                    default:
                        throw new IllegalArgumentException("Invalid option");
                }
                pressToContinue(scanner);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                clearScreen();
            }
            clearScreen();
        }

    }

    private void saveAndLoad(Scanner scanner){
        System.out.println("1. Save data");
        System.out.println("2. Load data");
        System.out.println("3. Delete saves");
        System.out.println("0. Go back");
        System.out.print("> ");
        int choice = Integer.parseInt(scanner.nextLine());

        clearScreen();
        switch(choice){

            case 0:
                return;

            case 1:
                manager.saveData();
                break;

            case 2:
                manager.loadData();
                break;

            case 3:
                manager.apagarFicheirosSalvos();
                break;

            default:
                System.out.println("Invalid option");
                break;
        }
        pressToContinue(scanner);
    }


    private void showAvailableActivities(Scanner scanner){
        manager.mostrarAtividades();
        System.out.println();
        pressToContinue(scanner);
    }

    private void showAllUsers(Scanner scanner){
        manager.mostrarUtilizadores();
        System.out.println();
        pressToContinue(scanner);
    }

    private void pressToContinue(Scanner scanner){
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    
    private void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    
    private void advanceTime(Scanner scanner) {
        System.out.print("Days: ");
        long days = Long.parseLong(scanner.nextLine());
        LocalDateTime newTime = this.currentTime.plusDays(days);

        
        for (Utilizador user : manager.getUtilizadores()) {
            for (PlanoTreino planoTreino : user.getPlanosDeTreino()) {
                if (planoTreino.getData().isBefore(newTime)) {
                    for (Atividade atividade : planoTreino.getAtividades()) {
                        user.addAtividadeRealizada(atividade,500,110); // Valores default
                    }
                }
            }
    }

    this.currentTime = newTime;
    System.out.println("Time advanced successfully!");
    }

    private void printHelp() {
        System.out.printf("Date: %s\n", this.currentTime.format(this.timeFormat));
        System.out.println("1. Create user");
        System.out.println("2. Create activity");
        System.out.println("3. Search user by ID");
        System.out.println("4. Show Available Activities");
        System.out.println("5. Show All Users");
        System.out.println("6. Advance time");
        System.out.println("7. Save and Load");
        System.out.println("8. Help");
        System.out.println("0. Exit");
    }
}