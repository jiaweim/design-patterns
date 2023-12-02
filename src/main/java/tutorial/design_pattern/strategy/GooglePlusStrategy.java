package tutorial.design_pattern.strategy;

public class GooglePlusStrategy implements ISocialMediaStrategy {

    public void connectTo(String friendName) {
        System.out.println("Connecting with " + friendName + " through GooglePlus");
    }
}