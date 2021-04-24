package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoginInformationReportController {

    @FXML
    private Label successfulLoginsLabel;

    @FXML
    private Label failedLoginsLabel;

    @FXML
    public void initialize() throws IOException {
        String filename = "login_activity.txt";
        File fileToRead = new File(filename);
        if (fileToRead.exists()) {
            Scanner scannedFile = new Scanner(fileToRead);
            int successfulAttempts = 0;
            int failedAttempts = 0;

            while (scannedFile.hasNext()) {
                String item = scannedFile.nextLine();
                if (item.equals("111SUCCESS")) {
                    successfulAttempts++;
                }
                if (item.equals("999FAIL")) {
                    failedAttempts++;
                }
            }
            scannedFile.close();
//        System.out.println("total successful attempts: " + successfulAttempts);
//        System.out.println("total failed attempts: " + failedAttempts);
            successfulLoginsLabel.setText(String.valueOf(successfulAttempts));
            failedLoginsLabel.setText(String.valueOf(failedAttempts));
        }
        else {
            System.out.println("file does not exist, please quit");
        }
    }
}
