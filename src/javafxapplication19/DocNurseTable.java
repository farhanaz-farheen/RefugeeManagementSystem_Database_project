package javafxapplication19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DocNurseTable {

    static Stage primaryStage;

    public static void docnurse(Connection con) {
        primaryStage = Main.primaryStage;
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1100,900);
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
        Text[] star = new Text[3];
        for (int i = 0; i < 3; i++) {
            star[i] = new Text("*");
            star[i].setFill(Color.RED);
        }
        Label title = new Label("Insert into Doctor/Nurse/Officer");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 35));
        scene.getStylesheets().add(DocNurseTable.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(DocNurseTable.class.getResource("animated.css").toExternalForm());
        scene.getStylesheets().add(DocNurseTable.class.getResource("css1.css").toExternalForm());
        root.setId("background-three");

        gridpane.add(title, 0, 0, 4, 1);
        Label fn = new Label("First name:");
        gridpane.add(star[0], 0, 1);
        gridpane.add(star[1], 0, 3);
        Label ln = new Label("Last name: ");
        Label spe = new Label("Specialization:");
        Label warn = new Label();
        warn.setTextFill(Color.RED);
        Button btn = new Button("Back");
        Button btn2 = new Button("Next");
        TextField fi = new TextField();
        TextField la = new TextField();
        ComboBox<String> sp = new ComboBox<>(FXCollections.observableArrayList("Allergist", "Anesthesiologist", "Cardiologist", "Dentist", "Dermatologist", "Endocrinologist", "Epidemiologist", "Gynecologist", "Immunologist", "Infectious Disease Specialist", "Internal Medicine Specialist", "Medical Geneticist", "Medical Officer", "Neonatologist", "Neurologist", "Nephrologist", "Neurosurgeon", "Nurse", "Obstetrician", "Oncologist", "Orthopedic Surgeon", "ENT Specialist", "Pediatrician", "Physiologist", "Plastic Surgeon", "Podiatrist", "Psychiatrist", "Radiologist", "Rheumatologist", "Surgeon", "Urologist"));
        Label mess = new Label("Must be less than 50 characters");
        Label mess2 = new Label("Must be less than 50 characters");
        fn.setFont(Font.font("System", FontWeight.BOLD, 25));
        ln.setFont(Font.font("System", FontWeight.BOLD, 25));
        spe.setFont(Font.font("System", FontWeight.BOLD, 25));
        mess.setFont(Font.font("System", FontWeight.MEDIUM, 23));
        mess2.setFont(Font.font("System", FontWeight.MEDIUM, 23));
        gridpane.add(fn, 1, 1);
        gridpane.add(ln, 1, 2);
        gridpane.add(spe, 1, 3);
        gridpane.add(fi, 2, 1);
        gridpane.add(la, 2, 2);
        gridpane.add(sp, 2, 3);
        gridpane.add(mess, 3, 1);
        gridpane.add(mess2, 3, 2);
        Label msg = new Label("indicates compulsory.");
        msg.setFont(Font.font("System", FontWeight.BOLD, 20));
        gridpane.add(star[2], 1, 4);
        gridpane.setHalignment(star[2], HPos.RIGHT);
        gridpane.add(msg, 2, 4);

        gridpane.add(btn, 1, 6);
        gridpane.add(btn2, 2, 6);
        gridpane.add(warn, 0, 5, 4, 1);
        warn.setFont(Font.font("System", FontWeight.BOLD, 20));
        btn2.setOnAction((ActionEvent event) -> {
            String fnm = fi.getText();
            String lnm = la.getText();
            if (fnm == null || fnm.length() == 0 || fnm.length() > 50 || (lnm != null && lnm.length() > 50) || sp.getValue() == null) {
                warn.setText("Criteria was not fulfilled");
            } else {
                //try {
                lnm = lnm == null || lnm.length() == 0 ? null : "'" + lnm + "'";
                String sql = "insert into DOCTOR_NURSE_OFFICER values(DOCTOR_NURSE_OFFICER_SEQ.nextval,'" + fi.getText() + "'," + lnm + ",'" + sp.getValue() + "',";
                sql = sql.toUpperCase();
                /*System.out.println(sql);
                    PreparedStatement s = con.prepareStatement(sql);
                    s.executeUpdate();
                    s.close();*/
                String tem;
                tem = null;
                imagedrag.imageInsert2(sql, "docins", tem);
                //insert2(con);
                // } catch (SQLException ex) {
                //   warn.setText(ex.getMessage());
                // }
            }
        });
        btn.setOnAction((ActionEvent event) -> {
            InsertFunctions.selectTable();
        });
        root.setCenter(gridpane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void insertmiddle(String sql,String img) {
        try {
            Connection con = Main.conn.con;
            //sql = sql.toUpperCase();
            System.out.println(sql);
            PreparedStatement s = con.prepareStatement(sql);
            s.executeUpdate();
            s.close();
            insert2(con,img);
        } catch (SQLException ex) {
            Logger.getLogger(DocNurseTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void insert2(Connection con,String imgUrl) {
        try {
            HBox hb = new HBox();
            hb.setSpacing(10);
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 1100,900);
            scene.getStylesheets().add(DocNurseTable.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(DocNurseTable.class.getResource("animated.css").toExternalForm());
            scene.getStylesheets().add(DocNurseTable.class.getResource("css1.css").toExternalForm());
            root.setId("background-three");
            
            Image image;
            if(imgUrl==null){
                image= new Image("/images/guest-user.jpg");
            }
            else{
                image= new Image("File:"+imgUrl);
            }
            
            //Image image = new Image("File:"+imgUrl);
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            iv1.setFitHeight(150);
            iv1.setFitWidth(200);
            iv1.setPreserveRatio(true);
            
            
            
            GridPane gridpane = new GridPane();
            gridpane.setAlignment(Pos.CENTER);
            gridpane.setPadding(new Insets(25, 25, 25, 25));
            gridpane.setVgap(20);
            gridpane.setHgap(30);
            Label title = new Label("Inserted:");
            title.setUnderline(true);
            title.setFont(Font.font("System", FontWeight.BOLD, 35));
            gridpane.add(title, 0, 0);
            gridpane.add(hb,2,1);
            
            root.setCenter(gridpane);
            GridPane grid = new GridPane();
            hb.getChildren().addAll(grid,iv1);
            ObservableList<String> nm = FXCollections.observableArrayList("ID", "First Name", "Last Name", "Specialization");
            String sql = "select doctor_nurse_officer_id,doctor_nurse_officer_firstname,doctor_nurse_officer_lastname,specialization from doctor_nurse_officer where doctor_nurse_officer_id=(select max(doctor_nurse_officer_id) from doctor_nurse_officer)";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            rs.next();
            ObservableList<String> vals = FXCollections.observableArrayList();
            for (int i = 1; i < 5; i++) {
                //nm.get(i-1) +":"+
                if (rs.getObject(i) == null) {
                    vals.add(i - 1, "");
                } else {
                    vals.add(i - 1, rs.getObject(i).toString());
                }

            }
            Label[] col = new Label[4];
            Label[] val = new Label[4];
            
            for (int i = 0; i < 4; i++) {
                grid.getRowConstraints().add(new RowConstraints(30));
                col[i] = new Label(" " + nm.get(i) + " ");
                //col[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(col[i], 0, i);
                grid.setHalignment(col[i], HPos.RIGHT);
                grid.setValignment(col[i], VPos.CENTER);
                val[i] = new Label(" " + vals.get(i) + " ");
                //val[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(val[i], 1, i);
                grid.setValignment(val[i], VPos.CENTER);
                col[i].setId("textone");
                val[i].setId("textone");
            }
            gridpane.add(grid, 0, 1);
            //grid.setVgap(50);
            Button next = designs.button("Next");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            gridpane.add(next, 0, 2);
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(DocNurseTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
