package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.algorithms.position.Position;

public class Player {
    /*Rep current position of player entity*/
    private Position position;
    private Position nextPosition;

    /*Rep the world in which the player entity is going to move */
    private final TETile[][] WORLD;
    private final int MAX_X ;
    private final  int MAX_Y;

    private int prevX;
    private int prevY;
    private boolean hasKey = false;

    /*Rep direction
    * 0 -> Up
    * 1-> Right
    * 2->Down
    * 3->Left
    */
    private int direction;
    public Player(Position initPos, TETile[][] inWorld){
        position = initPos;
        WORLD = inWorld;
        MAX_X = inWorld.length - 1;
        MAX_Y = inWorld[0].length - 1;

    }

    public int getX(){
        return position.getXxPosition();
    }

    public int getY(){
        return  position.getYyPosition();
    }

    public Position getPosition() {
        return position;
    }

    public void moveUp(){
        direction = 0;
        if (canMove()){
            savePrevPosition();
            position.moveUp(MAX_Y);
            drawMovement();
        }

    }

    public void moveDown(){
        direction = 2;
        if (canMove()){
            savePrevPosition();
            position.moveDown();
            drawMovement();
        }

    }

    public void moveRight(){
        direction = 1;
        if (canMove()){
            savePrevPosition();
            position.moveRight(MAX_X);
            drawMovement();
        }

    }

    public void moveLeft(){
        direction = 3;
        if (canMove()){
            savePrevPosition();
            position.moveLeft();
            drawMovement();
        }

    }

    /*
    * Detects collision in current direction
    * Collision is when there is a wall*/
    private boolean canMove(){
        int nextX = getX();
        int nextY = getY();
        switch (direction){
            case 0:
                nextY++;
                break;
            case 1:
                nextX++;
                break;
            case 2:
                nextY--;
                break;
            case 3:
                nextX--;
                break;
        }

        if (WORLD[nextX][nextY] == Tileset.KEY){
            hasKey = true;
        }
        return WORLD[nextX][nextY] != Tileset.WALL;
    }

    private void savePrevPosition(){
        prevX = getX();
        prevY = getY();
    }

    private void drawMovement(){
        WORLD[getX()][getY()] = Tileset.PLAYER;
        WORLD[prevX][prevY] = Tileset.GRASS;
    }

    public boolean hasKey(){
        return hasKey;
    }

    public boolean isNearDoor(){
        int yStart = getY() - 1;
        int yEnd = getY() + 1;
        int xStart = getX() - 1;
        int xEnd = getX() + 1;
        //Look for key in a 3 x 3 square
        for (int i = xStart; i <= xEnd ; i++) {
            for (int j = yStart; j <= yEnd ; j++) {
                if (isDoor(i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDoor(int x, int y){
        return WORLD[x][y] == Tileset.LOCKED_DOOR;
    }
}
