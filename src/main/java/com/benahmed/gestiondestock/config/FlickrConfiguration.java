package com.benahmed.gestiondestock.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlickrConfiguration {
    @Value("${flickr.apiKey}")
    private String apiKey;
    @Value("${flickr.apiSecret}")
    private String apiSecret;
    @Value("${flickr.appKey}")
    private String appKey;
    @Value("${flickr.appSecret}")
    private String appSecret;
    // au demarrage spring doit executer cette method parce que on l'annotation config pour qu'il execute tous les beans
    /*@Bean
    public Flickr getFlickr() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
    // creer un service avec la permission que je veux
        OAuth10aService service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .build(FlickrApi.instance(FlickrApi.FlickrPerm.DELETE));
        // pour utiliser la valeur fourni par flickr
        final Scanner scanner = new Scanner(System.in);

        final OAuth1RequestToken requestToken = service.getRequestToken();
        // url qui me permet a utiliser l'api
        final String authUrl = service.getAuthorizationUrl(requestToken);

        System.out.println(authUrl);
        System.out.println("copie le ici ==> ");
        // pour generer l'acces token de l'app
        final String authVerifier = scanner.nextLine();

        OAuth1AccessToken accessToken = service.getAccessToken(requestToken, authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());
        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("########################################");

        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());



        return flickr;
    }*/
    @Bean
    public Flickr getFlickr(){
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

        Auth auth = new Auth();
        auth.setPermission(Permission.DELETE);

        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
        return flickr;
    }
}
