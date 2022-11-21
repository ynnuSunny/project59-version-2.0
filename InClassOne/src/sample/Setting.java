package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Setting implements Initializable {


    public Button goToMain;
    public ImageView userImage;
    public TextArea aboutUser;
    public TextField phoneNumber;
    public Button choseImage;
    public TextField imageUrl;
    public Label mssageUserName;
    public TextField firstName;
    public TextField lastName;
    public Label hi;


    public void myProjectList() throws IOException {
        File file = new File(Create.address+"myProject.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("");
        bufferedWriter.close();
        fileWriter.close();
    }

    public void mainSeance(MouseEvent mouseEvent) throws IOException {

        if(firstName.getText().isEmpty()){
            mssageUserName.setText("Provide First name");
        }
        else if(lastName.getText().isEmpty()){
            mssageUserName.setText("Provide Last name");
        }
        else if(phoneNumber.getText().isEmpty()){
            mssageUserName.setText("Provide Phone number");
        }
        else if(aboutUser.getText().isEmpty()){
            mssageUserName.setText("Write your bio");
        }
        else {
            File file = new File(Create.address+"profileData.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write( Create.userNam+"\n");
          //  MainPage.name.setText(Create.userNam);
            bufferedWriter.write(aboutUser.getText()+"\n");
            bufferedWriter.write(firstName.getText()+" "+lastName.getText()+"\n");
            bufferedWriter.write(phoneNumber.getText()+"\n");
            bufferedWriter.write(Create.userEmail);

            bufferedWriter.close();
            fileWriter.close();

//            new MainPage(firstName.getText()+" "+lastName.getText()
//            ,aboutUser.getText(),Create.userEmail,phoneNumber.getText(),Create.userNam);
            myProjectList();


            AnchorPane pane = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            Stage stage = (Stage) goToMain.getScene().getWindow();
            stage.setScene(new Scene(pane));
        }

    }

    public void imageController(MouseEvent mouseEvent) throws FileNotFoundException {
          JFileChooser choooser = new JFileChooser();
          choooser.showOpenDialog(null);
          File file = choooser.getSelectedFile();
          String fileName = file.getAbsolutePath();

         InputStream stream = new FileInputStream(fileName);

        imageUrl.setText(fileName);
        Image image = new Image(file.getName());
       // ImageIcon icon = new ImageIcon(image);
        userImage.setImage(image);
        //Image image = icon.getImage().getScaledInstance(userImage.getW)

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.hi.setText("Hi! "+Create.userNam);
    }
}
