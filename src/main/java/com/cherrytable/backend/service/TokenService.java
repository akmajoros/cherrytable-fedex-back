package com.cherrytable.backend.service;

import com.cherrytable.backend.model.Organisation;
import com.cherrytable.backend.repository.OrganisationRepository;
import java.security.Key;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Autowired
  OrganisationRepository organisationRepository;

  private Key key;

  public String saveNewTokenToOrg(String organisationLogin){
    Organisation existingOrg = organisationRepository.findByOrganisationLogin(organisationLogin);
    String token = generateToken(existingOrg);
    existingOrg.setOrganisationToken(token);
    organisationRepository.save(existingOrg);
    return token;
  }

  public String generateToken(Organisation organisation) {
    HashMap<String, Object> claims = new HashMap<>();
    claims.put("organisationLogin", organisation.getOrganisationLogin());
    String token = generateTokenByClaims(claims);
    return token;
  }

  private String generateTokenByClaims(HashMap<String, Object> claims) {
    this.key = MacProvider.generateKey();
    String token = Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS256, this.key)
        .compact();
    return token;
  }

  private Claims getClaimsFromToken(String token) {
    Claims claims;
    claims = Jwts.parser()
        .setSigningKey(this.key)
        .parseClaimsJws(token)
        .getBody();
    return claims;
  }


  private String getLoginFromToken(String token) {
    String id = new String();
    try {
      Claims claims = getClaimsFromToken(token);
      id = Integer.toString((Integer)(claims.get("id")));
    } catch (MissingClaimException e) {
      id = null;
    }
    return id;
  }

  public Organisation getOrganisationFromToken(String token) {
    String loginFromToken = getLoginFromToken(token);
    return organisationRepository.findByOrganisationLogin(loginFromToken);
  }
}

