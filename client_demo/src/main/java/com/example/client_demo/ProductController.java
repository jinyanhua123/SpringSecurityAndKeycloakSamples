package com.example.client_demo;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping(path = "userinfo")
    public String userInfo(HttpServletRequest request, Model model){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        UserInfo info = new UserInfo();
        info.username = accessToken.getPreferredUsername();
        info.emailID = accessToken.getEmail();
        info.lastname = accessToken.getFamilyName();
        info.firstname = accessToken.getGivenName();
        info.realmName = accessToken.getIssuer();
        AccessToken.Access realmAccess = accessToken.getRealmAccess();
        info.roles = realmAccess.getRoles().toString();
        info.scopes = accessToken.getScope();
        info.accessToken = session.getTokenString();
        info.idToken = session.getIdTokenString();
        model.addAttribute("userinfo", info);
        return "userinfo";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }

    public static class UserInfo {
        public String username;
        public String emailID;
        public String lastname;
        public String firstname;
        public String realmName;
        public String roles;
        public String scopes;
        public String accessToken;
        public String idToken;

    }

}