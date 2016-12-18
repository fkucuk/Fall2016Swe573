package com.fkucuk;

import com.fkucuk.repository.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthenticationResource {

    private UserRepository userRepository = new UserRepository();


    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {


            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        boolean isAuthenticated = userRepository.authenticate(username, password);
        if (!isAuthenticated)
            throw new NotAuthorizedException("NOT AUTH", Response.status(Response.Status.UNAUTHORIZED).build());
    }

    private String issueToken(String email) {
        return Long.toString( userRepository.getUserByEmail(email).getUserId());
    }

}
