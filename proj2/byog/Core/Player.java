package byog.Core;

import byog.TileEngine.TETile;
import byog.algorithms.position.Position;

public class Player {
    /*Rep current position of player entity*/
    private Position position;

    /*Rep the world in which the player entity is going to move */
    private TETile[][] world;

    /*Rep direction
    * 0 -> Up
    * 1-> Right
    * 2->Down
    * 3->Left
    */
    private int direction;
    public Player(Position initPos, TETile[][] inWorld){
        position = initPos;
        world = inWorld;

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

    private void moveUp(){
        direction = 0;

    }

    private void moveDown(){
        direction = 2;

    }

    private void moveRight(){
        direction = 1;

    }

    private void moveLeft(){
        direction = 3;

    }

    /*
    * Detects collision in current direction*/
    private void canMove(){

    }
}
