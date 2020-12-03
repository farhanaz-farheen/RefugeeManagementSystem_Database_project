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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class complaint {
    static String by = null;
    static String combyref = null;
    static String combyvol = null;
    static String comag = null;
    static String misdeed = null;
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static Label warn;
    static Connection con;
    public static void insert1() {
        con= Main.conn.con;
        primaryStage = Main.primaryStage;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(complaint.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(complaint.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(complaint.class.getResource("fonts.css").toExternalForm());
        root.setId("background-five");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Insert into Complaint\n Page(1/3)");
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0);
        Button rb= new Button("Refugee");
        rb.setId("com-button");
        Button vb = new Button("Volunteer");
        vb.setId("com-button");
        Label coml = new Label("The Complainer is:");
        coml.setFont(Font.font("System", FontWeight.BOLD, 32));
        grid.add(coml, 0, 1);
        
        Button back = new Button("<< Back");
        back.setId("bwbutton");
        back.setOnAction((ActionEvent event) -> {
            InsertFunctions.selectTable();
        });
        
        rb.setOnAction((ActionEvent event) -> {
            by = "Refugee";
            insert2();
        });
        vb.setOnAction((ActionEvent event) -> {
            by = "Volunteer";
            insert2();
        });
        grid.add(back, 0, 4);
        grid.add(rb, 0, 2);
        grid.add(vb, 0, 3);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void insert2() {
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(complaint.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(complaint.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(complaint.class.getResource("fonts.css").toExternalForm());
        root.setId("background-five");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title1 = new Label("Insert into Complaint\n Page(2/3)");
        title1.setFont(Font.font("System", FontWeight.BOLD, 40));
        title1.setTextFill(Color.WHITESMOKE);
        grid.add(title1, 0, 0);
        Label title = new Label("ID of complainer "+by+":");
        title.setFont(Font.font("System", FontWeight.BOLD, 32));
        title.setTextFill(Color.WHITESMOKE);
        grid.add(title, 0, 1);
        TextField combyidtx = new TextField();
        if(by.equals("Refugee")){
            combyidtx.setText(combyref);
        }
        else    combyidtx.setText(combyvol);
        grid.add(combyidtx, 0, 2);
        
        warn = new Label();
        warn.setId("textfour");
        grid.add(warn, 0, 3);
        
        Button back = new Button("<< Back");
        back.setId("bwbutton");
        Button next = new Button("Next >>");
        next.setId("bwbutton");
        back.setOnAction((ActionEvent event) -> {
            insert1();
        });
        grid.add(back, 0, 4);
        
        next.setOnAction((ActionEvent event) -> {
            warn.setText("");
            if(by.equals("Refugee")){
                try{
                    combyref = combyidtx.getText();
                    combyvol = null;
                    if(combyref==null || combyref.length()<7){
                        warn.setText("Warning: Please enter a valid ID.");
                    }
                    Integer.parseInt(combyref);
                    PreparedStatement st = con.prepareStatement("select refugee_id from refugee where refugee_id="+combyref);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        insert3();
                    }
                    else{
                        warn.setText("ID not found");
                    }
                    rs.close();
                    st.close();
                }catch(NumberFormatException e){
                    warn.setText("Warning: Please enter number");
                } catch (SQLException ex) {
                    warn.setText(ex.getMessage());
                    Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try{
                    combyvol = combyidtx.getText();
                    combyref = null;
                    if(combyvol==null || combyvol.length()<7){
                        warn.setText("Warning: Please enter a valid ID.");
                    }
                    Integer.parseInt(combyvol);
                    PreparedStatement st = con.prepareStatement("select volunteer_id from volunteer where volunteer_id="+combyvol);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        insert3();
                    }
                    else{
                        warn.setText("ID not found");
                    }
                    rs.close();
                    st.close();
                }catch(NumberFormatException e){
                    warn.setText("Warning: Please enter number");
                } catch (SQLException ex) {
                    warn.setText(ex.getMessage());
                    Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(next, 0, 5);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void insert3() {
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(complaint.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(complaint.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(complaint.class.getResource("fonts.css").toExternalForm());
        root.setId("background-five");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title1 = new Label("Insert into Complaint\n Page(3/3)");
        title1.setFont(Font.font("System", FontWeight.BOLD, 40));
        title1.setTextFill(Color.WHITESMOKE);
        Label title = new Label("Details of complaint");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 32));
        title.setTextFill(Color.WHITESMOKE);
        grid.add(title1, 0, 0,2,1);
        grid.add(title, 0, 1);
        //1st row
        Label comagl = new Label("Refugee ID(Complaint against):");
        comagl.setId("texttwo");
        grid.add(comagl, 0, 2);
        TextField comagtx = new TextField(comag);
        grid.add(comagtx, 1, 2);
        //2nd row
        Label mdl = new Label("Misdeed(Max 50 characters):");
        mdl.setId("texttwo");
        grid.add(mdl, 0, 3);
        TextField mdtx = new TextField(misdeed);
        grid.add(mdtx, 1, 3);
        //3rd
        warn = new Label();
        warn.setId("textfour");
        grid.add(warn, 0, 4,2,1);
        //4th row
        Button back = new Button("<< Back");
        back.setId("bwbutton");
        Button next = new Button("Next >>");
        next.setId("bwbutton");
        back.setOnAction((ActionEvent event) -> {
            insert2();
        });
        grid.add(back, 0, 5);
        next.setOnAction((ActionEvent event) -> {
            warn.setText("");
            try{
                    comag = comagtx.getText().trim();
                    
                    if(comag==null || comag.length()<7){
                        warn.setText("Warning: Please enter a valid ID.");
                    }
                    Integer.parseInt(comag);
                    PreparedStatement st = con.prepareStatement("select refugee_id from refugee where refugee_id="+comag);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        misdeed = mdtx.getText();
                        if(misdeed==null || misdeed.length()==0){
                            warn.setText("Enter input for misdeed");
                        }
                        else if(misdeed.length()>50)    
                            warn.setText("Consider max length");
                        else if(combyref!=null && combyref.equals(comag)){
                            warn.setText("Can't complain against himself/herself");
                        }
                        else{
                            String sql = "insert into complaint values(complaint_serial_seq.nextval,'"+misdeed+"',"+combyref+","+combyvol+","+comag+")";
                            sql= sql.toUpperCase();
                            System.out.println(sql);
                            st.executeUpdate(sql);
                            insert4();
                        }
                    }
                    else{
                        warn.setText("ID not found");
                    }
                    rs.close();
                    st.close();
                }catch(NumberFormatException e){
                    warn.setText("Warning: Please enter number for ID");
                } catch (SQLException ex) {
                    warn.setText(ex.getMessage());
                    Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        grid.add(next, 1, 5);
        grid.setHalignment(next, HPos.RIGHT);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void insert4() {
        try {
            root = new BorderPane();
            grid = new GridPane();
            grid.setVgap(10);
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(complaint.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(complaint.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(complaint.class.getResource("fonts.css").toExternalForm());
            root.setId("background-five");
            Label title = new Label("Inserted:");
            title.setUnderline(true);
            title.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
            ObservableList<String> nm = FXCollections.observableArrayList("Complaint Serial","Misdeed","Complained by","Complainer's Name","Complaint Against");
            PreparedStatement st = con.prepareStatement("Select * from complaint where complaint_serial_number=(select max(complaint_serial_number) from complaint)");
            ResultSet rs = st.executeQuery();
            rs.next();
            ObservableList<String> vals = FXCollections.observableArrayList();
            vals.add(0, rs.getObject(1).toString());
            vals.add(1, rs.getObject(2).toString());
            int id;
            if(rs.getObject(3)==null){
                by = "Volunteer";
                id = 4;
            }
            else{
                id = 3;
                by = "Refugee";
            }
            vals.add(2, by);
            String ag = rs.getString(5);
            String sql;
            if(by.equals("Refugee")){
                sql= "select first_name||' '||last_name as Name from "+by+" where "+by+"_id="+rs.getString(id);
            }
            else{
                sql = "select volunteer_firstname||' '||volunteer_lastname as Name from "+by+" where "+by+"_id="+rs.getString(id);
            }
            System.out.println(sql);
            rs= st.executeQuery(sql);
            rs.next();
            vals.add(3,rs.getString(1));
            sql = "select first_name||' '||last_name as Name from refugee where refugee_id="+ag;
            System.out.println(sql);
            rs= st.executeQuery(sql);
            rs.next();
            vals.add(4, rs.getString(1));
            Label []col = new Label[5];
            Label []val = new Label[5];
            for(int i=0;i<5;i++){
                grid.getRowConstraints().add(new RowConstraints(30));
                col[i] = new Label(" "+nm.get(i)+" : ");
                col[i].setId("textfive");
                //col[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(col[i], 0, i);
                grid.setHalignment(col[i], HPos.RIGHT);
                grid.setValignment(col[i], VPos.CENTER);
                val[i] = new Label(" "+vals.get(i)+" ");
                //val[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(val[i], 1, i);
                grid.setValignment(val[i], VPos.CENTER);
                val[i].setId("textfive");
            }
            
            GridPane vbox = new GridPane();
            vbox.setVgap(20);
            vbox.add(title, 0, 0);
            vbox.add(grid, 0, 1);
            vbox.setValignment(title, VPos.BOTTOM);
            Button next = new Button("Next");
            next.setId("new-button");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            vbox.add(next, 0, 2);
            vbox.setAlignment(Pos.CENTER);
            root.setCenter(vbox);
            primaryStage.setScene(scene);
            rs.close();
            st.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(complaint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
