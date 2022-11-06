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

public class Menu extends JFrame implements ActionListener {
    //    public static void main(String[] args) throws IOException {
//        p1 p = new p1();
////        JFrame = new JFrame("Menu");
//////        jFrame.setSize(1280, 721);
//////        //BufferedImage image = ImageIO.read("C:\\Users\\Asus\\Downloads\\Game_Bomberman.zip\\JavaGame_BomberMan-main\\res\\menu.png");
//////        jFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Asus\\Downloads\\Game_Bomberman.zip\\JavaGame_BomberMan-main\\res\\menu.png")))));
//////        jFrame.setLocationRelativeTo(null);
//////        jFrame.setVisible(true);
//    }
    public Menu() {

    }
    public void p() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);//chieu rong va chieu cao cua frame
        setVisible(true);
        setLocation(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);//cho mo rong frame bang chuot.true= cho phep.false khoa
        JLabel label = new JLabel();
        add(label);
        label.setSize(600, 500);
        System.out.println("x : " + label.getSize().width + "y : " + label.getSize().height);
        setPicture(label, "res/menu.png");
        final String[] out = {""};
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();

                if (x >=0 && x<= label.getWidth() && y >=0 && y <= label.getHeight()) {
                    System.out.println(x);
                    System.out.println(y);
                    if (x >= 250 && x <= 355 && y >= 257 && y <= 290) {
                        out[0] = "PLAY";
                        setVisible(false);
                        Application.launch(BombermanGame.class);
                    } else if (x >= 254 && x <= 355 && y >= 343 && y <= 373) {
                        out[0] = "EXIT";
                        System.exit(0);
                    }
                }
            }
        });
//        if (out[0].equals("PLAY")) {
//            return true;
//        } else if (out[0].equals("EXIT")){
//            return false;
//        }
//        return false;
    }

//        public boolean set(String a) {
//        if (a.equals("PLAY")) {
//            return true;
//        } else if (a.equals("EXIT")){
//            return false;
//        }
//        return false;
//        }

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
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
