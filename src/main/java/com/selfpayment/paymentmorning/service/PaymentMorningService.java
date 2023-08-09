package com.selfpayment.paymentmorning.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.representation.TokenIntrospectionResponse;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.keycloak.representations.idm.authorization.Permission;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentMorningService {

    public String getWelcome(String token) {

        AuthzClient authzClient = AuthzClient.create();

        AuthorizationResponse response = authzClient.authorization(token.replace("Bearer ","")).authorize();
        String rpt = response.getToken();

        TokenIntrospectionResponse requestingPartyToken = authzClient.protection().introspectRequestingPartyToken(rpt);

        System.out.println("Token status is: " + requestingPartyToken.getActive());
        System.out.println("Permissions granted by the server: ");

        for (Permission granted : requestingPartyToken.getPermissions()) {
            System.out.println(granted);
        }
        return "Welcome to payment morning.";
    }
}
