package controler;

import Service.ServiceStudent;
import entities.Student;
import entities.validators.ValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Model {


    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldGrupa;

    private ServiceStudent service;
    Stage dialogStage;
    Student student;
    @FXML
    private void initialize() {
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        //textFieldId.setEditable(false);
        String id=textFieldId.getText();
        String nume=textFieldNume.getText();
        String grupa=textFieldGrupa.getText();

        if (null == this.student)
        {
            Student stud=new Student(nume,Integer.parseInt(grupa));
            saveStudent(stud);
        }
        else
        {
            Student stud=new Student(nume,Integer.parseInt(grupa),Long.parseLong(id));
            updateStuent(stud);
        }


    }

    private void updateStuent(Student stud) {
        try {
            Student r= this.service.update_student(stud);

            if (r==null)
                MesajEroare.showMessage(null, Alert.AlertType.INFORMATION,"Modificare mesaj","Mesajul a fost modificat");
        } catch (ValidationException e) {
            MesajEroare.showErrorMessage(null,e.getMessage());
        }
        dialogStage.close();
    }

    private void saveStudent(Student stud) {
        try {
            Student r= this.service.adauga_student(stud);
            if (r==null)
                dialogStage.close();
            MesajEroare.showMessage(null, Alert.AlertType.INFORMATION,"Slavare mesaj","Mesajul a fost salvat");
        } catch (ValidationException e) {
            MesajEroare.showErrorMessage(null,e.getMessage());
        }
    }

    public void setService(ServiceStudent studServ, Stage stage, Student stud)
    {
        service=studServ;
        dialogStage=stage;
        student=stud;
        textFieldId.setEditable(false);
        if (null != stud) {
            setFields(stud);

        }

    }
    public void setFields(Student s)
    {
        textFieldId.setText(s.getId().toString());
        textFieldNume.setText(s.getName());
        textFieldGrupa.setText(s.getGrupa().toString());
    }
    @FXML
    public void handleCancel(){
        dialogStage.close();
    }
}
