package net.proselyte.webfluxsecurity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;
import net.proselyte.webfluxsecurity.entity.UserRole;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {
  private Long id;
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  private UserRole role;
  private String firstName;
  private String lastName;
  private boolean enabled;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
