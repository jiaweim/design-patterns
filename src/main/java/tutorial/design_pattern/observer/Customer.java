package tutorial.design_pattern.observer;

/**
 * @author Jiawei Mao
 * @version 0.1.0
 * @since 02 Dec 2023, 5:11 PM
 */
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
