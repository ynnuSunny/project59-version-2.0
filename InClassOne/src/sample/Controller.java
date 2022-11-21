package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

public class Controller {
    public PasswordField passwordLogin;
    public Button loginButton;
    public Button createAccount;
    public Label massage;
    public static String address;
    public TextField userName;
    @FXML
    Label Loginmassage;




    public void loginFunction(MouseEvent mouseEvent) throws IOException {
        File file = new File("UserInformation/loginInfo");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        String [] data;
        int i = 0;
        while((s = bufferedReader.readLine())!=null){
             data = s.split(" ");
             if(data[0].equals(userName.getText())){
                if(data[1].equals(passwordLogin.getText())){
                    i = 1;
                    break;
                }
             }
        }
        if(i==1){
            address = "UserInformation/"+userName.getText()+"/";
            AnchorPane pane = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            Stage primay = (Stage) loginButton.getScene().getWindow();
            primay.setScene(new Scene(pane));
        }
        else{
           massage.setText("Wrong Email or Password");
        }


    }

    public void createFunction(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("create.fxml"));
        Stage primay = (Stage) createAccount.getScene().getWindow();
        primay.setScene(new Scene(pane));

    }

//
//    private void loginFunction(MouseEvent mouseEvent) {
//
//
//    }
}
