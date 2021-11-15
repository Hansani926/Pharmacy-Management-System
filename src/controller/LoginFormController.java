package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class LoginFormController {

    public AnchorPane root;

    public TextField txtEmail;
    public TextField txtPassword;
    public JFXButton btnLogin;

    public void setUI(String location) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/"+location))));
        stage.centerOnScreen();
    }


  
    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.length()>0 && password.length()>0){

            if (email.equalsIgnoreCase("Pharmacy@gmail.com")
                    && password.equalsIgnoreCase("1234")){


                URL resource = this.getClass().
                        getResource("/view/mainForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene= new Scene(load);
                Stage stage= (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();


            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again !!!!",
                        ButtonType.OK,ButtonType.NO).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty !!!!",
                    ButtonType.OK,ButtonType.NO).show();
        }


    }

    }






