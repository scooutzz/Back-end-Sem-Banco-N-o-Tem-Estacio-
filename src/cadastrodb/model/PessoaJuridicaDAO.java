package cadastrodb.model;

import cadastrodb.model.util.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {

    public PessoaJuridica getPessoa(int id) throws ClassNotFoundException, SQLException {
        PessoaJuridica pessoaJuridica = null;
        String sql = "SELECT * FROM PessoaJuridica WHERE id = ?";

        try (PreparedStatement ps = ConectorBD.getPrepared(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pessoaJuridica = new PessoaJuridica();
                    pessoaJuridica.setId(rs.getInt("id"));
                    pessoaJuridica.setNome(rs.getString("nome"));
                    pessoaJuridica.setLogradouro(rs.getString("logradouro"));
                    pessoaJuridica.setCidade(rs.getString("cidade"));
                    pessoaJuridica.setEstado(rs.getString("estado"));
                    pessoaJuridica.setTelefone(rs.getString("telefone"));
                    pessoaJuridica.setEmail(rs.getString("email"));
                    pessoaJuridica.setCnpj(rs.getString("cnpj"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoaJuridica;
    }

    public List<PessoaJuridica> getPessoas() throws ClassNotFoundException, SQLException {
        List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
        String sql = "SELECT * FROM PessoaJuridica";

        try (PreparedStatement ps = ConectorBD.getPrepared(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setId(rs.getInt("id"));
                pessoaJuridica.setNome(rs.getString("nome"));
                pessoaJuridica.setLogradouro(rs.getString("logradouro"));
                pessoaJuridica.setCidade(rs.getString("cidade"));
                pessoaJuridica.setEstado(rs.getString("estado"));
                pessoaJuridica.setTelefone(rs.getString("telefone"));
                pessoaJuridica.setEmail(rs.getString("email"));
                pessoaJuridica.setCnpj(rs.getString("cnpj"));
                pessoasJuridicas.add(pessoaJuridica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoasJuridicas;
    }

    public void incluir(PessoaJuridica pessoaJuridica) throws ClassNotFoundException, SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)";

        try (
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
            PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica)
        ) {
            psPessoa.setString(1, pessoaJuridica.getNome());
            psPessoa.setString(2, pessoaJuridica.getLogradouro());
            psPessoa.setString(3, pessoaJuridica.getCidade());
            psPessoa.setString(4, pessoaJuridica.getEstado());
            psPessoa.setString(5, pessoaJuridica.getTelefone());
            psPessoa.setString(6, pessoaJuridica.getEmail());

            psPessoa.executeUpdate();
            ResultSet rs = psPessoa.getGeneratedKeys();
            int pessoaId = 0;
            if (rs.next()) {
                pessoaId = rs.getInt(1);
            }

            psPessoaJuridica.setInt(1, pessoaId);
            psPessoaJuridica.setString(2, pessoaJuridica.getCnpj());
            psPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaJuridica pessoaJuridica) throws ClassNotFoundException, SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj=? WHERE id=?";

        try (
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
            PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica)
        ) {
            psPessoa.setString(1, pessoaJuridica.getNome());
            psPessoa.setString(2, pessoaJuridica.getLogradouro());
            psPessoa.setString(3, pessoaJuridica.getCidade());
            psPessoa.setString(4, pessoaJuridica.getEstado());
            psPessoa.setString(5, pessoaJuridica.getTelefone());
            psPessoa.setString(6, pessoaJuridica.getEmail());
            psPessoa.setInt(7, pessoaJuridica.getId());

            psPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            psPessoaJuridica.setInt(2, pessoaJuridica.getId());

            psPessoa.executeUpdate();
            psPessoaJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) throws ClassNotFoundException, SQLException {
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE id=?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";

        try (
            PreparedStatement psPessoaJuridica = ConectorBD.getPrepared(sqlPessoaJuridica);
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa)
        ) {
            psPessoaJuridica.setInt(1, id);
            psPessoa.setInt(1, id);

            psPessoaJuridica.executeUpdate();
            psPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}