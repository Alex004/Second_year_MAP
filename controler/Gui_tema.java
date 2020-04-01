package controler;

import Service.ServiceTema;
import entities.Tema;
import event.TemaChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import observer.Observer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Gui_tema implements Observer<TemaChangeEvent> {
    @FXML
    TableColumn<Tema,Long> tableColumnId;
    @FXML
    TableColumn<Tema,String> tableColumnSem;
    @FXML
    TableColumn<Tema,Integer> tableColumnDurata;
    @FXML
    TableColumn<Tema,Integer> tableColumnDates;
    @FXML
    TableColumn<Tema,Integer> tableColumnDescriere;
    
    @FXML
    TableView<Tema> tableView;
    ObservableList<Tema> modelGrade = FXCollections.observableArrayList();
    private ServiceTema serv=ServiceTema.getInstance();

//    @FXML
//    TextField textFieldId;
//    @FXML
//    TextField textFieldName;
//    @FXML
//    TextField textFieldGrupa;
    @FXML
    public void initialize()
    {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableColumnSem.setCellValueFactory(new PropertyValueFactory<>("sem"));
        tableColumnDurata.setCellValueFactory(new PropertyValueFactory<>("Durata"));
        tableColumnDates.setCellValueFactory(new PropertyValueFactory<>("sdate"));
        tableColumnDescriere.setCellValueFactory(new PropertyValueFactory<>("Descriere"));

        tableView.setItems(modelGrade);
    }

    private List<Tema> getTemaList() {
        List<Tema> l=new ArrayList<>();
        serv.getRepo().findAll().forEach(l::add);
        return l.stream()
                .map(n -> new Tema(n.getId(),n.getSem(),n.getDurata(),n.getSdate().toString(),n.getDescriere()))
                .collect(Collectors.toList());
    }

    private void handleFilter() {

    }


    public void setService(ServiceTema service) {
        serv = service;
        serv.addObserver( this);
        initModel();
    }



    private void showTematDialog(Tema tem) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Model_tema.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Message");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            Model_tema editTemaViewController = loader.getController();
            editTemaViewController.setService(serv, dialogStage, tem);



            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleAddTema(ActionEvent actionEvent) {

        showTematDialog(null);
    }
    @FXML
    public void handleDeleteTema(ActionEvent actionEvent) {
        Tema selected = (Tema) tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Tema deleted = serv.sterge_tema(selected);
            if (null != deleted)
                MesajEroare.showMessage(null, Alert.AlertType.INFORMATION, "Delete", "Tema a fost stearsa cu succes!");
        } else MesajEroare.showErrorMessage(null, "Nu ati selectat nicio Tema!");

    }
    @FXML
    public void handleUpdateTema(ActionEvent actionEvent) {
        Tema selected = tableView.getSelectionModel().getSelectedItem();
        System.out.println(selected);
        if (selected != null) {
            showTematDialog(selected);
        } else
            MesajEroare.showErrorMessage(null, "NU ati selectat nicio Tema");
    }

    @Override
    public void update(TemaChangeEvent e) {
        initModel();

    }

    private void initModel() {
        Iterable<Tema> stud = serv.getRepo().findAll();
        List<Tema> studList = StreamSupport.stream(stud.spliterator(), false)
                .collect(Collectors.toList());
        modelGrade.setAll(studList);
    }
}
