# Key Terms 
   1. Authentication
   2. Authorization
   3. Principal (contains the logged in users information)
   4. Grant Authority (Access to do something)
   5. Role (Bunch of authorities)



# How Authentication Work
Spring has one AuthenticationProvider class which contains authenticate method 
   * it take input as Credentials  and return output as Principal
   * it also contains support  method which tell the type of Authentication this provider will do.
   * it  uses UserDetailsService to get the user's information.
  
## AuthenticationProvider class contains:
   1. Authentication	authenticate(Authentication authentication)
            Performs authentication with the same contract as AuthenticationManager.authenticate(Authentication) .
   2. boolean	supports(Class<?> authentication)
            Returns true if this AuthenticationProvider supports the indicated Authentication object.
## Authentication class contains:
   1. Collection<? extends GrantedAuthority>	getAuthorities()
            Set by an AuthenticationManager to indicate the authorities that the principal has been granted.
   2.  Object	getCredentials()
            The credentials that prove the principal is correct.
   3.  Object	getDetails()
            Stores additional details about the authentication request. 
   4.  Object	getPrincipal()
            The identity of the principal being authenticated.
   5.  boolean	isAuthenticated()
            Used to indicate to AbstractSecurityInterceptor whether it should present the authentication token to the AuthenticationManager.
   6.  void setAuthenticated(boolean isAuthenticated)
            See isAuthenticated() for a full description. 
                
## Spring can have multiple AuthenticationProviders
   1. one with username password
   2. one with LDAP 
   3. one with oauth
        
## Question is how all these APs work with each other? 
   Ans: here comes the "ProviderManager which extends AuthenticationManager" class comes into the picture which will handle all APs.