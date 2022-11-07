package group6;

import javafx.application.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameOver extends JFrame implements ActionListener {
    //    public static void main(String[] args) throws IOException {
//        p1 p = new p1();
////        JFrame = new JFrame("Menu");
//////        jFrame.setSize(1280, 721);
//////        //BufferedImage image = ImageIO.read("C:\\Users\\Asus\\Downloads\\Game_Bomberman.zip\\JavaGame_BomberMan-main\\res\\menu.png");
//////        jFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Asus\\Downloads\\Game_Bomberman.zip\\JavaGame_BomberMan-main\\res\\menu.png")))));
//////        jFrame.setLocationRelativeTo(null);
//////        jFrame.setVisible(true);
//    }
    public GameOver() {

    }
    public void p() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 373);//chieu rong va chieu cao cua frame
        setVisible(true);
        setLocation(500, 373);
        setLocationRelativeTo(null);
        setResizable(false);//cho mo rong frame bang chuot.true= cho phep.false khoa
        JLabel label = new JLabel();
        add(label);
        label.setSize(500, 373);
        System.out.println("x : " + label.getSize().width + "y : " + label.getSize().height);
        setPicture(label, "res/gameOver.png");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();

                if (x >= 0 && x <= label.getWidth() && y >= 0 && y <= label.getHeight()) {
                    System.out.println(x);
                    System.out.println(y);
                    if (x >= 14 && x <= 199 && y >= 302 && y <= 324) {
                        setVisible(false);
                        Application.launch(BombermanGame.class);
                    } else if (x >= 301 && x <= 478 && y >= 300 && y <= 322) {
                        System.exit(0);
                    }
                }
            }
        });
    }
    public void setPicture (JLabel label, String filename ){
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            int x = label.getSize().width;
            int y = label.getSize().height;
            int ix = image.getWidth();
            int iy = image.getHeight();

            int dx = 0;
            int dy = 0;
            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            label.setIcon(icon);
        } catch (IOException ex) {
            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}