package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Controller class that handles login_information_report_screen.fxml.
 */
public class LoginInformationReportController {

    @FXML
    private Label successfulLoginsLabel;

    @FXML
    private Label failedLoginsLabel;

    /**
     * Initializes the Login Information Report screen by reading the login_activity.txt, counts the successful or failed
     * login attempts and displays it.
     */
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
        } else {
            System.out.println("file does not exist, please quit");
        }
    }
}
