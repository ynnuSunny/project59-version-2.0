package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Email implements Initializable {
    public Label to;
    public TextArea emilWrite;
    public Label from;
    public Button sendButton;
    public Label massage;
    public String address;
    public String recever;
    public String name;
    public void sendFunction(MouseEvent mouseEvent) throws IOException {
        if(emilWrite.getText().isEmpty()){
            massage.setText("Text Area Is empty!");
        }
        else {
            Date date = new Date();
            FileWriter fileWriter = new FileWriter(address + recever + "/email.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[Date    : "+date+"\n");
            bufferedWriter.write("Send By : "+name+"\n");
            bufferedWriter.write("Email Body : \n");
            bufferedWriter.write(emilWrite.getText());
            bufferedWriter.write("\nEnd.]\n");
            bufferedWriter.close();
            fileWriter.close();
            massage.setText("Send SuccessFully");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Admin.adminName!=null){
            to.setText("TO : "+Admin.to);
            from.setText("From : Admin");
            address = Admin.address;
            recever = Admin.to;
            name = "Admin";
        }
        else if(Employee.from!=null){
            to.setText("To : Admin");
            from.setText("From : "+Employee.from);
            address = Employee.address;
            recever = Employee.to;
            name = Employee.from;
        }
    }
}
