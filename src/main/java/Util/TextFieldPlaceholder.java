/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author Asus.com
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldPlaceholder extends JTextField {
    private String placeholder;
    private boolean showingPlaceholder;

    public TextFieldPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        
        setForeground(Color.GRAY);
        setText(placeholder);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(Color.BLACK);
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY);
                    setText(placeholder);
                    showingPlaceholder = true;
                }
            }
        });
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : super.getText();
    }
    
    public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
    if (showingPlaceholder) {
        setText(placeholder);
    }
}
}