package Library;

import com.sun.org.apache.xml.internal.security.utils.JDKXPathAPI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelTest extends JFrame{

    public JPanel01 jPanel01 = null;
    public JPanel02 jPanel02 = null;

    public void change(String panelName){
        if(panelName.equals("panel01")){
            getContentPane().removeAll();
            getContentPane().add(jPanel01);
            revalidate();
            repaint();
        }
        else{
            getContentPane().removeAll();
            getContentPane().add(jPanel02);
            revalidate();
            repaint();
        }
    }

    public static void main(String[] args){
        JPanelTest win = new JPanelTest();
        win.jPanel01 = new JPanel01(win);
        win.jPanel02 = new JPanel02(win);

        win.add(win.jPanel01);
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setSize(500, 700);
        win.setVisible(true);

    }
}

class JPanel01 extends JPanel{
    private JButton button;
    public JPanel01(JPanelTest win){
        button =new JButton("Panel1");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                win.change("panel02");
            }
        });
        add(button);
    }

}
class JPanel02 extends JPanel{
    private JButton button;
    public JPanel02(JPanelTest win){
        button = new JButton("Panel2");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                win.change("panel01");
            }
        });
        add(button);
    }
}