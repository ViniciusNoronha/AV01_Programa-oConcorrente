package av01;

public class Main {
    public static void main(String[] args) {
        Conta bancoConta = new Conta(0);
        Conta[] contasFunc = new Conta[4];
        Conta[] contasClie = new Conta[5];
        Conta[] contasInv = new Conta[4];

        for (int i = 0; i < 4; i++) {
            contasFunc[i] = new Conta(0);
            contasInv[i] = new Conta(0);
        }

        for (int i = 0; i < 5; i++) {
            contasClie[i] = new Conta(1000);
        }

        Banco banco = new Banco(); // Instância do banco para gerenciar transações
        Loja[] lojas = new Loja[2];

        for (int i = 0; i < 2; i++) {
            lojas[i] = new Loja(new Conta(0), banco); // Passar a instância do banco para as lojas
        }

        Thread[] funcionarios = new Thread[4];
        Thread[] clientes = new Thread[5];

        for (int i = 0; i < 4; i++) {
            Funcionario funcionario = new Funcionario(contasFunc[i], contasInv[i], banco); // Passar banco para os funcionários
            funcionarios[i] = new Thread(funcionario);
            funcionarios[i].start();
        }

        for (int i = 0; i < 5; i++) {
            clientes[i] = new Thread(new Cliente(contasClie[i], lojas, banco)); // Passar banco para os clientes
            clientes[i].start();
        }

        // Esperar até que todas as threads dos clientes terminem
        for (Thread cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Pagar funcionários após todos os clientes terem terminado suas compras
        for (Loja loja : lojas) {
            for (Conta contasFuncionario : contasFunc) {
                loja.pagarFuncionario(contasFuncionario);
            }
        }

        // Exibir saldos finais das contas
        System.out.println("Saldos finais das contas:");
        System.out.println("Conta do Banco: R$" + bancoConta.getSaldo());
        for (int i = 0; i < 4; i++) {
            System.out.println("Saldo do Funcionário " + (i + 1) + ": R$" + contasFunc[i].getSaldo());
            System.out.println("Saldo do Investimento do Funcionário " + (i + 1) + ": R$" + contasInv[i].getSaldo());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Saldo do Cliente " + (i + 1) + ": R$" + contasClie[i].getSaldo());
        }
    }
}