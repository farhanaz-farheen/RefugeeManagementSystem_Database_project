/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication19;

import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class designs {
    public static InnerShadow innShadow() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setChoke(0.2);
        innerShadow.setWidth(80);
        innerShadow.setHeight(30);
        innerShadow.setColor(Color.web("#464b50"));
        return innerShadow;
    }
    
    public static Font font(String s,FontWeight w) {
        return Font.font(s, w, 15);
    }
    
    public  static  Button button(String s){
        Button b = new Button(s);
        b.setFont(font("System", FontWeight.BOLD));
        //b.setEffect(innShadow());
        
        return b;
    }
}
