package com.example.projectcombinerv3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CombinerApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(CombinerApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 620, 240);
        //stage.setTitle("Project Combiner - version 3.0");
        //stage.setScene(scene);
        //stage.show();

        stage.setTitle("Project Combiner - version 3.0");
        stage.setMinWidth(480);
        stage.setMinHeight(200);

        //creating toolbar
        Button chooseBtn = new Button("Select Dir");
        Button pauseBtn = new Button("Pause Process");
        Button exitBtn = new Button("Exit App");
        ToolBar toolBar = new ToolBar(chooseBtn, pauseBtn, exitBtn);

        //setting progress bar
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(100.0);

        //setting up button event handlers
        chooseBtn.setOnAction(event -> selectDirectory(stage));
        exitBtn.setOnAction(event -> stopRunningApplication());

        //adding main ui parts to the window
        BorderPane mainWindow = new BorderPane();
        mainWindow.setTop(toolBar);
        mainWindow.setCenter(progressBar);

        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private void selectDirectory(Stage stage){
        DirectoryChooser dr = new DirectoryChooser();
        dr.setInitialDirectory(new File("."));
        dr.setTitle("Select file directory");
        File direc = dr.showDialog(stage);

        //passing directory path to TraverseFile class
        TraverseFiles tf = new TraverseFiles();
        tf.visitFile(direc);
        tf.writeToOneFile();
    }

    private void stopRunningApplication(){
        System.out.println("Kill process...");
        System.exit(0);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }




}