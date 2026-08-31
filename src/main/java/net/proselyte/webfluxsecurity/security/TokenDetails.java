package net.proselyte.webfluxsecurity.security;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TokenDetails {
  private Long userId;
  private String token;
  private Date issuedAt;
  private Date expiresAt;
}
