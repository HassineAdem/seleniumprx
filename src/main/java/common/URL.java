package common;

public enum URL {
   BO_URL ("http://bankerise-backoffice-rc.qa.proxym-it.tn/bankerise"),
   LOGIN_FRONT_URL ("http://bankerise-keycloak.demo.proxym-it.tn/auth/realms/bankerise-front/protocol/openid-connect/auth?client_id=front&scope=openid&response_type=code&redirect_uri=http://bkr.qa.proxym-it.tn/"),
    PUBLIC_FRONT_URL("http://bkr.qa.proxym-it.tn/public/dashboard");
   private String url;
   URL (String url ){
       this.url = url;
   }
   public String getUrl (){
       return url;
   }
}
