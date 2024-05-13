package cadastrodb.model;

import cadastrodb.model.util.ConectorBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {

    public PessoaFisica getPessoa(int id) throws ClassNotFoundException, SQLException {
        PessoaFisica pessoa = null;
        String sql = "SELECT * FROM PessoaFisica WHERE id = ?";

        try (PreparedStatement ps = ConectorBD.getPrepared(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pessoa = new PessoaFisica();
                    pessoa.setId(rs.getInt("id"));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setLogradouro(rs.getString("logradouro"));
                    pessoa.setCidade(rs.getString("cidade"));
                    pessoa.setEstado(rs.getString("estado"));
                    pessoa.setTelefone(rs.getString("telefone"));
                    pessoa.setEmail(rs.getString("email"));
                    pessoa.setCpf(rs.getString("cpf"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }

    public List<PessoaFisica> getPessoas() throws ClassNotFoundException, SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM PessoaFisica";

        try (PreparedStatement ps = ConectorBD.getPrepared(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PessoaFisica pessoa = new PessoaFisica();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setLogradouro(rs.getString("logradouro"));
                pessoa.setCidade(rs.getString("cidade"));
                pessoa.setEstado(rs.getString("estado"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) throws ClassNotFoundException, SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)";

        try (
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
            PreparedStatement psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica)
        ) {
            System.out.println("teste");
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getLogradouro());
            psPessoa.setString(3, pessoa.getCidade());
            psPessoa.setString(4, pessoa.getEstado());
            psPessoa.setString(5, pessoa.getTelefone());
            psPessoa.setString(6, pessoa.getEmail());

            psPessoa.executeUpdate();
            ResultSet rs = psPessoa.getGeneratedKeys();
            int pessoaId = 0;
            if (rs.next()) {
                pessoaId = rs.getInt(1);
            }

            psPessoaFisica.setInt(1, pessoaId);
            psPessoaFisica.setString(2, pessoa.getCpf());
            psPessoaFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(PessoaFisica pessoa) throws ClassNotFoundException, SQLException {
        String sqlPessoa = "UPDATE Pessoa SET nome=?, logradouro=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf=? WHERE id=?";

        try (
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa);
            PreparedStatement psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica)
        ) {
            psPessoa.setString(1, pessoa.getNome());
            psPessoa.setString(2, pessoa.getLogradouro());
            psPessoa.setString(3, pessoa.getCidade());
            psPessoa.setString(4, pessoa.getEstado());
            psPessoa.setString(5, pessoa.getTelefone());
            psPessoa.setString(6, pessoa.getEmail());
            psPessoa.setInt(7, pessoa.getId());

            psPessoaFisica.setString(1, pessoa.getCpf());
            psPessoaFisica.setInt(2, pessoa.getId());

            psPessoa.executeUpdate();
            psPessoaFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) throws ClassNotFoundException, SQLException {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id=?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id=?";

        try (
            PreparedStatement psPessoaFisica = ConectorBD.getPrepared(sqlPessoaFisica);
            PreparedStatement psPessoa = ConectorBD.getPrepared(sqlPessoa)
        ) {
            psPessoaFisica.setInt(1, id);
            psPessoa.setInt(1, id);

            psPessoaFisica.executeUpdate();
            psPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
