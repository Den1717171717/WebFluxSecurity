package net.proselyte.webfluxsecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import java.util.Date;
import net.proselyte.webfluxsecurity.exception.UnauthorizedException;
import reactor.core.publisher.Mono;

public class JwtHandler {

  private final String secret;

  JwtHandler(String secret) {
    this.secret = secret;
  }

  public static class VerificationResult {
    public Claims claims;
    public String token;

    public VerificationResult(Claims claims, String token) {
      this.claims = claims;
      this.token = token;
    }
  }

  public Mono<VerificationResult> check(String accessToken) {
    return Mono.just(verify(accessToken))
        .onErrorResume(e -> Mono.error(new UnauthorizedException(e.getMessage())));
  }

  private VerificationResult verify(String token) {
    Claims claims = getClaimsFromToken(token);
    var expirationDate = claims.getExpiration();

    if (expirationDate.before(new Date())) {
      throw new RuntimeException("Token expired");
    }

    return new VerificationResult(claims, token);
  }

  public Claims getClaimsFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
        .parseClaimsJws(token)
        .getBody();
  }
}
