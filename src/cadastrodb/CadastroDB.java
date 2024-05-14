package cadastrodb;

import cadastrodb.model.PessoaFisica;
import cadastrodb.model.PessoaFisicaDAO;
import cadastrodb.model.PessoaJuridica;
import cadastrodb.model.PessoaJuridicaDAO;
import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

public class CadastroDB {
    public static void main(String[] args) throws ClassNotFoundException, SQLException  {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Incluir");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Obter pelo ID");
            System.out.println("5. Obter Todos");
            System.out.println("0. Sair");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    incluir(scanner);
                    break;
                case 2:
                    alterar(scanner);
                    break;
                case 3:
                    excluir(scanner);
                    break;
                case 4:
                    obterPorId(scanner);
                    break;
                case 5:
                    obterTodos();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void incluir(Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Selecione o tipo (1 para Pessoa Física, 2 para Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        if (tipo == 1) {
            PessoaFisica pessoaFisica = obterDadosPessoaFisica(scanner);
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            pessoaFisicaDAO.incluir(pessoaFisica);
            System.out.println("Pessoa física incluída com sucesso!");
        } else if (tipo == 2) {
            PessoaJuridica pessoaJuridica = obterDadosPessoaJuridica(scanner);
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            pessoaJuridicaDAO.incluir(pessoaJuridica);
            System.out.println("Pessoa jurídica incluída com sucesso!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static PessoaFisica obterDadosPessoaFisica(Scanner scanner) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        System.out.println("Informe o nome:");
        pessoaFisica.setNome(scanner.nextLine());
        System.out.println("Informe o logradouro:");
        pessoaFisica.setLogradouro(scanner.nextLine());
        System.out.println("Informe a cidade:");
        pessoaFisica.setCidade(scanner.nextLine());
        System.out.println("Informe o estado:");
        pessoaFisica.setEstado(scanner.nextLine());
        System.out.println("Informe o telefone:");
        pessoaFisica.setTelefone(scanner.nextLine());
        System.out.println("Informe o e-mail:");
        pessoaFisica.setEmail(scanner.nextLine());
        System.out.println("Informe o CPF:");
        pessoaFisica.setCpf(scanner.nextLine());
        return pessoaFisica;
    }

    private static PessoaJuridica obterDadosPessoaJuridica(Scanner scanner) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        System.out.println("Informe o nome:");
        pessoaJuridica.setNome(scanner.nextLine());
        System.out.println("Informe o logradouro:");
        pessoaJuridica.setLogradouro(scanner.nextLine());
        System.out.println("Informe a cidade:");
        pessoaJuridica.setCidade(scanner.nextLine());
        System.out.println("Informe o estado:");
        pessoaJuridica.setEstado(scanner.nextLine());
        System.out.println("Informe o telefone:");
        pessoaJuridica.setTelefone(scanner.nextLine());
        System.out.println("Informe o e-mail:");
        pessoaJuridica.setEmail(scanner.nextLine());
        System.out.println("Informe o CNPJ:");
        pessoaJuridica.setCnpj(scanner.nextLine());
        return pessoaJuridica;
    }

    private static void alterar(Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Selecione o tipo (1 para Pessoa Física, 2 para Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe o ID:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);
            if (pessoaFisica != null) {
                System.out.println("Dados atuais da pessoa física:");
                System.out.println(pessoaFisica);

                System.out.println("Informe o novo nome:");
                pessoaFisica.setNome(scanner.nextLine());
                System.out.println("Informe o novo logradouro:");
                pessoaFisica.setLogradouro(scanner.nextLine());
                System.out.println("Informe a nova cidade:");
                pessoaFisica.setCidade(scanner.nextLine());
                System.out.println("Informe o novo estado:");
                pessoaFisica.setEstado(scanner.nextLine());
                System.out.println("Informe o novo telefone:");
                pessoaFisica.setTelefone(scanner.nextLine());
                System.out.println("Informe o novo e-mail:");
                pessoaFisica.setEmail(scanner.nextLine());
                System.out.println("Informe o novo CPF:");
                pessoaFisica.setCpf(scanner.nextLine());

                pessoaFisicaDAO.alterar(pessoaFisica);
                System.out.println("Pessoa física alterada com sucesso!");
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);
            if (pessoaJuridica != null) {
                System.out.println("Dados atuais da pessoa jurídica:");
                System.out.println(pessoaJuridica);

                System.out.println("Informe o novo nome:");
                pessoaJuridica.setNome(scanner.nextLine());
                System.out.println("Informe o novo logradouro:");
                pessoaJuridica.setLogradouro(scanner.nextLine());
                System.out.println("Informe a nova cidade:");
                pessoaJuridica.setCidade(scanner.nextLine());
                System.out.println("Informe o novo estado:");
                pessoaJuridica.setEstado(scanner.nextLine());
                System.out.println("Informe o novo telefone:");
                pessoaJuridica.setTelefone(scanner.nextLine());
                System.out.println("Informe o novo e-mail:");
                pessoaJuridica.setEmail(scanner.nextLine());
                System.out.println("Informe o novo CNPJ:");
                pessoaJuridica.setCnpj(scanner.nextLine());

                pessoaJuridicaDAO.alterar(pessoaJuridica);
                System.out.println("Pessoa jurídica alterada com sucesso!");
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void excluir(Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Selecione o tipo (1 para Pessoa Física, 2 para Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe o ID:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            pessoaFisicaDAO.excluir(id);
            System.out.println("Pessoa física excluída com sucesso!");
        } else if (tipo == 2) {
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            pessoaJuridicaDAO.excluir(id);
            System.out.println("Pessoa jurídica excluída com sucesso!");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void obterPorId(Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Selecione o tipo (1 para Pessoa Física, 2 para Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe o ID:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);
            if (pessoaFisica != null) {
                System.out.println("Pessoa física encontrada:");
                System.out.println(pessoaFisica);
            } else {
                System.out.println("Pessoa física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);
            if (pessoaJuridica != null) {
                System.out.println("Pessoa jurídica encontrada:");
                System.out.println(pessoaJuridica);
            } else {
                System.out.println("Pessoa jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }

    private static void obterTodos(Scanner scanner) throws ClassNotFoundException, SQLException {
        System.out.println("Selecione o tipo (1 para Pessoa Física, 2 para Pessoa Jurídica):");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
            List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
            System.out.println("Pessoas físicas no banco de dados:");
            for (PessoaFisica pessoa : pessoasFisicas) {
                System.out.println(pessoa);
            }
        } else if (tipo == 2) {
            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
            System.out.println("Pessoas jurídicas no banco de dados:");
            for (PessoaJuridica pessoa : pessoasJuridicas) {
                System.out.println(pessoa);
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }
}
