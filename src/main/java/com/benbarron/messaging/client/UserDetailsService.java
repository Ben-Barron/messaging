package com.benbarron.messaging.client;

import com.benbarron.messaging.client.LoginDetails;

public interface UserDetailsService {

    boolean hasRole(LoginDetails loginDetails, String role);

    boolean isValid(LoginDetails loginDetails);
}
