package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

public class StartProject {
    public Button create;
    public TextArea aboutProject;
    public TextField employeeTwo;
    public TextField employeeOne;
    public TextField projectName;
    public Button goHome;
    public Label massage;
    public static String projectAddress;
    public static String nameOfProject;

    public int checkEmployee(String name) throws IOException {
        FileReader fr = new FileReader("UserInformation/checkUserName");
        BufferedReader br = new BufferedReader(fr);
        String s;
        int i = 0;
        while((s=br.readLine())!=null){
            if(s.equals(name)){
                i=1;
                break;
            }
        }

        br.close();
        fr.close();
        return  i;
    }

    void makeFile() throws IOException {
        File file = new File("Project/"+projectName.getText());
        file.mkdir();
        String filePath = "Project/"+projectName.getText()+"/";
        projectAddress = filePath;
        File main = new File(filePath+"Main");
        main.mkdir();

        FileWriter mainWriter = new FileWriter(filePath+"Main/"+"main.txt");
        mainWriter.write("");
        mainWriter.close();
        FileWriter emailWriter = new FileWriter(filePath+"Main/"+"email.txt");
        emailWriter.write("No Email Exit\n");
        emailWriter.close();
        FileWriter employee1Writer = new FileWriter(filePath+"Main/"+employeeOne.getText()+".txt");
        employee1Writer.write("");
        employee1Writer.close();
        FileWriter employee2Writer = new FileWriter(filePath+"Main/"+employeeTwo.getText()+".txt");
        employee2Writer.write("");
        employee2Writer.close();


        File em1 = new File(filePath+employeeOne.getText());
        em1.mkdir();
        File em2 = new File(filePath+employeeTwo.getText());
        em2.mkdir();
        FileWriter employee1Email = new FileWriter(filePath+employeeOne.getText()+"/email.txt");
        employee1Email.write("");
        employee1Email.close();
        FileWriter employee1List = new FileWriter(filePath+employeeOne.getText()+"/versionList.txt");
        employee1List.write("");
        employee1List.close();
        FileWriter employee2Email = new FileWriter(filePath+employeeTwo.getText()+"/email.txt");
        employee2Email.write("");
        employee2Email.close();
        FileWriter employee2List = new FileWriter(filePath+employeeTwo.getText()+"/versionList.txt");
        employee2List.write("");
        employee2List.close();

        FileWriter nameList = new FileWriter("Project/projectNameList",true);
        nameList.write(projectName.getText()+"\n");
        nameList.close();



       // FileWriter employList = new FileWriter(filePath+"employee");
        FileWriter about = new FileWriter(filePath+"projectInformation");
        about.write(MainPage.adminName+"\n");
        about.write(employeeOne.getText()+"\n");
        about.write(employeeTwo.getText()+"\n");
        about.write(aboutProject.getText()+"\n");
        about.close();

    }


    public void goProfile(MouseEvent mouseEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage) goHome.getScene().getWindow();
        stage.setFullScreen(true);
        stage.setScene(new Scene(pane));
    }


    public void createProject(MouseEvent mouseEvent) throws IOException {

        if(employeeOne.getText().isEmpty()){
            massage.setText("provide 1st employee name");
        }
        else if(checkEmployee(employeeOne.getText())==0){
            massage.setText("1st Employee Not find");
        }
        else if(employeeTwo.getText().isEmpty()){
            massage.setText("provide 2nd employee name");
        }
        else if(checkEmployee(employeeTwo.getText())==0){
            massage.setText("2nd Employee Not find");
        }
        else if(aboutProject.getText().isEmpty()){
            massage.setText("About field is empty");
        }
        else{
            makeFile();
            nameOfProject = projectName.getText();

            File file = new File("UserInformation/"+MainPage.USERNAME+"/myProject.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(nameOfProject+"\n");
            bufferedWriter.close();
            fileWriter.close();

            AnchorPane pane = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Stage stage = (Stage) create.getScene().getWindow();
            stage.setFullScreen(true);
            stage.setScene(new Scene(pane));
        }


    }
}
