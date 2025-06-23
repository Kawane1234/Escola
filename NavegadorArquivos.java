import java.io.File;

import javax.swing.JOptionPane;

public class NavegadorArquivos {

    private static File diretorioAtual;

    public static void main(String[] args) {
        diretorioAtual = new File(System.getProperty("user.dir")); // Diretório inicial: onde o programa está sendo executado

        while (true) {
            String[] opcoes = {"Listar Conteudo", "Entrar em Subdiretorio", "Voltar ao Diretorio Pai", "Sair"};
            int escolha = JOptionPane.showOptionDialog(
                null,
                "Diretorio atual:\n" + diretorioAtual.getAbsolutePath(),
                "Navegador de Arquivos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            if (escolha == 0) {
                listarConteudo();
            } else if (escolha == 1) {
                entrarSubdiretorio();
            } else if (escolha == 2) {
                voltarDiretorioPai();
            } else if (escolha == 3 || escolha == JOptionPane.CLOSED_OPTION) {
                break;
            }
        }
    }

    private static void listarConteudo() {
        File[] arquivos = diretorioAtual.listFiles();
        if (arquivos == null || arquivos.length == 0) {
            JOptionPane.showMessageDialog(null, "Diretorio vazio ou inacessivel.");
            return;
        }

        StringBuilder conteudo = new StringBuilder("Conteudo de: " + diretorioAtual.getAbsolutePath() + "\n\n");
        for (File arquivo : arquivos) {
            conteudo.append(arquivo.getName());
            if (arquivo.isDirectory()) {
                conteudo.append(" [DIR]");
            }
            conteudo.append("\n");
        }
        JOptionPane.showMessageDialog(null, conteudo.toString());
    }

    private static void entrarSubdiretorio() {
        File[] arquivos = diretorioAtual.listFiles();
        if (arquivos == null || arquivos.length == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum subdiretorio disponivel.");
            return;
        }

        StringBuilder subdirs = new StringBuilder();
        for (int i = 0; i < arquivos.length; i++) {
            if (arquivos[i].isDirectory()) {
                subdirs.append(i).append(" - ").append(arquivos[i].getName()).append("\n");
            }
        }

        if (subdirs.length() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum subdiretorio encontrado.");
            return;
        }

        String input = JOptionPane.showInputDialog(null, "Subdiretorios:\n" + subdirs + "\nDigite o numero do diretorio para entrar:");
        if (input == null) return;

        try {
            int indice = Integer.parseInt(input);
            if (indice < 0 || indice >= arquivos.length || !arquivos[indice].isDirectory()) {
                JOptionPane.showMessageDialog(null, "Indice invalido ou não é um diretorio.");
            } else {
                diretorioAtual = arquivos[indice];
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida. Digite um numero.");
        }
    }

    private static void voltarDiretorioPai() {
        File pai = diretorioAtual.getParentFile();
        if (pai == null) {
            JOptionPane.showMessageDialog(null, "Diretorio pai não disponivel (você está na raiz).");
        } else {
            diretorioAtual = pai;
        }
    }
}
