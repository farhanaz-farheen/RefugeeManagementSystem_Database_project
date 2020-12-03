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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;


/**
 *
 * @author Oishi
 */
public class ShowSomePAT {

    static Stage primarystage = Main.primaryStage;

    public static void someshow(Connection con, String s) {
        try {
            GridPane root = new GridPane();
            root.setAlignment(Pos.CENTER);
            root.setHgap(10);
            root.setVgap(20);
            root.setPadding(new Insets(25, 25, 25, 25));
            Button next = designs.button("Next");
            Button back = designs.button("Back");
            Text greet = new Text("Diagnosis:");
            Text greet1 = new Text("Choose the criteria for patients:");
            root.add(greet1, 0, 0);
            root.add(greet,0,2);
            
            
            greet.setFont(Font.font("System", FontWeight.MEDIUM, 20));
            greet1.setFont(Font.font("System", FontWeight.BOLD, 23));
            
            String sql = "select distinct(D.DIAGNOSIS_NAME) nm,R.GENDER,R.BLOODGROUP\n" +
"FROM REFUGEE R JOIN PRESCRIPTION P\n" +
"ON (R.REFUGEE_ID=P.REFUGEE_ID)\n" +
"JOIN DOCTOR_NURSE_OFFICER DNO\n" +
"ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
"JOIN DIAGNOSIS D\n" +
"ON (D.PRESCRIPTION_SERIAL=P.PRESCRIPTION_SERIAL)\n" +
"WHERE DNO.DOCTOR_NURSE_OFFICER_ID=" + s;
            String jk = "select count(*) c from (select distinct(D.DIAGNOSIS_NAME) nm,R.GENDER,R.BLOODGROUP\n" +
"FROM REFUGEE R JOIN PRESCRIPTION P\n" +
"ON (R.REFUGEE_ID=P.REFUGEE_ID)\n" +
"JOIN DOCTOR_NURSE_OFFICER DNO\n" +
"ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
"JOIN DIAGNOSIS D\n" +
"ON (D.PRESCRIPTION_SERIAL=P.PRESCRIPTION_SERIAL)\n" +
"WHERE DNO.DOCTOR_NURSE_OFFICER_ID=" + s + ")";
            System.out.println(sql);
            System.out.println(jk);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Statement stmt2 = con.createStatement();
            ResultSet rst = stmt2.executeQuery(jk);
            //ResultSet r = st.executeQuery();
            
            int i;
            i=0;
            String count = null;
            while(rst.next()){
                count = rst.getString("c");
            }
            int p;
            p=Integer.parseInt(count);
            String []chec = new String[p];
            while (rs.next()) {
                chec[i] = rs.getString("nm");
                i++;
            }
            CheckBox cb[] = new CheckBox[p];
            for(i=0;i<p;i++){
                cb[i] = new CheckBox();
                cb[i].setText(chec[i]);
                root.add(cb[i],1,i+3);
            }
            int t;
            t = i+4;
            CheckBox male = new CheckBox("Male");
            CheckBox female = new CheckBox("Female");
            CheckBox others = new CheckBox("Others");
            Text second = new Text("Gender: ");
            second.setFont(Font.font("System", FontWeight.MEDIUM, 20));
            root.add(second, 0, t);
            root.add(male, 1, t+1);
            root.add(female, 1, t+2);
            root.add(others, 1, t+3);
            
            Text mnp = new Text("Medicine:");
            mnp.setFont(Font.font("System", FontWeight.MEDIUM, 20));
            
            String phar = "select distinct(D.MEDICINE_NAME) md FROM \n" +
"DIAGNOSIS D JOIN PRESCRIPTION P\n" +
"ON (P.PRESCRIPTION_SERIAL=D.PRESCRIPTION_SERIAL)\n" +
"JOIN DOCTOR_NURSE_OFFICER DNO\n" +
"ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
"WHERE DNO.DOCTOR_NURSE_OFFICER_ID="+s + " AND D.MEDICINE_NAME IS NOT NULL";
            String pharc = "select count(*) vw from (select distinct(D.MEDICINE_NAME) nm FROM \n" +
"DIAGNOSIS D JOIN PRESCRIPTION P\n" +
"ON (P.PRESCRIPTION_SERIAL=D.PRESCRIPTION_SERIAL)\n" +
"JOIN DOCTOR_NURSE_OFFICER DNO\n" +
"ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
"WHERE DNO.DOCTOR_NURSE_OFFICER_ID="+s + " AND D.MEDICINE_NAME IS NOT NULL)";
            
            Statement st = con.createStatement();
            ResultSet rss = st.executeQuery(phar);
            Statement stmtt = con.createStatement();
            ResultSet rsst = stmtt.executeQuery(pharc);
            
            int v;
            v=0;
            String countv = null;
            while(rsst.next()){
                countv = rsst.getString("vw");
            }
            int w;
            w=Integer.parseInt(countv);
            String []checp = new String[w];
            while (rss.next()) {
                checp[v] = rss.getString("md");
                v++;
            }
            root.add(mnp,0,t+4);
            int h;
            h=t+5;
            CheckBox cb2[] = new CheckBox[w];
            for(v=0;v<w;v++){
                cb2[v] = new CheckBox();
                cb2[v].setText(checp[v]);
                root.add(cb2[v],1,h+v);
            }
            Label warn = new Label();
            root.add(warn,1,h+v+1);
            root.add(next,1,h+v+2);
            root.add(back,0,h+v+2);
            ScrollPane scroll = new ScrollPane(root);
            Scene scene = new Scene(scroll, 1100,900);
            scene.getStylesheets().add(ShowSomePAT.class.getResource("scrollstyle.css").toExternalForm());
        scene.getStylesheets().add(ShowSomePAT.class.getResource("animated.css").toExternalForm());
        
            back.setOnAction((ActionEvent event) -> {
                Docprofile.profile(con, s);
            });
        
        
            next.setOnAction((ActionEvent event) -> {
                
                int j;
                String totalname;
                totalname=null;
                int k;
                k=0;
                for(j=0;j<p;j++){
                    if(cb[j].isSelected()){
                        if(k==0){
                            totalname = "'" + cb[j].getText() + "'";
                        }else{
                            totalname=totalname+",'"+cb[j].getText() + "'";
                        }
                        k++;
                    }
                }
                String gentotal;
                gentotal = null;
                k=0;
                for(j=0;j<3;j++){
                    if(male.isSelected()){
                        if(k==0){ gentotal = "'Male'";}
                        else { gentotal = gentotal + ",'Male'";}
                    }
                    if(female.isSelected()){
                        if(k==0){ gentotal = "'Female'";}
                        else { gentotal = gentotal + ",'Female'";}
                    }
                    if(others.isSelected()){
                        if(k==0){ gentotal = "'Others'";}
                        else { gentotal = gentotal + ",'Others'";}
                    }
                }
                String meditotal;
                meditotal = null;
                k=0;
                for(j=0;j<w;j++){
                    if(cb2[j].isSelected()){
                        if(k==0){
                            meditotal = "'" + cb2[j].getText() + "'";
                        }else{
                            meditotal=meditotal+",'"+cb2[j].getText() + "'";
                        }
                        k++;
                    }
                }
                
                if(totalname==null||gentotal==null){
                    warn.setText("Please check the requirements again!");
                }else{
                    System.out.println("yahoo");
                }
                System.out.println(totalname);
                //ShowSomeChoices.showsome(con, s, totalname);
                
                String sqlpl;
            if(meditotal==null){
                sqlpl = "select (R.FIRST_NAME|| ' ' || R.LAST_NAME) PATIENTSNAME,R.GENDER GENDER,R.BLOODGROUP BLOODGROUP,D.DIAGNOSIS_NAME DIAGNOSIS,D.MEDICINE_NAME MEDICINE\n" +
"FROM REFUGEE R JOIN PRESCRIPTION P\n" +
"ON (R.REFUGEE_ID=P.REFUGEE_ID)\n" +
"JOIN DOCTOR_NURSE_OFFICER DNO\n" +
"ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
"LEFT OUTER JOIN DIAGNOSIS D\n" +
"ON (D.PRESCRIPTION_SERIAL=P.PRESCRIPTION_SERIAL)\n" +
"WHERE DNO.DOCTOR_NURSE_OFFICER_ID=" + s + " AND (D.DIAGNOSIS_NAME IN (" + totalname + ")" + " OR R.GENDER IN (" + gentotal + "))";
                }else{
            sqlpl = "select (R.FIRST_NAME|| ' ' || R.LAST_NAME) PATIENTSNAME,R.GENDER GENDER,R.BLOODGROUP BLOODGROUP,D.DIAGNOSIS_NAME DIAGNOSIS,D.MEDICINE_NAME MEDICINE\n" +
"FROM REFUGEE R JOIN PRESCRIPTION P\n" +
"ON (R.REFUGEE_ID=P.REFUGEE_ID)\n" +
"JOIN DOCTOR_NURSE_OFFICER DNO\n" +
"ON (DNO.DOCTOR_NURSE_OFFICER_ID=P.DOCTOR_NURSE_OFFICER_ID)\n" +
"LEFT OUTER JOIN DIAGNOSIS D\n" +
"ON (D.PRESCRIPTION_SERIAL=P.PRESCRIPTION_SERIAL)\n" +
"WHERE DNO.DOCTOR_NURSE_OFFICER_ID=" + s + " AND (D.DIAGNOSIS_NAME IN (" + totalname + ")" + " OR R.GENDER IN (" + gentotal + ")" + " OR D.MEDICINE_NAME IN (" + meditotal + "))";
                
            }  
            System.out.println(sqlpl);
            Commonquery.showRes(con,sqlpl,s);
            });
            primarystage.setScene(scene);
            
            primarystage.show();
        } catch (SQLException ex) {
            Logger.getLogger(ShowSomePAT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
