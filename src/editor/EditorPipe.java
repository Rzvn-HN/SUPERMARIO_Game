package editor;

public class EditorPipe {

    private int pipeX;
    private int pipeY;
    private PipeType pipeType;

    public int getPipeX() {
        return pipeX;
    }

    public void setPipeX(int pipeX) {
        this.pipeX = pipeX;
    }

    public int getPipeY() {
        return pipeY;
    }

    public void setPipeY(int pipeY) {
        this.pipeY = pipeY;
    }

    public PipeType getPipeType() {
        return pipeType;
    }

    public void setPipeType(PipeType pipeType) {
        this.pipeType = pipeType;
    }


    EditorPipe(){}

    @Override
    public String toString() {
        return "PipeObject{" +
                "x='" + pipeX +
                "y='" + pipeY +
                "type='" + pipeType +
                '}';
    }
}
