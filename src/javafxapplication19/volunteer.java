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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class volunteer {
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static Label warn;
    static String firstnm = null;
    static String lastnm = null;
    static String volid= null;
    static Connection con;
    public static void insert1() {
        primaryStage = Main.primaryStage;
        root = new BorderPane();
        
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        root.setId("background-two");
        scene.getStylesheets().add(volunteer.class.getResource("css1.css").toExternalForm());
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Insert into Volunteer");
        title.setTextFill(Color.WHITESMOKE);
        title.setUnderline(true);
        title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
        
        grid.add(title, 0, 0, 4, 1);
        Text []star = new Text[2];
        for(int i=0;i<2;i++){
            star[i]= new Text("*");
            star[i].setFill(Color.RED);
            star[i].setFont(Font.font("Constantia", FontWeight.BOLD, 24));
        }
        
        //First Row:
        Label fnl = new Label("First Name :");
        fnl.setTextFill(Color.WHITESMOKE);
        fnl.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 24));
        TextField fnt = new TextField();
        Label fnn = new Label("At most 50 characters");
        fnn.setTextFill(Color.WHITESMOKE);
        fnn.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 24));
        grid.add(star[0], 0, 1);
        grid.add(fnl, 1, 1);
        grid.add(fnt, 2, 1);
        grid.add(fnn, 3, 1);
        
        //2nd Row
        Label lnl = new Label("Last Name :");
        lnl.setTextFill(Color.WHITESMOKE);
        lnl.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 24));
        TextField lnt = new TextField();
        Label lnn = new Label("At most 50 characters");
        lnn.setTextFill(Color.WHITESMOKE);
        lnn.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 24));
        grid.add(lnl, 1, 2);
        grid.add(lnt, 2, 2);
        grid.add(lnn, 3, 2);
        //3rd row
        Label msg = new Label("indicates compulsory.");
        msg.setTextFill(Color.WHITESMOKE);
        msg.setFont(Font.font("Constantia", FontWeight.SEMI_BOLD, 24));
        grid.add(star[1], 1, 3);
        grid.setHalignment(star[1], HPos.RIGHT);
        grid.add(msg, 2, 3);
        //warn
        warn = new Label();
        grid.add(warn, 0, 4,4,1);
        grid.setHalignment(warn, HPos.CENTER);
        warn.setTextFill(Color.FIREBRICK);
        warn.setFont(Font.font("Constantia", FontWeight.BOLD, 24));
        
        Button back = designs.button("Back");
        back.setId("new-button");
        back.setOnAction((ActionEvent event) -> {
            InsertFunctions.selectTable();
        });
        grid.add(back, 1, 5);
        Button next = designs.button("Next");
        next.setId("new-button");
        next.setOnAction((ActionEvent event) -> {
            warn.setText("");
            if(fnt.getText()== null ||fnt.getText().length()==0 || fnt.getText().length()>50 || (lnt.getText()!=null && lnt.getText().length()>50)){
                warn.setText("Warning: Please enter valid inputs.");
            }
            else{
                try {
                    Connection con = Main.conn.con;
                    firstnm = fnt.getText();
                    lastnm = lnt.getText();
                    if(lastnm!= null && lastnm.length()!=0){
                        lastnm = "'"+lastnm+"'";
                    }
                    else    lastnm = null;
                    String sql = "insert into volunteer values(volunteer_seq.nextval,'" + firstnm + "'," + lastnm + ")";
                    System.out.println(sql);
                    sql= sql.toUpperCase();
                    PreparedStatement s = con.prepareStatement(sql);
                    s.executeUpdate();
                    insert2();
                } catch (SQLException ex) {
                    warn.setText(ex.getMessage());
                    Logger.getLogger(volunteer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(next, 2, 5);
        grid.setHalignment(next, HPos.RIGHT);
        
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    public static void insert2() {
        try {
            grid = new GridPane();
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            root.setId("background-two");
            scene.getStylesheets().add(volunteer.class.getResource("css1.css").toExternalForm());
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            Label title = new Label("Inserted:");
            title.setUnderline(true);
            title.setTextFill(Color.WHITESMOKE);
            title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
            GridPane g = new GridPane();
            Label []l = new Label[6];
            Connection con = Main.conn.con;
            PreparedStatement st = con.prepareStatement("Select * from volunteer where volunteer_id=(select max(volunteer_id) from volunteer)");
            ResultSet rs = st.executeQuery();
            rs.next();
            l[0]= new Label(" Volunteer ID :");
            g.add(l[0], 0, 0);
            g.setHalignment(l[0], HPos.RIGHT);
            l[1]= new Label(" First Name :");
            g.add(l[1], 0, 1);
            g.setHalignment(l[1], HPos.RIGHT);
            l[2]= new Label(" Last Name :");
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
            Logger.getLogger(volunteer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void query1() {
        primaryStage= Main.primaryStage;
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100,900);
        scene.getStylesheets().add(volunteer.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(volunteer.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter values for query:\n(Press Add More for the corresponding\nvalue to be used and input more values)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 25));
        grid.add(title, 0, 0,3,3);
        Label ridl = new Label("Volunteer ID :");
        Label fnl = new Label("First Name :");
        Label lnl = new Label("Last Name :");
        ridl.setFont(Font.font("System", FontWeight.BOLD, 20));
        fnl.setFont(Font.font("System", FontWeight.BOLD, 20));
        lnl.setFont(Font.font("System", FontWeight.BOLD, 20));
        grid.add(ridl, 0, 3);
        grid.add(fnl, 0, 4);
        grid.add(lnl, 0, 5);
        String btn = "ADD";
        TextField ridtx = new TextField();
        TextField fntx = new TextField();
        TextField lntx = new TextField();
        grid.add(ridtx, 1, 3);
        grid.add(fntx, 1, 4);
        grid.add(lntx, 1, 5);
        Button ridb = new Button(btn);
        Button fnb = new Button(btn);
        Button lnb = new Button(btn);
        grid.add(ridb, 2, 3);
        grid.add(fnb, 2, 4);
        grid.add(lnb, 2, 5);
        ridb.setOnAction((ActionEvent event) -> {
            String s= ridtx.getText();
            if(s!=null && s.length()>0)
                try{
                    Integer.parseInt(s);
                    if(volid==null){
                        volid = "in("+s;
                    }
                    else{
                        volid = volid + ","+s;
                    }
                    ridtx.clear();
                }catch(NumberFormatException ex){

                }
        });
        fnb.setOnAction((ActionEvent event) -> {
            String s= fntx.getText();
            if(s!=null && s.length()>0){
                if(firstnm==null){
                    firstnm= "in('"+s.toLowerCase();
                }
                else{
                    firstnm=firstnm+"','"+s.toLowerCase();
                }
                fntx.clear();
            }
            
        });
        lnb.setOnAction((ActionEvent event) -> {
            String s= lntx.getText();
            if(s!=null && s.length()>0){
                if(lastnm==null){
                    lastnm= "in('"+s.toLowerCase();
                }
                else{
                    lastnm=lastnm+"','"+s.toLowerCase();
                }
                lntx.clear();
            }
            
        });
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            queryFunctions.selectTable();
        });
        grid.add(back, 0, 6);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            query2();
        });
        grid.add(next, 0, 7);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    static String sql;
    public static void query2() {
        Label msg= new Label();
        sql= " where ";
        if(volid!=null)     sql= sql+"volunteer_id "+volid+") or ";
        if(firstnm!=null)     sql= sql+"volunteer_firstname "+firstnm+"') or ";
        if(lastnm!=null)     sql= sql+"volunteer_lastname "+lastnm+"') or ";
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100,900);
        scene.getStylesheets().add(volunteer.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(volunteer.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Select which information is required for query:");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 25));
        grid.add(title, 0, 0);
        CheckBox[] choices = new CheckBox[14];
        ObservableList<String> nm = FXCollections.observableArrayList("Volunteer_ID", "Volunteer_FirstName", "Volunteer_LastName");
        for(int i=0;i<3;i++){
            choices[i]= new CheckBox(nm.get(i));
            grid.add(choices[i], 0, i+1);
        }
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            String s= "select ";
            for(int i=0;i<3;i++){
                if(choices[i].isSelected()) s= s+choices[i].getText()+",";
            }
            if(s.equals("select ")){
                s= s+ "* from volunteer";
            }
            else{
                s= s.substring(0, s.length()-1)+" from volunteer";
            }
            if(sql.equals(" where "))   sql = s;
            else    sql = s+ sql.substring(0,sql.length()-4);
            System.out.println(sql);
            queryFunctions.showRes(sql);
        });
        grid.add(next, 0, 4);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
}
