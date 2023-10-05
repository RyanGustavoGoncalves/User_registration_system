package br.com.criandoapi.projeto.service;

import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.repository.IUsuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private IUsuario repository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(IUsuario repository){
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public List<Usuario> listarUsuario(){
        List<Usuario> lista = repository.findAll();
        return lista;
    }
    public Usuario criarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }
    public Usuario editarUsuario(Usuario usuario){
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioNovo = repository.save(usuario);
        return usuarioNovo;
    }
    public Boolean excluirUsuario(Integer id){
        repository.deleteById(id);
        return true;
    }

    public Boolean validarSenha(Usuario usuario) {
        try {
            // Recupere a senha armazenada no banco de dados com base no ID do usuário
            String senhaArmazenada = repository.getReferenceById(usuario.getId()).getSenha();

            // Verifique se a senha fornecida corresponde à senha armazenada
            Boolean valid = passwordEncoder.matches(usuario.getSenha(), senhaArmazenada);
            return valid;
        } catch (Exception e) {
            // Lide com exceções, por exemplo, se o ID do usuário não existir no banco de dados
            return false; // ou lançar uma exceção adequada, dependendo da sua necessidade
        }
    }

}
