package com.example.systemcruddesign;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button goToRegistrationPageButton;
    @FXML
    private Button loginButton;
    @FXML
    private TextField userId;
    @FXML
    private TextField userPassword;
    @FXML
    private Label loginPageLabel;
    @FXML
    private Button backloginPageButton;
    @FXML
    private TextField semail;
    @FXML
    private TextField sloginid;
    @FXML
    private TextField smobile;
    @FXML
    private TextField spassword;
    @FXML
    private TextField susername;
    @FXML
    private Label registrationPageLabel;
    @FXML
    private Button deletePageButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button updatePageButton;
    @FXML
    private Button goBackToAdminPageButton;
    @FXML
    private TextField deletetheuser;
    @FXML
    private TextField updateemail;
    @FXML
    private TextField updateloginid;
    @FXML
    private TextField updatename;
    @FXML
    private Button updateUserInfoButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Button showuserInfos;

    @FXML
    TableView<Users> usertable = new TableView<>();
    @FXML
    TableColumn<Users, String> loginidCol = new TableColumn<>("loginid");
    @FXML
    TableColumn<Users, String> usernameCol = new TableColumn<>("username");
    @FXML
    TableColumn<Users, String> emailCol = new TableColumn<>("email");
    @FXML
    TableColumn<Users, String> mobileCol = new TableColumn<>("mobile");
    @FXML
    TableColumn<Users, String> passwordCol = new TableColumn<>("password");


    @FXML
    void setDeleteUserAction() throws Exception {
        System.out.println("Inside ");
        String userdelete = deletetheuser.getText();
        DatabaseOperation.deleteUserInfo(userdelete);
    }

    @FXML
    void setGoBackToAdminHomePageAction(ActionEvent event) throws Exception {
        System.out.println("Inside setGoBackToAdminHomePageAction function!");
        Parent root = FXMLLoader.load(getClass().getResource("admin-home-page.fxml"));
        Stage window = (Stage) goBackToAdminPageButton.getScene().getWindow();
        window.setTitle("Admin Home Page");
        window.setScene(new Scene(root));

    }


    @FXML
    void setUpdateUserInfoAction(ActionEvent event) throws Exception {
        System.out.println("Inside setUpdateUserInfoAction function!");
        DatabaseOperation.updateuserInfo(updateloginid.getText(), updatename.getText(), updateemail.getText());

    }

    @FXML
    void setGoToDeletePageAction(ActionEvent event) throws Exception {

        System.out.println("Inside setGoToDeletePageAction function!");
        Parent root = FXMLLoader.load(getClass().getResource("delete-user.fxml"));
        Stage window = (Stage) deletePageButton.getScene().getWindow();
        window.setTitle("Delete User Page");
        window.setScene(new Scene(root));

    }

    @FXML
    void setLogoutAction(ActionEvent event) throws Exception {
        System.out.println("Inside setLogoutAction function!");
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setTitle("Login Page");
        window.setScene(new Scene(root));

    }

    @FXML
    void setUpdatePageAction(ActionEvent event) throws Exception {
        System.out.println("Inside setUpdatePageAction function!");
        Parent root = FXMLLoader.load(getClass().getResource("update-from-admin.fxml"));
        Stage window = (Stage) updatePageButton.getScene().getWindow();
        window.setTitle("Update User Page");
        window.setScene(new Scene(root));
    }

    public void setLoginButton(ActionEvent event) throws Exception {
        boolean returnValue = false;
        System.out.println("Inside setLoginButton function!");
        if (!userId.getText().isBlank() && !userPassword.getText().isBlank() && (returnValue = DatabaseOperation.logInCheck(userId.getText(), userPassword.getText()))) {
            System.out.println("Successful Login!");
            System.out.println(returnValue);

            System.out.println("Back to Login Button is working!");
            UserInformations();
            Parent root = FXMLLoader.load(getClass().getResource("admin-home-page.fxml"));
            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setTitle("Admin Home Page");
            window.setScene(new Scene(root));
        } else {

            System.out.println("Enter UserID and UserPassword!");
            loginPageLabel.setText("Enter UserID and UserPassword!");
        }

    }

    public void setGoToRegistrationPageButton() throws Exception {
        System.out.println("Inside setGoToRegistrationPageButton");
        Parent root = FXMLLoader.load(getClass().getResource("registration-page.fxml"));
        Stage window = (Stage) goToRegistrationPageButton.getScene().getWindow();
        window.setTitle("Registration Page");
        window.setScene(new Scene(root));
    }

    public void setCancelButton(ActionEvent actionEvent) {
        System.out.println("Inside setCancelButton");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setGoBackLoginButton() throws Exception {
        System.out.println("Inside setGoBackLoginButton function!");
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) backloginPageButton.getScene().getWindow();
        window.setTitle("Login Page");
        window.setScene(new Scene(root));
    }

    public void setRegistration() throws Exception {
        System.out.println("Inside setRegistration function!");
        if (!sloginid.getText().isBlank() && !susername.getText().isBlank() && !semail.getText().isBlank() && !smobile.getText().isBlank() && !spassword.getText().isBlank()) {
            System.out.println("Registration Successful!");
            System.out.println(sloginid.getText());
            System.out.println(susername.getText());
            System.out.println(semail.getText());
            System.out.println(smobile.getText());
            System.out.println(spassword.getText());
            System.out.println("Sending data into database!");
            DatabaseOperation.writeToDatabase(sloginid.getText(), susername.getText(), semail.getText(), smobile.getText(), spassword.getText());

        } else {

            System.out.println("Enter all required information to register!");
            registrationPageLabel.setText("Enter all required information to register!");
        }
    }

    public void UserInformations() throws Exception {
        System.out.println("Inside UserInformations function!");
        ObservableList<Users> list = DatabaseOperation.getUsersList();
        System.out.println(list.get(0).getLoginid());
        loginidCol.setCellValueFactory(new PropertyValueFactory<>("loginid"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        usertable.setItems(list);
    }

}