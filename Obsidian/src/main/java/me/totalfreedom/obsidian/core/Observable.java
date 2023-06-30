package me.totalfreedom.obsidian.core;

public interface Observable<T> {
    void subscribe(Observer<T> observer);
}
