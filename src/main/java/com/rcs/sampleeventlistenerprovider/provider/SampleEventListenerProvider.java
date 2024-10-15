package com.rcs.sampleeventlistenerprovider.provider;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SampleEventListenerProvider implements EventListenerProvider {

    private static int numEvents = 0;

    public SampleEventListenerProvider() {
    }

    @Override
    public void onEvent(Event event) {
        numEvents = numEvents + 1;

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(String.format("EVENT '%s' Occurred: %s", numEvents, toString(event)));
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        if (EventType.LOGIN.equals(event.getType())) {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

            int maxSessionInMinutes = 1;
            int beforeExpiredInSeconds = 10;
            int periodInSeconds = 5;

            Runnable task = new Runnable() {
                private int periodsElapsed = 0;

                @Override
                public void run() {
                    int maxSession = maxSessionInMinutes * 60;
                    int timeElapsed = periodsElapsed * periodInSeconds;

                    if (timeElapsed >= maxSession - beforeExpiredInSeconds) {

                        callUserExpirationSession(timeElapsed);

                        scheduler.shutdown(); // Stop the scheduler after configured time
                        return;
                    }

                    // Perform your calculations here
                    int timeRemained = maxSession - timeElapsed;
                    System.out.println("------------------------------------------------------------");
                    System.out.println(String.format("[%s] Time elapsed: %s seconds -> Time remained: %s seconds.", event.getUserId(), timeElapsed, timeRemained));
                    System.out.println("------------------------------------------------------------");

                    periodsElapsed++;
                }

                private void callUserExpirationSession(int timeElapsed) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println(String.format("[%s] Time elapsed: %s seconds -> SESSION ABOUT TO EXPIRED in %s seconds!", event.getUserId(), timeElapsed, periodInSeconds));
                    System.out.println("------------------------------------------------------------");

                    ComponentNotifier client = new ComponentNotifier();
                    client.notifyComponent();
                }
            };

            // Schedule the task to run every period, with an initial delay of 0 seconds
            ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(task, 0, periodInSeconds, TimeUnit.SECONDS);
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        System.out.println("ADMIN EVENT Occurred:" + toString(adminEvent));
    }

    @Override
    public void close() {
    }

    private String toString(Event event) {

        StringBuilder sb = new StringBuilder();

        sb.append("type=");
        sb.append(event.getType());
        sb.append(", realmId=");
        sb.append(event.getRealmId());
        sb.append(", clientId=");
        sb.append(event.getClientId());
        sb.append(", userId=");
        sb.append(event.getUserId());
        sb.append(", ipAddress=");
        sb.append(event.getIpAddress());

        if (event.getError() != null) {
            sb.append(", error=");
            sb.append(event.getError());
        }

        if (event.getDetails() != null) {

            for (Map.Entry<String, String> e : event.getDetails().entrySet()) {
                sb.append(", ");
                sb.append(e.getKey());

                if (e.getValue() == null || e.getValue().indexOf(' ') == -1) {
                    sb.append("=");
                    sb.append(e.getValue());
                } else {
                    sb.append("='");
                    sb.append(e.getValue());
                    sb.append("'");
                }
            }
        }

        return sb.toString();
    }

    private String toString(AdminEvent adminEvent) {

        StringBuilder sb = new StringBuilder();

        sb.append("operationType=");
        sb.append(adminEvent.getOperationType());
        sb.append(", realmId=");
        sb.append(adminEvent.getAuthDetails().getRealmId());
        sb.append(", clientId=");
        sb.append(adminEvent.getAuthDetails().getClientId());
        sb.append(", userId=");
        sb.append(adminEvent.getAuthDetails().getUserId());
        sb.append(", ipAddress=");
        sb.append(adminEvent.getAuthDetails().getIpAddress());
        sb.append(", resourcePath=");
        sb.append(adminEvent.getResourcePath());

        if (adminEvent.getError() != null) {
            sb.append(", error=");
            sb.append(adminEvent.getError());
        }

        return sb.toString();
    }
}