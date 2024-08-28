package com.udemy_security.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

/**
 * @author nnkipkorir
 * created 28/08/2024
 */

//todo : for events have a look at https://docs.spring.io/spring-security/reference/servlet/getting-started.html then events
// we can add this to db
@Slf4j
@Component
public class AuthorizationEvents {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        log.info("Login successful for the user : {}", successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AuthorizationDeniedEvent deniedEvent) {
        log.error("Authorization failed for user due to {} : {}: " ,deniedEvent.getAuthentication().get().getName() ,
                deniedEvent.getAuthorizationDecision().toString());

    }
}
