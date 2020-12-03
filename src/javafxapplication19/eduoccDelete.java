package javafxapplication19;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class eduoccDelete {
    static Stage primarystage;
    static String refugeeid ;
    static Connection con;
    
    public static void delete() {
        primarystage= Main.primaryStage;
        con= Main.conn.con;
        refugeeid=null;
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1100, 900);
        scene.getStylesheets().add(eduoccDelete.class.getResource("css1.css").toExternalForm());
        scene.getStylesheets().add(eduoccDelete.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(eduoccDelete.class.getResource("fonts.css").toExternalForm());
        root.setId("background-four");
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(10);
        gridpane.setVgap(20);
        gridpane.setPadding(new Insets(25, 25, 25, 25));
     
        //Button btn = new Button("name");
        Text greet = new Text("Please enter the ID of refugee\nfor educational/occupational info:");
        greet.setFont(Font.font("Adobe Gothic Std B", FontWeight.BOLD, 40));
        Text refid = new Text("Refugee ID :");
        TextField ref = new TextField();
        refid.setId("texttwo");
        Button back = new Button("Back");
        Button add = new Button("Add");
        Button don = new Button("Done");
        Label warn = new Label();
        warn.setId("textfour");

        gridpane.add(greet,0,0,3,1);
        gridpane.add(refid,0,1);
        gridpane.add(ref,1,1);
        gridpane.add(add,2,1);
        gridpane.add(warn,3,1);
        gridpane.add(don,1,3);
        gridpane.add(back,0,3);
        gridpane.setHalignment(don, HPos.RIGHT);
        add.setOnAction((ActionEvent event) -> {
             if(ref.getText().length()<=0){
                 warn.setText("Please enter something!");
             }
             else {
                 int u;
                 u=0;
                 try {
                     Integer.parseInt(ref.getText());
                 } catch (NumberFormatException ex) {
                     warn.setText("Please enter valid ID!");
                     u=1;
                 }
                 if(u==0){
                     warn.setText(null);
                     if(refugeeid==null){
                         refugeeid = ref.getText();
                     }else{
                         refugeeid = refugeeid + "," + ref.getText();
                     }
                 }
             }
             ref.clear();
        });
        don.setOnAction((ActionEvent event) -> {
            String sql = "select r.first_name || ' ' || r.last_name \"Name\",i.institute_name,e.endyear,e.degree,e.occupation,e.specialization,\n" +
                 "l.city,l.state,l.country,e.institute_id,r.refugee_id\n" +
                 "from refugee r join educational_occupational_info e\n" +
                 "on (r.refugee_id= e.refugee_id)\n" +
                 "join institute i\n" +
                 "on(i.institute_id= e.institute_id)\n" +
                 "join location l\n" +
                 "on(i.location_id=l.location_id)\n";
            if(refugeeid!=null){
                sql=sql+" WHERE r.refugee_id IN (" + refugeeid + ")";
            }
            System.out.println(sql);
            delete1(sql.toUpperCase());
        });
        back.setOnAction((ActionEvent event) -> {
            deleteFunctions.selectTable();
        });
        add.setId("new-button");
        don.setId("new-button");
        back.setId("new-button");
        root.setCenter(gridpane);
        primarystage.setScene(scene);
        primarystage.show();
     }
     public static void delete1(String sql) {
         try {
             VBox vb= new VBox();
             vb.setSpacing(20);

             BorderPane root = new BorderPane();
             Scene scene = new Scene(root, 1100, 900);
             scene.getStylesheets().add(eduoccDelete.class.getResource("css1.css").toExternalForm());
             scene.getStylesheets().add(eduoccDelete.class.getResource("backgrounds.css").toExternalForm());
             scene.getStylesheets().add(eduoccDelete.class.getResource("fonts.css").toExternalForm());
             root.setId("background-four");
             primarystage.setScene(scene);
             //vb.setPadding(new in);
             vb.setPadding(new Insets(30));
             Label dl= new Label("Select the row to delete");
             dl.setFont(Font.font("Arial", FontWeight.BOLD, 40));
             dl.setTextFill(Color.WHITESMOKE);
             PreparedStatement pst= con.prepareStatement(sql);
             ResultSet rs= pst.executeQuery();
             ResultSetMetaData rsm;
             rsm = rs.getMetaData();
             int totcol= rsm.getColumnCount();
             GridPane grid = new GridPane();
             grid.setAlignment(Pos.CENTER);
             grid.setPadding(new Insets(30));
             ScrollPane root1= new ScrollPane(grid);
             Text []colnm= new Text[totcol-2];
             ArrayList<Button> btns= new ArrayList<>();
             ArrayList<String> rids= new ArrayList<>();
             ArrayList<String> inids= new ArrayList<>();
             grid.getRowConstraints().add(new RowConstraints(50));
             for(int i=0;i<totcol-2;i++){
                 StackPane sp= new StackPane();
                 sp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                 grid.setFillWidth(sp, true);
                 grid.setFillHeight(sp, true);
                 colnm[i]= new Text(" "+rsm.getColumnName(i+1)+" ");
                 //colnm[i].setId("labelgrid-border");
                 //colnm[i].setPrefSize(300, 50);
                 colnm[i].setFont(Font.font("Arial", FontWeight.BOLD, 20));
                 colnm[i].setFill(Color.WHITESMOKE);
                 grid.add(sp, i+1, 0);
                 sp.getChildren().add(colnm[i]);
                 sp.setId("border-two");
             }
             Button temp1= new Button();
             btns.add(temp1);
             int i=1;
             rids.add(0,"");
             inids.add(0,"");
             boolean nodata= true;
             while(rs.next()){
                 nodata= false;
                 rids.add(i,rs.getString(totcol));
                 inids.add(i,rs.getString(totcol-1));
                 for(int j=0;j<totcol-2;j++){
                     StackPane sp= new StackPane();
                     Text templ=new Text(rs.getObject(j+1)==null?"":" "+rs.getString(j+1)+" ");
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
                 Button temp = new Button("Select#"+i+":");
                 temp.setId("bwbutton");
                 temp.setOnAction((ActionEvent event)->{
                     try {
                         int numpart = Integer.parseInt(temp.getText().substring(7,temp.getText().length()-1));
                         String sql1= "delete from educational_occupational_info where refugee_id="+rids.get(numpart)
                                         +" and institute_id="+inids.get(numpart);
                         System.out.println(sql);
                         PreparedStatement stp= con.prepareStatement(sql1);
                         stp.executeUpdate(sql1);

                     } catch (SQLException ex) {
                         Logger.getLogger(eduoccDelete.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     delete1(sql);
                 });
                 btns.add(i,temp);
                 grid.add(btns.get(i), 0, i);
                 i++;
             }
             if(nodata){
                 Label nodatal= new Label("No Data Found");
                 nodatal.setFont(Font.font("Arial", FontWeight.BOLD, 30));
                 grid.add(nodatal, 0, 1,totcol-1,1);
                 grid.setHalignment(nodatal, HPos.CENTER);
             }
             Button next = designs.button("Home");
             Button back= new Button("Back");
             next.setOnAction((ActionEvent event)->{
                 Main.AuthorityHomePage();
             });
             next.setId("com-button");
             back.setOnAction((ActionEvent event)->{
                 delete();
             });
             back.setId("com-button");
             vb.getChildren().addAll(dl,root1,back,next);
             vb.setAlignment(Pos.CENTER);
             root.setCenter(vb);
         } catch (SQLException ex) {
             Logger.getLogger(eduoccDelete.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
