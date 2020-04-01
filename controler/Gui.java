package controler;

import Service.ServiceStudent;
import entities.Student;
import event.StudentChangeEvent;
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

public class Gui implements Observer<StudentChangeEvent> {
    @FXML
    TableColumn<Student,Long> tableColumnId;
    @FXML
    TableColumn<Student,String> tableColumnName;
    @FXML
    TableColumn<Student,Integer> tableColumnGrupa;
    @FXML
    TableView<Student> tableView;
    ObservableList<Student> modelGrade = FXCollections.observableArrayList();
    private ServiceStudent serv=ServiceStudent.getInstance();

    @FXML
    TextField textFieldId;
    @FXML
    TextField textFieldName;
    @FXML
    TextField textFieldGrupa;
    @FXML
    public void initialize()
    {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableColumnGrupa.setCellValueFactory(new PropertyValueFactory<>("grupa"));

        tableView.setItems(modelGrade);
    }

    private List<Student> getStudentList() {
        List<Student> l=new ArrayList<>();
        serv.getRepo().findAll().forEach(l::add);
        return l.stream()
                .map(n -> new Student(n.getName(),n.getGrupa(),n.getId()))
                .collect(Collectors.toList());
    }

    private void handleFilter() {

    }


    public void setService(ServiceStudent service) {
        serv = service;
        serv.addObserver(this);
        initModel();



    }

    @FXML
    public void handleAddStudent(ActionEvent actionEvent) {

        showStudenttDialog(null);
    }

    private void showStudenttDialog(Student stud) {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Model.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Message");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            Model editStudentViewController = loader.getController();
            editStudentViewController.setService(serv, dialogStage, stud);



            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteStudent(ActionEvent actionEvent) {
        Student selected = (Student) tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Student deleted = serv.sterge_student(selected);
            if (null != deleted)
                MesajEroare.showMessage(null, Alert.AlertType.INFORMATION, "Delete", "Studentul a fost sters cu succes!");
        } else MesajEroare.showErrorMessage(null, "Nu ati selectat nici un student!");

    }
    @FXML
    public void handleUpdateStudent(ActionEvent actionEvent) {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        System.out.println(selected);
        if (selected != null) {
            showStudenttDialog(selected);
        } else
            MesajEroare.showErrorMessage(null, "NU ati selectat nici un student");
    }

    @Override
    public void update(StudentChangeEvent studentChangeEvent) {
        initModel();

    }

    private void initModel() {
        Iterable<Student> stud = serv.getRepo().findAll();
        List<Student> studList = StreamSupport.stream(stud.spliterator(), false)
                .collect(Collectors.toList());
        modelGrade.setAll(studList);
    }
}
