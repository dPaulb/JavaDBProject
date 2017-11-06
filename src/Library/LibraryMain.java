package Library;

import Member.MemberDAO;
import Member.MemberDTO;
import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class LibraryMain extends JFrame {
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    private LoginPanel loginPanel = null;
    private JoinPanel joinPanel = null;

    public void change(String panelName) {
        if (panelName.equals("LoginPanel")) {
            getContentPane().removeAll();
            getContentPane().add(loginPanel);
            revalidate();
            repaint();
        } else if (panelName.equals("JoinPanel")) {
            getContentPane().removeAll();
            getContentPane().add(joinPanel);
            revalidate();
            repaint();
        }
    }


    public static void main(String[] args) {
        LibraryMain window = new LibraryMain();
        window.joinPanel = new JoinPanel(window);
        window.loginPanel = new LoginPanel(window);

        window.add(window.loginPanel);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

}

class LoginPanel extends JPanel {

    private JTextField idField;
    private JPasswordField passwordField;
    private JLabel idLabel, passwordLabel, mainLabel;
    private JLayeredPane mainPanel;
    private BufferedImage backgroundImage;
    private JButton loginButton, joinButton;
    private Font labelFont = new Font("돋움", Font.ITALIC | Font.BOLD, 50);


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public LoginPanel(LibraryMain window) {

        setLayout(null);
        idField = new JTextField(10);
        passwordField = new JPasswordField(10);
        idLabel = new JLabel("ID : ");
        passwordLabel = new JLabel("PW : ");
        loginButton = new JButton("로그인");
        joinButton = new JButton("회원가입");


        try {
            backgroundImage = ImageIO.read(new File("img/background.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        mainLabel = new JLabel("Library Management System");
        mainLabel.setBounds(300, 150, 800, 50);
        mainLabel.setFont(labelFont);
        add(mainLabel);

        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(labelFont);
        idLabel.setBounds(320, 240, 280, 50);
        add(idLabel);


        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(320, 320, 280, 50);
        add(passwordLabel);

        loginButton.setFont(labelFont);
        loginButton.setBounds(480, 400, 400, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberDAO memberDAO = new MemberDAO();
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setUserID(idField.getText());
                memberDTO.setUserPassword(new String(passwordField.getPassword()));

                int result = memberDAO.login(memberDTO);
                if(result == -2){
                    JOptionPane.showMessageDialog(null, "알수없는 오류입니다. 다시시도해주세요.");
                    return;
                }
                else if(result == -1){
                    JOptionPane.showMessageDialog(null, "아이디나 비밀번호가 다릅니다.");
                    return;
                }
                else if(result == 1){
                    JOptionPane.showMessageDialog(null, "로그인 성공.");
                }
            }
        });
        add(loginButton);

        idField.setBounds(480, 240, 400, 50);
        idField.setFont(labelFont);
        add(idField);

        passwordField.setBounds(480, 320, 400, 50);
        passwordField.setFont(labelFont);
        add(passwordField);

        joinButton.setFont(labelFont);
        joinButton.setBounds(480, 480, 400, 50);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.change("JoinPanel");
            }
        });
        add(joinButton);


    }

}

class JoinPanel extends JPanel {
    private BufferedImage backgroundImage;
    private JButton changeButton, backButton;
    private JTextField idField, emailField;
    private JPasswordField passwordField, passwordCheckField;
    private JLabel idLabel, passwordLabel, mainLabel, passwordCheckLabel, emailLabel, genderLabel, ageLabel;
    private JRadioButton manButton, womanButton;
    private JComboBox ageBox;
    private ButtonGroup genderGroup;
    private JLayeredPane mainPanel;
    private JButton loginButton, joinButton;
    private Font labelFont = new Font("돋움", Font.ITALIC | Font.BOLD, 50);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public JoinPanel(LibraryMain window) {
        setLayout(null);

        idField = new JTextField(10);
        passwordField = new JPasswordField(10);
        idLabel = new JLabel("ID : ");
        passwordLabel = new JLabel("PW : ");
        loginButton = new JButton("로그인");
        joinButton = new JButton("회원가입");
        passwordCheckLabel = new JLabel("PW Check : ");
        passwordCheckField = new JPasswordField(10);
        genderGroup = new ButtonGroup();
        manButton = new JRadioButton("남자");
        womanButton = new JRadioButton("여자");
        ageBox = new JComboBox();
        emailField = new JTextField(30);
        emailLabel = new JLabel("Email : ");
        backButton = new JButton("로그인 화면으로");
        genderLabel = new JLabel("성별 : ");
        ageLabel = new JLabel("나이 : ");

        for (int i = 10; i <= 90; i++) {
            ageBox.addItem(i);
        }

        try {
            backgroundImage = ImageIO.read(new File("img/background.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        mainLabel = new JLabel("Join Page");
        mainLabel.setBounds(550, 80, 800, 50);
        mainLabel.setFont(labelFont);
        add(mainLabel);

        backButton.setBounds(0, 0, 150, 80);
        backButton.setFont(new Font("돋움", Font.ITALIC | Font.BOLD, 13));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.change("LoginPanel");
            }
        });
        add(backButton);

        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(labelFont);
        idLabel.setBounds(320, 150, 280, 50);
        add(idLabel);

        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(320, 230, 280, 50);
        add(passwordLabel);

        passwordCheckLabel.setForeground(Color.WHITE);
        passwordCheckLabel.setFont(new Font("돋움", Font.ITALIC | Font.BOLD, 25));
        passwordCheckLabel.setBounds(320, 310, 280, 50);
        add(passwordCheckLabel);

        idField.setBounds(480, 150, 400, 50);
        idField.setFont(labelFont);
        add(idField);

        passwordField.setBounds(480, 230, 400, 50);
        passwordField.setFont(labelFont);
        add(passwordField);

        passwordCheckField.setBounds(480, 310, 400, 50);
        passwordCheckField.setFont(labelFont);
        add(passwordCheckField);


        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(320, 390, 400, 50);
        genderLabel.setFont(labelFont);
        add(genderLabel);

        manButton.setForeground(Color.WHITE);
        manButton.setBounds(500, 390, 200, 50);
        manButton.setOpaque(false);
        manButton.setFont(labelFont);
        add(manButton);

        womanButton.setForeground(Color.WHITE);
        womanButton.setBounds(750, 390, 200, 50);
        womanButton.setOpaque(false);
        womanButton.setFont(labelFont);
        add(womanButton);

        genderGroup.add(manButton);
        genderGroup.add(womanButton);


        ageBox.setFont(labelFont);
        ageBox.setBounds(580, 470, 200, 50);
        add(ageBox);

        ageLabel.setFont(labelFont);
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setBounds(320, 470, 400, 50);
        add(ageLabel);

        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(320, 550, 280, 50);
        emailLabel.setFont(new Font("돋움", Font.ITALIC | Font.BOLD, 40));
        add(emailLabel);

        emailField.setBounds(480, 550, 400, 50);
        emailField.setFont(labelFont);
        add(emailField);

        joinButton.setBounds(480, 630, 400, 50);
        joinButton.setFont(labelFont);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberDAO memberDAO = new MemberDAO();
                MemberDTO memberDTO = new MemberDTO();
                if(!passwordField.getText().equals(passwordCheckField.getText())){
                    JOptionPane.showMessageDialog(null, "두 비밀번호가 다릅니다.");
                    return;
                }
                else if(passwordField.getText().equals(passwordCheckField.getText())){
                    memberDTO.setUserPassword(new String(passwordField.getPassword()));
                }


                memberDTO.setUserID(idField.getText());

                memberDTO.setUserAge(Integer.parseInt(ageBox.getSelectedItem().toString()));
                if (manButton.isSelected()) {
                    memberDTO.setUserGender(manButton.getText());
                } else if (womanButton.isSelected()) {
                    memberDTO.setUserGender(womanButton.getText());
                }
                memberDTO.setUserEmail(emailField.getText());


                if(idField.getText().equals("") || passwordField.getPassword().toString().equals("") ||
                        ageBox.getSelectedItem().toString().equals("") || memberDTO.getUserGender().equals("") ||
                        memberDTO.getUserEmail().equals("")){
                    JOptionPane.showMessageDialog(null, "빈 칸 없이 채워주세요.");
                    return;
                }

                int result = memberDAO.join(memberDTO);
                if(result == -1){
                    JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다. 다시시도해주세요.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.");

                }

            }
        });
        add(joinButton);


    }
}