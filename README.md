# keycloak-event-listener-spi
A sample event listener SPI for keycloak

- Copy the jar to keycloak.

- Build.

sh-5.1$ ./opt/keycloak/bin/kc.sh build
The following run time non-cli properties were found, but will be ignored during build time: kc.hostname-admin-url, kc.hostname-strict-backchannel
Updating the configuration and installing your custom providers, if any. Please wait.
2024-09-10 07:40:31,798 WARN  [org.keycloak.services] (build-34) KC-SERVICES0047: sample_event_listener (com.rcs.sampleeventlistenerprovider.provider.SampleEventListenerProviderFactory) is implementing the internal SPI eventsListener. This SPI is internal and may change without notice
2024-09-10 07:40:35,810 INFO  [io.quarkus.deployment.QuarkusAugmentor] (main) Quarkus augmentation completed in 6072ms
Server configuration updated and persisted. Run the following command to review the configuration:

        kc.sh show-config



- Logs.

2024-09-10 15:03:03 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
2024-09-10 15:03:03 EVENT '1' Occurred: type=LOGIN, realmId=7c948502-d90c-4b7b-aa93-101e950d92d1, clientId=security-admin-console, userId=57fa1940-f642-4cc6-aa0c-6e826c2bc6b6, ipAddress=172.18.0.1, auth_method=openid-connect, auth_type=code, redirect_uri=http://localhost:8180/auth/admin/master/console/#/backoffice/events, consent=no_consent_required, code_id=2e68816d-6778-40ab-ae19-7490555d010e, username=admin
2024-09-10 15:03:03 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

2024-09-10 15:03:03 ------------------------------------------------------------
2024-09-10 15:03:03 [57fa1940-f642-4cc6-aa0c-6e826c2bc6b6] Time elapsed: 0 seconds -> Time remained: 60 seconds.
2024-09-10 15:03:03 ------------------------------------------------------------



2024-09-10 15:03:53 2024-09-10 13:03:53,550 DEBUG [org.apache.http.impl.conn.PoolingHttpClientConnectionManager] (pool-10-thread-3) Connection [id: 2][route: {}->http://wildfly:8080] can be kept alive indefinitely
2024-09-10 15:03:53 2024-09-10 13:03:53,550 DEBUG [org.apache.http.impl.conn.DefaultManagedHttpClientConnection] (pool-10-thread-3) http-outgoing-2: set socket timeout to 0
2024-09-10 15:03:53 2024-09-10 13:03:53,550 DEBUG [org.apache.http.impl.conn.PoolingHttpClientConnectionManager] (pool-10-thread-3) Connection released: [id: 2][route: {}->http://wildfly:8080][total available: 1; route allocated: 1 of 2; total allocated: 1 of 20]
2024-09-10 15:03:53 ------------------------------------------------------------
2024-09-10 15:03:53 Result from COMPONENT: Hello from PersonService ping
2024-09-10 15:03:53 ------------------------------------------------------------
2024-09-10 15:03:53 2024-09-10 13:03:53,550 DEBUG [org.apache.http.impl.conn.PoolingHttpClientConnectionManager] (pool-10-thread-3) Connection manager is shutting down
2024-09-10 15:03:53 2024-09-10 13:03:53,550 DEBUG [org.apache.http.impl.conn.DefaultManagedHttpClientConnection] (pool-10-thread-3) http-outgoing-2: Close connection
2024-09-10 15:03:53 2024-09-10 13:03:53,550 DEBUG [org.apache.http.impl.conn.PoolingHttpClientConnectionManager] (pool-10-thread-3) Connection manager shut down
