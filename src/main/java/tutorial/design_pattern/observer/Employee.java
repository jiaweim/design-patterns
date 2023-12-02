package tutorial.design_pattern.observer;

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