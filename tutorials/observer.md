# 观察者模式

2023-12-02, 17:31
@author Jiawei Mao
****
## 简介

观察者（observer）模式定义了**一对多**的依赖关系，当一个对象改变，它的所有依赖项都会收到通知并自动更新。

在该模式有两类对象：

- 观察者（observer）
- 主体（subject）

当主体发生变化，观察者会收到通知。

通常一个主体对应多个观察者：对主体感兴趣的观察者注册自己来获取主体的通知，失去兴趣就取消注册。该模式又称为**发布-订阅**模式。

## Java 示例

在 GUI 程序中常见这种模式，UI 控件需要监听数据库或任务进度，将其更新到可视化控件上。

在任何事件驱动的软件中都能看到这种模式。Java 中的 EventListener，就是典型的 Observer。在 Java 9 之前，有现成的 `Observable` 类和 `Observer` 接口，`Observable` 对应主体，`Observer` 对应观察者。

Java 9 弃用这两个类，表示它们支持的事件模型非常有限，且 `Observable` 未指定传递通知的顺序，状态变化与通知也不是一一对应：

- 对更丰富的事件模型，建议使用 `java.beans`
- 对线程间可靠且有序的消息传递，建议使用 `java.util.concurrent` 包
- 对响应式流风格编程，推荐 `java.util.concurrent.Flow`

在 `Flow` 类中，有 `Publisher`, `Subscriber` 和 `Subscription` 等静态接口，这些可以用作观察者模式的替代方案。

## 实现

- 观察值接口 `Observer`

```java
public interface Observer {

    void getNotification(Company company);

    String getObserverName();
}
```

这里 `Company` 对应主体。

- 两类观察者的实现 `Employee` 和 `Customer`

```java
class Employee implements Observer {

    String nameOfObserver;

    public Employee(String name) {
        this.nameOfObserver = name;
    }

    @Override
    public void getNotification(Company company) {
        System.out.print(nameOfObserver + " has received an alert from "
                + company.getName());
        System.out.println("The current stock price is:$" +
                company.getStockPrice());
    }

    @Override
    public String getObserverName() {
        return nameOfObserver;
    }
}

public class Customer implements Observer {

    String nameOfObserver;

    public Customer(String name) {
        this.nameOfObserver = name;
    }

    @Override
    public void getNotification(Company company) {
        System.out.print(nameOfObserver + " is notified from "
                + company.getName());
        System.out.println("Its current stock price is:$"
                + company.getStockPrice());
    }

    @Override
    public String getObserverName() {
        return nameOfObserver;
    }
}
```

- 主体 `Company` 实现

```java
abstract class Company {

    List<Observer> observerList = new ArrayList<>();
    // 主体名称
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // For the stock price
    private int stockPrice;

    public int getStockPrice() {
        return this.stockPrice;
    }

    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
        // 股票价格变化，通知所有注册用户
        notifyRegisteredUsers();
    }

    // 注册 observer
    abstract void register(Observer o);

    // 注销 observer
    abstract void unRegister(Observer o);

    // 通知所有注册的用户
    abstract void notifyRegisteredUsers();
}
```

- `SpecificCompany` 提供主体的具体实现

```java
class SpecificCompany extends Company {

    public SpecificCompany(String name) {
        super(name);
    }

    @Override
    void register(Observer anObserver) {
        observerList.add(anObserver);
        System.out.println(this.getName() + " registers " +
                anObserver.getObserverName());
    }

    @Override
    void unRegister(Observer anObserver) {
        observerList.remove(anObserver);
        System.out.println(this.getName() + " unregisters " +
                anObserver.getObserverName());
    }

    // Notify all registered observers.
    @Override
    public void notifyRegisteredUsers() {
        for (Observer observer : observerList) {
            observer.getNotification(this);
        }
    }
}
```

- 演示

到这里，就完成了整个观察者模式的一个实现，下面演示其使用：

```java
public class Client {

    public static void main(String[] args) {
        System.out.println("***Observer Pattern Demonstration.***\n");
        // 4 个观察者
        Observer roy = new Employee("Roy");
        Observer kevin = new Employee("Kevin");
        Observer bose = new Customer("Bose");
        Observer jacklin = new Customer("Jacklin");
        
        // 公司 Abc
        System.out.println("Working with the company: Abc Ltd.\n");
        Company abcLtd = new SpecificCompany("ABC Ltd. ");
        // 注册前 3 个观察者 - Roy, Kevin, Bose
        abcLtd.register(roy);
        abcLtd.register(kevin);
        abcLtd.register(bose);

        // 修改股票价格，产生事件
        System.out.println(" ABC Ltd.'s current stock price is $5.");
        abcLtd.setStockPrice(5);
        System.out.println("-----");

        // 注销 Kevin，使其收不到通知
        System.out.println("\nABC Ltd. is removing Kevin from the observer list now.");
        abcLtd.unRegister(kevin);
        // No notification is sent to Kevin any more.
        System.out.println("\n ABC Ltd.'s new stock price is $50.");
        abcLtd.setStockPrice(50);
        System.out.println("-----");
        System.out.println("\nKevin registers again to get notification from ABC Ltd.");
        abcLtd.register(kevin);
        System.out.println("\n ABC Ltd.'s new stock price is $100.");
        abcLtd.setStockPrice(100);
        System.out.println("-----");
        System.out.println("\n Working with another company: XYZ Co.");
        // Creating another company
        Company xyzCo = new SpecificCompany("XYZ Co. ");
        // Registering the observers-Roy and Jacklin
        xyzCo.register(roy);
        xyzCo.register(jacklin);
        System.out.println("\nXYZ Co.'s new stock price is $500.");
        xyzCo.setStockPrice(500);
    }
}
```

```
***Observer Pattern Demonstration.***

Working with the company: Abc Ltd.

ABC Ltd.  registers Roy
ABC Ltd.  registers Kevin
ABC Ltd.  registers Bose
 ABC Ltd.'s current stock price is $5.
Roy has received an alert from ABC Ltd. The current stock price is:$5
Kevin has received an alert from ABC Ltd. The current stock price is:$5
Bose is notified from ABC Ltd. Its current stock price is:$5
-----

ABC Ltd. is removing Kevin from the observer list now.
ABC Ltd.  unregisters Kevin

 ABC Ltd.'s new stock price is $50.
Roy has received an alert from ABC Ltd. The current stock price is:$50
Bose is notified from ABC Ltd. Its current stock price is:$50
-----

Kevin registers again to get notification from ABC Ltd.
ABC Ltd.  registers Kevin

 ABC Ltd.'s new stock price is $100.
Roy has received an alert from ABC Ltd. The current stock price is:$100
Bose is notified from ABC Ltd. Its current stock price is:$100
Kevin has received an alert from ABC Ltd. The current stock price is:$100
-----

 Working with another company: XYZ Co.
XYZ Co.  registers Roy
XYZ Co.  registers Jacklin

XYZ Co.'s new stock price is $500.
Roy has received an alert from XYZ Co. The current stock price is:$500
Jacklin is notified from XYZ Co. Its current stock price is:$500

Process finished with exit code 0
```

## 从 Observer 提供注册和注销功能

```java
@Override
public void registerTo(Company company) {
    company.register(this);
    System.out.println(this.nameOfObserver+ "registered himself/herself to "
                        + company.getName());
}

@Override
public void unregisterFrom(Company company) {
    company.unRegister(this);
    System.out.println(this.nameOfObserver+ " unregistered himself/herself from "
                        + company.getName());
}
```

## 主要问题

- 内存泄露

在 GC 语言如 Java 中，使用观察者模式容易出现**内存泄露**问题。

观察者注册到主体以获取通知，但是忘了取消注册，因此主体持有对观察者的有效引用，导致 GC 无法收集它们，从而引起内存泄露。

- 顺序

通知的顺序不可靠。

- 性能

当观察者增多，每次通知更新的成本增加。
