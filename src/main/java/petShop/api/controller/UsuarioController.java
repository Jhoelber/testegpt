package petShop.api.controller;
import petShop.api.domain.usuario.Usuario;
import petShop.api.infra.security.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Void> registrar(@RequestBody Usuario usuario) {
        usuarioService.registrarUsuario(usuario.getLogin(), usuario.getSenha());
        return ResponseEntity.ok().build();
    }
}
