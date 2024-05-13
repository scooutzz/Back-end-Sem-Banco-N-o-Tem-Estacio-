package cadastrodb;

import cadastrodb.model.PessoaFisica;
import cadastrodb.model.PessoaFisicaDAO;
import cadastrodb.model.PessoaJuridica;
import cadastrodb.model.PessoaJuridicaDAO;
import java.sql.SQLException;

import java.util.List;

public class CadastroBDTeste {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Instanciar uma pessoa física e persistir no banco de dados
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Fulano");
        pessoaFisica.setLogradouro("Rua 123");
        pessoaFisica.setCidade("Cidade A");
        pessoaFisica.setEstado("Estado X");
        pessoaFisica.setTelefone("123456789");
        pessoaFisica.setEmail("fulano@example.com");
        pessoaFisica.setCpf("12345678901");

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.incluir(pessoaFisica);
        System.out.println("Pessoa física incluída: " + pessoaFisica);

        // Alterar os dados da pessoa física no banco
        pessoaFisica.setNome("Novo Nome");
        pessoaFisicaDAO.alterar(pessoaFisica);
        System.out.println("Pessoa física alterada: " + pessoaFisica);

        // Consultar todas as pessoas físicas do banco de dados e listar no console
        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
        System.out.println("Pessoas físicas no banco de dados:");
        for (PessoaFisica pessoa : pessoasFisicas) {
            System.out.println(pessoa);
        }

        // Excluir a pessoa física criada anteriormente no banco
        pessoaFisicaDAO.excluir(pessoaFisica.getId());
        System.out.println("Pessoa física excluída: " + pessoaFisica);

        // Instanciar uma pessoa jurídica e persistir no banco de dados
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("Empresa XYZ");
        pessoaJuridica.setLogradouro("Avenida ABC");
        pessoaJuridica.setCidade("Cidade B");
        pessoaJuridica.setEstado("Estado Y");
        pessoaJuridica.setTelefone("987654321");
        pessoaJuridica.setEmail("empresa@example.com");
        pessoaJuridica.setCnpj("12345678901234");

        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.incluir(pessoaJuridica);
        System.out.println("Pessoa jurídica incluída: " + pessoaJuridica);

        // Alterar os dados da pessoa jurídica no banco
        pessoaJuridica.setNome("Nova Empresa XYZ");
        pessoaJuridicaDAO.alterar(pessoaJuridica);
        System.out.println("Pessoa jurídica alterada: " + pessoaJuridica);

        // Consultar todas as pessoas jurídicas do banco de dados e listar no console
        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
        System.out.println("Pessoas jurídicas no banco de dados:");
        for (PessoaJuridica pessoa : pessoasJuridicas) {
            System.out.println(pessoa);
        }

        // Excluir a pessoa jurídica criada anteriormente no banco
        pessoaJuridicaDAO.excluir(pessoaJuridica.getId());
        System.out.println("Pessoa jurídica excluída: " + pessoaJuridica);
    }
}
