package tutorial.design_pattern.strategy;

public class Demo {

    public static void main(String[] args) {

        // Creating social Media Connect Object for connecting with friend by
        // any social media strategy.
        SocialMediaContext context = new SocialMediaContext();

        context.setSocialMediaStrategy(new FacebookStrategy());
        context.connect("Lilei");

        System.out.println("====================");

        context.setSocialMediaStrategy(new TwitterStrategy());
        context.connect("Lilei");

        System.out.println("====================");

        context.setSocialMediaStrategy(new GooglePlusStrategy());
        context.connect("Lilei");

        System.out.println("====================");

        context.setSocialMediaStrategy(new WeiboStrategy());
        context.connect("Lilei");
    }
}