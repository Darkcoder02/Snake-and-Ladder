package com.project.snake_and_ladder;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class HelloController implements Initializable {
    private static double inrx,inry, ingx,ingy;
    protected player1 p1 = new player1();
    protected player2 p2 = new player2();
    private int c=-1,block=0;
    protected red_thread b = new red_thread();
    protected green_thread b1 = new green_thread();
    private Thread avatar_thread;
    private Thread avatar2;
    private backend ba;
    private avatar2 av;
    @FXML
    protected Label ps1,ps2,winner;
    @FXML
    private Button red,green;
    @FXML
    protected ImageView board,dice,play1,play2,game_over;


    public void move_red(){
        if(p2.get_score()!=0){
            red.setTranslateX((b.trgx[p2.get_score() - 1] + 10) - inrx);
            red.setTranslateY(b.trgy[p2.get_score() - 1] - inry);
        }

    }
    public void move_green(){
        if(p1.get_score()!=0){
            green.setTranslateX((b.trgx[p1.get_score() - 1] - 10) - ingx);
            green.setTranslateY((b.trgy[p1.get_score() - 1]) - ingy);
        }
    }


    @FXML
    public void dice_roll() {
        if(block==0){
            c++;
            RotateTransition rotate = new RotateTransition();
            rotate.setNode(dice);
            rotate.setDuration(Duration.seconds(1));
            rotate.setByAngle(360);
            rotate.play();
            int randomNum = ThreadLocalRandom.current().nextInt(1, (6 + 1));
            if (randomNum == 1) {
                Image img = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/dice_face/dice1.png");
                dice.setImage(img);
            }
            if (randomNum == 2) {
                Image img = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/dice_face/dice2.png");
                dice.setImage(img);
            }
            if (randomNum == 3) {
                Image img = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/dice_face/dice3.png");
                dice.setImage(img);
            }
            if (randomNum == 4) {
                Image img = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/dice_face/dice4.png");
                dice.setImage(img);
            }
            if (randomNum == 5) {
                Image img = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/dice_face/dice5.png");
                dice.setImage(img);
            }
            if (randomNum == 6) {
                Image img = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/dice_face/dice6.png");
                dice.setImage(img);
            }
            if (c % 2 == 0) {
                avatar2 = new Thread(av, "avatar2");
                avatar2.start();
                int flag = 0;
                if (p1.get_state().equals("close") && randomNum == 1) {
                    p1.score_update(randomNum);
                    green.setTranslateX((b.trgx[p1.get_score() - 1] + 10) - ingx);
                    green.setTranslateY(b.trgy[p1.get_score() - 1] - ingy);
                    ps1.setText("Score: " + p1.get_score());
                    p1.state_update("open");
                } else if (p1.get_state().equals("open")) {
                    if ((p1.get_score() + randomNum) <= 100) {
                        p1.score_update(randomNum);
                        green.setTranslateX((b.trgx[p1.get_score() - 1] + 5) - ingx);
                        green.setTranslateY(b.trgy[p1.get_score() - 1] - ingy);
                        for (int i = 0; i < 11; i++) {
                            if (p1.get_score() == (b.ladder_start[i] + 1)) {
                                int sp = b.ladder_start[i];
                                int ep = b.ladder_end[i];
                                Thread thread = new Thread(new red_thread(b.trgx[sp], b.trgy[sp], b.trgx[ep], b.trgy[ep], ingx, ingy, green, p1), "mythread");
                                thread.start();
                                p1.score_update2((b.ladder_end[i] + 1));
                                ps1.setText("Score: " + p1.get_score());
                                flag = 1;
                                break;
                            } else if (i < 10) {
                                if (p1.get_score() == (b.snake_start[i] + 1)) {
                                    int sp = b.snake_start[i];
                                    int ep = b.snake_end[i];
                                    Thread thread = new Thread(new red_thread(b.trgx[sp], b.trgy[sp], b.trgx[ep], b.trgy[ep], ingx, ingy, green, p1), "mythread");
                                    thread.start();
                                    p1.score_update2((b.snake_end[i] + 1));
                                    ps1.setText("Score: " + p1.get_score());
                                    flag = 1;
                                    break;
                                }
                            }
                        }
                        if (flag == 0) {
                            ps1.setText("Score: " + p1.get_score());
                        }
                        winner();
                    }
                }
            } else {
                avatar_thread = new Thread(ba, "backend");
                avatar_thread.start();
                int flag = 0;
                if (p2.get_state().equals("close") && randomNum == 1) {
                    p2.score_update(randomNum);
                    red.setTranslateX((b1.trgx[p2.get_score() - 1] + 10) - inrx);
                    red.setTranslateY(b1.trgy[p2.get_score() - 1] - inry);
                    ps2.setText("Score: " + p2.get_score());
                    p2.state_update("open");
                } else if (p2.get_state().equals("open")) {
                    if ((p2.get_score() + randomNum) <= 100) {
                        p2.score_update(randomNum);
                        red.setTranslateX((b1.trgx[p2.get_score() - 1] - 5) - inrx);
                        red.setTranslateY(b1.trgy[p2.get_score() - 1] - inry);
                        for (int i = 0; i < 11; i++) {
                            if (p2.get_score() == (b1.ladder_start[i] + 1)) {
                                int sp = b1.ladder_start[i];
                                int ep = b1.ladder_end[i];
                                Thread thread = new Thread(new green_thread(b1.trgx[sp], b1.trgy[sp], b1.trgx[ep], b1.trgy[ep], inrx, inry, red, p2), "mythread");
                                thread.start();
                                p2.score_update2((b.ladder_end[i] + 1));
                                ps2.setText("Score: " + p2.get_score());
                                flag = 1;
                                break;
                            } else if (i < 10) {
                                if (p2.get_score() == (b1.snake_start[i] + 1)) {
                                    int sp = b1.snake_start[i];
                                    int ep = b1.snake_end[i];
                                    Thread thread = new Thread(new green_thread(b1.trgx[sp], b1.trgy[sp], b1.trgx[ep], b1.trgy[ep], inrx, inry, red, p2), "mythread");
                                    thread.start();
                                    p2.score_update2((b1.snake_end[i] + 1));
                                    ps2.setText("Score: " + p2.get_score());
                                    flag = 1;
                                    break;
                                }
                            }
                        }
                        if (flag == 0) {
                            ps2.setText("Score: " + p2.get_score());
                        }
                        winner();
                    }
                }
            }
        }
        else{
            Image img = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/images/game_over.jpeg");
            game_over.setImage(img);
            FadeTransition fade = new FadeTransition();
            fade.setNode(game_over);
            fade.setDuration(Duration.millis(5000));
            fade.setCycleCount(5);
            fade.setInterpolator(Interpolator.LINEAR);
            fade.setFromValue(0.95);
            fade.setToValue(0);
            fade.setAutoReverse(true);
            fade.play();
        }
    }


    public void winner(){
        if(p1.get_score()==100){
            winner.setText("PLAYER 1 IS THE WINNER");
            block=1;
        }
        else if(p2.get_score()==100){
            winner.setText("PLAYER 2 IS THE WINNER");
            block=1;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //game board
        Image mat = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/images/SnL_Board.png");
        board.setImage(mat);

        //game pieces
        Image g = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/images/green.png");
        ImageView gre = new ImageView(g);
        green.setGraphic(gre);
        green.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Image r = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/images/red.png");
        ImageView re = new ImageView(r);
        red.setGraphic(re);
        red.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        //Initial Position of the Pieces
        Bounds br = red.localToScene(red.getBoundsInLocal());
        inrx = br.getMinX();
        inry = br.getMinY();

        Bounds bgr = green.localToScene(green.getBoundsInLocal());
        ingx = bgr.getMinX();
        ingy = bgr.getMinY();


        //dice
        Image roller = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/images/dice.png");
        dice.setImage(roller);

        //Player avatar
        Image p1 = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/images/avatar1.png");
        play1.setImage(p1);
        Image p2 = new Image("file:/Users/ayush/Desktop/Snake_and_Ladder/src/images/avatar2.png");
        play2.setImage(p2);
        ba = new backend(play2);
        av = new avatar2(play1);

        //initial chance
        FadeTransition fade = new FadeTransition();
        fade.setNode(play2);
        fade.setDuration(Duration.millis(2000));
        fade.setCycleCount(2);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setAutoReverse(true);
        fade.play();
    }
}