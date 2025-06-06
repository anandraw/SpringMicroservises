package org.anand.observer;

public class ConsoleNotificationObserver implements NotificationObserver {
    @Override
    public void notify(String message, String rideId) {
        System.out.println("[Ride " + rideId + "] " + message);
    }
}
