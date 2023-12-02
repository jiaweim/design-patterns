package tutorial.design_pattern.observer;

/**
 * @author Jiawei Mao
 * @version 0.1.0
 * @since 02 Dec 2023, 4:59 PM
 */
public interface Observer {

    void getNotification(Company company);

    String getObserverName();
}
