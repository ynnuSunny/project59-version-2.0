package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    public Button goBack;
    public TextArea mainDesk;
    public TextArea employee1Desk;
    public TextArea employee2Desk;
    public Button employee1Save;
    public Button employee2Save;
    public Button mainSave;
    public Label projectName;
    public TextArea emailDesk;
    public Button employee1Check;
    public Button employee2Check;
    public Button emailReload;
    public static String adminName;
    public Label employee1Level;
    public Label employee2Level;
    public Button sendTaskEm1;
    public Button sendTaskEm2;
    public Button deleteAll;
    String mainAddress;
    //For Email Sending;
    public  static  String to;
    public static String from;
    public static String address;


    public void goBackFunction(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage) goBack.getScene().getWindow();
        stage.setScene(new Scene(pane));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if(StartProject.projectAddress!=null) {
            mainAddress = StartProject.projectAddress;
            projectName.setText(StartProject.nameOfProject);
        }
        else{
            mainAddress = MainPage.projectAddress;
            projectName.setText(MainPage.PROJECTNAME);
        }


        //Write in main Desk
        try {
            FileReader mainFile = new FileReader(mainAddress+"Main/"+"main.txt");
            BufferedReader mainDeskReader = new BufferedReader(mainFile);
            String s;
            while((s=mainDeskReader.readLine())!=null){
                mainDesk.appendText(s+"\n");
            }
            mainDeskReader.close();
            mainFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Write in email Desk
        try {
            FileReader mainFile = new FileReader(mainAddress+"Main/"+"email.txt");
            BufferedReader mainDeskReader = new BufferedReader(mainFile);
            String s;
            while((s=mainDeskReader.readLine())!=null){
                emailDesk.appendText(s+"\n");
            }
            mainDeskReader.close();
            mainFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Full Page information
        try {
            FileReader mainFile = new FileReader(mainAddress+"projectInformation");
            BufferedReader mainDeskReader = new BufferedReader(mainFile);
            String s;
            int i=0;
            while((s=mainDeskReader.readLine())!=null){
               if(i==0){
                   adminName = s;
               }
               else if(i==1){
                   employee1Level.setText(s);

               }
               else if(i==2){
                   employee2Level.setText(s);
               }
               i++;
            }
            mainDeskReader.close();
            mainFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void reloadEmail(MouseEvent mouseEvent) {
        try {
            FileReader mainFile = new FileReader(mainAddress+"Main/"+"email.txt");
            BufferedReader mainDeskReader = new BufferedReader(mainFile);
            String s;
            emailDesk.setText("");
            while((s=mainDeskReader.readLine())!=null){
                emailDesk.appendText(s+"\n");
            }
            mainDeskReader.close();
            mainFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInMain(MouseEvent mouseEvent) throws IOException {
        FileWriter fileWriter = new FileWriter(mainAddress+"Main/"+"main.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(mainDesk.getText());
        bufferedWriter.close();
        fileWriter.close();
    }

    public void saveEm1TaskToMain(MouseEvent mouseEvent) throws IOException {
//        FileReader fileReader = new FileReader(mainAddress+"Main/"+employee1Level.getText()+".txt");
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        String s;
//        while((s=bufferedReader.readLine())!=null){
//            mainDesk.appendText(s+"\n");
//        }
//        bufferedReader.close();
//        fileReader.close();
         mainDesk.appendText(employee1Desk.getText());


    }

    public void saveEm2TaskToMain(MouseEvent mouseEvent) throws IOException {
//        FileReader fileReader = new FileReader(mainAddress+"Main/"+employee2Level.getText()+".txt");
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        String s;
//        while((s=bufferedReader.readLine())!=null){
//            mainDesk.appendText(s+"\n");
//        }
//        bufferedReader.close();
//        fileReader.close();
        mainDesk.appendText(employee2Desk.getText());

    }

    public void checkEm1Desk(MouseEvent mouseEvent) {
        try {
            FileReader mainFile = new FileReader(mainAddress+"Main/"+employee1Level.getText()+".txt");
            BufferedReader mainDeskReader = new BufferedReader(mainFile);
            String s;
            while((s=mainDeskReader.readLine())!=null){
                employee1Desk.appendText(s+"\n");
            }
            mainDeskReader.close();
            mainFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkEm2Task(MouseEvent mouseEvent) {
        try {
            FileReader mainFile = new FileReader(mainAddress+"Main/"+employee2Level.getText()+".txt");
            BufferedReader mainDeskReader = new BufferedReader(mainFile);
            String s;
            while((s=mainDeskReader.readLine())!=null){
                employee2Desk.appendText(s+"\n");
            }
            mainDeskReader.close();
            mainFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendTask1(MouseEvent mouseEvent) throws IOException {
        to = employee1Level.getText();
        from = "Main";
        address = mainAddress;


        AnchorPane secondaryLayout = FXMLLoader.load(getClass().getResource("email.fxml"));


        Scene secondScene = new Scene(secondaryLayout);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Email");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.


        newWindow.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("email.fxml"));
//        Stage stage = (Stage) sendTaskEm1.getScene().getWindow();
//        stage.setFullScreen(true);
//        stage.setScene(new Scene(pane));
    }

    public void sendTask2(MouseEvent mouseEvent) throws IOException {
        to = employee2Level.getText();
        from = adminName;
        address = mainAddress;
        AnchorPane secondaryLayout = FXMLLoader.load(getClass().getResource("email.fxml"));


        Scene secondScene = new Scene(secondaryLayout);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Email");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.


        newWindow.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("email.fxml"));
//        Stage stage = (Stage) sendTaskEm2.getScene().getWindow();
//        stage.setFullScreen(true);
//        stage.setScene(new Scene(pane));

    }

    public void deleteAllemail(MouseEvent mouseEvent) throws IOException {
        FileWriter fileWriter = new FileWriter(mainAddress+"Main/"+"email.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("");
        bufferedWriter.close();
        fileWriter.close();
        emailDesk.setText("");

    }
}
