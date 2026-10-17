package editor;

public class EditorBrick
{
    private int brickX;
    private int brickY;
    private BrickType brickType;

    public int getBrickX() {
        return brickX;
    }

    public void setBrickX(int brickX) {
        this.brickX = brickX;
    }

    public int getBrickY() {
        return brickY;
    }

    public void setBrickY(int brickY) {
        this.brickY = brickY;
    }

    public BrickType getBrickType() {
        return brickType;
    }

    public void setBrickType(BrickType brickType) {
        this.brickType = brickType;
    }

    EditorBrick(){}



    @Override
    public String toString() {
        return "BlockObject{" +
                "x='" + brickX +
                "y='" + brickY +
                "type='" + brickType +
                '}';
    }

}
