package com.codigo.examen.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
public class SignUpRequest {
    private Long idUsuario;
    @NotEmpty(message = "El nombre no puede estar vacio o nulo.")
    @Size(min = 2, max = 50, message = "Este campo debe tener entre 2 a 50 caracteres.")
    private String username;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, un carácter especial y tener un mínimo de 8 dígitos."
    )
    @NotBlank(message = "Este campo no puede ser nulo")
    private String password;
    @Email(message = "El formato del correo electrónico no es válido.")
    @NotBlank(message = "Este campo no puede ser nulo")
    private String email;
    @Pattern(regexp = "\\d{8,15}", message = "El número de teléfono debe tener entre 8 y 15 dígitos.")
    private String telefono;
    @NotNull(message = "El conjunto de roles no puede ser nulo")
    @Size(min = 1, message = "Se debe especificar al menos un rol")
    private Set<String> roles = new HashSet<>();
}
