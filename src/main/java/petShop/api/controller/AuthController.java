package petShop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import petShop.api.domain.usuario.Usuario;
import petShop.api.infra.security.DadosTokenJWT;
import petShop.api.infra.security.TokenService;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getSenha())
            );

            // Carrega os detalhes do usuário
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gera o token
            String token = tokenService.gerarToken((Usuario) userDetails);

            // Retorna o token
            return ResponseEntity.ok(new DadosTokenJWT(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
