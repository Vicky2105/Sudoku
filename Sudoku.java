import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
class sudoko extends JFrame {
    static int mistakes=0;
    static String filePath="C:\\Users\\pilli\\Desktop\\forFun\\pop.wav";
class MyPanel extends JPanel implements ActionListener,KeyListener {
    Random r;
    String vis="";
    JButton but[][];
    Font font;
    String val="";
    int xind=0,yind=0;
    int a[][],ans[][];
    Clip clip;
    AudioInputStream audio;
    ArrayList<int [][]>answers;
    MyPanel() {
        answers=new ArrayList<>();
        int[][] b = {
    {4, 3, 5, 2, 6, 9, 7, 8, 1},
    {6, 8, 2, 5, 7, 1, 4, 9, 3},
    {1, 9, 7, 8, 3, 4, 5, 6, 2},
    {8, 2, 6, 1, 9, 5, 3, 4, 7},
    {3, 7, 4, 6, 8, 2, 9, 1, 5},
    {9, 5, 1, 7, 4, 3, 6, 2, 8},
    {5, 1, 9, 3, 2, 6, 8, 7, 4},
    {2, 4, 8, 9, 5, 7, 1, 3, 6},
    {7, 6, 3, 4, 1, 8, 2, 5, 9}
};
    answers.add(b);
    int[][] b1 = {
    {4, 3, 5, 2, 6, 9, 7, 8, 1},
    {6, 8, 2, 5, 7, 1, 4, 9, 3},
    {1, 9, 7, 8, 3, 4, 5, 6, 2},
    {8, 2, 6, 1, 9, 5, 3, 4, 7},
    {3, 7, 4, 6, 8, 2, 9, 1, 5},
    {9, 5, 1, 7, 4, 3, 6, 2, 8},
    {5, 1, 9, 3, 2, 6, 8, 7, 4},
    {2, 4, 8, 9, 5, 7, 1, 3, 6},
    {7, 6, 3, 4, 1, 8, 2, 5, 9}
};

int[][] b2 = {
    {5, 3, 4, 6, 7, 8, 9, 1, 2},
    {6, 7, 2, 1, 9, 5, 3, 4, 8},
    {1, 9, 8, 3, 4, 2, 5, 6, 7},
    {8, 5, 9, 7, 6, 1, 4, 2, 3},
    {4, 2, 6, 8, 5, 3, 7, 9, 1},
    {7, 1, 3, 9, 2, 4, 8, 5, 6},
    {9, 6, 1, 5, 3, 7, 2, 8, 4},
    {2, 8, 7, 4, 1, 9, 6, 3, 5},
    {3, 4, 5, 2, 8, 6, 1, 7, 9}
};
int[][] b3 = {
    {5, 1, 7, 6, 9, 8, 2, 3, 4},
    {2, 8, 9, 7, 3, 4, 6, 1, 5},
    {3, 4, 6, 2, 1, 5, 8, 9, 7},
    {6, 7, 2, 8, 5, 9, 1, 4, 3},
    {9, 3, 8, 1, 6, 2, 9, 7, 5},
    {4, 5, 1, 7, 3, 9, 5, 6, 8},
    {1, 2, 4, 3, 7, 6, 9, 5, 8},
    {8, 6, 5, 4, 1, 9, 7, 2, 3},
    {7, 9, 3, 5, 8, 1, 4, 2, 6}
};
int[][] b4 = {
    {1, 5, 8, 4, 8, 9, 3, 7, 6},
    {7, 3, 2, 5, 6, 7, 9, 4, 1},
    {6, 4, 9, 3, 2, 1, 2, 9, 5},
    {3, 8, 7, 1, 2, 4, 6, 5, 9},
    {5, 9, 1, 7, 5, 3, 4, 6, 8},
    {2, 4, 6, 8, 9, 5, 7, 1, 2},
    {9, 1, 4, 6, 7, 8, 3, 5, 2},
    {4, 2, 5, 9, 4, 6, 8, 3, 7},
    {8, 7, 3, 5, 1, 2, 9, 8, 4}
};
int[][] b5 = {
    {1, 2, 9, 3, 4, 8, 7, 6, 5},
    {8, 6, 4, 7, 6, 2, 9, 1, 3},
    {7, 1, 3, 8, 9, 5, 2, 4, 6},
    {6, 7, 8, 5, 2, 1, 3, 9, 4},
    {5, 9, 7, 4, 1, 3, 6, 2, 8},
    {4, 3, 2, 9, 5, 6, 8, 7, 1},
    {3, 8, 1, 6, 7, 9, 5, 2, 4},
    {7, 5, 9, 6, 3, 8, 4, 1, 2},
    {2, 4, 6, 1, 2, 4, 3, 7, 9}
};
        answers.add(b1);
        answers.add(b2);
        answers.add(b3);
        answers.add(b4);
        answers.add(b5);
        r=new Random();
        int aind=Math.abs(r.nextInt()%7);
        a=new int[9][9];
        a=answers.get(aind);
        ans=new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                ans[i][j]=a[i][j];
            }
        }
        for(int i=0;i<9;i++){
            int count=0;
            while(count<4){
                int x=Math.abs(r.nextInt()%9);
                if(a[i][x]!=0){
                a[i][x]=0;
                count++;
                }
            }
        }
        this.setSize(500,500);
        this.setLayout(new GridLayout(9,9));
        font = new Font("Arial",Font.BOLD,25);
        but=new JButton[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                but[i][j]=new JButton(a[i][j]==0?vis:String.valueOf(a[i][j]));
                but[i][j].addActionListener(this);
                but[i][j].addKeyListener(this);
                but[i][j].setFocusable(true);
                but[i][j].setFont(font);
                but[i][j].setForeground(Color.black);
                but[i][j].setBackground(Color.white);
                but[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,1)); 
                this.add(but[i][j]);
            }
        }
    }
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                but[i][j].setBackground(Color.white);
            }
        }
        // TODO Auto-generated method stub
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(e.getSource()==but[i][j]){
                    but[i][j].setBackground(new Color(89,89,89));
                    for(int k=0;k<9;k++){
                        but[i][k].setBackground(new Color(89,89,89));
                    }
                    for(int l=0;l<9;l++){
                        but[l][j].setBackground(new Color(89,89,89));
                    }
                    xind=i;
                    yind=j;
                }
            }
        }
    }
    public void keyPressed(KeyEvent k){
        String comp=but[xind][yind].getText();
        if(comp.equals(vis)){
            val=String.valueOf(k.getKeyChar());
            but[xind][yind].setFont(font);
            if(!val.equals(String.valueOf(ans[xind][yind]))){
                but[xind][yind].setText(val);
                but[xind][yind].setForeground(Color.black);
                but[xind][yind].setBackground(Color.red);
                mistakes++;
                mis.setText("Mistakes: "+mistakes+"/3");
            }
            else{
                but[xind][yind].setText(val);
                but[xind][yind].setForeground(Color.black);
            }
        }
        if(mistakes==3){
            JOptionPane.showMessageDialog(this,"Game Over");
            //clip.start();
            this.setVisible(false);
        }
    }
    public void keyReleased(KeyEvent k){
        //val=k.getKeyChar();
    }
    public void keyTyped(KeyEvent k){
        //val=k.getKeyChar();
    }
}
    JPanel panel;
    JButton but[][];
    JLabel head,mis;
    Font font;
    MyPanel p;
    sudoko(){
        this.setSize(1550,840);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SUDOKU");
        this.setLayout(null);
        
        p=new MyPanel();
        p.setBounds(500,175,500,500);
        font = new Font("Arial",Font.BOLD,35);
        head=new JLabel("SUDOKU");
        head.setFont(font);
        head.setBounds(675,100,200,50);
        font = new Font("Arial",Font.BOLD,20);
        mis=new JLabel("Mistakes: "+mistakes+"/3");
        mis.setFont(font);
        mis.setBounds(875,150,150,25);
        this.add(head);
        this.add(mis);
        this.add(p,BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    
    public static void main(String []args){
        new sudoko();
    }
    
}
