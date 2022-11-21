package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;

public class Create {

    public Button createButton;
    public Label goBeck;
    public TextField email;
    public  PasswordField password;
    public Label massages;
    public static String address;

    public static String getPassword ;
    public TextField userName;
    public PasswordField rePassword;
    public static String userNam;
    public static String userEmail;



    public  void infoSaving() throws IOException {
        File folder = new File("UserInformation/"+userName.getText());
        folder.mkdir();

        File file = new File("UserInformation/"+userName.getText()+"/"+"data.txt");

        address = "UserInformation/"+userName.getText()+"/";
        userNam = userName.getText();
        userEmail = email.getText();
        FileWriter fileWriter = new FileWriter(file);

        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write("User Name :"+userName.getText()+"\n");
        writer.write("Email      : "+email.getText()+"\n");
        writer.write("password   : "+password.getText()+"\n");
        writer.close();
        fileWriter.close();

        FileWriter fw1 = new FileWriter("UserInformation/checkEmail",true);
        fw1.write(email.getText()+"\n");
        fw1.close();
        FileWriter fw2 = new FileWriter("UserInformation/checkUserName",true);
        fw2.write(userName.getText()+"\n");
        fw2.close();




    }
    int chekPassword(){
        int len = password.getText().length();
        if(len<6){
          return 0;
        }
        else return 1;
    }
    void insetLoginInfo() throws IOException {
        File f= new File("UserInformation/loginInfo");
        FileWriter fileWriter1 = new FileWriter(f,true);
        BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
        bufferedWriter1.write(userName.getText()+" "+password.getText()+"\n");
        bufferedWriter1.close();
        fileWriter1.close();
    }

   int checkUserName() throws IOException {
       File file = new File("UserInformation/checkUserName");
       FileReader fileReader = new FileReader(file);
       BufferedReader bufferedReader = new BufferedReader(fileReader);
       String s;
       int chek = 1;


       while((s=bufferedReader.readLine())!=null){
           if(s.equals(userName.getText())) {
               System.out.println(s);
               chek = 0;
               break;
           }
       }
       bufferedReader.close();
       fileReader.close();
       return chek;
    }
    int  checkEmail() throws IOException {
        File file = new File("/home/sunny/IdeaProjects/InClassOne/UserInformation/checkEmail");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        int chek = 1;
        //  String [] data;

        while((s=bufferedReader.readLine())!=null){
            if(s.equals(email.getText())) {
                chek = 0;
                break;
            }
        }
        bufferedReader.close();
        fileReader.close();
        return chek;
    }

    public void goSttrings(MouseEvent mouseEvent) throws IOException {
           if (userName.getText().isEmpty()) {

               massages.setText("Provide User name");
           }
           else if (checkUserName()==0) {

              massages.setText("User name already have");
           }else if (email.getText().isEmpty()) {

               massages.setText("Provide email address");
           } else if (email.getText().indexOf('@') == -1) {
               massages.setText("Invalid Email!!!");
           }else if(checkEmail()==0){

               massages.setText("Email is already have");
           }else if (password.getText().isEmpty()) {
               massages.setText("Provide password");
           } else if(chekPassword()==0){

               massages.setText("Password is Too sort");
           }else{

               String pass = rePassword.getText();
               if(password.getText().equals(pass)){
                   infoSaving();
                   insetLoginInfo();
                   getPassword = pass;
                   AnchorPane pane = FXMLLoader.load(getClass().getResource("setting.fxml"));
                   Stage stage = (Stage) createButton.getScene().getWindow();
                   stage.setScene(new Scene(pane));
               }
               else{
                   massages.setText("Password not Match!");
               }
           }


    }

    public void goLoginPage(MouseEvent mouseEvent) throws IOException {
        GridPane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) goBeck.getScene().getWindow();
        stage.setScene(new Scene(pane));
    }
}
