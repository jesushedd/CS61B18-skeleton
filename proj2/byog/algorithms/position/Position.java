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



    public void moveUp(){
        yyPosition++;
    }

    public void moveDown(){
        yyPosition--;
    }

    public void moveLeft(){
        xxPosition--;
    }

    public void moveRight(){
        xxPosition++;
    }
}


