package net.proselyte.webfluxsecurity.security;

import lombok.RequiredArgsConstructor;
import net.proselyte.webfluxsecurity.exception.UnauthorizedException;
import net.proselyte.webfluxsecurity.repository.UserRepository;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

  private final UserRepository userRepository;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
      CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
      return userRepository.findById(principal.getId())
              .filter(user->user.isEnabled())
              .switchIfEmpty(Mono.error(new UnauthorizedException("User disabled")))
              .thenReturn(authentication);

  }
}
