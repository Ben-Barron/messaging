package com.benbarron.messaging.client;

import com.benbarron.messaging.Endpoint;
import com.benbarron.messaging.MessageBroker;
import com.benbarron.messaging.messages.Message;
import com.benbarron.util.flow.Flow;

class DefaultClientManager implements ClientManager {

    private final UserDetailsService userDetailsService;
    private final MessageBroker messageBroker;

    public DefaultClientManager(UserDetailsService userDetailsService,
                                MessageBroker messageBroker) {

        this.userDetailsService = userDetailsService;
        this.messageBroker = messageBroker;
    }

    @Override
    public Client register(Endpoint endpoint, LoginDetails loginDetails) {
        if (!userDetailsService.isValid(loginDetails)) {
            throw new RuntimeException("Login details are incorrect. username=" + loginDetails.getUsername());
        }

        Client client = createNewClient(endpoint, loginDetails);
        messageBroker.register(client);

        return client;
    }

    private Client createNewClient(Endpoint endpoint, LoginDetails loginDetails) {
        return new Client() {
            @Override
            public Flow<Void> disconnected() {
                return endpoint.disconnected();
            }

            @Override
            public boolean isConnected() {
                return endpoint.isConnected();
            }

            @Override
            public String getId() {
                return loginDetails.getUsername();
            }

            @Override
            public boolean hasRole(String role) {
                return userDetailsService.hasRole(loginDetails, role);
            }

            @Override
            public Flow<Message> messages() {
                return endpoint.messages();
            }

            @Override
            public void send(Message message) {
                endpoint.send(message);
            }
        };
    }
}
