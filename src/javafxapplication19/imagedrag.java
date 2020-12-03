/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 *
 * @author Oishi
 */
public class imagedrag {
   static ImageView imageView;
    static StackPane contentPane;
    static BorderPane layout;
    static Label pathl;
    static Stage primaryStage = Main.primaryStage;
    static GridPane grid;
    static String imgUrl;
    static String sql;
    public static void imageInsert2(String sqlpl,String req,String s) {
        imgUrl=null;
        sql=sqlpl;
        primaryStage.setTitle("Refugee Registration Management");
        layout = new BorderPane();
        contentPane = new StackPane();
        Scene scene = new Scene(layout, 1100, 900);
        stage= primaryStage;
        layout.setId("background-three");
        scene.getStylesheets().add(imagedrag.class.getResource("backgrounds.css").toExternalForm());
        scene.getStylesheets().add(imagedrag.class.getResource("css1.css").toExternalForm());
        Label title = new Label("Drag and Drop Image:");
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
 
        layout.setCenter(grid);
        Button back = designs.button("Back");
        back.setOnAction((ActionEvent event) -> {
            //insert2(year, month, day, passport, ethtrisecgrp, religion);
            if(req.equals("docins")){
               DocNurseTable.docnurse(Main.conn.con);
            }else if(req.equals("profilepic")){
                EditDoc.editstuff(Main.conn.con, s);
            }
        });
        grid.add(back, 0, 7);
        Button next = designs.button("Next");
        next.setOnAction((ActionEvent event) -> {
            //insert3(father,mother,legal,spouse);
            if(req.equals("docins")){
                if(imgUrl!=null){
                   sql=sql+"'" +imgUrl + "')";
               }else{
                   sql=sql+"null)";
               }
               DocNurseTable.insertmiddle(sql,imgUrl);
            }else if(req.equals("profilepic")){
                try {
                    Connection con = Main.conn.con;
                    if(imgUrl==null){
                        sql=sql+"null where doctor_nurse_officer_id="+s;
                    }else{
                        sql=sql + "'" + imgUrl + "' where doctor_nurse_officer_id="+s;
                    }
                    System.out.println(sql);
                    PreparedStatement st = con.prepareStatement(sql);
                    st.executeUpdate();
                    st.close();
                    UpdatedINFO.message(Main.conn.con, s);
                } catch (SQLException ex) {
                    Logger.getLogger(imagedrag.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        grid.add(title, 1, 0);
        grid.add(contentPane, 1, 1,1,5);
        contentPane.setId("border-one");
        contentPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pathl= new Label();
        pathl.setText("Image : ");
        pathl.setId("textone");
        grid.add(pathl,1,6,3,1);
        grid.add(next, 2,7);
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
                          Logger.getLogger(imagedrag.class.getName()).log(Level.SEVERE, null, ex);
                      }
                      pathl.setText(file.getAbsolutePath());
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


