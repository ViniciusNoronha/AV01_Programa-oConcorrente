package av01;


public class Conta {

	    private double saldo;

	    public Conta(double saldoInicial) {
	        this.saldo = saldoInicial;
	    }

	    public synchronized void debitar(double valor) {
	        this.saldo -= valor;
	    }

	    public synchronized void creditar(double valor) {
	        this.saldo += valor;
	    }

	    public synchronized double getSaldo() {
	        return saldo;
	    }
	}

