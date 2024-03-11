package com.codigo.examen.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class SignInRequest {
    @NotEmpty(message = "El nombre no puede estar vacio o nulo.")
    @Size(min = 2, max = 50, message = "Este campo debe tener entre 2 a 50 caracteres.")
    private String username;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, un carácter especial y tener un mínimo de 8 dígitos."
    )
    @NotBlank(message = "Este campo no puede ser nulo")
    private String password;
}
