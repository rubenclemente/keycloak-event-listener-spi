package com.rcs.sampleeventlistenerprovider.provider;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class SampleEventListenerProviderFactory implements EventListenerProviderFactory {


    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {

        return new SampleEventListenerProvider();
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "sample_event_listener";
    }
}