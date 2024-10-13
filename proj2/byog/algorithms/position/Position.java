package byog.algorithms.position;

public class Position {


    private int xxPosition;
    private int yyPosition;




    public Position(int xPos, int yPos){
        xxPosition = xPos;
        yyPosition = yPos;
    }




    public int getXxPosition(){
        return xxPosition;
    }

    public int getYyPosition(){
        return yyPosition;
    }



    public void moveUp(int maxY){
        if (yyPosition == maxY){
            return;
        }
        yyPosition++;
    }

    public void moveDown(){
        if (yyPosition == 0){
            return;
        }
        yyPosition--;
    }

    public void moveLeft(){
        if (xxPosition == 0){
            return;
        }
        xxPosition--;
    }

    public void moveRight(int maxX){
        if (xxPosition == maxX){
            return;
        }
        xxPosition++;
    }
}


