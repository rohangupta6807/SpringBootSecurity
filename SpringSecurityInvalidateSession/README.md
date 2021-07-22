Redirecting user to custom page if session gets timeout or invalid session id received.

Here storing the redirection url in a Map.

1. Create a custom session listener:
    This will listen each and every destroyed events and store the custom redirection url for the user.
   
2. Implement custom invalid session strategy
    This will get trigger when the invalid session id received. 
    Pick the redirection url form storage for the user and redirect the user to custom url.
    
