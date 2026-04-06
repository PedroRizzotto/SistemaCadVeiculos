import java.util.ArrayList;
import java.util.List;

List<String> veiculos = new ArrayList<>();

void main() {
    IO.println("Bem vindo ao Sistema CadVeiculos");
    String menu = """
            MENU DE OPÇÕES
            1 - Cadastrar Veículo;
            2 - Lista Veículos;
            3 - Remover Veículo;
            4 - Buscar por Nome;
            5 - Editar Veículo;
            6 - Remover por Nome;
            0 - Sair
            """;

    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite a opção desejada: ");

        switch (opcao) {
            case 1 -> {
                cadastrar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 2 -> {
                listar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 3 -> {
                excluir();
                IO.readln("Pressione Enter para Continuar");
            }
            case 4 -> {
                buscarNome();
                IO.readln("Pressione Enter para Continuar");
            }
            case 5 -> {
                editar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 6 -> {
                removerNome();
                IO.readln("Pressione Enter para Continuar");
            }
            case 0 -> {
                IO.println("Volte sempre!!!");
            }
            default -> {
                IO.println("Opção Inválida");
                IO.readln("Pressione Enter para Continuar");
            }
        }

    } while (opcao != 0);
}

void excluir() {
    if (!listaVazia(veiculos)) {
        listar();
        int indice = Input.scanInt("Digite o indíce do veículo a ser removido: ");
        if (indice > veiculos.size() || indice <= 0)
            IO.println("Veículo não encontrado");
        else {
            veiculos.remove(indice - 1);
            IO.println("Veículo removido com sucesso!");
        }
    } else
        IO.println("Não há nenhum veículo cadastrado!");
}

void cadastrar() {
    String veiculo = IO.readln("Digite o nome do novo veículo: ");
    veiculo = veiculo.trim();

    if (validaNome(veiculo))
        veiculos.add(veiculo);
    else
        IO.println("Nome do veículo está vazio ou já existe!");
}

void listar() {
    if (!listaVazia(veiculos)) {
        veiculos = ordenar(veiculos);
        IO.println("Veículos cadastrados:");
        for (int i = 1; i <= veiculos.size(); i++) {
            IO.println(i + " - " + veiculos.get(i - 1));
        }
    } else
        IO.println("Não há nenhum veículo cadastrado.");
}

void buscarNome() {
    if (!listaVazia(veiculos)) {
        String pesquisa = IO.readln("Digite o nome do carro: ");
        int i = 1;
        for (String veiculoCad : veiculos) {
            if (veiculoCad.toLowerCase().contains(pesquisa.toLowerCase())) {
                IO.println(i + " - " + veiculoCad);
            }
            i++;
        }
    } else
        IO.println("Não há nenhum veículo cadastrado.");
}

void editar() {
    if (!listaVazia(veiculos)) {
        listar();
        int indice = Input.scanInt("Digite o indíce a ser editado: ");
        if (indice != 0 && indice - 1 < veiculos.size()) {
            String novoNome = IO.readln("Digite o novo nome: ");
            if (validaNome(novoNome)) {
                veiculos.set(indice - 1, novoNome);
                IO.println("Nome do veículo editado!");
            } else
                IO.println("O nome digitado já existe ou está vazio");
        } else
            IO.println("Indíce não existe!");
    } else
        IO.println("Não há nenhum veículo cadastrado.");
}

boolean existe(String nome) {
    for (String veiculoCad : veiculos) {
        if (veiculoCad.equalsIgnoreCase(nome))
            return true;
    }
    return false;
}

boolean validaNome(String nome) {
    nome = nome.trim();
    if (nome.isEmpty())
        return false;
    else if (existe(nome))
        return false;
    else
        return true;
}

void removerNome() {
    if (!listaVazia(veiculos)) {
        listar();
        String nome = IO.readln("Digite o nome do veículo a ser removido: ").trim();
        int i = 0;
        for (String veiculoCad : veiculos) {
            if (veiculoCad.equalsIgnoreCase(nome)) {
                veiculos.remove(i);
                IO.println("Veículo removido!");
                return;
            }
            i++;
        }
        IO.println("Veículo não encontrado!");
    } else
        IO.println("Não há nenhum veículo cadastrado.");
}

List<String> ordenar(List<String> lista) {
    for (int i = 0; i < lista.size() - 1; i++) {
        for (int j = 0; j < lista.size() - 1 - i; j++) {
            if (lista.get(j).compareToIgnoreCase(lista.get(j + 1)) >= 1) {
                String aux = lista.get(j);
                lista.set(j, lista.get(j + 1));
                lista.set(j + 1, aux);
            }
        }
    }
    return lista;
}

boolean listaVazia(List<String> lista) {
    if (lista.size() < 1)
        return true;
    else
        return false;
}