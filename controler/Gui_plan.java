package controler;

import Service.ServiceNota;
import Service.ServiceStudent;
import Service.ServiceTema;
import event.TemaChangeEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import observer.Observer;

import java.io.IOException;

public class Gui_plan implements Observer<TemaChangeEvent> {




    @FXML
    public void handleStudent()
    {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Gui.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Studenti");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            Gui ctrl=loader.getController();
            ctrl.setService(ServiceStudent.getInstance());

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleTema()
    {
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Gui_tema.fxml"));

            AnchorPane root = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Teme");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);

            Gui_tema ctrl=loader.getController();
            ctrl.setService(ServiceTema.getInstance());

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleNota()
    {
        System.out.println("handasN");
        try {
            // create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Gui_nota.fxml"));
//            loader.setLocation(getClass().getResource("/view/Gui_nota.fxml"));
            System.out.println("ceva1");
            AnchorPane root = (AnchorPane) loader.load();
            System.out.println("ceva adsf");
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Note");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);


            System.out.println("handN");
            Gui_nota ctrl=loader.getController();
            ctrl.setService(ServiceNota.getInstance());

            dialogStage.show();

        } catch (IOException e) {
            System.out.println(e);
         //   e.printStackTrace();
        }
    }


    @Override
    public void update(TemaChangeEvent temaChangeEvent) {

    }
}
