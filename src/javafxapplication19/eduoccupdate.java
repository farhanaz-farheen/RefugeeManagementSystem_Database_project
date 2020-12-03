package javafxapplication19;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class eduoccupdate {
    static  ArrayList<String> countries=Main.countries;
    static Label warn;
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static String refugeeID = null;
    static String instituteID= null;
    static String chinstituteID= null;
    static Connection con;
    static String institutenm = null;
    static String countrynm = null;
    static String state = null;
    static String city = null;
    static String endyear = null;
    static String degree = null;
    static String specialization = null;
    static String occupation = null;
    static boolean  in = false;
    static boolean en = false;
    static boolean dg = false;
    static boolean sp = false;
    static boolean oc = false;
    
    public static void update() {
        con = Main.conn.con;
        primaryStage = Main.primaryStage;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(eduoccupdate.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(eduoccupdate.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(eduoccupdate.class.getResource("fonts.css").toExternalForm());
        root.setId("background-four");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Update Educational/Occupational Information");
        title.setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 3, 1);
        
        Label ridl = new Label("Refugee ID:");
        ridl.setId("texttwo");
        TextField ridtx = new TextField();
        ridtx.setText(refugeeID);
        Label ridwarn = new Label();
        ridwarn.setId("textfour");
        grid.add(ridl, 0, 1);
        grid.add(ridtx, 1, 1);
        grid.add(ridwarn, 2, 1);
        
        Button back1 = new Button("Back");
        back1.setOnAction((ActionEvent event) -> {
            //Main.AuthorityHomePage();
            updateFunctions.updatePage();
        });
        grid.add(back1, 0, 2);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            ridwarn.setText("");
            refugeeID = ridtx.getText();
            if(refugeeID==null || refugeeID.length()<7){
                ridwarn.setText("Please insert valid ID");
            }
            else{
                try{
                    int refid = Integer.parseInt(refugeeID);
                    PreparedStatement st = con.prepareStatement("select refugee_id from educational_occupational_info where refugee_id="+refugeeID);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        update1();
                    }
                    else{
                        ridwarn.setText("ID not found");
                    }
                }catch(NumberFormatException ex){
                    ridwarn.setText("Enter number");
                } catch (SQLException ex) {
                    ridwarn.setText(ex.getMessage());
                }
            }
        });
        grid.add(next, 2, 2);
        back1.setId("new-button");
        next.setId("new-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void update1() {
        String sql = "select r.first_name || ' ' || r.last_name \"Name\",i.institute_name,e.endyear,e.degree,e.occupation,e.specialization,\n" +
                "l.city,l.state,l.country,e.institute_id\n" +
                "from refugee r join educational_occupational_info e\n" +
                "on (r.refugee_id= e.refugee_id)\n" +
                "join institute i\n" +
                "on(i.institute_id= e.institute_id)\n" +
                "join location l\n" +
                "on(i.location_id=l.location_id)\n" +
                "where r.refugee_id="+refugeeID;
        sqlst= sql;
        showRes(sql);
    }
    static String sqlst;
    static  int k;
    public static void showRes(String sql) {
        try {
            root= new BorderPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(eduoccupdate.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(eduoccupdate.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(eduoccupdate.class.getResource("fonts.css").toExternalForm());
            root.setId("background-four");
            
            VBox vb= new VBox();
            vb.setPadding(new Insets(30));
            grid = new GridPane();
            grid.setPadding(new Insets(25, 25, 25, 25));
            ScrollPane root1 = new ScrollPane(grid);
            PreparedStatement   st= Main.conn.con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsm;
            rsm = rs.getMetaData();
            int totcol= rsm.getColumnCount();
            Label title = new Label("Select the row to be updated:");
            title.setUnderline(true);
            title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
            title.setTextFill(Color.WHITESMOKE);
            vb.getChildren().addAll(title,root1);
            vb.setSpacing(20);
            Text []colnm= new Text[totcol];
            grid.getRowConstraints().add(new RowConstraints(50));
            for(int i=0;i<totcol-1;i++){
                colnm[i]= new Text("  "+rsm.getColumnName(i+1)+"  ");
                StackPane sp= new StackPane();
                sp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                grid.setFillWidth(sp, true);
                grid.setFillHeight(sp, true);
                colnm[i].setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
                colnm[i].setFill(Color.WHITESMOKE);
                grid.add(sp, i+1, 0);
                sp.getChildren().add(colnm[i]);
                sp.setId("border-two");
            }
            int i=1;
            ArrayList<Button> sel= new ArrayList<>();
            ArrayList<String> insids= new ArrayList<>();
            insids.add(0, "");
            Button temp1 = new Button("Select");
            sel.add(0,temp1);
            while(rs.next()){
                Button temp = new Button("Select#"+i+" >>");
                temp.setId("bwbutton");
                sel.add(i,temp);
                grid.add(sel.get(i), 0, i);
                grid.setHalignment(sel.get(i), HPos.RIGHT);
                insids.add(i,rs.getString(totcol));
                temp.setOnAction((ActionEvent event) -> {
                    instituteID=insids.get(Integer.parseInt(temp.getText().substring(7, temp.getText().length()-3)));
                    update2();
                });
                for(int j=0;j<totcol-1;j++){
                    StackPane sp= new StackPane();
                    Text templ=new Text(rs.getObject(j+1)==null?"":"  "+rs.getString(j+1)+"  ");
                    sp.setId("border-one");
                    templ.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
                    grid.setFillWidth(sp, true);
                    grid.setFillHeight(sp, true);
                    if(i%2==0){
                        sp.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                    sp.getChildren().add(templ);
                    grid.add(sp,j+1,i);
                }
                i++;
            }
            Button back = new Button("Back");
            back.setId("com-button");
            back.setOnAction((ActionEvent event) -> {
                update();
            });
            vb.getChildren().add(back);
            root.setCenter(vb);
            vb.setAlignment(Pos.CENTER);
            primaryStage.setScene(scene);
        } catch (SQLException ex) {
            Logger.getLogger(queryFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void update2() {
        grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Update Educational/Occupational Information");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        grid.add(title, 0, 0, 4, 1);
        root = new BorderPane();
        Label inl = new Label("Institute Name :");
        inl.setId("texttwo");
        TextField intx = new TextField();
        intx.setText(institutenm);
        Label inn = new Label("At most 100 characters");
        inn.setId("textthree");
        grid.add(inl, 0, 1);
        grid.add(intx, 1, 1);
        grid.add(inn, 2, 1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        ArrayList<String> years = new ArrayList<>(201);
        years.add(null);
        for(int i=currentYear;i>=currentYear-200+1;i--){
            years.add(Integer.toString(i));
        }
        ObservableList<String> yearVal = FXCollections.observableArrayList(years);
        ComboBox<String> yearsCB = new ComboBox<>(yearVal);
        yearsCB.setValue(endyear);
        Label yearLab = new Label("End Year:");
        yearLab.setId("texttwo");
        CheckBox ycb = new CheckBox("Set Null");
        ycb.setId("textthree");
        grid.add(yearLab, 0, 2);
        grid.add(yearsCB, 1, 2);
        grid.add(ycb,2,2);
        
        Label dl = new Label("Degree :");
        dl.setId("texttwo");
        TextField dt = new TextField();
        dt.setText(degree);
        Label dnn = new Label("At most 100 characters");
        dnn.setId("textthree");
        CheckBox dcb = new CheckBox("Set Null");
        dcb.setId("textthree");
        grid.add(dl, 0, 3);
        grid.add(dt, 1, 3);
        grid.add(dnn, 2, 3);
        grid.add(dcb, 3, 3);
        //3rd row
        Label ocl = new Label("Occupation :");
        ocl.setId("texttwo");
        TextField oct = new TextField();
        oct.setText(occupation);
        Label ocn = new Label("At most 50 characters");
        ocn.setId("textthree");
        grid.add(ocl, 0, 4);
        grid.add(oct, 1, 4);
        grid.add(ocn, 2, 4);
        //4th row
        Label spl = new Label("Specialization :");
        spl.setId("texttwo");
        TextField spt = new TextField();
        spt.setText(specialization);
        Label spn = new Label("At most 100 characters");
        spn.setId("textthree");
        CheckBox scb = new CheckBox("Set Null");
        scb.setId("textthree");
        grid.add(spl, 0, 5);
        grid.add(spt, 1, 5);
        grid.add(spn, 2, 5);
        grid.add(scb, 3, 5);
        //warn
        warn = new Label();
        warn.setId("textfour");
        grid.add(warn,0,6,4,1);
        
        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            showRes(sqlst);
        });
        grid.add(back, 1, 7);
        Button next = new Button("Next");
        next.setOnAction((ActionEvent event) -> {
            warn.setText(null);
            
            if(yearsCB.getValue()!=null && ycb.isSelected()){
                warn.setText("Invalid Input");
            }
            else if(ycb.isSelected()){
                en= true;
                endyear= null;
            }
            else if(yearsCB.getValue()!=null){
                en= true;
                endyear= yearsCB.getValue();
            }
            else    en= false;
            
            if(dt.getText()!=null && dt.getText().length()>100){
                warn.setText("Consider Max Length");
            }
            else if(dt.getText()!=null && dt.getText().length()>0 && dcb.isSelected()){
                warn.setText("Invalid Input");
            }
            else if(dcb.isSelected()){
                dg = true;
                degree= null;
            }
            else if(dt.getText()!=null && dt.getText().length()>0){
                dg= true;
                degree= dt.getText();
            }
            else    dg= false;
            
            if(spt.getText()!=null && spt.getText().length()>100){
                warn.setText("Consider Max Length");
            }
            else if(spt.getText()!=null && spt.getText().length()>0 && scb.isSelected()){
                warn.setText("Invalid Input");
            }
            else if(scb.isSelected()){
                sp = true;
                specialization= null;
            }
            else if(spt.getText()!=null && spt.getText().length()>0){
                sp= true;
                specialization= spt.getText();
            }
            else    sp= false;
            
            if(oct.getText()!=null && oct.getText().length()>50){
                warn.setText("Consider Max Length");
            }
            else if(oct.getText()!=null && oct.getText().length()>0){
                occupation= oct.getText();
                oc= true;
            }
            else oc= false;
            
            if(intx.getText()!=null && intx.getText().length()>100){
                warn.setText("Consider Max Length");
            }
            else if(intx.getText()!=null && intx.getText().length()!=0){
                in= true;
                institutenm= intx.getText();
            }
            else    in= false;
            
            if(!in && !en && !oc && !sp && !dg){
                warn.setText("No changes made");
            }
            if(warn.getText()==null){
                update3();
            }
        });
        back.setId("new-button");
        next.setId("new-button");
        grid.add(next, 2, 7);
        grid.setHgap(5);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(eduoccupdate.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(eduoccupdate.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(eduoccupdate.class.getResource("fonts.css").toExternalForm());
        root.setId("background-four");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void update3() {
        String sql;
        
        if (in) {
            
            try {
                
                VBox vb = new VBox();
                grid = new GridPane();
                grid.setPadding(new Insets(25, 25, 25, 25));
                grid.setAlignment(Pos.CENTER);
                vb.setPadding(new Insets(30));
                Label title = new Label("Select the Institute to update or\nselect new for new Institute");
                title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
                title.setTextFill(Color.WHITESMOKE);
                warn.setText("");
                vb.setSpacing(20);
                
                    sql = "select i.institute_name,l.city,l.state,l.country,i.institute_id from institute i join location l on(i.location_id=l.location_id) where i.institute_name='"+institutenm+"'";
                    PreparedStatement st = con.prepareStatement(sql.toUpperCase());
                    ResultSet rs = st.executeQuery();
                    ResultSetMetaData rsm;
                    rsm = rs.getMetaData();
                    int totcol = rsm.getColumnCount();
                    Text[] colnm = new Text[totcol];
                    grid.getRowConstraints().add(new RowConstraints(50));
                    for (int i = 0; i < totcol - 1; i++) {
                        colnm[i] = new Text("  " + rsm.getColumnName(i + 1) + "  ");
                        StackPane sp= new StackPane();
                        sp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                        grid.setFillWidth(sp, true);
                        grid.setFillHeight(sp, true);
                        //colnm[i].setId("labelgrid-border");
                        //colnm[i].setPrefSize(300, 50);
                        colnm[i].setFont(Font.font("Arial", FontWeight.BOLD, 20));
                        colnm[i].setFill(Color.WHITESMOKE);
                        grid.add(sp, i+1, 0);
                        sp.getChildren().add(colnm[i]);
                        sp.setId("border-two");
                    }
                    int i = 1;
                    ArrayList<Button> sel = new ArrayList<>();
                    ArrayList<String> insids = new ArrayList<>();
                    insids.add(0, "");
                    Button temp1 = new Button("Select");
                    sel.add(0, temp1);
                    while (rs.next()) {
                        Button temp = new Button("Select#" + i + " >>");
                        temp.setId("bwbutton");
                        sel.add(i, temp);
                        grid.add(sel.get(i), 0, i);
                        grid.setHalignment(sel.get(i), HPos.RIGHT);
                        insids.add(i, rs.getString(totcol));
                        temp.setOnAction((ActionEvent event) -> {
                            chinstituteID = insids.get(Integer.parseInt(temp.getText().substring(7, temp.getText().length()-3)));
                            update4();
                        });
                        for (int j = 0; j < totcol - 1; j++) {
                            StackPane sp= new StackPane();
                            Text templ=new Text(rs.getObject(j+1)==null?"":"  "+rs.getString(j+1)+"  ");
                            sp.setId("border-one");
                            //templ.setPrefSize(300, 50);
                            //templ.setId("labelgrid-border");
                            templ.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
                            //grid.setFillWidth(templ, true);
                            //grid.setFillHeight(templ, true);
                            grid.setFillWidth(sp, true);
                            grid.setFillHeight(sp, true);
                            if(i%2==0){
                                sp.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                            }
                            sp.getChildren().add(templ);
                            grid.add(sp,j+1,i);
                        }
                        i++;
                    }
                
                Button newins= new Button("New Institute");
                newins.setId("new-button");
                newins.setOnAction((ActionEvent event) -> {
                    updateins();
                });
                Button back= new Button("Back");
                back.setId("new-button");
                back.setOnAction((ActionEvent event) -> {
                    update2();
                });
                
                ScrollPane root1 = new ScrollPane(grid);
                vb.getChildren().addAll(title,root1,warn,newins,back);
                root= new BorderPane(vb);
                scene = new Scene(root, 1100, 900);
                scene.getStylesheets().add(eduoccupdate.class.getResource("css1.css").toExternalForm());
                scene.getStylesheets().add(eduoccupdate.class.getResource("backgrounds.css").toExternalForm());
                scene.getStylesheets().add(eduoccupdate.class.getResource("fonts.css").toExternalForm());
                root.setId("background-four");
                primaryStage.setScene(scene);
            } catch (SQLException ex) {
                Logger.getLogger(eduoccupdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else    update4();
    }
    public static void update4() {
        try {
            String sql = "update educational_occupational_info set ";
            if(in){
                sql= sql+ "institute_id="+chinstituteID+",";
            }
            if(en){
                if(endyear==null)   sql= sql+"endyear=null,";
                else    sql= sql+"endyear="+endyear+",";
            }
            if(dg){
                if(degree==null)   sql= sql+"degree=null,";
                else    sql= sql+"degree='"+degree+"',";
            }
            if(oc){
                sql= sql+"occupation='"+occupation+"',";
            }
            if(sp){
                if(specialization==null)     sql = sql + "specialization=null,";
                else    sql = sql + "specialization='"+specialization+"',";
            }
            
                sql= sql.substring(0, sql.length()-1) + " where refugee_id="+refugeeID+" and institute_id="+instituteID;
                sql= sql.toUpperCase();
                System.out.println(sql);
                PreparedStatement p= con.prepareStatement(sql);
                p.executeUpdate();
                /*
                p= con.prepareCall("begin delinsid("+instituteID+");end;");
                p.execute();*/
                showUpdate();
            
        } catch (SQLException ex) {
            warn.setText("Invalid update operation");
            Logger.getLogger(eduoccupdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void updateins() {
        Text []star = new Text[4];
        for(int i=0;i<4;i++){
            star[i]= new Text("*");
            star[i].setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 24));
            star[i].setFill(Color.RED);
        }
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(eduoccupdate.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(eduoccupdate.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(eduoccupdate.class.getResource("fonts.css").toExternalForm());
        root.setId("background-four");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Institute Information");
        title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 4, 1);
        //1st row
        Label inl = new Label("Institute Name : "+institutenm);
        inl.setId("texttwo");
        grid.add(inl, 1, 1);
        //2nd row
        Label cnl = new Label("Country :");
        cnl.setId("texttwo");
        ObservableList<String> country = FXCollections.observableArrayList(countries);
        ComboBox<String> co = new ComboBox<>(country);
        co.setValue(countrynm);
        grid.add(star[1], 0, 2);
        grid.add(cnl, 1, 2);
        grid.add(co, 2, 2);
        //3rd row
        Label stl = new Label("State :");
        stl.setId("texttwo");
        TextField stt = new TextField();
        stt.setText(state);
        Label stn = new Label("At most 50 characters");
        stn.setId("textthree");
        grid.add(stl, 1, 3);
        grid.add(stt, 2, 3);
        grid.add(stn, 3, 3);
        //4th row
        Label ctl = new Label("City :");
        ctl.setId("texttwo");
        TextField ctt = new TextField();
        ctt.setText(city);
        Label ctn = new Label("At most 50 characters");
        ctn.setId("textthree");
        grid.add(star[2], 0, 4);
        grid.add(ctl, 1, 4);
        grid.add(ctt, 2, 4);
        grid.add(ctn, 3, 4);
        //
        Label msg = new Label("indicates compulsory.");
        msg.setId("texttwo");
        grid.add(star[3], 1, 5);
        grid.setHalignment(star[3], HPos.RIGHT);
        grid.add(msg, 2, 5);
        //warn
        warn = new Label();
        warn.setId("textfour");
        grid.add(warn, 0, 6,4,1);
        grid.setHalignment(warn, HPos.CENTER);
        
        Button back = new Button("Back");
        back.setId("new-button");
        back.setOnAction((ActionEvent event) -> {
            update3();
        });
        grid.add(back, 1, 7);
        Button next = new Button("Next");
        next.setId("new-button");
        next.setOnAction((ActionEvent event) -> {
            warn.setText("");
            countrynm = co.getValue();
            state= stt.getText();
            city= ctt.getText();
            if(countrynm==null || city==null || city.length()==0){
                warn.setText("Insert compulsory ones");
            }
            else if(city.length()>50 || (state!=null && state.length()>50) ){
                warn.setText("Consider max length");
            }
            else{
                try {
                    String st=null;
                    if(state!=null && state.length()>0) st= colon(state);
                    String sql = "begin uplocins(" + colon(city) + "," +st+ "," + colon(countrynm) + "," + colon(institutenm)+",?,?);end;";
                    System.out.println(sql);
                    sql= sql.toUpperCase();
                    CallableStatement cst = con.prepareCall(sql);
                    cst.registerOutParameter(1, Types.INTEGER);
                    cst.registerOutParameter(2, Types.INTEGER);
                    
                    cst.execute();
                    int ver = cst.getInt(1);
                    
                    String insid= String.valueOf(cst.getInt(2));
                    if(ver==1){
                        chinstituteID= insid;
                        update4();
                    }
                    else{
                        warn.setText("Couldn't update");
                    }
                } catch (SQLException ex) {
                    warn.setText("Update Failed");
                    Logger.getLogger(eduoccupdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(next, 2, 7);
        grid.setHalignment(next, HPos.RIGHT);
        
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static String colon(String s){
        if(s.indexOf("'")==0 && s.lastIndexOf("'")==s.length()-1)     return s;
        return "'"+s+"'";
    }
    public static void showUpdate() {
        try {
            grid = new GridPane();
            ObservableList<String> vals = FXCollections.observableArrayList();
            //String sql  = "select * from institute where institute_id = " + insid;
            ObservableList<String> nm = FXCollections.observableArrayList("Refugee_ID","Name","Institute Name","City","State","Country"
                    ,"End Year","Degree","Occupation","Specialization");
            String sql  = "select first_name||' '||last_name as Name from refugee where refugee_id = " + refugeeID;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Label [][]l = new Label[10][2];
            String fullnm = rs.getString(1);
            vals.add(refugeeID);
            vals.add(fullnm);
            if(in)
            sql  = "select * from institute where institute_id = " + chinstituteID;
            else    sql  = "select * from institute where institute_id = "+instituteID;
            pst= con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            vals.add(rs.getString(2));
            sql = "select * from location where location_id="+rs.getString(3);
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            vals.add(rs.getString(2));
            vals.add(rs.getObject(3)==null?"":rs.getString(3));
            vals.add(rs.getString(4));
            if(in)
            sql = "select * from educational_occupational_info where refugee_id="+refugeeID+" AND institute_id="+chinstituteID;
            else    sql = "select * from educational_occupational_info where refugee_id="+refugeeID+" AND institute_id="+instituteID;
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            rs.next();
            vals.add(rs.getObject(3)==null?"":rs.getString(3));
            vals.add(rs.getObject(4)==null?"":rs.getString(4));
            vals.add(rs.getString(5));
            vals.add(rs.getObject(6)==null?"":rs.getString(6));
            Label []col = new Label[10];
            Label []val = new Label[10];
            for(int i=0;i<10;i++){
                grid.getRowConstraints().add(new RowConstraints(30));
                col[i] = new Label("  "+nm.get(i)+" : ");
                //col[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(col[i], 0, i);
                grid.setHalignment(col[i], HPos.RIGHT);
                grid.setValignment(col[i], VPos.CENTER);
                val[i] = new Label(" "+vals.get(i)+" ");
                //val[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                grid.add(val[i], 1, i);
                col[i].setId("textfive");
                val[i].setId("textfive");
                grid.setValignment(val[i], VPos.CENTER);
            }
            
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(eduoccupdate.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(eduoccupdate.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(eduoccupdate.class.getResource("fonts.css").toExternalForm());
            root.setId("background-four");
            Label title = new Label("Updated Educational/Occupational Information");
            title.setFont(Font.font("System", FontWeight.BOLD, 40));
            GridPane vbox = new GridPane();
            vbox.setVgap(10);
            vbox.add(title, 0, 0);
            vbox.setAlignment(Pos.CENTER);
            vbox.add(grid, 0, 1);
            vbox.setValignment(title, VPos.BOTTOM);
            Button next = new Button("Next");
            next.setId("com-button");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            vbox.add(next, 0, 2);
            grid.setVgap(10);
            root.setCenter(vbox);
            primaryStage.setScene(scene);
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(eduoccupdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
