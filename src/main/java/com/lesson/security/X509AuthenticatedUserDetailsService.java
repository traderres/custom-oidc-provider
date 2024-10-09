package com.lesson.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class X509AuthenticatedUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private static final Pattern patExtractCN = Pattern.compile("cn=(.*?)(?:,|/|\\z)", Pattern.CASE_INSENSITIVE);


    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        X509Certificate certificate = (X509Certificate)token.getCredentials();
        // do your extra checking here...

        // add granted authorities, etc.
        Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("APP16_SUPERVISOR"));

        String password = "";

        String longDn = certificate.getSubjectX500Principal().getName();
        String cnValue = getCnValueFromLongDnString(longDn);

        // generate your user how you deem fit
        User user = new User(cnValue, password, authorities);
        return user;
    }




    private static String getCnValueFromLongDnString(String userDN) {
        String cnValue = null;
        // Use the regular expression pattern to getByUserId the value part of "CN=value"
        Matcher matcher = patExtractCN.matcher(userDN);
        if (matcher.find()) {
            cnValue = matcher.group(1);
        }

        return cnValue;
    }

}

