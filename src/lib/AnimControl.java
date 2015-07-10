package lib;

import java.util.HashMap;

public class AnimControl {
    private HashMap<String, Animation> animStatus;
    private String activeName;
    private Animation activeAnimation;
    private int index;
    private float counter;

    public AnimControl() {
        animStatus = new HashMap<String, Animation>();
        counter = 0;
    }

    public void addAnim(String animType, String name, int start, int end, float duration) {
        animStatus.put(name, new Animation(animType, name, start, end, duration));
        setActiveAnim(name);
    }

    public void removeAnim(String name) {
        animStatus.remove(name);
    }

    public void setAnim(String animType, String name, int start, int end, float duration) {
        removeAnim(name);
        addAnim(animType, name, start, end, duration);
    }

    public void setActiveAnim(String name) {
        activeAnimation = animStatus.get(name);
        activeName = name;
        index = animStatus.get(name).getStart();
    }

    public void updateAnim(float tpf) {
        counter += tpf;
        if (counter >= activeAnimation.getAnimDuration()) {
            counter -= activeAnimation.getAnimDuration();
            if (activeAnimation.getAnimType().equalsIgnoreCase("StraightLoop")) {
                index++;
                if (index > activeAnimation.getEnd()) index = activeAnimation.getStart();
            } else if (activeAnimation.getAnimType().equalsIgnoreCase("RoundLoop")) {

            } else if (activeAnimation.getAnimType().equalsIgnoreCase("NoLoop")) {
                if (index < activeAnimation.getEnd()) index++;
            }
        }
    }

    public int getIndex() {
        return (int) index;
    }

    public String getActiveName() {
        return activeName;
    }
}
