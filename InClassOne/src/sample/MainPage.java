package sample;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class MainPage implements Initializable {
    public Button startButton;
    public Label name;
    public Label bio;
    public Label email;
    public Label phone;
    public Label userName;
    public Button joinButton;
    public static  String adminName;
    public TextField joinProjectName;
    public Label joinMassage;
    public static String projectAddress;
    public static String USERNAME;
    public static String PROJECTNAME;
    public ListView listView;
    public Button reLoad;
    public Button logout;

    //    MainPage(String name,String bio,String email,String phone,String userName){
//        this.name.setText(name);
//        this.bio.setText(bio);
//        this.email.setText(email);
//        this.email.setText(email);
//        this.phone.setText(phone);
//        this.userName.setText(userName);
//    }

    public void myProjectList() throws IOException {
        File file = new File("UserInformation/"+USERNAME+"/myProject.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        ArrayList<String> data = new ArrayList<>();
       // listView.getItems().add(null);

        while ((s=bufferedReader.readLine())!=null){
            data.add(s);
        }
        bufferedReader.close();
        fileReader.close();
        listView.getItems().addAll(data);

    }

@Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

    File file;
    if(Create.address!=null){
     file = new File(Create.address+"profileData.txt");}
    else{
     file = new File(Controller.address+"profileData.txt");
    }

    try {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        int i=0;
        while((s=bufferedReader.readLine())!=null){
            if(i==0)userName.setText(s);
            else if(i==1)bio.setText(s);
            else if(i==2)name.setText(s);
            else if(i==3)phone.setText(s);
            else if(i==4)email.setText(s);
            i++;
        }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    USERNAME = userName.getText();
    try {
        myProjectList();
    } catch (IOException e) {
        e.printStackTrace();
    }



}


    public int checkProjectName() throws IOException {
       File file = new File("Project/projectNameList");
       FileReader fileWriter = new FileReader(file);
       BufferedReader bufferedReader = new BufferedReader(fileWriter);

       String s;

       while((s=bufferedReader.readLine())!=null){
           if(s.equals(joinProjectName.getText())){
               return 1;
           }

       }
       bufferedReader.close();
       fileWriter.close();

      return  0;
    }
    public int checkEmployee() throws IOException {
        File file = new File("Project/"+joinProjectName.getText()+"/"+"projectInformation");
        FileReader fileWriter = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileWriter);

        String s;

        while((s=bufferedReader.readLine())!=null){
            if(s.equals(userName.getText())){
                return 1;
            }

        }
        bufferedReader.close();
        fileWriter.close();

        return  0;

    }



    public void startProject(MouseEvent mouseEvent) throws IOException {
        adminName = userName.getText();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("startProject.fxml"));
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setFullScreen(true);
        stage.setScene(new Scene(pane));

    }


    public void joinAProject(MouseEvent mouseEvent) throws IOException {

        if(checkProjectName()==0){
            joinMassage.setText("Not Found This Project");
        }
        else if(checkEmployee()==0){
            joinMassage.setText("You Are Not Assign for this Task");
        }
        else {

            PROJECTNAME = joinProjectName.getText();
            projectAddress = "Project/"+joinProjectName.getText();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("employee.fxml"));
            Stage stage = (Stage) joinButton.getScene().getWindow();
            stage.setFullScreen(true);
            stage.setScene(new Scene(pane));
        }
    }



    public void visitButton(MouseEvent mouseEvent) throws IOException {
            String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
            PROJECTNAME = selectedItem;
            projectAddress = "Project/"+selectedItem+"/";
        AnchorPane pane = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Stage stage = (Stage) joinButton.getScene().getWindow();
        stage.setFullScreen(true);
        stage.setScene(new Scene(pane));

    }

    public void logoutButton(MouseEvent mouseEvent) throws IOException {
        GridPane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }
}
