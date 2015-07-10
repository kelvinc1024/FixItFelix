package lib;

public class Animation {
    private String name;
    private int start, end;
    private String animType;
    private float animDuration;

    public Animation(String animType, String name, int start, int end, float animDuration) {
        this.animType = animType;
        this.name = name;
        this.start = start;
        this.end = end;
        this.animDuration = animDuration;
    }

    public String getAnimType() {
        return animType;
    }

    public void setAnimType(String animType) {
        this.animType = animType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setAnimDuration(float animDuration) {
        this.animDuration = animDuration;
    }

    public float getAnimDuration() {
        return animDuration;
    }
}
