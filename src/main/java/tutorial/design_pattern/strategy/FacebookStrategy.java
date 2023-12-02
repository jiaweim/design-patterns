package tutorial.design_pattern.strategy;

/**
 * @author Jiawei Mao
 * @version 0.1.0
 * @since 02 Dec 2023, 3:39 PM
 */
public class FacebookStrategy implements ISocialMediaStrategy {

    @Override
    public void connectTo(String friendName) {
        System.out.println("Connecting with " + friendName + " through Facebook");
    }
}
