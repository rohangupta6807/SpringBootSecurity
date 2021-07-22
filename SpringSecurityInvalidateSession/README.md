Redirecting user to custom page if session gets timeout or invalid session id received.

Here storing the redirection url in a Map.

1. Create a custom session listener:
    This will listen each and every destroyed events and add a custom url for them in a storage.
   
2. Implement custom invalidate session strategy
    This will get trigger when the invalid session received. It will pick the redirection url form storage and reidrect the user to custom url.
    
