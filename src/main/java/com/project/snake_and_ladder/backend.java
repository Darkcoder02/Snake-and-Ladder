package com.project.snake_and_ladder;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

interface player {
    void score_update(int num);
}
class player1 implements player {
    protected int score=0;
    protected String state;
    player1(){
        this.state = "close";
    }
    @Override
    public void score_update(int dice_num){
        score+=dice_num;
    }
    public void score_update2(int num){score=num;}
    public String get_state(){
        return state;
    }
    public void state_update(String state){
        this.state = state;
    }
    public int get_score(){
        return score;
    }
}

class player2  implements player {
    protected int score=0;
    protected String state;
    player2(){
        this.state = "close";
    }
    @Override
    public void score_update(int dice_num){
        score+=dice_num;
    }
    public void score_update2(int num){score=num;}
    public String get_state(){
        return state;
    }
    public void state_update(String state){
        this.state = state;
    }
    public int get_score(){
        return score;
    }
}

abstract class board{
    protected double trgx[]=new double[]{52.0,110.0,152.0,205.0,259.0,301.0,360.0,408.0,459.0,512.0,512.0,459.0,408.0,360.0,301.0,259.0,205.0,152.0,110.0,52.0,52.0,110.0,152.0,205.0,259.0,301.0,360.0,408.0,459.0,512.0,512.0,459.0,408.0,360.0,301.0,259.0,205.0,152.0,110.0,52.0,52.0,110.0,152.0,205.0,259.0,301.0,360.0,408.0,459.0,512.0,512.0,459.0,408.0,360.0,301.0,259.0,205.0,152.0,110.0,52.0,52.0,110.0,152.0,205.0,259.0,301.0,360.0,408.0,459.0,512.0,512.0,459.0,408.0,360.0,301.0,259.0,205.0,152.0,110.0,52.0,52.0,110.0,152.0,205.0,259.0,301.0,360.0,408.0,459.0,512.0,512.0,459.0,408.0,360.0,301.0,259.0,205.0,152.0,110.0,52.0};
    protected double trgy[]=new double[]{477.0,477.0,477.0,477.0,477.0,477.0,477.0,477.0,477.0,477.0,428.0,428.0,428.0,428.0,428.0,428.0,428.0,428.0,428.0,428.0,377.0,377.0,377.0,377.0,377.0,377.0,377.0,377.0,377.0,377.0,325.0,325.0,325.0,325.0,325.0,325.0,325.0,325.0,325.0,325.0,274.0,274.0,274.0,274.0,274.0,274.0,274.0,274.0,274.0,274.0,226.0,226.0,226.0,226.0,226.0,226.0,226.0,226.0,226.0,226.0,174.0,174.0,174.0,174.0,174.0,174.0,174.0,174.0,174.0,174.0,124.0,124.0,124.0,124.0,124.0,124.0,124.0,124.0,124.0,124.0,72.0,72.0,72.0,72.0,72.0,72.0,72.0,72.0,72.0,72.0,18.0,18.0,18.0,18.0,18.0,18.0,18.0,18.0,18.0,18.0};
    protected int snake_start[] = new int[]{15,48,45,61,63,73,88,91,94,98};
    protected int snake_end[] = new int[]{5,10,24,18,59,52,67,87,74,79};
    protected int ladder_start[] = new int[]{1,6,7,14,20,27,35,50,70,77,86};
    protected int ladder_end[] = new int[]{37,13,30,25,41,83,43,66,90,97,93};
}
class red_thread extends board implements Runnable{
    private double start_pt_x,start_pt_y,end_pt_x,end_pt_y;
    private double ingx,ingy;
    private Button button;
    private player1 p1;
    red_thread(){}
    red_thread(double start_pt_x,double start_pt_y,double end_pt_x,double end_pt_y,double ingx,double ingy,Button button,player1 p1){
        this.start_pt_x = start_pt_x;
        this.start_pt_y = start_pt_y;
        this.end_pt_x = end_pt_x;
        this.end_pt_y = end_pt_y;
        this.ingx = ingx;
        this.ingy = ingy;
        this.button = button;
        this.p1 = p1;
    }

public void follow_ladder(){
    Line line = new Line();
    line.setStartX((start_pt_x+15)-ingx);
    line.setStartY((start_pt_y+15)-ingy);
    line.setEndX((end_pt_x+15)-ingx);
    line.setEndY((end_pt_y+15)-ingy);
    PathTransition pt = new PathTransition();
    pt.setNode(button);
    pt.setDuration(Duration.seconds(1));
    pt.setPath(line);
    pt.play();
    return;
}

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        follow_ladder();
    }
}
class green_thread extends board implements Runnable{
    private double start_pt_x,start_pt_y,end_pt_x,end_pt_y;
    private double inrx,inry;
    private Button button;
    private player2 p2;
    green_thread(){}
    green_thread(double start_pt_x,double start_pt_y,double end_pt_x,double end_pt_y,double inrx,double inry,Button button,player2 p2){
        this.start_pt_x = start_pt_x;
        this.start_pt_y = start_pt_y;
        this.end_pt_x = end_pt_x;
        this.end_pt_y = end_pt_y;
        this.inrx = inrx;
        this.inry = inry;
        this.button = button;
        this.p2 = p2;
    }

    public void follow_ladder(){
        Line line = new Line();
        line.setStartX((start_pt_x+15)-inrx);
        line.setStartY((start_pt_y+15)-inry);
        line.setEndX((end_pt_x+15)-inrx);
        line.setEndY((end_pt_y+15)-inry);
        PathTransition pt = new PathTransition();
        pt.setNode(button);
        pt.setDuration(Duration.seconds(1));
        pt.setPath(line);
        pt.play();
        return;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        follow_ladder();
    }
}
class avatar2 implements Runnable{
    private ImageView image;

    avatar2(ImageView image){
        this.image = image;
    }
    @Override
    public void run() {
            FadeTransition fade = new FadeTransition();
            fade.setNode(image);
            fade.setDuration(Duration.millis(2000));
            fade.setCycleCount(2);
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setAutoReverse(true);
            fade.play();

    }
}

public class backend implements Runnable{
    private ImageView image;

    backend(ImageView image){
        this.image = image;
    }
    @Override
    public void run() {
        FadeTransition fade = new FadeTransition();
        fade.setNode(image);
        fade.setDuration(Duration.millis(2000));
        fade.setCycleCount(2);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setAutoReverse(true);
        fade.play();
    }
}