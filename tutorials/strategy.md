# Strategy

2023-12-02, 15:56
@author Jiawei Mao
****
## 简介

**策略**（Strategy）设计模式是一种行为设计模式，在运行时为某一任务从多个实现中选择一种执行。

策略模式将算法从逻辑中拆分出来，作为单独的类，以便在运行时对不同实现的算法进行选择。对支持多态和泛型的 Java 来说，策略模式几乎无处不在。

以 `Collections.sort(list, comparator)` 方法为例，不需要修改 `sort()` 算法，只需要提供不同的 `comparator` 实现。

## 实现

假设需要设计一个社交媒体应用，能够连接 Facebook，Google Plus, Twitter 和 Weibo。能够根据朋友姓名和平台平台自动连接对应好友。

所以该问题可以描述为：**联系好友**有多种方式，用户可以在运行时选择自己需要的方式。正好与策略设计模式对上。

- `ISocialMediaStrategy` 提供算法的统一接口

```java
public interface ISocialMediaStrategy {

    void connectTo(String friendName);
}
```

- `SocialMediaContext` 负责选择算法

```java
public class SocialMediaContext {

    ISocialMediaStrategy smStrategy;

    public void setSocialMediaStrategy(ISocialMediaStrategy smStrategy) {
        this.smStrategy = smStrategy;
    }

    public void connect(String name) {
        smStrategy.connectTo(name);
    }
}
```

- `FacebookStrategy`, `GooglePlusStrategy`, `TwitterStrategy` 和 `WeiboStrategy` 各提供一种算法实现

```java
public class FacebookStrategy implements ISocialMediaStrategy {
    public void connectTo(String friendName) {
        System.out.println("Connecting with " + friendName + " through Facebook");
    }
}

public class GooglePlusStrategy implements ISocialMediaStrategy {
    public void connectTo(String friendName) {
        System.out.println("Connecting with " + friendName + " through GooglePlus");
    }
}

public class TwitterStrategy implements ISocialMediaStrategy {
    public void connectTo(String friendName) {
        System.out.println("Connecting with " + friendName + " through Twitter");
    }
}

public class WeiboStrategy implements ISocialMediaStrategy {
    public void connectTo(String friendName) {
        System.out.println("Connecting with " + friendName + " through Weibo");
    }
}
```

- 客户端使用

```java
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
```

```
Connecting with Lilei through Facebook
====================
Connecting with Lilei through Twitter
====================
Connecting with Lilei through GooglePlus
====================
Connecting with Lilei through Weibo
```

