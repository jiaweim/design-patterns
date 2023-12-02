package tutorial.design_pattern.strategy;

/**
 * @author Jiawei Mao
 * @version 0.1.0
 * @since 02 Dec 2023, 3:36 PM
 */
public class SocialMediaContext {

    ISocialMediaStrategy smStrategy;

    public void setSocialMediaStrategy(ISocialMediaStrategy smStrategy) {
        this.smStrategy = smStrategy;
    }

    public void connect(String name) {
        smStrategy.connectTo(name);
    }
}
