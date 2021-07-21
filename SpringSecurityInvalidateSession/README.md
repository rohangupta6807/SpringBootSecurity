Redirecting user to custom page if session gets timeout or invalid session id received.

Here storing the redirection url in a MAP. But you use any DB.

1. Create a custom session listener:
    This will listen each and every destroyed events and add a custom url for them.
   
2. Implement custom invalidate session strategy
    This will get trigger when the invalid session received and reidrect the user to custom url.
   