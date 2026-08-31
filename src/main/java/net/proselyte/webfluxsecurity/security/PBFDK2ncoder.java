package net.proselyte.webfluxsecurity.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import net.proselyte.webfluxsecurity.exception.PasswordEncodingException;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PBFDK2ncoder implements PasswordEncoder {

  @Value("${jwt.password.encoder.secret}")
  String secret;

  @Value("${jwt.password.encoder.iterations}")
  private Integer iterations;

  @Value("${jwt.password.encoder.keylength}")
  private Integer keyLength;

  private static final String SECRET_KEY_INSTANCE = "PBKDF2WithHmacSHA512";

  @Override
  public @Nullable String encode(@Nullable CharSequence rawPassword) {
    try {
      var result =
          SecretKeyFactory.getInstance(SECRET_KEY_INSTANCE)
              .generateSecret(
                  new PBEKeySpec(
                      rawPassword.toString().toCharArray(),
                      secret.getBytes(),
                      iterations,
                      keyLength))
              .getEncoded();
      return Base64.getEncoder().encodeToString(result);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new PasswordEncodingException(
          "Error occurred while encoding password: " + e.getMessage());
    }
  }

  @Override
  public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
    return encode(rawPassword).equals(encodedPassword);
  }
}
