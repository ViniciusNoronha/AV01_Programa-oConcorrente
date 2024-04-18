package av01;

class Cliente implements Runnable {
    private Conta conta;
    private Loja[] lojas = new Loja[2];
    private double saldoInicial = 1000;

    public Cliente(Conta conta, Loja[] lojas, Banco banco) {
        this.conta = conta;
        this.lojas = lojas;
    }

    @Override
    public void run() {
        while (conta.getSaldo() > 0) {
            double valorCompra = Math.random() < 0.5 ? 100 : 200; // Compra aleatória de R$100 ou R$200
            int lojaIndex = (int) (Math.random() * 2); // Seleciona aleatoriamente uma das duas lojas
            realizarCompra(lojas[lojaIndex], valorCompra);
        }
    }

    private void realizarCompra(Loja loja, double valor) {
        if (conta.getSaldo() >= valor) {
            conta.debitar(valor);
            loja.pagarFuncionario(conta); // A loja paga seus funcionários a partir das compras dos clientes
            System.out.println("Cliente realizou compra de R$" + valor);
        } else {
            System.out.println("Saldo insuficiente para realizar compra de R$" + valor);
        }
    }

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
}