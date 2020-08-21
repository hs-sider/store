package com.jalasoft.store.service.notifications;

public interface Observable {

    void addObserver(Observer observer);

    void notifyToObservers();
}
