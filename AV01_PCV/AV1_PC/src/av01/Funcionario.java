package av01;

class Funcionario implements Runnable {
    private Conta contaSalario;
    private Conta contaInvestimento;
    private double salario = 1400;

    public Funcionario(Conta contaSalario, Conta contaInvestimento, Banco banco) {
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }

    @Override
    public void run() {
        receberSalario();
        investirSalario();
    }

    private void receberSalario() {
        contaSalario.creditar(salario);
    }

    private void investirSalario() {
        double valorInvestimento = salario * 0.2;
        contaSalario.debitar(valorInvestimento);
        contaInvestimento.creditar(valorInvestimento);
        System.out.println("Funcionário investiu R$" + valorInvestimento);
    }
}

