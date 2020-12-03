package javafxapplication19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class volunteerUpdate {
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static Label warn;
    static String firstnm = null;
    static String lastnm = null;
    static String volid= null;
    static Connection con;
    static boolean fn = false;
    static boolean ln = false;
    public static void update() {
        con= Main.conn.con;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        root.setId("background-two");
        scene.getStylesheets().add(volunteerUpdate.class.getResource("css1.css").toExternalForm());
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Update Volunteer Info");
        title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
        title.setTextFill(Color.WHITE);
        grid.add(title, 0, 0, 4, 1);
        Label ridl = new Label("Volunteer ID:");
        ridl.setId("textone");
        ridl.setTextFill(Color.WHITESMOKE);
        TextField ridtx = new TextField();
        ridtx.setText(volid);
        Label ridwarn = new Label();
        ridwarn.setTextFill(Color.RED);
        ridwarn.setId("textone");
        grid.add(ridl, 0, 1);
        grid.add(ridtx, 1, 1);
        grid.add(ridwarn, 2, 1);
        
        Button back1 = new Button("Back");
        back1.setOnAction((ActionEvent event) -> {
            updateFunctions.updatePage();
        });
        grid.add(back1, 0, 2);
        Button next = new Button("Next");
        next.setOnAction((ActionEvent event) -> {
            ridwarn.setText("");
            volid = ridtx.getText();
            if (volid == null || volid.length() < 7) {
                ridwarn.setText("Please insert valid ID");
            } else {
                try {
                    int refid = Integer.parseInt(volid);
                    PreparedStatement st = con.prepareStatement("select volunteer_id from volunteer where volunteer_id=" + volid);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        update1();
                    } else {
                        ridwarn.setText("ID not found");
                    }
                } catch (NumberFormatException ex) {
                    ridwarn.setText("Enter number");
                } catch (SQLException ex) {
                    ridwarn.setText(ex.getMessage());
                }
            }
        });
        grid.add(next, 2, 2);
        back1.setId("com-button");
        next.setId("com-button");
        primaryStage = Main.primaryStage;
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }

    public static void update1() {
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        root.setId("background-two");
        scene.getStylesheets().add(volunteerUpdate.class.getResource("css1.css").toExternalForm());
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Update Volunteer Info");
        title.setTextFill(Color.WHITESMOKE);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 3, 1);
        Label fnl = new Label("First Name :");
        fnl.setId("textone");
        fnl.setTextFill(Color.WHITESMOKE);
        TextField fnt = new TextField();
        fnt.setText(firstnm);
        Label fnn = new Label("At most 50 characters");
        fnn.setId("textone");
        fnn.setTextFill(Color.WHITESMOKE);
        grid.add(fnl, 0, 1);
        grid.add(fnt, 1, 1);
        grid.add(fnn, 2, 1);

        //2nd Row
        CheckBox lnsn = new CheckBox("Set Null");
        lnsn.setId("textone");
        lnsn.setTextFill(Color.WHITESMOKE);
        Label lnl = new Label("Last Name :");
        lnl.setId("textone");
        lnl.setTextFill(Color.WHITESMOKE);
        TextField lnt = new TextField();
        lnt.setText(lastnm);
        Label lnn = new Label("At most 50 characters");
        lnn.setId("textone");
        lnn.setTextFill(Color.WHITESMOKE);
        grid.add(lnsn, 3, 2);
        grid.add(lnl, 0, 2);
        grid.add(lnt, 1, 2);
        grid.add(lnn, 2, 2);
        
        warn= new Label();
        warn.setId("textone");
        warn.setTextFill(Color.RED);
        grid.add(warn, 0, 3,4,1);
        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            update();
        });
        grid.add(back, 1, 4);
        Button next = new Button("Next");
        next.setOnAction((ActionEvent event) -> {
            warn.setText(null);
            if (fnt.getText() != null && fnt.getText().length() != 0) {
                if (fnt.getText().length() > 50) {
                    warn.setText("Consider Max Length");
                } else {
                    fn = true;
                    firstnm = fnt.getText();
                }
            } else {
                fn = false;
            }
            if (lnt.getText() != null && lnt.getText().length() != 0 && lnsn.isSelected()) {
                warn.setText("Enter Valid Inputs");
            } else if (lnsn.isSelected()) {
                ln = true;
                lastnm = null;

            } else if (lnt.getText() != null && lnt.getText().length() != 0) {
                if (lnt.getText().length() > 50) {
                    warn.setText("Consider Max Length");
                } else {
                    ln = true;
                    lastnm = lnt.getText();

                }
            } else {
                ln = false;
            }

            if (warn.getText() == null) {
                try {
                    update2();
                } catch (SQLException ex) {
                    warn.setText("Update Failed.");
                    Logger.getLogger(volunteerUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(next, 2, 4);
        grid.setHalignment(next, HPos.RIGHT);
        back.setId("com-button");
        next.setId("com-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void update2() throws SQLException {
        
        warn.setText(null);
        String sql= "update volunteer set ";
        if(fn)  sql = sql+ "volunteer_firstname='"+firstnm+"',";
        if(ln){
            if(lastnm==null)    sql= sql+"volunteer_lastname=null,";
            else    sql = sql+ "volunteer_lastname='"+lastnm+"',";
        }
        if(sql.equals("update volunteer set ")){
            warn.setText("No changes made");
        }
        else{
            sql = sql.substring(0, sql.length()-1) + " where volunteer_id="+volid;
            sql= sql.toUpperCase();
            PreparedStatement p= con.prepareStatement(sql);
            p.executeUpdate();
            updateShow();
        }
    }
    public static void updateShow() {
        try {
            grid = new GridPane();
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            root.setId("background-two");
            scene.getStylesheets().add(volunteerUpdate.class.getResource("css1.css").toExternalForm());
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            Label title = new Label("Updated:");
            title.setTextFill(Color.WHITESMOKE);
            title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
            GridPane g = new GridPane();
            Label []l = new Label[6];
            Connection con = Main.conn.con;
            PreparedStatement st = con.prepareStatement("Select * from volunteer where volunteer_id="+volid);
            ResultSet rs = st.executeQuery();
            rs.next();
            l[0]= new Label(" Volunteer ID : ");
            g.add(l[0], 0, 0);
            g.setHalignment(l[0], HPos.RIGHT);
            l[1]= new Label(" First Name : ");
            g.add(l[1], 0, 1);
            g.setHalignment(l[1], HPos.RIGHT);
            l[2]= new Label(" Last Name : ");
            g.add(l[2],0,2);
            g.setHalignment(l[2], HPos.RIGHT);
            l[3] = new Label(" "+rs.getObject(1).toString()+" ");
            g.add(l[3], 1, 0);
            l[4] = new Label(" "+rs.getString(2)+" ");
            g.add(l[4], 1, 1);
            l[5]= new Label(rs.getObject(3)==null?"":" "+rs.getString(3)+" ");
            g.add(l[5], 1, 2);
            g.getRowConstraints().add(new RowConstraints(30));
            g.getRowConstraints().add(new RowConstraints(30));
            g.getRowConstraints().add(new RowConstraints(30));
            for(int i=0;i<6;i++){
                g.setValignment(l[i], VPos.CENTER);
                l[i].setTextFill(Color.WHITESMOKE);
                l[i].setFont(Font.font("Calibri", FontWeight.SEMI_BOLD, 24));
            }
            Button next = new Button("Next");
            next.setId("com-button");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            grid.setVgap(20);
            grid.setHalignment(next, HPos.RIGHT);
            grid.add(title, 0, 0);
            grid.add(g, 0, 1);
            grid.add(next, 0, 2);
            root.setCenter(grid);
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(volunteerUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
