package execessao.quest4;

import java.time.LocalDate;
import java.util.Scanner;

public class ContaEspecial extends Conta {
    private double limite;
    public final double JUROS=0.03;
    private double limiteMax;

    public ContaEspecial(String agencia,int numConta,double saldo,String senha,double limite, double limiteMax) {
        super(agencia, numConta, saldo, senha);
        this.limite = limite;
        this.limiteMax = limiteMax;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getLimiteMax() {
        return limiteMax;
    }

    public void debitarJuros(){
        LocalDate dia = LocalDate.now();
        LocalDate data = LocalDate.of(dia.getYear(),dia.getMonth(),15);
        if(dia.equals(data)){
            if(getLimiteMax()!= getLimite()){
                double aux=this.getLimiteMax()-this.getLimite();
                aux*=this.JUROS;
                setLimite(this.getLimite()-aux);
                System.out.println("Saldo atual: R$"+ this.getLimite());
                
            }
            else
                System.out.println("Conta não utilizou o limite.");
        }
        
    }
    @Override
    public void sacar(double valor) throws SaldoExcecao{
        double aux;
        System.out.println("Digite a senha:");
        Scanner sc = new Scanner(System.in);
        String senha=sc.next();
        if(this.autenticar(senha)){
            aux = this.getSaldo()+this.getLimite();
            if(aux <valor)
                throw new SaldoExcecao("Saldo insuficiente. Saque não realizado.");
            else{
                aux = aux-valor;
                if(aux<=getLimiteMax()){
                    setSaldo(0);
                    setLimite(aux);
                }else
                    setSaldo(this.getSaldo()-valor);
                    
            }
             
            
        }
        
    }
    
    
    
    
}

