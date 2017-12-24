package application;


import grid.MainPanel;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import logic.*;
import logic.impl.*;
import world.BlockedParts;
import world.GlobalClock;
import world.impl.BlockedPartsImpl;
import world.impl.LightMap;

import java.util.concurrent.locks.ReentrantLock;

public class ApplicationContext {
    private SettingsController settingsController;
    private LightMap lightMap;
    private MainPanel mainPanel;
    private CreatureLogic creatureLogic;
    private GlobalClock clock;
    private ReentrantLock panelLock;
    private ReentrantLock lightMapLock;
    private ReentrantLock blockedPartsLock;
    private BlockedParts blockedParts;
    private Line line;


    public ApplicationContext(){
        settingsController = new SettingsControllerImpl();
        blockedParts = new BlockedPartsImpl();
        this.lightMap = new LightMap(10, 10);
        this.mainPanel = new MainPanel(this,3000, 2000);
        panelLock = new ReentrantLock();
        lightMapLock = new ReentrantLock();
        blockedPartsLock = new ReentrantLock();
        creatureLogic = new CreatureLogicImpl(this);
        clock = new GlobalClock(1, lightMap,lightMapLock);
    }

    public MainPanel getMainPanel(){
        return mainPanel;
    }

    public void run(){
        settingsController.deactivateSlider();
        for(Thread creature:creatureLogic.getCreatureList()){
            creature.start();
            try {
                creature.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        clock.start();
        mainPanel.getTopPane().setOnMouseDragged(event -> {});
    }
    public void stop(){
        settingsController.activateSlider();
        for(Thread creature:creatureLogic.getCreatureList()){
            creature.interrupt();
        }
        creatureLogic.getCreatureList().clear();
        mainPanel.getTopPane().getChildren().clear();
        blockedParts.getBlockedY().clear();
        blockedParts.getBlockedX().clear();
        clock.stop();
    }
    public void drawWorld(){
        Pane drawingPane = mainPanel.getTopPane();
        drawingPane.setOnMousePressed(event -> {
            line = new Line();
            line.setFill(Color.BLACK);
            line.setStartX((int)event.getX());
            line.setStartY((int)event.getY());

        });
        drawingPane.setOnMouseDragged(event -> {
            if(drawingPane.getChildren().contains(line)){
                drawingPane.getChildren().remove(line);
            }
            line.setEndY(event.getY());
            line.setEndX(event.getX());
            drawingPane.getChildren().add(line);
        });
        drawingPane.setOnMouseReleased(event -> {
            line.setEndX((int)event.getX());
            line.setEndY((int)event.getY());
            double res = Math.sqrt(Math.pow(line.getEndX()-line.getStartX(),2)+Math.pow(line.getEndY()-line.getStartY(),2));
            double directionX = (line.getEndX()-line.getStartX())/res;
            double directionY = (line.getEndY()-line.getStartY())/res;
            int i = 0;
            int lastPointX = 0, lastPointY = 0;
            double pointX = line.getStartX(), pointY = line.getStartY();
            if(directionX<=0 && directionY<=0) {
                while (pointX > line.getEndX() && pointY > line.getEndY()) {
                    pointX = calcPointX(pointX,directionX,i);
                    pointY = calcPointY(pointY,directionY,i);
                    if((int)pointX != lastPointX || (int)pointY != lastPointY){
                        lastPointX = (int)pointX;
                        lastPointY = (int)pointY;
                        blockedParts.addBlockedPart(lastPointX, lastPointY);
                    }
                    i++;
                }
            }
            if(directionX>=0 && directionY>=0) {
                while (pointX < line.getEndX() && pointY < line.getEndY()) {
                    pointX = calcPointX(pointX,directionX,i);
                    pointY = calcPointY(pointY,directionY,i);
                    if((int)pointX != lastPointX || (int)pointY != lastPointY){
                        lastPointX = (int)pointX;
                        lastPointY = (int)pointY;
                        blockedParts.addBlockedPart(lastPointX, lastPointY);
                    }
                    i++;
                }
            }
            if(directionX<=0 && directionY>=0){
                while (pointX > line.getEndX() && pointY < line.getEndY()) {
                    pointX = calcPointX(pointX,directionX,i);
                    pointY = calcPointY(pointY,directionY,i);
                    if((int)pointX != lastPointX || (int)pointY != lastPointY){
                        lastPointX = (int)pointX;
                        lastPointY = (int)pointY;
                        blockedParts.addBlockedPart(lastPointX, lastPointY);
                    }
                    i++;
                }
            }
            if(directionX>=0 && directionY<=0){
                while (pointX < line.getEndX() && pointY > line.getEndY()) {
                    pointX = calcPointX(pointX,directionX,i);
                    pointY = calcPointY(pointY,directionY,i);
                    if((int)pointX != lastPointX || (int)pointY != lastPointY){
                        lastPointX = (int)pointX;
                        lastPointY = (int)pointY;
                        blockedParts.addBlockedPart(lastPointX, lastPointY);
                    }
                    i++;
                }
            }
        });


    }
    private double calcPointX(double pointX, double directionX, int i){
        pointX = line.getStartX() + directionX * i;
        return pointX;
    }
    private double calcPointY(double pointY, double directionY, int i){
        pointY = line.getStartY() + directionY * i;
        return pointY;
    }
    public SettingsController getSettingsController(){
        return this.settingsController;
    }

    public LightMap getLightMap() {
        return lightMap;
    }

    public ReentrantLock getLightMapLock() {
        return lightMapLock;
    }

    public ReentrantLock getPanelLock() {
        return panelLock;
    }

    public ReentrantLock getBlockedPartsLock(){return blockedPartsLock;}

    public BlockedParts getBlockedParts(){ return blockedParts;}
}
