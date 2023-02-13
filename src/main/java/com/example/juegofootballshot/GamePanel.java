package com.example.juegofootballshot;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

// Collect the Money Bags!
public class GamePanel {
    Circle circle;
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
    Group root;
    Group obstacles;

    ScaleTransition scaleTransition2;

    private ProgressBar energyBar2;
    private Pane energyBarPane;
    private Line energyLine;
    private int energyCounter = 0;
    Scene theScene;
    Canvas canvas;
    Canvas canvasJug;
    Rectangle energyRect;
    GraphicsContext gc;
    GraphicsContext gc2;
    //Animation Variables
    Timeline animation;
    int NUM_SPRITES = 10;
    int index = 0;
    double posX = 480;
    double posY = 800;
    Image imageSprite = new Image("messiSprite2.png", false);
    double width = imageSprite.getWidth();
    double height = imageSprite.getHeight();
    boolean disparo;
    Molino molino;
    Circle circleM1;
    Circle circleM2;

    Sprite bullseye1;
    Sprite bullseye2;
    TranslateTransition translate ;
    Circle circle4;
    Circle circle5;
    double angle1 = 0;
    double angle2 = 90;
    double x1 = 860;
    double x2 = 860;
    boolean vidaMenys=false;
    //Velocitat molino
    double sKeyframeMolino;
    Timeline timelineCircle2;
    KeyFrame k=null;

    boolean molinoFinish = true;
    public void start(Stage theStage) throws InterruptedException {
        molino = new Molino();
        theStage.setTitle("Consigue Acertar!");
        circle = new Circle(450, 500, 16, new ImagePattern(new Image("soccerball.png")));
        circle2 = new Circle(0, 300, 16);
        circle2.setFill(Color.TRANSPARENT);
        Pane pane = new Pane(circle);
        Image earth = new Image("earth.png");
        disparo = false;


        // Barra de fuerza

        energyBar2 = new ProgressBar(100);
        energyBar2.setLayoutX(100);
        energyBar2.setLayoutY(480);
        energyBar2.setPrefWidth(200);
        energyBar2.getStylesheets().add(getClass().getResource("energybar.css").toExternalForm());


        root = new Group();
        Group root2 = new Group();
        theScene = new Scene(root);
        theStage.setScene(theScene);
        Image porteria = new Image("porteria.jpg");
//Creacio dels Canvas i del GraphicsContext

        canvas = new Canvas(900, 600);
        canvasJug = new Canvas(900, 600);
        gc2 = canvasJug.getGraphicsContext2D();

        //Transicio de la pilota que dura 10 milisegons
        scaleTransition3 = new ScaleTransition(Duration.millis(10), circle);


        // gc2.drawImage(new Image("pilota.png"),450,300);

        //Afegim al Grup els elements
        root.getChildren().add(canvas);
        root.getChildren().add(pane);
        root.getChildren().add(circle2);
        root.getChildren().add(canvasJug);
        canvasJug.toFront();
        powerBar();
        TranslateTransition tt = new TranslateTransition(Duration.seconds(8));
        tt.setNode(circle2);
        RotateTransition rt = new RotateTransition(Duration.INDEFINITE, circle2);
        tt.setFromX(0);
        tt.setToX(900);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);
       // startObstacle();
        obstacleMolino();
        // showTargetMolino();
        // molino.showTargetMolino(this);


// Array de botones pulsados
        ArrayList<String> input = new ArrayList<String>();

        theScene.setOnKeyPressed(evt -> {
            String code = evt.getCode().toString();
            if (!input.contains(code))
                //&& !input.equals(KeyCode.SPACE))
                input.add(code);

            if (evt.getCode() == KeyCode.SPACE) {

                circle2.setFill(Color.TRANSPARENT);
                circle2.setRadius(16);
                scoreInt += 1;
                increaseCounter();
            }


        });


        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.equals(KeyCode.SPACE))
                            input.remove(code);
                        if (e.getCode() == KeyCode.SPACE) {
                            if (disparo == true) {
                                System.out.println("disparo = true ");
                            } else {
                                disparo = true;
                                System.out.println(scoreInt + " score: Energycounter " + energyCounter);
                                input.remove(code);

                                showSprite(gc);
                               // checkforCollisionMolino();

                                //Temporizador para que la animacion llegue a la pelota
                                Timeline contador1 = new Timeline(new KeyFrame(
                                        Duration.millis(800),
                                        acción ->
                                                moveTo(briefcase.getPositionX(), briefcase.getPositionY(), 1)));
                                contador1.play();
                                //Temporizador para que la pelota llegue al objetivo
                                Timeline contador = new Timeline(new KeyFrame(
                                        Duration.seconds(2),
                                        acción ->
                                                checkforCollisionMolino()));

                              //  checkforCollision()));
                                contador.play();
                                //Temporizador para que la pelota llegue al objetivo Molino

                                           //     checkforCollisionMolino();
                                circle.setCenterX(450);
                                circle.setCenterY(500);

                                scaleTransition3.setToY(1.5);
                                scaleTransition3.setToX(1.5);
                                scaleTransition3.play();
                                disparo = false;

                            }
                        }

                    }
                });
        gc = canvas.getGraphicsContext2D();
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        Image messi = new Image("messi.png");

        Image image = new Image("objetivonaranja.png", 50, 50, false, false);
        briefcase = new Sprite();

        briefcase.setImage(image);
        briefcase.setPosition(450, 100);

        LongValue lastNanoTime = new LongValue(System.nanoTime());

        score = new IntValue(0);

        // Animation timer que mueve el punto de mira y acelera en una posicion concreta al mover el teclado
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;


                // game logic

                briefcase.setVelocity(0, 0);
                if (input.contains("LEFT"))
                    briefcase.addVelocity(-100, 0);


                if (input.contains("RIGHT"))
                    briefcase.addVelocity(100, 0);
                if (input.contains("UP"))
                    briefcase.addVelocity(0, -100);


                if (input.contains("DOWN"))
                    briefcase.addVelocity(0, 100);

                briefcase.update(elapsedTime);

                // collision detection


// renderizacion de los Graficos de la imagen de fondo y la porteria

                gc.clearRect(0, 0, 900, 600);
                gc.drawImage(porteria, 0, 0);
                briefcase.render(gc);
                String pointsText = "Puntos: !" + (100 * scoreInt);
                gc.fillText(pointsText, 360, 36);
                gc.strokeText(pointsText, 360, 36);
                gc2.save();
                gc2.scale(0.3, 0.3);
                gc2.drawImage(imageSprite, (index % NUM_SPRITES) * (width / NUM_SPRITES), 0, (width / NUM_SPRITES), height, posX, posY, (width / NUM_SPRITES), height);
                gc2.restore();

            }
        }.start();


        theStage.show();
        // Hacemos la pelota grande al comenzar
        scaleTransition3.setFromX(0.5);
        scaleTransition3.setFromY(0.5);
        scaleTransition3.setToX(1.5);
        scaleTransition3.setToY(1.5);
        scaleTransition3.play();

    }

    void checkforCollision() {

        //Defino el circulo para ponerlo en la posicion del punto de mira.

        circle2.setCenterX(briefcase.getPositionX() + briefcase.getBoundary().getWidth() / 2);
        circle2.setCenterY(briefcase.getPositionY() + briefcase.getBoundary().getHeight() / 2);

        if (circle2.intersects(circle3.getCenterX(), circle3.getCenterY(), circle3.getLayoutX(), circle3.getLayoutY())) {

            System.out.println("Collision");
            circle2.setRadius(32);
            circle2.setFill(new ImagePattern(new Image("explosion.png")));

            startObstacle();

        }


// etc..
    }

    void checkforCollisionMolino() {

       //Defino el circulo para ponerlo en la posicion del punto de mira.
      //  System.out.println("Diana1 position  Y "+ circle4.getCenterX()+" Y "+ circle4.getCenterY());
       // System.out.println(briefcase.getPositionX() + briefcase.getBoundary().getWidth() / 2);
        circle2.setCenterX(briefcase.getPositionX() + briefcase.getBoundary().getWidth() / 2);
        circle2.setCenterY(briefcase.getPositionY() + briefcase.getBoundary().getHeight() / 2);

        if (circle2.intersects(circle4.getCenterX(), circle4.getCenterY(), circle4.getLayoutX(), circle4.getLayoutY())) {

            System.out.println("Collision");
            //molino.circle1.setRadius(32);
            circle4.setFill(new ImagePattern(new Image("explosion.png")));
            circle4.setStroke(Color.BLACK);
            Timeline contador = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    acción ->
                            circle4.setFill(Color.TRANSPARENT)));
            contador.play();

        } else if (circle2.intersects(circle5.getCenterX(), circle5.getCenterY(), circle5.getLayoutX(), circle5.getLayoutY())) {
            System.out.println("Collision");
            // molino.circle2.setRadius(32);
            circle5.setFill(new ImagePattern(new Image("explosion.png")));
            circle5.setStroke(Color.BLACK);

            Timeline contador = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    acción ->

            circle5.setFill(Color.TRANSPARENT)));

            contador.play();

        }
        else if (molinoFinish==true) obstacleMolino();



// etc..
    }

    public void gameLoopTimeline(Circle gc) {
        Timeline gameLoop = new Timeline();
        double x = 0;
        double y = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        final long timeStart = System.currentTimeMillis();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),                // 60 FPS
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        double t = (System.currentTimeMillis() - timeStart) / 1000.0;

                        double x = 232 + 128 * Math.cos(t);
                        double y = 232 + 128 * Math.sin(t);

                    }
                });
        gc.setLayoutX(x);
        gc.setLayoutY(y);

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    public void moveTo(double x, double y, double duration) {
        if (disparo == false) {
            scaleTransition2 = new ScaleTransition(Duration.seconds(1), circle);
            scaleTransition2.setFromX(1.5);
            scaleTransition2.setFromY(1.5);
            scaleTransition2.setToY(0.3);
            scaleTransition2.setToX(0.3);
            scaleTransition2.play();
            KeyValue xValue = new KeyValue(circle.centerXProperty(), x);
            KeyValue yValue = new KeyValue(circle.centerYProperty(), y);
            KeyValue effect = new KeyValue(circle.centerYProperty(), y, new Interpolator() {
                @Override
                protected double curve(double t) {
                    double menost = 0.5;
                    if (briefcase.getPositionX() > 450) menost = 0.6;

                    // parabola with zeros at t=0 and t=1 and a maximum of 1 at t=0.5
                    return -4 * (t - menost) * (t - menost) + 1.6;
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

                        scaleTransition3.setToX(1.5);
                        scaleTransition3.setToY(1.5);
                        scaleTransition3.play();
                        circle.setCenterX(450);
                        circle.setCenterY(500);
                        scoreInt = 0;
                        energyCounter = -5;
                        increaseCounter();
                    }
            );

        }
    }

    public void startObstacle() {
        if (timelineCircle != null) timelineCircle.stop();
        if (circle3 != null) root.getChildren().remove(circle3);
        circle3 = new Circle(900, 250, 20);
        circle3.setFill(new ImagePattern(new Image("sun.png")));
        circle3.setManaged(true);

        double speed = 1;
        double radius = 1;
        //  angle=0;
      //  TranslateTransition translateTransition = new TranslateTransition(10,)

        timelineCircle = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            angle = (angle + speed) % 360;
            circle3.setCenterX(circle3.getCenterX() - speed);

            circle3.setCenterY(circle3.getCenterY() + radius * Math.cos(Math.toRadians(angle)));
            if (circle3.getCenterX() < 0) {
                System.out.println("Vida menys");
                circle3.setCenterX(900);
            }


        }));
        root.getChildren().add(circle3);


        // root.getChildren().add(rect);
        timelineCircle.setCycleCount(Timeline.INDEFINITE);

        timelineCircle.play();
    }

    public void powerBar() {
        Image zero = new Image("zero.png");
        Image hundred = new Image("hundredRed.png");

        Rectangle energyBarBackground = new Rectangle(200, 10);
        energyBarBackground.setFill(Color.LIGHTGRAY);
        energyBarBackground.setStroke(Color.DARKGRAY);
        energyBarBackground.setStrokeWidth(1);
        Rectangle zeroPower = new Rectangle(20, 30);
        Rectangle hundredPower = new Rectangle(40, 30);
        hundredPower.setFill(new ImagePattern(hundred));
        zeroPower.setFill(new ImagePattern(zero));
        hundredPower.setX(200);
        hundredPower.setY(-13);
        zeroPower.setX(-20);
        zeroPower.setY(-13);
        Rectangle greenBar = new Rectangle(20, 10);
        greenBar.setFill(Color.GREEN);

        Rectangle yellowBar = new Rectangle(20, 10);
        yellowBar.setFill(Color.YELLOW);

        Rectangle redBar = new Rectangle(70, 10);
        redBar.setFill(Color.RED);

        Rectangle yellowBar2 = new Rectangle(20, 10);
        yellowBar2.setFill(Color.YELLOW);

        Rectangle redBar2 = new Rectangle(70, 10);
        redBar2.setFill(Color.RED);

        energyRect = new Rectangle(5, 10);
        energyRect.setFill(Color.BLUE);


        HBox colorBars = new HBox(0);
        colorBars.getChildren().addAll(redBar, yellowBar, greenBar, yellowBar2, redBar2);

        energyBarPane = new Pane();
        energyBarPane.setPrefSize(200, 40);
        energyBarPane.getChildren().addAll(zeroPower, energyBarBackground, colorBars, energyRect, hundredPower);

        //   Button btnIncreaseCounter = new Button("Aumentar contador");
        // btnIncreaseCounter.setOnAction(e -> increaseCounter());

        root.getChildren().addAll(energyBarPane);
        //, btnIncreaseCounter);
        energyBarPane.setLayoutX(370);
        energyBarPane.setLayoutY(570);


    }

    public void showTargetMolino() {
        Group molino = new Group();
        Rectangle body = new Rectangle();
        body.setX(100.0f);
        body.setY(300.0f);
        body.setWidth(30);
        body.setHeight(95);
        body.setFill(Color.PURPLE);
        body.setStroke(Color.BLACK);

        // create the sails of the mill
        Rectangle sail1 = new Rectangle();
        sail1.setX(75);
        sail1.setY(300.0f);
        sail1.setWidth(80.0f);
        sail1.setHeight(5.0f);
        sail1.setFill(Color.RED);
        sail1.setStroke(Color.BLACK);
// create a group for the circles
        Group circles = new Group();

// create two circles
       circleM1 = new Circle(75, 300, 16);
       circleM2 = new Circle(155, 300, 16);
        bullseye1 = new Sprite();
        bullseye2= new Sprite();

// set the fill color for the circles
        circleM1.setFill(new ImagePattern(new Image("bullseye.png")));
        circleM2.setFill(new ImagePattern(new Image("bullseye.png")));
        bullseye1.setImage(new Image("bullseye.png"));
        bullseye2.setImage(new Image("bullseye.png"));
        bullseye1.setPosition(75,300);

// add the circles to the group
       // circles.getChildren().add(circleM1);
        circles.getChildren().add(bullseye1.getNodeRepresentation());
        circles.getChildren().add(circleM2);
        molino.getChildren().addAll(body, sail1, circles);

// add the group to the root

        RotateTransition rotate2 = new RotateTransition(Duration.seconds(4), sail1);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(Timeline.INDEFINITE);
        rotate2.setAutoReverse(false);


        // add the shapes to the group

        root.getChildren().add(molino);
        canvasJug.toFront();

        // create the rotation animation for the sails
        RotateTransition rotate1 = new RotateTransition(Duration.seconds(4), circles);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(Timeline.INDEFINITE);
        rotate1.setAutoReverse(false);


        // create the translation animation for the mill
         translate = new TranslateTransition(Duration.seconds(25), molino);
        translate.setFromX(900);
        translate.setToX(-200);
        translate.setCycleCount(Timeline.INDEFINITE);
        // add the shapes to the group


        // start the rotation and translation animations
        rotate1.play();
        rotate2.play();
        translate.play();
    }

    private void increaseCounter() {
        energyCounter = Math.min(200, energyCounter + 5);
        energyRect.setX(energyCounter);
    }

    public void showSprite(GraphicsContext gc) {
        if (animation != null) animation.stop();
        KeyFrame frame = new KeyFrame(Duration.millis(150), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // gc.clearRect(0, 0, 900, 600);
                gc2.clearRect(0, 0, 900, 600);
                gc2.drawImage(imageSprite, (index % NUM_SPRITES) * (width / NUM_SPRITES), 0, (width / NUM_SPRITES), height, posX, posY, (width / NUM_SPRITES), height);
                index++;
                if (index == NUM_SPRITES) {
                    index = 0;
                    animation.stop();
                }
            }
        });
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

    }

    private void startTimeline() {
 /*       Timeline animation2 = new Timeline();
        animation2.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gc.clearRect(300, 300, 300, 500);
                gc.drawImage(image, (index % NUM_SPRITES) * (width / NUM_SPRITES), 0, (width / NUM_SPRITES), height, posX, posY, (width / NUM_SPRITES), height);
                index++;
                if (index == NUM_SPRITES) {
                    index = 0;
                    animation.stop();

                }
            }
        });
        animation2.getKeyFrames().add(frame);

        animation2.play();

  */
        gc.drawImage(imageSprite, 300, 300, 50, 50);
    }
    public void obstacleMolino()  {
        vidaMenys=false;
      //  while (!molinoFinish)wait();
        molinoFinish=false;
        sKeyframeMolino=100;
        Rectangle body = new Rectangle();
        body.setX(845);
        body.setY(300.0f);
        body.setWidth(30);
        body.setHeight(95);
        body.setFill(Color.PURPLE);
        body.setStroke(Color.BLACK);
        Group molinoSprite = new Group();

        Circle sail1 = new Circle(860, 300, 30);
       // sail1.setLayoutX(850);
        //sail1.setLayoutY(300.0f);
     //   sail1.s(90.0f);
       // sail1.setHeight(5.0f);
        sail1.setFill(Color.RED);
        sail1.setStroke(Color.BLACK);

        // create the rotation animation for the sails
        RotateTransition rotate1 = new RotateTransition(Duration.seconds(8), sail1);
        rotate1.setByAngle(360);
        rotate1.setCycleCount(Timeline.INDEFINITE);
        rotate1.setAutoReverse(false);



        //if (timelineCircle != null) timelineCircle.stop();
       // if (circle3 != null) root.getChildren().remove(circle3);
        circle4 = new Circle(860, 250, 20);
        circle5 = new Circle(800, 250, 20);
        circle4.setFill(new ImagePattern(new Image("bullseye.png")));
        circle5.setFill(new ImagePattern(new Image("bullseye.png")));
        //circle4.setLayoutX(-40);
        circle4.setManaged(true);
        circle5.setManaged(true);

        double radius1 = 50;
        double radius2 = 50;

        double speed1 = 2;
        double speed2 = 2;

        //  angle=0;
                 k=new KeyFrame(Duration.millis(sKeyframeMolino), e -> {
                     angle1 = (angle1 + (speed1)) % 360;
                     angle2 = (angle2 + (speed2)) % 360;

                     x1 -= speed1;
                     x2 -= speed1;

                     circle4.setCenterX(circle4.getCenterX() - 1);
                     circle5.setCenterX(circle5.getCenterX() - 1);

                     body.setX(body.getX() - speed1);
                     sail1.setCenterX(sail1.getCenterX() - speed1);

                     circle4.setCenterX(x1 + radius2 * Math.cos(Math.toRadians(angle2)));
                     circle4.setCenterY(300 + radius2 * Math.sin(Math.toRadians(angle2)));
                     // sail1.setX(body.getX() * Math.cos(Math.toRadians(angle2)));
                     //sail1.setY(body.getY() * Math.sin(Math.toRadians(angle2)));

                     circle5.setCenterX(x2 + radius2 * Math.cos(Math.toRadians(angle1)));
                     circle5.setCenterY(300 + radius2 * Math.sin(Math.toRadians(angle1)));
           /*
            if (x1 < -100 || x2 < -100) {
                x1 = 860;
                x2 = 860;
                body.setX(850);
                sail1.setCenterX(860);
            }

            */
                     if (circle4.getCenterX() < -20) {
                         if (!vidaMenys) System.out.println("Vida menys");
                         vidaMenys = true;
                         circle4.setCenterX(860);
                     } else if (circle5.getCenterX() < -20) {
                         if (!vidaMenys) System.out.println("Vida menys");
                         vidaMenys = true;
                         circle5.setCenterX(860);
                     } else if (circle5.getFill() == Color.TRANSPARENT && circle4.getFill() == Color.TRANSPARENT) {
                         molinoFinish = true;

                         timelineCircle2.setRate(4);
                         System.out.println(sail1.getCenterX());

//                if (circle5.getCenterX() < 0 && circle4.getCenterX() < 0) {
                    /*
                    System.out.println(sail1.getCenterX());
                    timelineCircle2.stop();
                    timelineCircle2.setRate(1);
                    circle4.setFill(new ImagePattern(new Image("bullseye.png")));
                    circle5.setFill(new ImagePattern(new Image("bullseye.png")));
                    molinoFinish=true;


                     */
  /*
                    try {
                       // timelineCircle2.stop();

                        molinoFinish=true;

                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }


   */





            }
           molinoFinish=true;
        });
        root.getChildren().add(body);
        root.getChildren().add(sail1);
        root.getChildren().add(circle4);
        root.getChildren().add(circle5);
        //molinoSprite.getChildren().add(circle5);
        //root.getChildren().add(molinoSprite);
        canvasJug.toFront();
        timelineCircle2 = new Timeline(k);

        // root.getChildren().add(rect);
        timelineCircle2.setCycleCount(Timeline.INDEFINITE);
        timelineCircle2.play();
       // rotate1.play();

    }
}