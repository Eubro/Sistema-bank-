package execessao.quest4;

import java.util.Scanner;
import java.util.Set;

public class Conta {
    protected String agencia;
    protected int numConta;
    protected double saldo;
    protected String senha;

    public Conta(String agencia, int numConta, double saldo, String senha) {
        this.agencia = agencia;
        this.numConta = numConta;
        this.saldo = saldo;
        this.senha = senha;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void depositar(double valor){
        this.setSaldo(this.getSaldo()+valor);
        
    }
    
    public void sacar(double valor) throws SaldoExcecao{
        double aux;
        System.out.println("Digite a senha:");
        Scanner sc = new Scanner(System.in);
        String senha=sc.next();
        if(this.autenticar(senha)){
            if(this.getSaldo() <valor)
                throw new SaldoExcecao("Saldo insuficiente. Saque não realizado.");
            else
                this.setSaldo(this.getSaldo()-valor);
            
        }
        
    }
    public boolean autenticar (String senha){
        if(this.getSenha().equals(senha)){
            System.out.println("Usuário validado");
            return true;
        }
        else{
            System.out.println("senha inválida!");
            return false;
        }
    }
    
    
}
