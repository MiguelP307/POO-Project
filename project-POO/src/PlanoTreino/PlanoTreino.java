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
