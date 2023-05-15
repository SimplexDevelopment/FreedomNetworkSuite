package me.totalfreedom.event;

import java.util.ArrayList;
import java.util.List;

class SubscriptionBox<T extends FEvent>
{
    private final List<EventSubscription<T>> subscriptions;

    public SubscriptionBox() {
        this.subscriptions = new ArrayList<>();
    }

    public void addSubscription(EventSubscription<T> subscription) {
        subscriptions.add(subscription);
    }

    public void removeSubscription(EventSubscription<?> subscription) {
        subscriptions.remove(subscription);
    }

    public void tick() {
        subscriptions.forEach(s -> s.getCallback().call(s.getEvent()));
    }
}
