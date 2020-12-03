package javafxapplication19;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class instituteQuery {
    static Stage primaryStage;
    static Scene scene;
    static BorderPane root;
    static GridPane grid;
    static String institutenm = null;
    static String city = null;
    static String state = null;
    static String countrynm = null;
    public static void query1() {
        primaryStage= Main.primaryStage;
        grid = new GridPane();
        root = new BorderPane();
        scene = new Scene(root, 1100,900);
        scene.getStylesheets().add(instituteQuery.class.getResource("style.css").toExternalForm());
        scene.getStylesheets().add(instituteQuery.class.getResource("animated.css").toExternalForm());
        root.setId("paneq");
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(5);
        grid.setVgap(15);
        Label title = new Label("Enter values for query:\n(Press Add More for the corresponding\nvalue to be used and input more values)");
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 25));
        grid.add(title, 0, 0,3,3);
        Label inl = new Label("Institute Name :");
        Label ctl = new Label("City :");
        Label stl = new Label("State :");
        Label cnl = new Label("Country :");
        
        inl.setFont(Font.font("System", FontWeight.BOLD, 20));
        ctl.setFont(Font.font("System", FontWeight.BOLD, 20));
        stl.setFont(Font.font("System", FontWeight.BOLD, 20));
        cnl.setFont(Font.font("System", FontWeight.BOLD, 20));
        
        grid.add(inl, 0, 3);
        grid.add(ctl, 0, 4);
        grid.add(stl, 0, 5);
        grid.add(cnl, 0, 6);
        String btn = "ADD";
        TextField intx = new TextField();
        TextField cttx = new TextField();
        TextField sttx = new TextField();
        TextField cntx = new TextField();
        grid.add(intx, 1, 3);
        grid.add(cttx, 1, 4);
        grid.add(sttx, 1, 5);
        grid.add(cntx, 1, 6);
        Button inb = new Button(btn);
        Button ctb = new Button(btn);
        Button stb = new Button(btn);
        Button cnb = new Button(btn);
        grid.add(inb, 2, 3);
        grid.add(ctb, 2, 4);
        grid.add(stb, 2, 5);
        grid.add(cnb, 2, 6);
        inb.setOnAction((ActionEvent event) -> {
            String s= intx.getText();
            if(s!=null && s.length()>0)
                if (institutenm == null) {
                    institutenm = "in ('" + s;
                } else {
                    institutenm = institutenm + "','" + s;
                }
            intx.clear();
        });
        ctb.setOnAction((ActionEvent event) -> {
            String s= cttx.getText();
            if(s!=null && s.length()>0){
                if(city==null){
                    city= "in ('"+s;
                }
                else{
                    city=city+"','"+s;
                }
                cttx.clear();
            }
        });
        stb.setOnAction((ActionEvent event) -> {
            String s= sttx.getText();
            if(s!=null && s.length()>0){
                if(state==null){
                    state= "in ('"+s.toLowerCase();
                }
                else{
                    state=state+"','"+s.toLowerCase();
                }
                sttx.clear();
            }
            
        });
        cnb.setOnAction((ActionEvent event) -> {
            String s= cntx.getText();
            if(s!=null && s.length()>0){
                if(countrynm==null){
                    countrynm
                            = "in ('"+s;
                }
                else{
                    countrynm=countrynm+"','"+s;
                }
                cntx.clear();
            }
            
        });
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            queryFunctions.selectTable();
        });
        grid.add(back, 0, 7);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            query2();
        });
        grid.add(next, 0, 7);
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }
    public static void query2() {
        String sql = "select i.institute_name,count(e.institute_id) \"Total Refugees\",l.city,l.state,l.country\n" +
                        "from institute i left outer join educational_occupational_info e\n" +
                        "on(i.institute_id= e.institute_id)\n" +
                        "join location l\n" +
                        "on(i.location_id= l.location_id)";
        String sql1= " where ";
        if(institutenm!=null)    sql1= sql1+"i.institute_name "+ institutenm+"') or ";
        if(city!=null)  sql1= sql1+"l.city "+city+"') or ";
        if(state!=null)     sql1= sql1 +"l.state "+state+"') or ";
        if(countrynm!=null)     sql1= sql1+"l.country "+countrynm+"') or ";
        if(sql1.equals(" where ")){
            sql= sql + " group by i.institute_id,i.institute_name,l.city,l.state,l.country";
        }
        else{
            sql= sql + sql1.substring(0,sql1.length()-4)+" group by i.institute_id,i.institute_name,l.city,l.state,l.country";
        }
        System.out.println(sql);
        queryFunctions.showRes(sql.toUpperCase());
    }
    
}
