package controler;

import Service.ServiceNota;
import Service.ServiceStudent;
import Service.ServiceTema;
import entities.Nota;
import entities.Student;
import entities.Tema;
import event.NotaChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import observer.Observer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Gui_nota implements Observer<NotaChangeEvent> {

    public Gui_nota() {
        System.out.println("constr_nota");
    }

    @FXML
    TableColumn<Student,String> tableColumnStudent;
    @FXML
    TableColumn<Student,Integer> tableColumnGrupa;
    @FXML
    TableColumn<Nota,Float> tableColumnNota;
    @FXML
    TableColumn<Nota,String> tableColumnFeedback;
    @FXML
    TableColumn<Nota,String> tableColumnTema;

    @FXML
    TableView<Nota> tableView;

    ObservableList<Nota> modelGradeNota= FXCollections.observableArrayList();
//    ObservableList<Student> modelGradeStudent= FXCollections.observableArrayList();
//    ObservableList<Tema> modelGradeTems= FXCollections.observableArrayList();
//    private ServiceTema servTema=ServiceTema.getInstance();
//    private ServiceStudent servStudent=ServiceStudent.getInstance();
    private ServiceNota servNota= ServiceNota.getInstance();

    @FXML
    public void initialize(){
        tableColumnStudent.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        tableColumnTema.setCellValueFactory(new PropertyValueFactory<>("id_tema"));
        tableColumnFeedback.setCellValueFactory(new PropertyValueFactory<>("feedback"));

        tableView.setItems(modelGradeNota);
        //tableView.setItems(modelGradeStudent);

    }



    public void setService(ServiceNota nota ) {
        System.out.println("srvn");
 //       servTema = service;
      servNota= nota;
//        servStudent=student;



        servNota.addObserver( this);
        initModel();
    }

    private void initModel() {
        Iterable<Nota> not = servNota.getRepo().findAll();
        List<Nota> notList = StreamSupport.stream(not.spliterator(), false)
                .collect(Collectors.toList());
//        Iterable<Tema> tema = servTema.getRepo().findAll();
//        List<Tema> temaList = StreamSupport.stream(tema.spliterator(), false)
//                .collect(Collectors.toList());
//        Iterable<Student> stud = servStudent.getRepo().findAll();
//        List<Student> studList = StreamSupport.stream(stud.spliterator(), false)
//                .collect(Collectors.toList());
        modelGradeNota.setAll(notList);
//        modelGradeStudent.setAll(studList);
//        modelGradeTems.setAll(temaList);
    }

    @Override
    public void update(NotaChangeEvent notaChangeEvent) {
        initModel();
    }

    @FXML
    public void handleAddNota(ActionEvent actionEvent) {

        showTematDialog(null);
    }

    private void showTematDialog(Object o) {
    }

    @FXML
    public void handleDeleteNota(ActionEvent actionEvent) {
        Nota selected = (Nota) tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Nota deleted=servNota.sterge_nota(selected);
            if (null != deleted)
                MesajEroare.showMessage(null, Alert.AlertType.INFORMATION, "Delete", "Nota a fost stearsa cu succes!");
        } else MesajEroare.showErrorMessage(null, "Nu ati selectat nicio Nota!");

    }
    @FXML
    public void handleUpdateNota(ActionEvent actionEvent) {
        Nota selected = tableView.getSelectionModel().getSelectedItem();
        System.out.println(selected);
        if (selected != null) {
            showTematDialog(selected);
        } else
            MesajEroare.showErrorMessage(null, "NU ati selectat nicio Tema");
    }
}
