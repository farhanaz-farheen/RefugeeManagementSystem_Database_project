package javafxapplication19;

import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class deleteFunctions {
    static Connection con;
    public  static Stage primaryStage ;
    public static void selectTable() {
        primaryStage = Main.primaryStage;
            Label title= new Label("Select for deletion");
            title.setTextFill(Color.WHITE);
            title.setUnderline(true);
            title.setFont(Font.font("Castellar", FontWeight.BOLD, 40));
            BorderPane root = new BorderPane();
            root.setId("background-one");
            
            
            //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Scene scene1 = new Scene(root, 1100, 900);
            scene1.getStylesheets().add(deleteFunctions.class.getResource("css1.css").toExternalForm());
            GridPane gridg = new GridPane();
            gridg.add(title, 0, 0);
            gridg.setAlignment(Pos.CENTER);
            gridg.setHgap(10);
            gridg.setVgap(40);
            gridg.setPadding(new Insets(25, 25, 25, 25));
            
            Button rb= new Button("Refugee");
            rb.setId("com-button");
            //rb.setPrefSize(400, 50);
            gridg.add(rb, 0, 1);
            rb.setOnAction((ActionEvent event) -> {
                refugeeDelete.frstnm=null;
                refugeeDelete.lastnm= null;
                refugeeDelete.delete();
            }); 
            Button vb= new Button("Volunteer");
            vb.setId("com-button");
            //vb.setPrefSize(400, 50);
            gridg.add(vb, 0, 2);
            vb.setOnAction((ActionEvent event) -> {
                volunteerDelete.frstnm=null;
                volunteerDelete.lastnm=null;
                volunteerDelete.delete();
            }); 
            Button ib= new Button("Institute");
            ib.setId("com-button");
            gridg.add(ib, 0, 3);
            ib.setOnAction((ActionEvent event) -> {
                instituteDel.delete();
            }); 
            Button eb= new Button("Educational/Occupational Info");
            eb.setId("com-button");
            //eb.setPrefSize(400, 50);
            gridg.add(eb, 0, 4);
            eb.setOnAction((ActionEvent event) -> {
                eduoccDelete.delete();
            }); 
            Button dnob= new Button("Doctor/Nurse/Officer");
            dnob.setId("com-button");
            //cb.setPrefSize(400, 50);
            gridg.add(dnob, 0, 5);
            dnob.setOnAction((ActionEvent event) -> {
                docnuroffDelete.delete();
            });
            Button chb= new Button("Checkup History");
            chb.setId("com-button");
            //cb.setPrefSize(400, 50);
            gridg.add(chb, 0, 6);
            chb.setOnAction((ActionEvent event) -> {
                checkupDelete.docid = null;
                checkupDelete.refid = null;
                checkupDelete.docname = null;
                checkupDelete.delcheck();
            });
            Button cb= new Button("Complaint");
            cb.setId("com-button");
            //cb.setPrefSize(400, 50);
            gridg.add(cb, 0, 7);
            cb.setOnAction((ActionEvent event) -> {
                ComplaintDelete.comdel(Main.conn.con);
            });
            
            Button back , next;
            back = new Button("");
            back.setId("back-one");
            back.setPrefSize(120, 40);
            back.setOnAction((ActionEvent event) -> {
                Main.AuthorityHomePage();
            });
            
            gridg.add(back, 0, 8);
            root.setCenter(gridg);
            
            primaryStage.setScene(scene1);
    }
    /*
    next.setOnAction((ActionEvent event) -> {
            if(tabChoice.getValue()!=null){
                String s= tabChoice.getValue();
                if(s.equals("REFUGEE")){
                    refugeeDelete.delete();
                }
                else if(s.equals("VOLUNTEER")){
                    volunteerDelete.delete();
                }
                else if(s.equals("DOCTOR_NURSE_OFFICER")){
                    docnuroffDelete.delete();
                }
                else if(s.equals("CHECKUP")){
                    checkupDelete.docid= null;
                    checkupDelete.refid= null;
                    checkupDelete.docname= null;
                    checkupDelete.delcheck();
                }
                else if(s.equals("COMPLAINT")){
                    ComplaintDelete.comdel(Main.conn.con);
                }
                else if(s.equals("EDUCATIONAL_OCCUPATIONAL_INFO")){
                    eduoccDelete.delete();
                }
                else if(s.equals("INSTITUTE")){
                    instituteDel.delete();
                }
            }
                
        });
    
    public static void delete1(String tabName) {
        con = Main.conn.con;
        primaryStage = Main.primaryStage;
        root = new BorderPane();
        grid = new GridPane();
        scene = new Scene(root, 800, 550);
        grid.setHgap(5);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label title = new Label("Delete From "+tabName);
        title.setUnderline(true);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        grid.add(title, 0, 0, 3, 1);
        
        Label ridl = new Label(tabName+" ID:");
        TextField ridtx = new TextField();
        ridtx.setText(deleteFunctions.id);
        Label ridwarn = new Label();
        ridwarn.setTextFill(Color.RED);
        grid.add(ridl, 0, 1);
        grid.add(ridtx, 1, 1);
        grid.add(ridwarn, 2, 1);
        
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            selectTable();
        });
        grid.add(back, 0, 2);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            ridwarn.setText("");
            deleteFunctions.id = ridtx.getText();
            if(deleteFunctions.id==null || deleteFunctions.id.length()<7){
                ridwarn.setText("Please insert valid ID");
            }
            else{
                try{
                    int refid = Integer.parseInt(deleteFunctions.id);
                    PreparedStatement st = con.prepareStatement("select "+tabName+"_id from "+tabName+" where "+ tabName+"_id="+deleteFunctions.id);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        st = con.prepareStatement("delete from "+tabName+" where "+tabName+"_id="+deleteFunctions.id);
                        st.executeUpdate();
                        Main.AuthorityHomePage();
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
        
        root.setCenter(grid);
        primaryStage.setScene(scene);
    }*/
}
