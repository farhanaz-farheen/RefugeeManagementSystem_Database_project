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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;

/**
 *
 * @author Oishi
 */
public class ChangeSpec {

    static Stage primarystage = Main.primaryStage;

    public static void editspe(Connection con, String s) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1100,900);
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
scene.getStylesheets().add(ChangeSpec.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(ChangeSpec.class.getResource("animated.css").toExternalForm());
        //scene.getStylesheets().add(ChangeSpec.class.getResource("combo.css").toExternalForm());
        root.setId("panewood");
        //Button btn = new Button("name");
        Text greet = new Text("Choose New Field:");
        ComboBox<String> sp = new ComboBox<>(FXCollections.observableArrayList("Allergist", "Anesthesiologist", "Cardiologist", "Dentist", "Dermatologist", "Endocrinologist", "Epidemiologist", "Gynecologist", "Immunologist", "Infectious Disease Specialist", "Internal Medicine Specialist", "Medical Geneticist", "Medical Officer", "Neonatologist", "Neurologist", "Nephrologist", "Neurosurgeon", "Nurse", "Obstetrician", "Oncologist", "Orthopedic Surgeon", "ENT Specialist", "Pediatrician", "Physiologist", "Plastic Surgeon", "Podiatrist", "Psychiatrist", "Radiologist", "Rheumatologist", "Surgeon", "Urologist"));
        Label mess = new Label();
        
        greet.setFont(Font.font("System", FontWeight.MEDIUM, 30));
        mess.setFont(Font.font("System", FontWeight.MEDIUM, 18));
        Button b2 = designs.button("Back");
        Button b3 = designs.button("Change");

        gridpane.add(greet, 0, 0);
        gridpane.add(sp, 0, 2);
        gridpane.add(b2, 0, 5);
        gridpane.add(b3, 2, 5);
        gridpane.add(mess, 0, 4);

        b2.setOnAction((ActionEvent event) -> {
            EditDoc.editstuff(con, s);
//Docprofile.profile(con, s);
        });
        b3.setOnAction((ActionEvent event) -> {
            if (sp.getValue() == null || sp.getValue().length() <= 0) {
                mess.setText("You must choose a new field!");
            } else {
                try {
                    String sql = "UPDATE DOCTOR_NURSE_OFFICER SET SPECIALIZATION ='" + sp.getValue() + "' WHERE DOCTOR_NURSE_OFFICER_ID=" + s;
                    sql=sql.toUpperCase();
                    System.out.println(sql);
                    PreparedStatement se = con.prepareStatement(sql);
                    se.executeUpdate();
                    UpdatedINFO.message(con, s);
                } catch (SQLException ex) {
                    Logger.getLogger(ChangeSpec.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        root.setCenter(gridpane);
        primarystage.setScene(scene);
        primarystage.show();
    }
}
