package PlanoTreino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Atividades.Atividade;

public class PlanoTreino {
    
    private int id;
    private LocalDate data;
    private List<Atividade> atividades;

    //Construtor
    public PlanoTreino(int id, LocalDate date){
        this.id = id;
        this.data = date;
        this.atividades = new ArrayList<>(); // Inicialize a lista de atividades como uma nova ArrayList
    }

    public void addAtividadeRealizada(Atividade atividade){
        int i = 0;
        for(; i < atividades.size(); i++){

            Atividade atv = atividades.get(i);
            if(atv.getID() == atividade.getID())
                return;
        }
        atividades.add(atividade);
    }

    public void removeAtividadeRealizada(int idAtividade){
        for(int i = 0; i < atividades.size(); i++){
            Atividade atv = atividades.get(i);

            if(atv.getID() == idAtividade){
                atividades.remove(i);
                break;
            }
        }
    }

    //SETS E GETS
    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}
