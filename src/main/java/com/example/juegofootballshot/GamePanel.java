package com.example.juegofootballshot;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontPosture;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;

// Collect the Money Bags!
public class GamePanel {
    Circle circle;
    boolean movingDown = true;
    Sprite briefcase;
    // Sprite sun;

    Circle circle2;
    ScaleTransition scaleTransition3;
    private IntValue score;
    int scoreInt;
    double angle;
    Circle circle3;
    Timeline timelineCircle;
    Group root;

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
    public static double speed1 = 1;
    static int puntos;
    boolean disparo;
    //Obstaculos Molino
    Circle circle4;
    Circle circle5;
    Circle obstacle3;
    double angle1;
    double angle2;
    double angle3;
    double x1;
    double x2;
    boolean vidaMenys = false;
    //Velocitat molino
    double sKeyframeMolino = 200;
    Timeline timelineCircle2 = new Timeline();
    KeyFrame k = null;
    boolean molinoFinish = true;

    double positionXb;
    double positionYb;

    int vidas = 3;
    boolean pressed = false;
    private String s = getClass().getClassLoader().getResource("shot-.mp3").toExternalForm();
    private String s2 = getClass().getClassLoader().getResource("hitSound.mp3").toExternalForm();

    private Media sound = new Media(s);
    private Media sound2 = new Media(s2);

    private MediaPlayer shotSound = new MediaPlayer(sound);

    private MediaPlayer audioClip2 = new MediaPlayer(sound2);
    private boolean hitMolino;
    private Rectangle rectangle;
    private Rectangle pressStart;
    private int level = 0;
    Stage theStageGamepanel;
    MainApplication mainApplication;


    public void setLevel(int level) {
        this.level = level;
    }

    public void start(Stage theStage) throws InterruptedException {
        puntos = 0;
        theStageGamepanel = theStage;
        theStage.setTitle("Consigue Acertar!");
        circle = new Circle(450, 500, 16, new ImagePattern(new Image("soccerball.png")));
        circle2 = new Circle(0, 300, 16);
        circle2.setFill(Color.TRANSPARENT);
        Pane pane = new Pane(circle);
        disparo = false;

        // Barra de fuerza

        energyBar2 = new ProgressBar(100);
        energyBar2.setLayoutX(100);
        energyBar2.setLayoutY(480);
        energyBar2.setPrefWidth(200);
        energyBar2.getStylesheets().add(getClass().getResource("energybar.css").toExternalForm());

// Afegim la escena al grup root
        root = new Group();
        theScene = new Scene(root);
        theStage.setScene(theScene);

        //Creacio dels Canvas i del GraphicsContext

        Image porteria = new Image("porteria.jpg");
        canvas = new Canvas(900, 600);
        canvasJug = new Canvas(900, 600);
        gc2 = canvasJug.getGraphicsContext2D();

        //Transicio de la pilota que dura 10 milisegons
        scaleTransition3 = new ScaleTransition(Duration.millis(10), circle);
        rectangle = new Rectangle(200, 100, 500, 300);
        rectangle.setFill(new ImagePattern(new Image("LevelOne.png")));
        pressStart = new Rectangle(250, 400, 400, 100);
        pressStart.setFill(new ImagePattern(new Image("spaceStart.png")));


        //Afegim al Grup els elements
        root.getChildren().add(canvas);
        root.getChildren().add(pane);
        root.getChildren().add(circle2);
        root.getChildren().add(canvasJug);
        root.getChildren().add(rectangle);
        root.getChildren().add(pressStart);

        canvasJug.toFront();
        powerBar();
        TranslateTransition tt = new TranslateTransition(Duration.seconds(8));
        tt.setNode(circle2);
        RotateTransition rt = new RotateTransition(Duration.INDEFINITE, circle2);
        tt.setFromX(0);
        tt.setToX(900);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);

// Array de botones pulsados
        ArrayList<String> input = new ArrayList<String>();

        theScene.setOnKeyPressed(evt -> {
            String code = evt.getCode().toString();


            if (!input.contains(code)) {
                if (!disparo) input.add(code);
                else pressed = true;
            }
            if (evt.getCode() == KeyCode.SPACE) {
                circle2.setFill(Color.TRANSPARENT);
                circle2.setRadius(16);
                scoreInt += 1;
                increaseCounter();
                disparo = true;

            }
        });


        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        if (!input.equals(KeyCode.SPACE)) {
                            input.remove(code);
                        }
                        if (e.getCode() == KeyCode.SPACE) {
                            if (molinoFinish == false) {

                                System.out.println(scoreInt + " score: Energycounter " + energyCounter);
                                input.remove(code);
                                positionXb = briefcase.getPositionX();
                                positionYb = briefcase.getPositionY();
                                // if (scoreInt>15&&scoreInt<30) {
                                if (scoreInt < 15) positionYb = 450;
                                else if (scoreInt > 26) positionYb = 100;
                                else if (pressed) positionXb = 900;


                                showSprite(gc);


                                //Temporizador para que la animacion llegue a la pelota

                                Timeline contador1 = new Timeline(new KeyFrame(
                                        Duration.millis(800),
                                        acción ->
                                                moveTo(positionXb, positionYb, 1)));
                                contador1.play();

                                Timeline contador2 = new Timeline(new KeyFrame(
                                        Duration.millis(600),
                                        acción ->
                                                shotSound.play()));
                                contador2.play();
                                shotSound.stop();

                                //Temporizador para que la pelota llegue al objetivo
                                Timeline contador = new Timeline(new KeyFrame(
                                        Duration.seconds(2),
                                        acción ->
                                                checkforCollisionMolino()));

                                contador.play();

                                //     Ponemos la pelota en posicion después de la animacion
                                circle.setCenterX(450);
                                circle.setCenterY(500);

                                scaleTransition3.setToY(1.5);
                                scaleTransition3.setToX(1.5);
                                scaleTransition3.play();
                                disparo = false;
                                pressed = false;
                            } else {
                                if (circle4 == null) {
                                    if (!vidaMenys) {
                                        level += 1;
                                        sKeyframeMolino -= 30;
                                    }
                                    vidaMenys = false;
                                    disparo = false;
                                    rectangle.setFill(Color.TRANSPARENT);
                                    pressStart.setFill(Color.TRANSPARENT);

                                    obstacleMolino();
                                }
                            }
                        }

                    }
                });
        gc = canvas.getGraphicsContext2D();
        Font theFont = Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 46);
        Font theFont2 = Font.font("Comic Sans MS", FontWeight.EXTRA_LIGHT, FontPosture.ITALIC, 34);

        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc2.setFont(theFont2);
        gc2.setFill(Color.ORANGE);
        gc2.setStroke(Color.RED);
        gc2.setLineWidth(1);
        Image messi = new Image("messi.png");

        Image image = new Image("objetivonaranja.png", 50, 50, false, false);
        briefcase = new Sprite();

        briefcase.setImage(image);
        briefcase.setPosition(450, 100);

        LongValue lastNanoTime = new LongValue(System.nanoTime());

        // Animation timer que mueve el punto de mira y acelera en una posicion concreta al mover el teclado
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;


                // game logic
                briefcase.setVelocity(0, 0);

                if (disparo != true) {
                    if (input.contains("LEFT"))
                        briefcase.addVelocity(-100, 0);

                    if (input.contains("RIGHT"))
                        briefcase.addVelocity(100, 0);

                    if (input.contains("UP"))
                        briefcase.addVelocity(0, -100);

                    if (input.contains("DOWN"))
                        briefcase.addVelocity(0, 100);

                } else {

                }
                briefcase.update(elapsedTime);

                // collision detection


// renderizacion de los Graficos de la imagen de fondo y la porteria

                gc.clearRect(0, 0, 900, 600);
                gc2.clearRect(0, 0, 900, 600);

                gc.drawImage(porteria, 0, 0);
                briefcase.render(gc2);
                String pointsText = "Points:" + (puntos);
                gc.fillText(pointsText, 360, 42);
                gc.strokeText(pointsText, 361, 42);
                String vidasText = "Vidas: " + (vidas);
                gc2.fillText(vidasText, 60, 36);
                gc2.strokeText(vidasText, 61, 36);
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


    //Metodo Para las colisiones


    public void checkforCollisionMolino() {

        //Defino el circulo para ponerlo en la posicion del punto de mira.
        circle2.setCenterX(positionXb + briefcase.getBoundary().getWidth() / 2);
        circle2.setCenterY(positionYb + briefcase.getBoundary().getHeight() / 2);
       // hitMolino = false;
        if (circle2.intersects(circle4.getCenterX(), circle4.getCenterY(), circle4.getLayoutX(), circle4.getLayoutY())&&circle4.getFill()!=Color.TRANSPARENT) {
            hitMolino = true;
            System.out.println("Collision");
            System.out.println(hitMolino);
            puntos += 100;
            //molino.circle1.setRadius(32);
            circle4.setFill(new ImagePattern(new Image("explosion.png")));
            circle4.setStroke(Color.BLACK);

            Timeline contador = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    acción ->
                            circle4.setFill(Color.TRANSPARENT)));
            contador.play();

        } else if (circle2.intersects(circle5.getCenterX(), circle5.getCenterY(), circle5.getLayoutX(), circle5.getLayoutY())&&circle5.getFill()!=Color.TRANSPARENT) {
            System.out.println("Collision");
            hitMolino = true;
            puntos += 100;
            // molino.circle2.setRadius(32);
            circle5.setFill(new ImagePattern(new Image("explosion.png")));
            circle5.setStroke(Color.BLACK);

            Timeline contador = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    acción ->

                            circle5.setFill(Color.TRANSPARENT)));

            contador.play();

        } else if (circle2.intersects(obstacle3.getCenterX(), obstacle3.getCenterY(), obstacle3.getLayoutX(), obstacle3.getLayoutY())&&obstacle3.getFill()!=Color.TRANSPARENT) {
            System.out.println("Collision");
            hitMolino = true;

            puntos += 100;

            // molino.circle2.setRadius(32);
            obstacle3.setFill(new ImagePattern(new Image("explosion.png")));
            obstacle3.setStroke(Color.BLACK);

            Timeline contador = new Timeline(new KeyFrame(
                    Duration.seconds(2),
                    acción ->

                            obstacle3.setFill(Color.TRANSPARENT)));

            contador.play();
        }
        if (hitMolino == true) {
            soundCollision();
            Timeline contadorSound = new Timeline(new KeyFrame(
                    Duration.millis(100),
                    acción ->
                            soundCollision()));
            contadorSound.play();


        }

    }


    //Movimiento de la pelota
    public void moveTo(double x, double y, double duration) {
        //      if (disparo == false) {
        //if (scoreInt >25 && scoreInt < 17) {
        //  System.out.println("disparo = Fallo ");
        //}
        //  else{
        double potencia = 1.6;
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
                return -4 * (t - menost) * (t - menost) + potencia;
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


        //}
    }

public void soundCollision(){

    audioClip2.play();
    audioClip2.setCycleCount(1);
    audioClip2.stop();
    hitMolino = false;


}

    //Barra de potencia de disparo


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
        root.getChildren().addAll(energyBarPane);
        energyBarPane.setLayoutX(370);
        energyBarPane.setLayoutY(570);


    }

    //Contador de la powerBar
    private void increaseCounter() {
        if (!molinoFinish) {
            energyCounter = Math.min(200, energyCounter + 5);
            energyRect.setX(energyCounter);
        }
    }

    //imagen jugador
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


    public void obstacleMolino() {
        angle1 = 0;
        angle2 = 90;
        angle3 = 180;
        x1 = 860;
        x2 = 860;
        molinoFinish = false;
        Rectangle body = new Rectangle();
        body.setX(845);
        body.setY(300.0f);
        body.setWidth(30);
        body.setHeight(95);
        body.setFill(Color.PURPLE);
        body.setStroke(Color.BLACK);
        Circle sail1 = new Circle(860, 300, 30);
        sail1.setFill(Color.RED);
        sail1.setStroke(Color.BLACK);

        circle4 = new Circle(860, 250, 20);
        circle5 = new Circle(830, 300, 20);
        obstacle3 = new Circle(800, 250, 20);

        circle4.setFill(new ImagePattern(new Image("bullseye.png")));
        circle5.setFill(new ImagePattern(new Image("bullseye.png")));
        obstacle3.setFill(new ImagePattern(new Image("bullseye.png")));
        circle4.setManaged(true);
        circle5.setManaged(true);
        obstacle3.setManaged(true);

        double radius1 = 50;
        double radius2 = 50;


        //double speed2 = 3;

        //  angle=0;
        k = new KeyFrame(Duration.millis(sKeyframeMolino), e -> {

            angle1 = (angle1 + (speed1)) % 360;
            angle2 = (angle2 + (speed1)) % 360;
            angle3 = (angle3 + (speed1)) % 360;


            x1 -= speed1;
            x2 -= speed1;

            circle4.setCenterX(circle4.getCenterX() - 1);
            circle5.setCenterX(circle5.getCenterX() - 1);
            obstacle3.setCenterX(obstacle3.getCenterX() - 1);

            body.setX(body.getX() - speed1);
            sail1.setCenterX(sail1.getCenterX() - speed1);

            circle4.setCenterX(x1 + radius2 * Math.cos(Math.toRadians(angle2)));
            circle4.setCenterY(300 + radius2 * Math.sin(Math.toRadians(angle2)));


            circle5.setCenterX(x2 + radius2 * Math.cos(Math.toRadians(angle1)));
            circle5.setCenterY(300 + radius2 * Math.sin(Math.toRadians(angle1)));

            obstacle3.setCenterX(x2 + radius2 * Math.cos(Math.toRadians(angle3)));
            obstacle3.setCenterY(300 + radius2 * Math.sin(Math.toRadians(angle3)));

            if (circle5.getFill() == Color.TRANSPARENT && circle4.getFill() == Color.TRANSPARENT && obstacle3.getFill() == Color.TRANSPARENT) {
                molinoFinish = true;
                timelineCircle2.setRate(10);

                if (level == 1) {
                    rectangle.setFill(new ImagePattern(new Image("Level2.png")));

                } else if (level == 2) {
                    rectangle.setFill(new ImagePattern(new Image("Level3.png")));

                } else if (level == 3 && molinoFinish) {
                    rectangle.setFill(new ImagePattern(new Image("win.png")));

                    Timeline contador = new Timeline(new KeyFrame(
                            Duration.seconds(2),
                            acción ->
                                    theStageGamepanel.hide()));
                    contador.play();

                    Timeline contadorM = new Timeline(new KeyFrame(
                            Duration.seconds(2),
                            acción ->
                                    mainApplication.hideShowStage(true)));
                    contadorM.play();

                    Timeline contadorS = new Timeline(new KeyFrame(
                            Duration.seconds(2),
                            acción ->
                                    showStatsName(puntos)));
                    contadorS.play();
                    timelineCircle2.stop();
                }
            }

            if (circle4.getCenterX() < -20 || circle5.getCenterX() < -20 || obstacle3.getCenterX() < -20) {
                if (molinoFinish) {
                    root.getChildren().remove(body);
                    root.getChildren().remove(sail1);
                    root.getChildren().remove(circle4);
                    root.getChildren().remove(circle5);
                    root.getChildren().remove(obstacle3);
                    timelineCircle2.stop();
                    circle4 = null;
                    pressStart.setFill(new ImagePattern(new Image("spaceStart.png")));

                } else if (!vidaMenys) {
                    System.out.println("Vida menys");
                    molinoFinish = true;
                    vidas -= 1;
                    vidaMenys = true;
                    root.getChildren().remove(body);
                    root.getChildren().remove(sail1);
                    root.getChildren().remove(circle4);
                    root.getChildren().remove(circle5);
                    root.getChildren().remove(obstacle3);
                    timelineCircle2.stop();
                    circle4 = null;
                    pressStart.setFill(new ImagePattern(new Image("spaceStart.png")));
                    if (vidas == 0) {
                        rectangle.setFill(new ImagePattern(new Image("gver.png")));
                        System.out.println(100 * puntos);
                        int puntosFinales = 100 * puntos;

                        Timeline contador = new Timeline(new KeyFrame(
                                Duration.seconds(2),
                                acción ->
                                        theStageGamepanel.hide()));

                        contador.play();

                        Timeline contadorM = new Timeline(new KeyFrame(
                                Duration.seconds(2),
                                acción ->
                                        mainApplication.hideShowStage(true)));

                        contadorM.play();

                        Timeline contadorS = new Timeline(new KeyFrame(
                                Duration.seconds(2),
                                acción ->
                                        showStatsName(puntosFinales)));


                        contadorS.play();
                    } else if (level == 1) {
                        rectangle.setFill(new ImagePattern(new Image("Level1.png")));


                    } else if (level == 2) {
                        rectangle.setFill(new ImagePattern(new Image("Level2.png")));


                    } else if (level == 3) {
                        rectangle.setFill(new ImagePattern(new Image("Level3.png")));

                    }

                }

            }
        });
        root.getChildren().add(body);
        root.getChildren().add(sail1);
        root.getChildren().add(circle4);
        root.getChildren().add(circle5);
        root.getChildren().add(obstacle3);
        canvasJug.toFront();
        timelineCircle2 = new Timeline(k);
        timelineCircle2.setCycleCount(Timeline.INDEFINITE);
        timelineCircle2.play();

    }

    public void setMain(MainApplication m) {
        mainApplication = m;
    }

    public void showStatsName(int puntos) {
        int puntosStat = puntos;
        System.out.println(puntos);
        ControllerStats controllerStats = new ControllerStats();
        try {
            controllerStats.showStatsName(puntosStat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}