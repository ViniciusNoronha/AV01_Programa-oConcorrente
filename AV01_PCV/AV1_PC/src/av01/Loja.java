package av01;

public class Loja {
    private Conta conta;
    private double salarioFuncionario = 1400;

    public Loja(Conta conta, Banco banco) {
        this.conta = conta;
    }

    public void pagarFuncionario(Conta contaFuncionario) {
        if (conta.getSaldo() >= salarioFuncionario) {
            conta.debitar(salarioFuncionario);
            contaFuncionario.creditar(salarioFuncionario);
            System.out.println("Salário de funcionário pago: R$" + salarioFuncionario);
        } else {
            System.out.println("Saldo insuficiente para pagar funcionário.");
        }
    }
}
