import Service.ServiceStudent;
import Service.ServiceTema;
import UI.Ui;
import controler.Gui;
import controler.Gui_plan;
import controler.Gui_tema;
import controler.test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    protected Object clone() throws
            CloneNotSupportedException
    {
        return super.clone();
    }
    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
//        Ui x=Ui.getInstance();
//        x.cmd("23");

    }
    @Override
    public void start(Stage primaryStage) throws CloneNotSupportedException,IOException {

//Load root layout from fxml file.

            FXMLLoader loader=new FXMLLoader(Main.class.getResource("/view/start.fxml"));


            AnchorPane rootLayout=(AnchorPane) loader.load();
// Show the scene containing the root layout.

            //Gui ctrl=loader.getController();
            //ctrl.setService(ServiceStudent.getInstance());
        primaryStage.setScene(new Scene(rootLayout,700,500));
            primaryStage.setTitle("Lab_7");
            primaryStage.show();



    }
}
