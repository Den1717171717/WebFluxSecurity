package net.proselyte.webfluxsecurity.security;

import java.security.Principal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPrincipal implements Principal {

  private Long id;
  private String name;

  @Override
  public String getName() {
    return "";
  }
}
