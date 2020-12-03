package javafxapplication19;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class refugeeUpdate {
    static  ArrayList<String> countries;
    public  static Stage primaryStage ;
    static GridPane grid;
    static Scene scene;
    static BorderPane root;
    static Label warning;
    static String refugeeID = null;
    static String firstnm = null;
    static String lastnm = null;
    static String countrynm = null;
    static String gender = null;
    static String bloodGroup = null;
    static String year = null;
    static String month = null;
    static String day = null;
    static String passport = null;
    static String ethtrisecgrp = null;
    static String religion = null;
    static String father = null;
    static String mother = null;
    static String legal = null;
    static String spouse = null;
    static String imgUrl= null;
    static Connection con;
    
    static boolean fn = false;
    static boolean ln = false;
    static boolean cn = false;
    static boolean gn = false;
    static boolean bgp = false;
    static boolean dt = false;
    static boolean pp = false;
    static boolean etsg = false;
    static boolean rn = false;
    static boolean fi = false;
    static boolean mi = false;
    static boolean si = false;
    static boolean lgi = false;
    static boolean imi= false;
    
    public static void update(String r){
        imgUrl= null;
        imi= false;
        con= Main.conn.con;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(refugeeUpdate.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugeeUpdate.class.getResource("css1.css").toExternalForm());
        root.setId("background-three");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Update Refugee Info");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 4, 1);
        Label ridl = new Label("Refugee ID:");
        ridl.setId("textone");
        TextField ridtx = new TextField();
        ridtx.setText(r);
        Label ridwarn = new Label();
        ridwarn.setTextFill(Color.RED);
        ridwarn.setId("textone");
        grid.add(ridl, 0, 1);
        grid.add(ridtx, 1, 1);
        grid.add(ridwarn, 2, 1);
        
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            updateFunctions.updatePage();
        });
        grid.add(back, 0, 2);
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
                    PreparedStatement st = con.prepareStatement("select refugee_id from refugee where refugee_id="+refugeeID);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        update1(firstnm, lastnm, countrynm, gender, bloodGroup);
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
        back.setId("com-button");
        next.setId("com-button");
        grid.add(next, 2, 2);
        primaryStage = Main.primaryStage;
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void update1(String fname, String lname, String coo, String gndr, String bldgrp) {
        try {
            
            countries = new ArrayList<>(250);
            File f = new File("D:\\Lecture slides , pdfs\\2 2\\216\\Project\\countries");
            BufferedReader in;
            in = new BufferedReader(new FileReader(f));
            String s;
            while((s=in.readLine())!=null){
                countries.add(s);
            }
            root = new BorderPane();
            grid = new GridPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(refugeeUpdate.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(refugeeUpdate.class.getResource("css1.css").toExternalForm());
            root.setId("background-three");
            grid.setHgap(5);
            grid.setVgap(20);
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            Label title = new Label("Update Refugee Info  Page(1/3)");
            title.setUnderline(true);
            title.setFont(Font.font("System", FontWeight.BOLD, 40));
            grid.add(title, 0, 0, 4, 1);
            
            
            //First Row:
            Label fnl = new Label("First Name :");
            fnl.setId("textone");
            TextField fnt = new TextField();
            fnt.setText(fname);
            Label fnn = new Label("At most 50 characters");
            fnn.setId("textone");
            grid.add(fnl, 0, 1);
            grid.add(fnt, 1, 1);
            grid.add(fnn, 2, 1);
            
            //2nd Row
            CheckBox lnsn = new CheckBox("Set Null");
            lnsn.setId("textone");
            Label lnl = new Label("Last Name :");
            lnl.setId("textone");
            TextField lnt = new TextField();
            lnt.setText(lname);
            Label lnn = new Label("At most 50 characters");
            lnn.setId("textone");
            grid.add(lnsn, 3, 2);
            grid.add(lnl, 0, 2);
            grid.add(lnt, 1, 2);
            grid.add(lnn, 2, 2);
            
            //3rd row
            Label cnl = new Label("Country of\nOrigin :");
            cnl.setId("textone");
            ObservableList<String> country = FXCollections.observableArrayList(countries);
            country.add(null);
            ComboBox<String> co = new ComboBox<>(country);
            if(coo!=null)   co.setValue(coo);
            grid.add(cnl, 0, 3);
            grid.add(co, 1, 3);
            
            //4th row
            Label gl = new Label("Gender :");
            gl.setId("textone");
            ObservableList<String> Gender = FXCollections.observableArrayList("Male", "Female" ,"Others");
            Gender.add(null);
            ComboBox<String> gnd = new ComboBox<>(Gender);
            if(gndr!=null)   gnd.setValue(gndr);
            grid.add(gl, 0, 4);
            grid.add(gnd, 1, 4);
            
            //5th Row
            Label bgl = new Label("Blood Group:");
            bgl.setId("textone");
            ObservableList<String> bg = FXCollections.observableArrayList(null,"OP", "AP" ,"BP","ABP","ON", "AN" ,"BN","ABN");
            ComboBox<String> bgcb = new ComboBox<>(bg);
            if(bldgrp!=null)   bgcb.setValue(bldgrp);
            grid.add(bgl, 0, 5);
            grid.add(bgcb, 1, 5);
            
            //warn
            Label warn = new Label();
            warn.setTextFill(Color.RED);
            warn.setId("textone");
            grid.add(warn, 0, 6,4,1);
            grid.setHalignment(warn, HPos.CENTER);
            
            Button back1 = new Button("Back");
            back1.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            grid.add(back1, 1, 7);
            Button next = new Button("Next");
            next.setOnAction((ActionEvent event) -> {
                warn.setText(null);
                if(fnt.getText()!=null && fnt.getText().length()!=0){
                    if(fnt.getText().length()>50){
                        warn.setText("Consider Max Length");
                    }
                    else{
                        fn = true;
                        firstnm= fnt.getText();
                        
                    }
                }
                else    fn = false;
                if(lnt.getText()!=null && lnt.getText().length()!=0 && lnsn.isSelected()){
                    warn.setText("Enter Valid Inputs");
                }
                else if(lnsn.isSelected()){
                    ln= true;
                    lastnm = null;
                    
                }
                else if(lnt.getText()!=null && lnt.getText().length()!=0){
                    if(lnt.getText().length()>50){
                        warn.setText("Consider Max Length");
                    }
                    else{
                        ln= true;
                        lastnm= lnt.getText();
                        
                    }
                }
                else    ln = false;
                if(co.getValue()!=null){
                    cn= true;
                    countrynm= co.getValue();
                   
                }
                else    cn = false;
                if(gnd.getValue()!=null){
                    gn= true;
                    gender= gnd.getValue();
                }
                else    gn = false;
                if(bgcb.getValue()!=null){
                    bgp= true;
                    bloodGroup= bgcb.getValue();
                }
                else bgp = false;
                if(warn.getText()==null){
                    update2();
                }
            });
            back1.setId("com-button");
            next.setId("com-button");
            grid.add(next, 2, 7);
            grid.setHalignment(next, HPos.RIGHT);
            
            root.setCenter(grid);
            primaryStage.setScene(scene);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(refugeeUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(refugeeUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void update2() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        int currentYear = localDate.getYear();
        int currentMonth = localDate.getMonthValue();
        int currentDay = localDate.getDayOfMonth();
        ArrayList<String> years = new ArrayList<>(201);
        years.add(null);
        for(int i=currentYear;i>=currentYear-200+1;i--){
            years.add(Integer.toString(i));
        }
        Hashtable<Integer, String> allMonths = new Hashtable<Integer, String>(12);
        allMonths.put(1, "JAN");
        allMonths.put(2, "FEB");
        allMonths.put(3, "MAR");
        allMonths.put(4, "APR");
        allMonths.put(5, "MAY");
        allMonths.put(6, "JUN");
        allMonths.put(7, "JUL");
        allMonths.put(8, "AUG");
        allMonths.put(9, "SEP");
        allMonths.put(10, "OCT");
        allMonths.put(11, "NOV");
        allMonths.put(12, "DEC");
        
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(refugeeUpdate.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugeeUpdate.class.getResource("css1.css").toExternalForm());
        root.setId("background-three");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Insert into Refugee   Page(2/3)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 3, 1);
        
        //1st row
        Label dobl = new Label("Date of Birth :");
        dobl.setId("textone");
        grid.add(dobl, 0, 1);
        
        //2nd  Row:
        HBox hb = new HBox(2);
        Label yearLab = new Label("Year:");
        yearLab.setId("textone");
        hb.getChildren().add(yearLab);
        ObservableList<String> yearVal = FXCollections.observableArrayList(years);
        ComboBox<String> yearsCB = new ComboBox<>(yearVal);
        if(year!=null)  yearsCB.setValue(year);
        hb.getChildren().add(yearsCB);
        Label monLab = new Label("Month:");
        hb.getChildren().add(monLab);
        ObservableList<String> monOB = FXCollections.observableArrayList();
        ComboBox<String> monCB = new ComboBox<>(monOB);
        if(month!=null)     monCB.setValue(month);
        hb.getChildren().add(monCB);
        ArrayList<String> months = new ArrayList<>();
        Label dayLab = new Label("Day:");
        hb.getChildren().add(dayLab);
        ObservableList<String> days = FXCollections.observableArrayList();
        ComboBox<String> dayCB = new ComboBox<>(days);
        if(day!=null)   dayCB.setValue(day);
        hb.getChildren().add(dayCB);
        if(yearsCB.getValue()!=null){
            days.clear();
            monOB.clear();
            months.clear();
            if (Integer.parseInt(yearsCB.getValue()) == currentYear) {
                System.out.println(currentYear);
                System.out.println(currentMonth);
                for (int i = 1; i <= currentMonth; i++) {
                    months.add(allMonths.get(i));
                }
            } else {
                months.addAll(allMonths.values());
            }
            monOB.setAll(months);
        }
        if(monCB.getValue()!=null){
            days.clear();
            for (int i = 1; i < 32; i++) {
                if (i < 10) {
                    days.add("0" + String.valueOf(i));
                } else {
                    days.add(String.valueOf(i));
                }
            }
            if (yearsCB.getValue() != null) {
                int selYear = Integer.parseInt(yearsCB.getValue());

                if (monCB.getValue().equals("FEB")) {
                    if (selYear % 4 != 0) {
                        days.removeAll("29", "30", "31");
                    } else if (selYear % 400 == 0) {
                        days.removeAll("30", "31");
                    } else if (selYear % 100 == 0) {
                        days.removeAll("29", "30", "31");
                    } else {
                        days.removeAll("30", "31");
                    }
                } else {
                    String curMonth = allMonths.get(currentMonth);
                    if (selYear == currentYear && monCB.getValue().equals(curMonth)) {
                        days.clear();
                        for (int i = 1; i <= currentDay; i++) {
                            if (i < 10) {
                                days.add("0" + String.valueOf(i));
                            } else {
                                days.add(String.valueOf(i));
                            }
                        }
                    } else {
                        if (monCB.getValue().equals("APR") || monCB.getValue().equals("JUN") || monCB.getValue().equals("SEP") || monCB.getValue().equals("NOV")) {
                            days.remove("31");
                        }
                    }
                }

            }
        }
        yearsCB.setOnAction((ActionEvent event) -> {
            if(yearsCB.getValue()!=null){
                days.clear();
                monOB.clear();
                months.clear();
                if(Integer.parseInt(yearsCB.getValue())==currentYear){
                    System.out.println(currentYear);
                    System.out.println(currentMonth);
                    for(int i=1;i<=currentMonth;i++){
                        months.add(allMonths.get(i));
                    }
                }
                else{
                    months.addAll(allMonths.values());
                }
                monOB.setAll(months);
            }
            else{
                days.clear();
                monOB.clear();
                months.clear();
            }
        });
        monCB.setOnAction((ActionEvent event) -> {
            if(monCB.getValue()!=null){
                days.clear();
                for(int i=1;i<32;i++){
                    if(i<10){
                        days.add("0"+String.valueOf(i));
                    }
                    else{
                        days.add(String.valueOf(i));
                    }
                }
                System.out.println(days.size());
                if(yearsCB.getValue()!=null){
                    int selYear = Integer.parseInt(yearsCB.getValue());
                   
                    if(monCB.getValue().equals("FEB")){
                        if(selYear%4!=0){
                            days.removeAll("29","30","31");
                        }
                        else if(selYear%400==0){
                            days.removeAll("30","31");
                        }
                        else if(selYear%100==0){
                            days.removeAll("29","30","31");
                        }
                        else{
                            days.removeAll("30","31");
                        }
                    }
                    else{
                        String curMonth = allMonths.get(currentMonth);
                        if(selYear==currentYear && monCB.getValue().equals(curMonth)){
                            days.clear();
                            for(int i=1;i<=currentDay;i++){
                                if(i<10){
                                    days.add("0"+String.valueOf(i));
                                }
                                else{
                                    days.add(String.valueOf(i));
                                }
                            }
                        }
                        else{
                            if(monCB.getValue().equals("APR")||monCB.getValue().equals("JUN")||monCB.getValue().equals("SEP")||monCB.getValue().equals("NOV")){
                                days.remove("31");
                            }
                        }
                    }
                    
                }
            }
            else{
                days.clear();
            }
        });
        monLab.setId("textone");
        dayLab.setId("textone");
        CheckBox dtsn = new CheckBox("set null");
        dtsn.setId("textone");
        grid.add(hb, 0, 2,3,1);
        grid.add(dtsn, 0, 3);
        //3rd row
        Label ppl = new Label("Passport No:");
        ppl.setId("textone");
        TextField pptx= new TextField(); 
        pptx.setText(passport);
        Label ppmsg = new Label("At most 20 characters");  
        ppmsg.setId("textone");
        grid.add(ppl, 0, 4);
        grid.add(pptx, 1, 4);
        grid.add(ppmsg, 2, 4);
        CheckBox psn = new CheckBox("Set Null");
        psn.setId("textone");
        grid.add(psn,3 , 4);
        //4th row
        Label etsgl = new Label("Ethnic/Tribal/\nSectarian Group:");
        TextField etsgtx= new TextField();
        etsgtx.setText(ethtrisecgrp);
        Label etsgmsg = new Label("At most 50 characters");  
        grid.add(etsgl, 0, 5);
        grid.add(etsgtx, 1, 5);
        grid.add(etsgmsg, 2, 5);
        CheckBox etssn = new CheckBox("Set Null");
        grid.add(etssn,3 , 5);
        etsgl.setId("textone");
        etsgmsg.setId("textone");
        etssn.setId("textone");
        //5th row
        Label rl = new Label("Religion :");
        TextField rtx= new TextField();
        rtx.setText(religion);
        Label rmsg = new Label("At most 50 characters");  
        grid.add(rl, 0, 6);
        grid.add(rtx, 1, 6);
        grid.add(rmsg, 2, 6);
        CheckBox rsn = new CheckBox("Set Null");
        grid.add(rsn,3 , 6);
        rl.setId("textone");
        rmsg.setId("textone");
        rsn.setId("textone");
        
        Label warn = new Label();
        warn.setTextFill(Color.RED);
        warn.setId("textone");
        grid.add(warn, 0, 7,3,1);
        grid.setHalignment(warn, HPos.CENTER);
        
        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            year = yearsCB.getValue();
            month = monCB.getValue();
            day = dayCB.getValue();
            passport = pptx.getText();
            ethtrisecgrp = etsgtx.getText();
            religion = rtx.getText();
            update1(firstnm, lastnm, countrynm, gender, bloodGroup);
        });
        grid.add(back, 0, 8);
        Button next = new Button("Next");
        next.setOnAction((ActionEvent event) -> {
            warn.setText(null);
            if(!dtsn.isSelected()){
                if(yearsCB.getValue()==null)    dt= false;
                else if(yearsCB.getValue()!=null && (monCB.getValue()==null || dayCB.getValue()==null)){
                    warn.setText("Please insert date of birth properly.");
                }
                else{
                    dt = true;
                    year = yearsCB.getValue();
                    month = monCB.getValue();
                    day = dayCB.getValue();
                }
            }
            else{
                if(yearsCB.getValue()!=null && monCB.getValue()!=null && dayCB.getValue()!=null){
                    warn.setText("Invalid Input");
                }
                else{
                    dt = true;
                    year= null;
                    month= null;
                    day = null;
                }
            }
            if((pptx.getText()!=null && pptx.getText().length()>20) 
                      || (etsgtx.getText()!=null && etsgtx.getText().length()>50) 
                      || (rtx.getText()!=null && rtx.getText().length()>50)){
                warn.setText("Please consider max size.");
            }
            else{
                passport = pptx.getText();
                ethtrisecgrp = etsgtx.getText();
                religion = rtx.getText();
                if(psn.isSelected() && pptx.getText()!=null && pptx.getText().length()!=0){
                    warn.setText("Enter valid inputs");
                }
                else if(psn.isSelected()){
                    pp= true;
                    passport= null;
                    
                }
                else if(pptx.getText()!=null && pptx.getText().length()!=0) {
                    pp= true;
                    passport= pptx.getText();
                    
                }
                else    pp= false;
                if(etssn.isSelected() && etsgtx.getText()!=null && etsgtx.getText().length()!=0){
                    warn.setText("Enter valid inputs");
                }
                else if(etssn.isSelected()){
                    etsg= true;
                    ethtrisecgrp= null;
                    
                }
                else if(etsgtx.getText()!=null && etsgtx.getText().length()!=0) {
                    etsg= true;
                    ethtrisecgrp= etsgtx.getText();
                    
                }
                else    etsg = false;
                if(rsn.isSelected() && rtx.getText()!=null && rtx.getText().length()!=0){
                    warn.setText("Enter valid inputs");
                }
                else if(rsn.isSelected()){
                    rn= true;
                    religion= null;
                    
                }
                else if(rtx.getText()!=null && rtx.getText().length()!=0) {
                    rn= true;
                    religion=rtx.getText();
                    
                }
                else    rn= false;
            }
            if(warn.getText()==null){
                imi= false;
                imgUrl= null;
                //update3(father, mother, legal, spouse);
                imageupdate();
            }
        });
        grid.add(next, 2, 8);
        back.setId("com-button");
        next.setId("com-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    public static void update3(String f,String m, String l, String s) {
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(refugeeUpdate.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugeeUpdate.class.getResource("css1.css").toExternalForm());
        root.setId("background-three");
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Update Refugee Info  Page(3/3)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid.add(title, 0, 0, 3, 1);
        Label msg = new Label("Enter ID of accompanying people(If any)\n(If information of that person has not been\ninserted, update accordingly after inserting.)");
        grid.add(msg, 0, 1,3,3);
        msg.setFont(Font.font("System", FontWeight.BOLD, 30));
        //4th row 
        Label fid = new Label("Father's ID:");
        fid.setId("textone");
        TextField fidt = new TextField();
        fidt.setText(f);
        CheckBox fmsg = new CheckBox("Set Null");
        fmsg.setId("textone");
        grid.add(fid, 0, 4);
        grid.add(fidt, 1, 4);
        grid.add(fmsg, 2, 4);
        //5th row
        Label mid = new Label("Mother's ID:");
        TextField midt = new TextField();
        midt.setText(m);
        CheckBox mmsg = new CheckBox("Set Null");
        grid.add(mid, 0, 5);
        grid.add(midt, 1, 5);
        grid.add(mmsg, 2, 5);
        mid.setId("textone");
        mmsg.setId("textone");
        //6th row
        Label lid = new Label("Legal\nGuardian's\nID:");
        TextField lidt = new TextField();
        lidt.setText(l);
        CheckBox lmsg = new CheckBox("Set Null");
        grid.add(lid, 0, 6);
        grid.add(lidt, 1, 6);
        grid.add(lmsg, 2, 6);
        lid.setId("textone");
        lmsg.setId("textone");
        //7th row
        Label sid = new Label("Spouse's ID:");
        TextField sidt = new TextField();
        sidt.setText(s);
        CheckBox smsg = new CheckBox("Set Null");
        grid.add(sid, 0, 7);
        grid.add(sidt, 1, 7);
        grid.add(smsg, 2, 7);
        sid.setId("textone");
        smsg.setId("textone");
        //8th row for other sql exceptions
        warning = new Label();
        warning.setTextFill(Color.RED);
        warning.setId("textone");
        grid.add(warning, 0, 8,3,1);
        Button back = new Button("Back");
        back.setOnAction((ActionEvent event) -> {
            father = fidt.getText();
            mother= midt.getText();
            legal= lidt.getText();
            spouse= sidt.getText();
            imageupdate();
        });
        grid.add(back, 0, 9);
        Button next = new Button("Next");
        next.setOnAction((ActionEvent event) -> {
            warning.setText(null);
            if(fidt.getText()!=null && fidt.getText().length()>0 && fmsg.isSelected()){
                warning.setText("Invalid Input");
            }
            else if(fidt.getText()!=null && fidt.getText().length()>0){
                fi= true;
                father = fidt.getText();
            }
            else if(fmsg.isSelected()){
                fi = true;
                father = null;
            }
            else    fi = false;
            if(midt.getText()!=null && midt.getText().length()>0 && mmsg.isSelected()){
                warning.setText("Invalid Input");
            }
            else if(midt.getText()!=null && midt.getText().length()>0){
                mi= true;
                mother = midt.getText();
            }
            else if(mmsg.isSelected()){
                mi = true;
                mother = null;
            }
            else    mi = false;
            if(sidt.getText()!=null && sidt.getText().length()>0 && smsg.isSelected()){
                warning.setText("Invalid Input");
            }
            else if(sidt.getText()!=null && sidt.getText().length()>0){
                si= true;
                spouse = sidt.getText();
            }
            else if(smsg.isSelected()){
                si = true;
                spouse = null;
            }
            else    si = false;
            if(lidt.getText()!=null && lidt.getText().length()>0 && lmsg.isSelected()){
                warning.setText("Invalid Input");
            }
            else if(lidt.getText()!=null && lidt.getText().length()>0){
                lgi= true;
                legal = lidt.getText();
            }
            else if(lmsg.isSelected()){
                lgi = true;
                legal = null;
            }
            else    lgi = false;
            if(warning.getText()==null){
                try {
                    update4();
                } catch (SQLException ex) {
                    warning.setText("Invalid Input. Update Failed.");
                    Logger.getLogger(refugeeUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(next, 2, 9);
        back.setId("com-button");
        next.setId("com-button");
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    
    public static void update4() throws SQLException {
        
        String sql= "update refugee set ";
        if(fn)  sql = sql+ "first_name='"+firstnm+"',";
        if(ln){
            if(lastnm==null)    sql= sql+"last_name=null,";
            else    sql = sql+ "last_name='"+lastnm+"',";
        }
        if (cn) {
            sql = sql + "country_of_origin='" + countrynm + "',";
        }
        if (bgp) {
            sql = sql + "bloodgroup='" + bloodGroup + "',";
        }
        if (gn) {
            sql = sql + "gender='" + gender + "',";
        }
        if (pp) {
            if (passport == null) {
                sql = sql + "passport_no=null,";
            } else {
                sql = sql + "passport_no='" + passport + "',";
            }
        }
        if (etsg) {
            if (ethtrisecgrp == null) {
                sql = sql + "ethnic_tribal_sectarian_group=null,";
            } else {
                sql = sql + "ethnic_tribal_sectarian_group='" + ethtrisecgrp + "',";
            }
        }
        if (rn) {
            if (religion == null) {
                sql = sql + "religion=null,";
            } else {
                sql = sql + "religion='" + religion + "',";
            }
        }
        if(dt){
            if (year == null) {
                sql = sql + "date_of_birth=null,";
            } else {
                sql = sql + "date_of_birth='" + day + "-" + month + "-" + year + "',";
            }
        }
        if(fi){
            sql= sql + "father_id="+father+",";
        }
        if(mi){
            sql= sql + "mother_id="+mother+",";
        }
        if(si){
            sql= sql + "spouse_id="+spouse+",";
        }
        if(lgi){
            sql= sql + "legalguardian_id="+legal+",";
        }
        if(imi){
            sql= sql.toUpperCase();
            if(imgUrl!=null)    sql= sql+ "image_path='"+imgUrl+"',";
            else    sql= sql+ "image_path=null,";
        }
        if(sql.equals("update refugee set ")){
            warning.setText("No changes made");
        }
        else{
            if(!imi)    sql= sql.toUpperCase();
            sql = sql.substring(0, sql.length()-1) + " where refugee_id="+refugeeID;
            System.out.println(sql);
            PreparedStatement p= con.prepareStatement(sql);
            p.executeUpdate();
            update5();
        }
    }
    public static void update5() {
        try{
            GridPane hb= new GridPane();
            StackPane imsp= new StackPane();
            imsp.setMaxSize(500, 300);
            imsp.setPrefSize(500, 300);
            Connection con = Main.conn.con;
            hb.setHgap(10);
            ObservableList<String> nm = FXCollections.observableArrayList(
                    "Refugee ID", "First Name", "Last Name", "Date of Birth","Gender","Blood Group","Country of Origin",
                    "Passport No", "Ethnic/Tribal/Sectarian Group","Religion","ID of Father","ID of Mother"
                    ,"ID of Spouse","ID of Legal Guardian");
            PreparedStatement st = con.prepareStatement("Select * from refugee where refugee_id="+refugeeID);
            ResultSet rs = st.executeQuery();
            rs.next();
            ObservableList<String> vals = FXCollections.observableArrayList();
            for(int i=1;i<15;i++){
                //nm.get(i-1) +":"+
                if(rs.getObject(i)==null)   vals.add(i-1,"");
                else if(i==4)   vals.add(i-1, rs.getObject(i).toString().substring(0, 10));
                else    vals.add(i-1, rs.getObject(i).toString());
                
            }
            String idspouse= rs.getString(13);
            String sp;
            sp = rs.getString(15);
            System.out.println(sp);
            Image image;
            if(sp==null){
                image= new Image("/images/guest-user.jpg");
            }
            else{
                image= new Image("File:"+sp);
            }
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            imsp.getChildren().add(iv1);
            //iv1.fitWidthProperty().bind(imsp.widthProperty());
            //iv1.fitHeightProperty().bind(imsp.heightProperty());
            iv1.setFitHeight(250);
            iv1.setFitWidth(400);
            
            iv1.setPreserveRatio(true);
            grid = new GridPane();
            
            root = new BorderPane();
            scene = new Scene(root, 1100, 900);
            scene.getStylesheets().add(refugeeUpdate.class.getResource("backgrounds.css").toExternalForm());
            scene.getStylesheets().add(refugeeUpdate.class.getResource("css1.css").toExternalForm());
            scene.getStylesheets().add(refugeeUpdate.class.getResource("scroll1.css").toExternalForm());
            root.setId("background-three");
            grid.setAlignment(Pos.CENTER);
            grid.setPadding(new Insets(25, 25, 25, 25));
            Label title = new Label("Updated:");
            title.setUnderline(true);
            title.setFont(Font.font("Constantia", FontWeight.BOLD, 40));
            ScrollPane scr= new ScrollPane(grid);
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            //grid.getColumnConstraints().add(new ColumnConstraints(200));
            Label []col = new Label[14];
            Label []val = new Label[14];
            for(int i=0;i<14;i++){
                grid.getRowConstraints().add(new RowConstraints(30));
                col[i] = new Label(" "+nm.get(i)+" : ");
                val[i] = new Label(" "+vals.get(i)+" ");
                col[i].setId("textone");
                val[i].setId("textone");
                //col[i].setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
                
                grid.add(col[i], 0, i);
                grid.setHalignment(col[i], HPos.RIGHT);
                grid.setValignment(col[i], VPos.CENTER);
                grid.add(val[i], 1, i);
                grid.setValignment(val[i], VPos.CENTER);
                
            }
            hb.add(scr, 0, 0);
            hb.add(imsp, 1, 0);
            GridPane vbox = new GridPane();
            vbox.setVgap(10);
            vbox.add(title, 0, 0);
            vbox.setAlignment(Pos.CENTER);
            vbox.add(hb, 0, 1);
            vbox.setValignment(title, VPos.BOTTOM);
            Button next = new Button("");
            next.setPrefSize(120, 40);
            next.setId("next-one");
            next.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            vbox.add(next, 0, 2);
            root.setCenter(vbox);
            primaryStage.setScene(scene);
            rs.close();
            if(idspouse!=null){
                st= con.prepareStatement("update refugee set spouse_id="+refugeeID+" where refugee_id="+idspouse);
                st.executeUpdate();
            }
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(refugeeUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
    }
    static ImageView imageView;
    static StackPane contentPane;
    static BorderPane layout;
    static Label pathl;
 
    public static void imageupdate() {
        layout = new BorderPane();
        contentPane = new StackPane();
        Scene scene = new Scene(layout, 1100, 900);
        stage= primaryStage;
        layout.setId("background-three");
        scene.getStylesheets().add(refugeeUpdate.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(refugeeUpdate.class.getResource("css1.css").toExternalForm());
        Label title = new Label("Drag and Drop Updated Image:");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 40));
        grid= new GridPane();
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        contentPane.setMaxSize(500, 300);
        contentPane.setPrefSize(500, 300);
        contentPane.setOnDragOver((final DragEvent event) -> {
            mouseDragOver(event);
        });
 
        contentPane.setOnDragDropped((final DragEvent event) -> {
            mouseDragDropped(event);
        });
 
         contentPane.setOnDragExited((final DragEvent event) -> {
             contentPane.setStyle("-fx-border-color: #C6C6C6;");
        });
        Label wrng= new Label();
        wrng.setFont(Font.font("System", FontWeight.BOLD, 25));
        wrng.setTextFill(Color.FIREBRICK);
        CheckBox imcb= new CheckBox("Set Null");
        imcb.setId("textone");
        layout.setCenter(grid);
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            update2();
        });
        grid.add(back, 0, 9);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            wrng.setText(null);
            if(imgUrl!=null && imcb.isSelected()){
                wrng.setText("Invalid Input");
            }
            else if(imcb.isSelected()){
                imi= true;
                imgUrl= null;
            }
            else if(imgUrl==null)   imi=false;
            if(wrng.getText()==null)
            update3(father,mother,legal,spouse);
        });
        grid.add(title, 1, 0);
        grid.add(contentPane, 1, 1,1,5);
        contentPane.setId("border-one");
        contentPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pathl= new Label();
        pathl.setText("Image : ");
        pathl.setId("textone");
        grid.add(imcb,1, 6);
        grid.add(pathl,1,7,3,1);
        grid.add(wrng, 1, 8,2,1);
        grid.add(next, 2,9);
        back.setId("com-button");
        next.setId("com-button");
        primaryStage.setScene(scene);
        primaryStage.show();
 
    }
 
    static void addImage(Image i, StackPane pane){
 
        imageView = new ImageView();
        imageView.setImage(i);
        pane.getChildren().add(imageView);
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());
    }
    static  Stage stage;
    private static void mouseDragDropped(final DragEvent e) {
          final Dragboard db = e.getDragboard();
          boolean success = false;
          if (db.hasFiles()) {
              success = true;
              // Only get the first file from the list
              final File file = db.getFiles().get(0);
              Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                      System.out.println(file.getAbsolutePath());
                      try {
                          if(!contentPane.getChildren().isEmpty()){
                              contentPane.getChildren().remove(0);
                          }
                          Image img = new Image(new FileInputStream(file.getAbsolutePath()));  

                          addImage(img, contentPane);
                      } catch (FileNotFoundException ex) {
                          Logger.getLogger(refugee.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      pathl.setText(file.getAbsolutePath());
                      imi= true;
                      imgUrl=file.getAbsolutePath();
                      //show(file.getAbsolutePath());
                  }
              });
          }
          e.setDropCompleted(success);
          e.consume();
      }
 
    
  
    private static void mouseDragOver(final DragEvent e) {
        final Dragboard db = e.getDragboard();
 
        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");
 
        if (db.hasFiles()) {
            if (isAccepted) {
                contentPane.setStyle("-fx-border-color: red;"
              + "-fx-border-width: 5;"
              + "-fx-background-color: #C6C6C6;"
              + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }
}
