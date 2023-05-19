package me.totalfreedom.event;

import com.sun.source.tree.ContinueTree;

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
        subscriptions.forEach(s -> {
            if (!s.event().shouldCall()) return;

            s.callback().call(s.event());
            s.event().reset();
        });
    }
}
