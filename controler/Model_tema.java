package controler;

import Service.ServiceTema;
import entities.Tema;
import entities.validators.ValidationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Model_tema {


    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldDurata;
    @FXML
    private TextField textFieldDates;
    @FXML
    private TextField textFieldSem;
    @FXML
    private TextField textFieldDescriere;

    private ServiceTema service;
    Stage dialogStage;
    Tema Tema;
    @FXML
    private void initialize() {
    }

    @FXML
    public void handleSave(ActionEvent actionEvent) {
        //textFieldId.setEditable(false);
        String id=textFieldId.getText();
        String sem=textFieldSem.getText();
        String durata=textFieldDurata.getText();
        String data=textFieldDates.getText();
        String descr=textFieldDescriere.getText();

        if (null == this.Tema)
        {
            Tema tem=new Tema(Long.parseLong(id),Integer.parseInt(sem),Integer.parseInt(durata),data,descr);
            saveTema(tem);
        }
        else
        {
            Tema tem=new Tema(Long.parseLong(id),Integer.parseInt(sem),Integer.parseInt(durata),data,descr);
            updateStuent(tem);
        }


    }

    private void updateStuent(Tema stud) {
        try {
            Tema r= this.service.update_tema(stud);

            if (r==null)
                MesajEroare.showMessage(null, Alert.AlertType.INFORMATION,"Modificare mesaj","Mesajul a fost modificat");
        } catch (ValidationException e) {
            MesajEroare.showErrorMessage(null,e.getMessage());
        }
        dialogStage.close();
    }

    private void saveTema(Tema stud) {
        try {
            Tema r= this.service.adauga_tema(stud);
            if (r==null)
                dialogStage.close();
            MesajEroare.showMessage(null, Alert.AlertType.INFORMATION,"Slavare mesaj","Mesajul a fost salvat");
        } catch (ValidationException e) {
            MesajEroare.showErrorMessage(null,e.getMessage());
        }
    }

    public void setService(ServiceTema studServ, Stage stage, Tema stud)
    {
        service=studServ;
        dialogStage=stage;
        Tema=stud;
        textFieldId.setEditable(false);
        if (null != stud) {
            setFields(stud);

        }

    }
    public void setFields(Tema s)
    {
        textFieldId.setText(s.getId().toString());
        textFieldSem.setText(s.getSem().toString());
        textFieldDescriere.setText(s.getDescriere());
        textFieldDates.setText(s.getSdate().toString());
        textFieldDurata.setText(s.getDurata().toString());
    }
    @FXML
    public void handleCancel(){
        dialogStage.close();
    }
}
