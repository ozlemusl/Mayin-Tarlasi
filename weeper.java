package minesweeper;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class weeper implements ActionListener {

    JFrame frame = new JFrame("MayınTarlası");
    JButton reset = new JButton("Tekrar Oyna");
    JButton[][] box = new JButton[10][10];
    int[][] mayın = new int[10][10];
    Container grid = new Container();
    final int mayınlar = 10;

    public weeper() {
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());
        frame.add(reset, BorderLayout.NORTH);
        reset.addActionListener(this);
        grid.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                box[i][j] = new JButton();
                box[i][j].addActionListener(this);
                grid.add(box[i][j]);
            }
        }
        frame.add(grid, BorderLayout.CENTER);
        mayinata();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

   public static void main(String[] args) {
       new weeper();
    }

    public void mayinata() {
        ArrayList<Integer> diz = new ArrayList<>();
        for (int i = 0; i < mayın.length; i++) {
            for (int j = 0; j < mayın[0].length; j++) {
                diz.add(i * 100 + j);
            }
        }

        mayın = new int[10][10];
        for (int i = 0; i < 10; i++) {

            int bomp = (int) (Math.random() * diz.size());
            mayın[diz.get(bomp) / 100][diz.get(bomp) % 100] = mayınlar;
            diz.remove(bomp);

        }

        for (int i = 0; i < mayın.length; i++) {
            for (int j = 0; j < mayın[0].length; j++) {

                if (mayın[i][j] != mayınlar) {
                    int komsumayın = 0;
                    if (i > 0 && j > 0 && mayın[i - 1][j - 1] == mayınlar) {
                        komsumayın++;

                    }
                    if (j > 0 && mayın[i][j - 1] == mayınlar) {
                        komsumayın++;
                    }
                    if (i < mayın.length - 1 && j < mayın[0].length - 1 && mayın[i + 1][j + 1] == mayınlar) {
                        komsumayın++;
                    }
                    mayın[i][j] = komsumayın;
                }
            }
        }

    }

    public void theend() {
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                if (box[i][j].isEnabled()) {
                    if (mayın[i][j] != mayınlar) {
                        box[i][j].setText(mayın[i][j] + "");
                        box[i][j].setEnabled(false);
                    } else {
                        box[i][j].setText("X");
                        box[i][j].setEnabled(false);
                    }
                }
            }
        }
    }

    public void kontrol(ArrayList<Integer>sorun) {

        if (sorun.isEmpty()) {
           
            
        } else {
            int a = sorun.get(0) / 100;
            int b = sorun.get(0) % 100;
            sorun.remove(0);
            //sol tarafı kontrol ediyoruz
            if(mayın[a][b]==0){ // sıfırları kontrol ediyoruz
                if(a>0 && b>0){ // sıfır değilse komşuları kontrol ediyoruz
                    box[a-1][b-1].setText(mayın[a-1][b-1]+ "");
                    box[a-1][b-1].setEnabled(false);
                    if(mayın[a-1][b-1]==0){
                       
                        sorun.add((a-1)*100 +(b-1));
                    }
                    
                }
                // üst kısmı kontrol ediyoruz
                //iki yada üç bomba komşularını kontrol ediyoruz
                // a sabit kalıyor b değişiyor
                if (b>0){
                    box[a][b-1].setText(mayın[a][b-1]+ "");
                    box[a][b-1].setEnabled(false);
                     if(mayın[a][b-1]==0){
                       
                        sorun.add(a*100 +(b-1));
                    }
                }
                // a bir artma durumu
                // sağ taraf için
                if(a< mayın.length-1 && b>0 ){
                    box[a+1][b-1].setText(mayın[a+1][b-1]+ "");
                    box[a+1][b-1].setEnabled(false);
                    if(mayın[a+1][b-1]==0){
                       
                        sorun.add((a+1)*100 +(b-1));
                    }
                    
                }
                // sol taraf için b aynı kalıyor
                if(a>0 ){ 
                    box[a-1][b].setText(mayın[a-1][b]+ "");
                    box[a-1][b].setEnabled(false);
                    if(mayın[a-1][b]==0){
                       
                        sorun.add((a-1)*100 +b);
                    }
                    
                }
                
                
                // sağ taraf için
                if(a< mayın.length-1  ){
                    box[a+1][b].setText(mayın[a+1][b]+ "");
                    box[a+1][b].setEnabled(false);
                    if(mayın[a+1][b]==0){
                       
                        sorun.add((a+1)*100 +b);
                    }
                    
                }
                // solt alt köşe için b ve mayınlar karışaltırılması
                if(a>0 && b < mayın[0].length-1){ 
                    box[a-1][b+1].setText(mayın[a-1][b+1]+ "");
                    box[a-1][b+1].setEnabled(false);
                    if(mayın[a-1][b+1]==0){
                       
                        sorun.add((a-1)*100 +(b+1));
                    }
                    
                }
                
                // alt köşe için
                if (b <mayın[0].length-1 ){
                    box[a][b+1].setText(mayın[a][b+1]+ "");
                    box[a][b+1].setEnabled(false);
                     if(mayın[a][b+1]==0){
                       
                        sorun.add(a*100 +(b+1));
                    }
                }
               
                //alt  sağ köşe için
                if(a< mayın.length-1 && b <mayın[0].length-1 ){
                    box[a+1][b+1].setText(mayın[a+1][b+1]+ "");
                    box[a+1][b+1].setEnabled(false);
                    if(mayın[a+1][b+1]==0){
                       
                        sorun.add((a+1)*100 +(b+1));
                    }
                    
                }
            }
        kontrol(sorun);
        }
  
    }
    
    public void win(){
        boolean win =true;
        for(int i= 0; i<mayın.length;i++){
        for(int j=0 ;  j<mayın[0].length;j++){
            if(mayın[i][j]!=mayınlar && box[i][j].isEnabled()==true){
                win=false;}
            }
        }
        
        if(win==true){
            JOptionPane.showMessageDialog(frame,"Kazandın");
        }
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(reset)) {
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[0].length; j++) {
                    box[i][j].setEnabled(true);
                    box[i][j].setText("");
                }
            }

            mayinata();
        } else {
            for (int i = 0; i < box.length; i++) {
                for (int j = 0; j < box[0].length; j++) {
                    if (e.getSource().equals(box[i][j])) {
                        if (mayın[i][j] == mayınlar) {
                            box[i][j].setForeground(Color.DARK_GRAY);
                            box[i][j].setText("X");
                            theend();

                        } 
                        else if(mayın [i][j]==0) {
                        
                        box[i][j].setText(mayın[i][j]+"");
                        box[i][j].setEnabled(false);
                        ArrayList<Integer>sorun= new ArrayList <>();
                        sorun.add(i*100+j);
                        kontrol(sorun);
                        win();
                        
                    }
                        
                        else {
                            box[i][j].setText(mayın[i][j] + "");
                            box[i][j].setEnabled(false);
                            win();
                        }
                    }
                }
            }
        }
    }

}