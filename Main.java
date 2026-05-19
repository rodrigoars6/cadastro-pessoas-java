import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Pessoa> lista = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static String arquivo = "pessoas.txt";

    public static void main(String[] args) {

        lerArquivo();

        int opcao;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Listar Pessoas");
            System.out.println("9 - Sair");

            System.out.print("Digite a opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    incluirPessoa();
                    break;

                case 2:
                    alterarPessoa();
                    break;

                case 3:
                    excluirPessoa();
                    break;

                case 4:
                    listarPessoas();
                    break;

                case 9:
                    salvarArquivo();
                    System.out.println("Programa encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 9);

    }

    // INCLUIR
    public static void incluirPessoa() {

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.print("Cargo: ");
        String cargo = sc.nextLine();

        Pessoa p = new Pessoa(nome, idade, cargo);

        lista.add(p);

        System.out.println("Pessoa cadastrada!");
    }

    // ALTERAR
    public static void alterarPessoa() {

        System.out.print("Digite o nome da pessoa: ");
        String nomeBusca = sc.nextLine();

        for (Pessoa p : lista) {

            if (p.getNome().equalsIgnoreCase(nomeBusca)) {

                System.out.println("Pessoa encontrada!");

                System.out.print("Novo nome: ");
                p.setNome(sc.nextLine());

                System.out.print("Nova idade: ");
                p.setIdade(sc.nextInt());
                sc.nextLine();

                System.out.print("Novo cargo: ");
                p.setCargo(sc.nextLine());

                System.out.println("Pessoa alterada!");

                return;
            }
        }

        System.out.println("Pessoa não encontrada!");
    }

    // EXCLUIR
    public static void excluirPessoa() {

        System.out.print("Digite o nome da pessoa: ");
        String nomeBusca = sc.nextLine();

        for (Pessoa p : lista) {

            if (p.getNome().equalsIgnoreCase(nomeBusca)) {

                lista.remove(p);

                System.out.println("Pessoa excluída!");

                return;
            }
        }

        System.out.println("Pessoa não encontrada!");
    }

    // LISTAR
    public static void listarPessoas() {

        System.out.println("\n===== LISTA =====");

        for (Pessoa p : lista) {

            System.out.println("Nome: " + p.getNome());
            System.out.println("Idade: " + p.getIdade());
            System.out.println("Cargo: " + p.getCargo());
            System.out.println("----------------------");
        }
    }

    // SALVAR TXT
    public static void salvarArquivo() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));

            for (Pessoa p : lista) {

                writer.write(p.toString());
                writer.newLine();
            }

            writer.close();

            System.out.println("Arquivo salvo!");

        } catch (IOException e) {

            System.out.println("Erro ao salvar arquivo.");
        }
    }

    // LER TXT
    public static void lerArquivo() {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(arquivo));

            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(",");

                String nome = dados[0];
                int idade = Integer.parseInt(dados[1]);
                String cargo = dados[2];

                Pessoa p = new Pessoa(nome, idade, cargo);

                lista.add(p);
            }

            reader.close();

            System.out.println("Arquivo carregado!");

        } catch (IOException e) {

            System.out.println("Arquivo não encontrado. Será criado.");
        }
    }
}
