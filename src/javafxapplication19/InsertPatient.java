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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Oishi
 */
public class InsertPatient {

    static Stage primarystage = Main.primaryStage;

    public static void refins(Connection con, String s) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,1100,900);
        scene.getStylesheets().add(InsertPatient.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(InsertPatient.class.getResource("animated.css").toExternalForm());
        root.setId("panewood");
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));

        //Button btn = new Button("name");
        Text ref = new Text("*Refugee ID: ");
        Text diag = new Text("*Diagnosis: ");

        TextField refid = new TextField();
        TextField di = new TextField();
        Text greet = new Text("New Prescription");
        greet.setFont(Font.font("System", FontWeight.BOLD, 25));
        ref.setFont(Font.font("System", FontWeight.MEDIUM, 20));
        diag.setFont(Font.font("System", FontWeight.MEDIUM, 20));
        Text req = new Text("Symbol * indicates compulsory");
        req.setFont(Font.font("System", FontWeight.BOLD, 18));
        Button back = designs.button("Back");
        Button next = designs.button("Next");
        Label warn = new Label();
        Label mess = new Label("Must be less than 50 characters");
        mess.setFont(Font.font("System", FontWeight.MEDIUM, 16));
        warn.setFont(Font.font("System", FontWeight.MEDIUM, 16));
        gridpane.add(greet, 0, 0);
        gridpane.add(req, 0, 1);
        gridpane.add(ref, 0, 2);
        gridpane.add(refid, 1, 2);
        gridpane.add(diag, 0, 3);
        gridpane.add(di, 1, 3);
        gridpane.add(back, 0, 5);
        gridpane.add(next, 2, 5);
        gridpane.add(warn, 0, 4);
        gridpane.add(mess, 2, 3);
        back.setOnAction((ActionEvent event) -> {
            Docprofile.profile(con, s);
        });

        next.setOnAction((ActionEvent event) -> {
            warn.setText(null);
            if (refid.getText().length() <= 0) {
                warn.setText("Please enter refugee id");
            } else if (di.getText().length() <= 0 || di.getText().length() > 50) {
                warn.setText("Diagnosis must be 1 to 50 characters");
            } else {
                try {
                    String id = refid.getText();
                    int u = 0;
                    try {
                        Integer.parseInt(id);
                    } catch (NumberFormatException ex) {
                        u = 1;
                        warn.setText("Please enter valid id!");
                    }
                    if (u == 0) {
                        String sql = "SELECT REFUGEE_ID FROM REFUGEE WHERE REFUGEE_ID=" + id;
                        System.out.println(sql);
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        String chec = null;
                        while (rs.next()) {
                            chec = rs.getString("REFUGEE_ID");
                        }
                        if (chec == null) {
                            warn.setText("Refugee not present in record!");
                        } else {
                            if (di.getText().length() > 50 || di.getText().length() <= 0) {
                                warn.setText("Check Diagnosis field again!");
                            } else {
                                String jk = "insert into prescription values(prescription_seq.nextval,'" + refid.getText() + "','" + s + "')";
                                System.out.println(jk);
                                PreparedStatement sq = con.prepareStatement(jk);
                                sq.executeUpdate();
                                String str;
                                str = null;
                                //
                                String polo = "select PRESCRIPTION_SERIAL from PRESCRIPTION WHERE PRESCRIPTION_SERIAL = (SELECT MAX(PRESCRIPTION_SERIAL) FROM PRESCRIPTION)";
                                System.out.println(polo);
                                Statement stmt2 = con.createStatement();
                                ResultSet rst = stmt2.executeQuery(polo);
                                while (rst.next()) {
                                    str = rst.getString("PRESCRIPTION_SERIAL");
                                }
                                System.out.println(str);
                                Inspatient.refins2(con, s, str, di.getText());
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(InsertPatient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        root.setCenter(gridpane);
        primarystage.setScene(scene);
        primarystage.show();
    }
}
