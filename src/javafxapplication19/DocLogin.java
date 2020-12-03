/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;

/**
 *
 * @author Oishi
 */
public class DocLogin {

    static Stage primarystage = Main.primaryStage;

    public static void doclog(Connection con) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1100,900);
        scene.getStylesheets().add(DocLogin.class.getResource("style.css").toExternalForm());
        //root.setId("pane");
        scene.getStylesheets().add(DocLogin.class.getResource("animated.css").toExternalForm());
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
        root.setId("panedoc");
        //Button btn = new Button("name");
        Text lab1 = new Text("User ID: ");
        Text lab = new Text("Password:");

        lab1.setFont(Font.font("System", FontWeight.BOLD, 30));
        lab.setFont(Font.font("System", FontWeight.BOLD, 30));

        Label message = new Label();
        message.setTextFill(Color.RED);
        TextField acc = new TextField();
        PasswordField pass = new PasswordField();
        Button btn2 = designs.button("Login");
        Button b2 = designs.button("Back");
        message.setFont(Font.font("System", FontWeight.BOLD, 25));
        btn2.setMaxWidth(70);
        b2.setMaxWidth(100);
        gridpane.add(lab1, 0, 0);
        gridpane.add(lab, 0, 1);
        gridpane.add(acc, 1, 0);
        gridpane.add(pass, 1, 1);
        gridpane.add(btn2, 0, 4);
        gridpane.add(message, 0, 3);
        gridpane.add(b2, 2, 4);
        b2.setOnAction((ActionEvent event) -> {
            Main h = new Main();
            h.homePage();
        });
        btn2.setOnAction((ActionEvent event) -> {
            try {
                if (acc.getText().length() <= 0) {
                    message.setText("Please enter a valid ID");
                } else {
                    int u;
                    u = 0;
                    try {
                        Integer.parseInt(acc.getText());
                    } catch (NumberFormatException ex) {
                        u = 1;
                        message.setText("Please enter a valid ID");
                    }
                    if (u == 0) {
                        String pas = pass.getText();
                        if (pass != null) {
                            pas = pas.trim();
                        }
                        String sql = "select PASSWORD from PASS where DOCTOR_NURSE_OFFICER_ID=" + acc.getText();
                        //PreparedStatement st = con.prepareStatement(sql);
                        //String str = "abcd";
                        System.out.println(sql);
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        //ResultSet r = st.executeQuery();
                        String chec = null;
                        while (rs.next()) {
                            chec = rs.getString("PASSWORD");
                        }

                        if (chec == null) {
                            message.setText("Please enter a valid ID");
                        } else {
                            if (pas.equals(chec.trim())) {
                                //doctest.AuthorityHomePage(con, acc.getText());
                                Docprofile.profile(con, acc.getText());
                            } else {
                                message.setText("Incorrect password");
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                message.setText("Please enter a valid ID!");
                Logger.getLogger(DocLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        root.setCenter(gridpane);
        primarystage.setScene(scene);
        primarystage.show();
    }
}
