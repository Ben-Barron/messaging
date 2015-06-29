package com.benbarron.messaging.client;

class AcceptAllUserDetailsService implements UserDetailsService {

    @Override
    public boolean hasRole(LoginDetails loginDetails, String role) {
        return true;
    }

    @Override
    public boolean isValid(LoginDetails loginDetails) {
        return true;
    }
}
