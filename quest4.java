package execessao.quest4;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class quest4 {
    public static void main(String[] args ){
        Scanner sc = new Scanner(System.in);
        ArrayList<Conta> carteira = new ArrayList<Conta>();
        int opcao, opcao2;
        boolean flag=true;
        double valor=0;
        do{
            System.out.println("""
                               1.Cadastrar uma conta
                               2.Realizar Saque
                               3.Realizar Deposito
                               4. Calcular rendimento de poupança
                               5.Debitar Juros de Conta Especial
                               6.Sair
                               Digite opção:
                               """);
            opcao = sc.nextInt();
            switch(opcao){
                case 1:
                    System.out.println("1. Conta Corrente\n2 Conta Especial\n3 conta poupança");
                    opcao2= sc.nextInt();
                    switch(opcao2){
                        case 1:
                            System.out.println("Digite: agencia, conta, saldo, senha");
                            Conta a= new Conta(sc.next(),sc.nextInt(), sc.nextDouble(),sc.next() );
                            carteira.add(a);
                            break;
                        case 2:
                            System.out.println("Digite: agencia, conta, saldo, senha, limite");
                            ContaEspecial b= new ContaEspecial(sc.next(),sc.nextInt(), sc.nextDouble(),sc.next(), sc.nextDouble(), sc.nextDouble() );
                            carteira.add(b);
                            break;
                        case 3:
                            System.out.println("Digite: agencia, conta, saldo, senha");
                            ContaPoupanca c = new ContaPoupanca(sc.next(),sc.nextInt(), sc.nextDouble(),sc.next(),sc.nextDouble() );
                            carteira.add(c);
                            break;
                            
                    }
                case 2:
                    System.out.println("qual o tipo de conta [1. Conta Corrente\n2 Conta Especial\n3 conta poupança]");
                    opcao2=sc.nextInt();
                    System.out.println("Digite numero de conta:");
                    Conta aux = busca(sc.nextInt(), carteira);
                    if(aux!=null){
                        System.out.println("valor para saque:");
                        try{
                            aux.sacar(sc.nextDouble());
                        }
                        catch(SaldoExcecao e){
                            System.out.println(e.getMessage());
                        }
                        finally{
                            if(opcao2==1 || opcao2==3)
                                System.out.println("Saldo:"+aux.getSaldo());
                            else
                                System.out.println("Saldo:"+(((ContaEspecial)aux).getSaldo()+((ContaEspecial)aux).getLimite()));
                        }
                    }
                    else 
                        System.out.println("Conta inexistente.");
                    break;
                case 3:
                    System.out.println("qual o tipo de conta [1. Conta Corrente\n2 Conta Especial\n3 conta poupança]");
                    opcao2=sc.nextInt();
                    System.out.println("Digite numero de conta:");
                    Conta aux2 = busca(sc.nextInt(), carteira);
                    if(aux2!=null){
                        do{
                            try{
                                System.out.println("Digite valor a depositar");
                                valor= sc.nextDouble();
                                aux2.depositar(valor);
                                flag=false;
                            }
                            catch(InputMismatchException e ){
                                System.out.println("Tipo inválido");
                                valor = 0;
                                sc.next();
                                
                            }
                            finally{
                                if(opcao2==1|| opcao2==3)
                                    System.out.println("Saldo:"+aux2.getSaldo());
                                else
                                    System.out.println("saldo"+(aux2.getSaldo()+((ContaEspecial)aux2).getLimite()));
                                
                                    
                            }
                            else
                            System.out.println("Conta inexistente.");
                            break;
                        }
                    }
                case 4:
                    System.out.println("Digite numero da conta:");
                    Conta aux3= busca(sc.nextInt(), carteira);
                    if(aux3!=null){
                    System.out.println("Taxa de rendimento:");
                    ((ContaPoupanca)aux3).setPercRendimento(sc.nextDouble());
                    ((ContaPoupanca)aux3).calcularRendimento();
                    System.out.println("Saldo:"+aux3.getSaldo());
                }
                else
                    System.out.println("conta inexistente.");
                break;
                case 5:
                    System.out.println("Digite numero da conta:");
                    Conta aux4 = busca(sc.nextInt(), carteira);
                    if(aux4!=null){
                        ((ContaEspecial)aux4).debitarJuros();
                    }
                    else
                        System.out.println("Conta inexistente");
            }
        }while(opcao!=6);
        sc.close();
        
    }
    public static Conta busca(int numero, ArrayList<Conta> lista){
        for(Conta elemento:lista){
            if(elemento.getNumConta()==numero)
            return elemento;
            
        }
        return null;
    }
}
