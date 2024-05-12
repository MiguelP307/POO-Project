package plano_treino;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import atividades.Atividade;

public class PlanoTreino implements Serializable{
    
    private int id;
    private LocalDateTime data;
    private List<Atividade> atividades;

    //Construtor
    public PlanoTreino(int id, LocalDateTime date){
        this.id = id;
        this.data = date;
        this.atividades = new ArrayList<>();
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}
