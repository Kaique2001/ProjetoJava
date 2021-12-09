
package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<UsuarioDTO> lista = new ArrayList<>();
    
    public void cadastrarUsuario(UsuarioDTO objusuariodto){
        String sql = "INSERT INTO usuarios (email, senha) VALUES (?,?)";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getEmail());
            pstm.setString(2, objusuariodto.getSenha());
            
            pstm.execute();
            pstm.close();
            
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null, "UsuarioDAO Cadastrar: " + erro);
        }
    }
    
    public ArrayList<UsuarioDTO> PesquisarUsuario() {
        String sql = "SELECT * FROM usuarios";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                UsuarioDTO objusuarioDTO = new UsuarioDTO();
                objusuarioDTO.setId(rs.getInt("id"));
                objusuarioDTO.setEmail(rs.getString("email"));
                objusuarioDTO.setSenha(rs.getString("senha"));
                
                lista.add(objusuarioDTO);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showConfirmDialog(null, "UsuarioDAO Pesquisar: " + erro);
        }
        return lista;
    }
    
    public void excluirUsuario(UsuarioDTO objusuariodto){
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {            
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objusuariodto.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "UsuarioDAO Excluir: " + erro);
        }
    }
}