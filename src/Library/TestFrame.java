package Library;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class TestFrame extends JFrame {

    private BufferedImage backgroundImage;
    private BufferedImage secondBackgroundImage;
    private JButton changeButton;
    private JButton secondChangeButton;
    private JPanel firstPanel, secondPanel;
    public TestFrame(){
        setTitle("");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        try {
            backgroundImage = ImageIO.read(new File("img/background.jpg"));
            secondBackgroundImage = ImageIO.read(new File("img/secondBackground.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }



        firstPanel = new FirstPanel();
        firstPanel.setBounds(0, 0, 600, 600);
        firstPanel.setLayout(null);

        secondPanel = new SecondPanel();
        secondPanel.setBounds(0, 0, 600, 600);
        secondPanel.setLayout(null);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(secondPanel);
                revalidate();
                repaint();
            }
        });
        secondChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(firstPanel);
                revalidate();
                repaint();
            }
        });





        add(firstPanel);

        setVisible(true);
    }

    class FirstPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            g.drawImage(backgroundImage, 0, 0, null);
        }

        public FirstPanel(){
            changeButton =new JButton("Change");
            changeButton.setBounds(0, 0, 100, 100);


            add(changeButton);
        }

    }

    class SecondPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            g.drawImage(secondBackgroundImage, 0, 0, null);
        }

        public SecondPanel(){
            secondChangeButton = new JButton("secondChange");
            secondChangeButton.setBounds(0, 0, 100, 100);

            add(secondChangeButton);
        }
    }

    public static void main(String[] args){
        new TestFrame();
    }
}
