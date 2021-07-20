Here we created one class(CustomUsernamePasswordAuthenticationFilter) for authentication
    which  gets triggered when we hit /login controller and it  return the JWT token
Second class(CustomBasicAuthenticationFilter) is for authorization , it  validate the token
    and add the user details with authentication = true in Security context  holder so that next chain works properly