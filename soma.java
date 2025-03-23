import javax.swing.JOptionPane;
public class soma{
    public static void main(String[]args){
        String primeironumero = JOptionPane.showInputDialog ("Digite o primeiro numero inteiro");
        String segundonumero = JOptionPane.showInputDialog ("Digite o segundo numero inteiro");
        int numero1 = Integer.parseInt (primeironumero);
        int numero2 = Integer.parseInt (segundonumero);
        int soma = numero1 + numero2;
        JOptionPane.showMessageDialog (null,"A soma dos dois numeros: "+ soma,"Soma de dois inteiros", JOptionPane.PLAIN_MESSAGE);
    }
}