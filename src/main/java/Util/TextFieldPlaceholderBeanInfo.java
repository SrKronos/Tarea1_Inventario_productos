/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author Leonardo Loor <leonardoloor1988@gmail.com>
 */
import java.beans.*;

public class TextFieldPlaceholderBeanInfo extends SimpleBeanInfo {
    
    @Override
    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor bd = new BeanDescriptor(TextFieldPlaceholder.class);
        bd.setDisplayName("TextField with Placeholder");
        return bd;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor placeholder = new PropertyDescriptor("placeholder", TextFieldPlaceholder.class);
            placeholder.setDisplayName("Placeholder");
            
            return new PropertyDescriptor[] { placeholder };
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}