package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Employee implements Initializable {

    public Button backButton;
    public Button sendEmailButton;
    public Button saveTask;
    public Button sendAdmin;
    public TextArea myTaskDesk;
    public Label projectName;
    public ListView listView;
    public Button open;
    public TextArea myEmailDesk;
    public Button reloadEmail;
    public static String to;
    public static String from;
    public static String address;
    public Button deleteMail;
    String userName;
    String Address;
    public void goBack(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }

    public void myProjectList(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        ArrayList<String> data = new ArrayList<>();
        listView.getItems().clear();

        while ((s=bufferedReader.readLine())!=null){
            data.add(s);
        }
        bufferedReader.close();
        fileReader.close();
        listView.getItems().addAll(data);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //List Implement
        userName = MainPage.USERNAME;
        Address = MainPage.projectAddress;
        projectName.setText("Project Name : "+MainPage.PROJECTNAME) ;

        String ad =Address + "/" + userName + "/"+"versionList.txt";
        try {
            myProjectList(ad);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileAddress =  Address + "/" + userName + "/email.txt";
        try {
            FileReader fileReader = new FileReader(fileAddress);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s;
            myEmailDesk.setText("");
            while ((s = bufferedReader.readLine()) != null) {
                myEmailDesk.appendText(s + "\n");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void SendEmail(MouseEvent mouseEvent) throws IOException {
        to = "Main";
        from = userName;
        address = Address+"/";
        AnchorPane secondaryLayout = FXMLLoader.load(getClass().getResource("email.fxml"));


        Scene secondScene = new Scene(secondaryLayout);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Email");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.


        newWindow.show();
    }


    //save with version
    public void saveFunction(MouseEvent mouseEvent) throws IOException {
        if(myTaskDesk.getText().isEmpty()){

        }
        else {
            Date date = new Date();
            String ad;
            ad = Address + "/" + userName + "/"+date+".txt";
            File file = new File(ad);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(myTaskDesk.getText());
            bufferedWriter.close();
            fileWriter.close();


            ad =Address + "/" + userName + "/"+"versionList.txt";
            File file2 = new File(ad);
            FileWriter fileWriter2 = new FileWriter(file2,true);
            BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            bufferedWriter2.write(date+".txt\n");
            bufferedWriter2.close();
            fileWriter2.close();

        }
        String ad =Address + "/" + userName + "/"+"versionList.txt";
        try {
            myProjectList(ad);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendFunction(MouseEvent mouseEvent) throws IOException {
       if(myTaskDesk.getText().isEmpty()){

       }
       else{
           File file = new File(Address+ "/"+"Main/"+userName+".txt");
           FileWriter fileWriter = new FileWriter(file);
           BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           bufferedWriter.write(myTaskDesk.getText());
           bufferedWriter.close();
           fileWriter.close();
       }

    }


    public void openButton(MouseEvent mouseEvent) throws IOException {
        myTaskDesk.setText("");
        String selectedItem = (String) listView.getSelectionModel().getSelectedItem();
        String fileAddress =  Address + "/" + userName + "/"+selectedItem;
        File file = new File(fileAddress);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        while ((s=bufferedReader.readLine())!=null){
            myTaskDesk.appendText(s+"\n");
        }
        bufferedReader.close();
        fileReader.close();


    }

    public void reloadFunction(MouseEvent mouseEvent) throws IOException {
        String fileAddress =  Address + "/" + userName + "/email.txt";
        FileReader fileReader = new FileReader(fileAddress);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s ;
        myEmailDesk.setText("");
        while ((s=bufferedReader.readLine())!=null){
            myEmailDesk.appendText(s+"\n");
        }
        bufferedReader.close();
        fileReader.close();
    }

    public void delteAllEmail(MouseEvent mouseEvent) throws IOException {
        String fileAddress =  Address + "/" + userName + "/email.txt";
        FileWriter fileWriter = new FileWriter(fileAddress);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("");
        bufferedWriter.close();
        fileWriter.close();
        myEmailDesk.setText("");
    }
}
