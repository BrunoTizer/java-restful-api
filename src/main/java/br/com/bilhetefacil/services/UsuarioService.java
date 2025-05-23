package br.com.bilhetefacil.services;

import br.com.bilhetefacil.dao.UsuarioDAO;
import br.com.bilhetefacil.model.Usuario;

import java.sql.SQLException;

import java.util.List;
import java.sql.Timestamp;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() throws SQLException, ClassNotFoundException {
        this.usuarioDAO = new UsuarioDAO();
    }

    public String cadastrarUsuario(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return "Erro: O nome é obrigatório.";
        }

        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            return "Erro: Email inválido.";
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 4) {
            return "Erro: A senha deve ter pelo menos 4 caracteres.";
        }

        String status = usuario.getStatusPagamentoTaxa();
        if (usuario.getStatusPagamentoTaxa() == null || (!usuario.getStatusPagamentoTaxa().equals("S") && !usuario.getStatusPagamentoTaxa().equals("N"))) {
            return "Erro: Status de pagamento da taxa deve ser 'S' ou 'N'.";
        }

        usuario.setDataCadastro(new Timestamp(System.currentTimeMillis()));

        return usuarioDAO.inserir(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    public Usuario buscarUsuario(int id) {
        return usuarioDAO.buscar(id);
    }

    public String atualizarUsuario(Usuario usuario) {
        if (usuario.getIdUsuario() == 0) {
            return "Erro: ID do usuário é obrigatório.";
        }
        return usuarioDAO.atualizar(usuario);
    }

    public String atualizarFaceId(int id, String faceIdHash) {
        return usuarioDAO.atualizarFaceId(id, faceIdHash);
    }

    public String deletarUsuario(int id) {
        return usuarioDAO.deletar(id);
    }


}
