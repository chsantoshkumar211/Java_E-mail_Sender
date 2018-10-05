package mail;
import javax.swing.*;
public class HelloJTextField
{
  public static void main(String args[])
  {
    String str1 = JOptionPane.showInputDialog("Enter First Number");
    String str2 = JOptionPane.showInputDialog("Enter Second Number");
                                   // input dialog returns always a string
    double fn = Double.parseDouble(str1);
    double sn = Double.parseDouble(str2);
 
    JOptionPane.showMessageDialog( null, "The Product is " + fn*sn, "Product", JOptionPane.INFORMATION_MESSAGE);
  }
}