package com.example.juegofootballshot;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

// Collect the Money Bags!
public class GamePanel
{    Circle circle ;
    boolean movingDown = true;
    Sprite briefcase;
   // Sprite sun;

    Circle circle2;
    ScaleTransition scaleTransition3;
    IntValue score;

    int scoreInt;
    double angle;
    Circle circle3;
    Timeline timelineCircle;

    public void start(Stage theStage) {
        theStage.setTitle("Consigue Acertar!");
        circle = new Circle(450, 500, 16);
        circle2 = new Circle(0, 300, 16);
        Pane pane2 = new Pane(circle2);
        Pane pane = new Pane(circle);
        Image earth = new Image("earth.png");
        circle.setFill(new ImagePattern(new Image("soccerball.png")));
    /*
        sun= new Sprite();
        sun.setImage("sun.png");


     */
        circle3 = new Circle(800, 250, 20);
        circle3.setFill(new ImagePattern(new Image("sun.png")));
        circle3.setManaged(false);
/*
        Rectangle rect = new Rectangle(10, 300, 50, 50);
        rect.setManaged(false);
        rect.setArcHeight(50);
        rect.setArcWidth(50);
        rect.setFill(Color.BLACK);


 */
        Group root = new Group();
        Group root2 = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        Image porteria = new Image("porteria.jpg");

        Canvas canvas = new Canvas(900, 600);
        root.getChildren().add(canvas);
        root.getChildren().add(pane);
        root.getChildren().add(circle2);
        TranslateTransition tt = new TranslateTransition(Duration.seconds(8));
        tt.setNode(circle2);
        RotateTransition rt = new RotateTransition(Duration.INDEFINITE, circle2);
        tt.setFromX(0);
        tt.setToX(900);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);


        ArrayList<String> input = new ArrayList<String>();

        theScene.setOnKeyPressed(evt -> {
            String code = evt.getCode().toString();
            if (!input.contains(code))
                //&& !input.equals(KeyCode.SPACE))
                input.add(code);

            if (evt.getCode() == KeyCode.SPACE) {
                scoreInt += 1;
            }


        });


        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.equals(KeyCode.SPACE))
                            input.remove(code);
                        if (e.getCode() == KeyCode.SPACE) {
                            input.remove(code);

                            ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), circle);
                            scaleTransition3 = new ScaleTransition(Duration.millis(10), circle);
                            scaleTransition2.setFromX(2);
                            scaleTransition2.setFromY(2);
                            scaleTransition2.setToY(0.3);
                            scaleTransition2.setToX(0.3);
                            // scaleTransition2.setToZ(-0.5);

                            // translateTransition.play();
                            // scaleTransition.stop();
                            scaleTransition2.play();

                            moveTo(briefcase.getPositionX()//+(briefcase.getBoundary().getHeight()/2)
                                    , briefcase.getPositionY(), 1);
                            //+(briefcase.getBoundary().getWidth()/2), 2);
                            //     if (circle.getCenterX() == briefcase.getPositionX() && circle.getCenterY() == briefcase.getPositionY())
                            Point2D targetLocation = circle2.localToParent(circle2.getRadius(), (circle2.getRadius() * 2));
                            Point2D ballLocation = circle.localToScene(circle.getRadius(), (circle.getRadius() * 2));
                            double circleX = circle2.getCenterX() + tt.getByX();
                            double circleY = circle2.getCenterY() + tt.getByY();


                            System.out.println("Posicion Bola: " + briefcase.getPositionX() + " x " + briefcase.getPositionY() + " Tarjet : " +  circle3.getCenterX() + " x " + circle3.getCenterY());
                            Timeline contador = new Timeline(new KeyFrame(
                                    Duration.seconds(1),
                                    acción ->
                                            checkforCollision()));
                            contador.play();
                            // scaleTransition2.stop();
                            circle.setCenterX(450);
                            circle.setCenterY(500);

                            scaleTransition2.setToY(2);
                            scaleTransition2.setToX(2);
                            scaleTransition2.play();


                        }
                    }
                });
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        Image messi = new Image("messi.png");
        Image image = new Image("objetivonaranja.png", 50, 50, false, false);
        briefcase = new Sprite();

        briefcase.setImage(image);
        briefcase.setPosition(450, 100);
        // sun.setPosition(100,200);


        LongValue lastNanoTime = new LongValue(System.nanoTime());

        score = new IntValue(0);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.1000000000.0
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;


                // game logic
                if (input.contains("SPACE")) {
                    // System.out.println(circle.getCenterX()+ " x "+ circle.getCenterY()+ " radius "+ circle.getRadius());

                } else {
                    briefcase.setVelocity(0, 0);
                    if (input.contains("LEFT"))
                        briefcase.addVelocity(-100, 0);
                    // System.out.println(briefcase.getBoundary().toString());

                    if (input.contains("RIGHT"))
                        briefcase.addVelocity(100, 0);
                    if (input.contains("UP"))
                        briefcase.addVelocity(0, -100);
                    //System.out.println(briefcase.getBoundary().toString());

                    if (input.contains("DOWN"))
                        briefcase.addVelocity(0, 100);

                    briefcase.update(elapsedTime);
                }
                // collision detection


                // render

                gc.clearRect(0, 0, 512, 512);
                gc.drawImage(porteria, 0, 0);
                // gc.drawImage(messi,450,450);
                //  sun.render(gc);
                briefcase.render(gc);
                //  gc.drawImage( earth,850,300,50,50 );
                String pointsText = "Puntos: !" + (100 * scoreInt);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);
            }
        }.start();
        //     tt.play();
/*
        //Timeline Rectangle


        final Duration SEC_2 = Duration.millis(8000);

        System.out.println("Location before relocation = " + rect.getX() + ","
                + rect.getY() + ")");

        Timeline timeline = new Timeline();

        KeyFrame end = new KeyFrame(SEC_2,
                new KeyValue(rect.xProperty(), 900),
                new KeyValue(rect.yProperty(), 300));

        timeline.getKeyFrames().add(end);
        final long timeStart = System.currentTimeMillis();

        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Location after relocation = " + rect.getX()
                        + "," + rect.getY() + ")");


            }
        });


 */
    //    for (int i = 0; i < 3; i++) {


        double speed = 1;
        double radius = 1;
        //  angle=0;
        timelineCircle = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            angle = (angle + speed) % 360;
            circle3.setCenterX(circle3.getCenterX() - speed);
            circle3.setCenterY(circle3.getCenterY() + radius * Math.cos(Math.toRadians(angle)));
        }));

        root.getChildren().add(circle3);

        // root.getChildren().add(rect);
        timelineCircle.setCycleCount(Timeline.INDEFINITE);

        timelineCircle.play();

     //   timeline.play();
        theStage.show();
    }
    void checkforCollision() {
/*
        if(circle2.getCenterY()==circle3.getCenterY()&&circle2.getCenterX()==circle3.getCenterX()){
          //  movingDown = !movingDown;
            // do something
           // timeline.stop();
          //  System.out.println("Collision");
        }

        else
        */
        circle2.setCenterX(briefcase.getPositionX()+ briefcase.getBoundary().getWidth()/2);
        circle2.setCenterY(briefcase.getPositionY()+briefcase.getBoundary().getHeight()/2);




         if(circle2.intersects(circle3.getCenterX(),circle3.getCenterY(),circle3.getLayoutX(),circle3.getLayoutY())){

            //movingDown = !movingDown;
            // do something
            System.out.println("Collision");
            timelineCircle.play();
        }



// etc..
    }
    public void gameLoopTimeline(Circle gc){
        Timeline gameLoop = new Timeline();
        double x =0;
        double y =0;
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent ae)
                    {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                         double x = 232 + 128 * Math.cos(t);
                         double y = 232 + 128 * Math.sin(t);

                    }
                });
        gc.setLayoutX(x);
        gc.setLayoutY(y);

        gameLoop.getKeyFrames().add( kf );
        gameLoop.play();
    }

    public void moveTo(double x, double y, double duration) {

        KeyValue xValue = new KeyValue(circle.centerXProperty(), x);
        KeyValue yValue = new KeyValue(circle.centerYProperty(), y);
        KeyValue effect =  new KeyValue(circle.centerYProperty(), y, new Interpolator() {
            @Override
            protected double curve(double t) {
                // parabola with zeros at t=0 and t=1 and a maximum of 1 at t=0.5
                return -4 * (t - 0.5) * (t - 0.5) + 1.6;
            }
        });

        KeyFrame frame = new KeyFrame(Duration.seconds(duration), xValue, effect);


        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);


        timeline.getKeyFrames().add(frame);


        timeline.play();
        timeline.setOnFinished(evtf -> {
                    scaleTransition3.setFromX(0.5);
                    scaleTransition3.setFromY(0.5);

                    scaleTransition3.setToX(2);
                    scaleTransition3.setToY(2);
                    scaleTransition3.play();
                    circle.setCenterX(450);
                    circle.setCenterY(500);
                    scoreInt=0;
                }
        );
    }
   public void startObstacle(){
        
   }
}