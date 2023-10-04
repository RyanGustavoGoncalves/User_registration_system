package br.com.criandoapi.projeto.Controller;

import br.com.criandoapi.projeto.repository.IUsuario;
import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //endPoint
@CrossOrigin("*") //Liberando entrada
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios () {
        return ResponseEntity.status(200).body(usuarioService.listarUsuario());
    }
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario));
    }
    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario (@PathVariable Integer id){
        usuarioService.excluirUsuario(id);
        return ResponseEntity.status(204).build();
    }
    @PostMapping("/login")
    public ResponseEntity<Usuario> validarSenha(@Valid @RequestBody Usuario usuario){
        Boolean valid = usuarioService.validarSenha(usuario);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else{
            return ResponseEntity.status(200).build();
        }
    }
    //Pegando o erro de cadastro
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
