package javafxapplication19;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
/**
 *
 * @author Oishi
 */
public class GeneralQuery {
    static Stage primarystage = Main.primaryStage;
    public static void querystuff(Connection con){
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root,1100,900);
        scene.getStylesheets().add(GeneralQuery.class.getResource("scrollstyle.css").toExternalForm());
        //root.setId("pane");
        scene.getStylesheets().add(GeneralQuery.class.getResource("animated.css").toExternalForm());
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
        Text greet = new Text("Frequently asked Questions!");
        greet.setUnderline(true);
        greet.setFont(Font.font("System", FontWeight.BOLD, 32));
        Text q1 = new Text("1. Number of refugees from different countries");
        gridpane.add(greet,0,0);
        gridpane.add(q1,0,1);
        String sql = "SELECT COUNTRY_OF_ORIGIN COUNTRY_OF_ORIGIN,COUNT(COUNTRY_OF_ORIGIN) NUMBER_OF_REFUGEES\n" +
                "FROM REFUGEE\n" +
                "GROUP BY COUNTRY_OF_ORIGIN\n" +
                "ORDER BY COUNT(COUNTRY_OF_ORIGIN) DESC";
        Button btn = designs.button("Check");
        gridpane.add(btn, 1, 1);
        btn.setOnAction((ActionEvent event) -> {
            System.out.println(sql);
           queryFunctions.showRes2(sql);
        });
        Text q2 = new Text("2. Available doctors from different units");
        gridpane.add(q2,0,2);
        Button btn2 = designs.button("Check");
        gridpane.add(btn2,1,2);
        String sql2 = "SELECT SPECIALIZATION,COUNT(SPECIALIZATION) NUMBER_OF_DOCTORS\n" +
"FROM DOCTOR_NURSE_OFFICER\n" +
"GROUP BY SPECIALIZATION\n" +
"ORDER BY COUNT(SPECIALIZATION) DESC";
        btn2.setOnAction((ActionEvent event) -> {
            queryFunctions.showRes2(sql2);
        });
        Text q3 = new Text("3. Refugees committing more than n crimes. Enter n:");
        gridpane.add(q3,0,3);
        
        TextField n = new TextField();
        gridpane.add(n,0,4);
        Label warn = new Label();
        Button btn3 = designs.button("Check");
        gridpane.add(btn3,1,4);
        btn3.setOnAction((ActionEvent event) -> {
            if(n.getText().length()<=0){
                warn.setText("Please enter a valid number!");
            }else{
                int u;
                u=0;
                try{
                    Integer.parseInt(n.getText());
                }catch(NumberFormatException ex){
                    u=1;
                    warn.setText("Please enter a valid number!");
                }
                if(u==0){
                    String sql3 = "SELECT (R.FIRST_NAME||' '||R.LAST_NAME) NAME,C.MISDEED\n" +
"FROM COMPLAINT C JOIN REFUGEE R\n" +
"ON (C.COMPLAINT_AGAINST = R.REFUGEE_ID)\n" +
"WHERE " + n.getText() + " < (SELECT COUNT(*) FROM COMPLAINT J WHERE J.COMPLAINT_AGAINST=R.REFUGEE_ID)";
                    queryFunctions.showRes2(sql3);
                }
            }
        });
        Text q4 = new Text("4. Refugees diagnosed with more diseases than the average amount");
            Button btn4 = designs.button("Check");
            gridpane.add(q4,0,5);
            gridpane.add(btn4,1,5);
            
            btn4.setOnAction((ActionEvent event) -> {
                    String sql4 = "SELECT (R.FIRST_NAME||' '||R.LAST_NAME) NAME,COUNT(P.REFUGEE_ID) NUMBER_OF_REFUGEE\n" +
"FROM PRESCRIPTION P RIGHT OUTER JOIN REFUGEE R\n" +
"ON (R.REFUGEE_ID = P.REFUGEE_ID)\n" +
"GROUP BY P.REFUGEE_ID,(R.FIRST_NAME||' '||R.LAST_NAME)\n" +
"HAVING COUNT(P.REFUGEE_ID) > (SELECT AVG(COUNT(Y.REFUGEE_ID)) FROM PRESCRIPTION Y GROUP BY (Y.REFUGEE_ID))";
            queryFunctions.showRes2(sql4);
            });
            
            Text q5 = new Text("5. Refugees within the age group:");
            Text f = new Text("From:");
            Text t = new Text("To:");
            f.setFont(Font.font("System", FontWeight.BOLD, 18));
            t.setFont(Font.font("System", FontWeight.BOLD, 18));
            TextField from = new TextField();
            TextField to = new TextField();
            Label warn2 = new Label();
            Label warn3 = new Label();
            Button btn5 = designs.button("Check");
            gridpane.add(q5,0,6);
            gridpane.add(f,0,7);
            gridpane.add(t,0,8);
            gridpane.add(from,1,7);
            gridpane.add(to,1,8);
            gridpane.add(warn2,2,7);
            gridpane.add(warn3,2,8);
            gridpane.add(btn5,1,9);
            btn5.setOnAction((ActionEvent event) -> {
                if(from.getText().length()<=0){
                    warn2.setText("Please enter valid age");
                }else if(to.getText().length()<=0){
                    warn3.setText("Please enter valid age");
                }else{
                    int u,v;
                    u=0;
                    v=0;
                    try{
                        Integer.parseInt(from.getText());
                    }catch(NumberFormatException ex){
                        warn2.setText("Please enter valid age");
                        u=1;
                    }
                    try{
                        
                        Integer.parseInt(to.getText());
                    }catch(NumberFormatException ex1){
                        v=1;
                        warn3.setText("Please enter valid age");
                    }
                    if((u==0)&&(v==0)){
                        System.out.println("WHAT THE HELL");
                        String sql6 = "SELECT (FIRST_NAME||' '||LAST_NAME) NAME,(FLOOR((MONTHS_BETWEEN(SYSDATE,DATE_OF_BIRTH))/12)) AGE\n" +
                        "FROM REFUGEE\n" +
                        "WHERE ((MONTHS_BETWEEN(SYSDATE,DATE_OF_BIRTH))/12) > " + from.getText() +" AND ((MONTHS_BETWEEN(SYSDATE,DATE_OF_BIRTH))/12) < " +to.getText();
                        System.out.println(sql6);
                        queryFunctions.showRes2(sql6);
                    }
                }
            });
            Text q6 = new Text("6. Number of refugees from different relIgions");
            gridpane.add(q6,0,10);
            Button btn7 = designs.button("Check");
            gridpane.add(btn7,1,10);
            btn7.setOnAction((ActionEvent event) -> {
                String sql7 = "SELECT RELIGION,COUNT(RELIGION)\n" +
"FROM REFUGEE WHERE RELIGION IS NOT NULL\n" +
"GROUP BY RELIGION\n" +
"ORDER BY COUNT(RELIGION) DESC";
                queryFunctions.showRes2(sql7);
            });
            Text q7 = new Text("7. Number of refugees of different genders");
            gridpane.add(q7,0,11);
            Button btn8 = designs.button("Check");
            gridpane.add(btn8,1,11);
            btn8.setOnAction((ActionEvent event) -> {
                String sql8 = "SELECT GENDER,COUNT(GENDER)\n" +
"FROM REFUGEE\n" +
"GROUP BY GENDER\n" +
"ORDER BY COUNT(GENDER) DESC";
                queryFunctions.showRes2(sql8);
            });
            Text q8 = new Text("8. Number of refugees of different bloodgroups");
            gridpane.add(q8,0,12);
            Button btn9 = designs.button("Check");
            gridpane.add(btn9,1,12);
            btn9.setOnAction((ActionEvent event) -> {
                String sql9 = "SELECT BLOODGROUP,COUNT(BLOODGROUP)\n" +
"FROM REFUGEE\n" +
"GROUP BY BLOODGROUP\n" +
"ORDER BY COUNT(BLOODGROUP) DESC";
                queryFunctions.showRes2(sql9);
            });
            Text q9 = new Text("9. Total children of refugees");
            gridpane.add(q9,0,13);
            Button btn10 = designs.button("Check");
            gridpane.add(btn10,1,13);
            btn10.setOnAction((ActionEvent event) -> {
                String sql10 = "select r.first_name||' '||r.last_name \"Name\",count(c.refugee_id) \"Total Children\" \n" +
"from refugee r left outer join refugee c \n" +
"on(c.father_id= r.refugee_id or c.mother_id= r.refugee_id) \n" +
"GROUP BY r.refugee_id, r.first_name||' '||r.last_name";
                queryFunctions.showRes2(sql10);
            });
            Text q10 = new Text("10.Refugees");
            Text bg = new Text("with bloodgroup: ");
            Text dis = new Text("without disease: ");
            TextField dis1 = new TextField();
            TextField bg1 = new TextField();
            gridpane.add(q10,0,14);
            gridpane.add(bg,0,15);
            gridpane.add(dis,0,16);
            gridpane.add(bg1,1,15);
            gridpane.add(dis1,1,16);
            Label warn11 = new Label();
            Label warn12 = new Label();
            gridpane.add(warn11,2,15);
            gridpane.add(warn12,2,16);
            bg.setFont(Font.font("System", FontWeight.BOLD, 18));
            dis.setFont(Font.font("System", FontWeight.BOLD, 18));
            Button btnf = designs.button("Check");
            gridpane.add(btnf,1,17);
            btnf.setOnAction((ActionEvent event) -> {
                if(bg1.getText().length()<=0){
                    warn11.setText("Please enter something!");
                }else if(dis1.getText().length()<=0){
                    warn12.setText("Please enter something!");
                }else{
                    String sqlf = "select first_name||' '||last_name \"Name\" from refugee\n" +
"where refugee_id not in(\n" +
"select r.refugee_id\n" +
"from refugee r left outer join prescription p\n" +
"on(r.refugee_id= p.refugee_id)\n" +
"join diagnosis d\n" +
"on(p.prescription_serial= d.prescription_serial)\n" +
"where d.diagnosis_name='" + dis1.getText() + "') and bloodgroup='" + bg1.getText() + "'";
                    queryFunctions.showRes2(sqlf);
                }
            });
            Button back = designs.button("Back");
            gridpane.add(back, 0, 19);
            back.setOnAction((ActionEvent event) -> {
                Main.homePage();
            });
        q1.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q2.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q3.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q4.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q5.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q6.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q7.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q8.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q9.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        q10.setFont(Font.font("System", FontWeight.MEDIUM, 22));
        root.setContent(gridpane);
        primarystage.setScene(scene);
        primarystage.show();
    }
}
