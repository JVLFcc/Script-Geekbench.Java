import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Geekbench {

    public static void main(String[] args) {
        String arquivo = "Planilha.CSV";

        ArrayList<Double> single = new ArrayList<>();
        ArrayList<Double> multi = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean primeira = true;

            while ((linha = br.readLine()) != null) {
                if (primeira) { // pra pular os nomes
                    
                    primeira = false;
                    continue;
                }
                String[] partes = linha.trim().split(",");
                if (partes.length >= 3) {

                    single.add(Double.parseDouble(partes[1]));
                    multi.add(Double.parseDouble(partes[2]));
                }
            }

            System.out.println("=== Resultados Geekbench ===");
            System.out.println();
            System.out.printf("Single-Core (média): %.4f%n", media(single));
            System.out.printf("Single-Core (desvio padrão): %.4f%n", desvio(single));
            System.out.printf("Multi-Core (média): %.4f%n", media(multi));
            System.out.printf("Multi-Core (desvio padrão): %.4f%n", desvio(multi));

        } catch (IOException e) {
            e.printStackTrace(); // meio desnecessário, mas como outra pessoa que vai rodar, é útil
        }
    }

    // Função q calcula a média
    public static double media(ArrayList<Double> valores) {
        double soma = 0.0;

        for (double v : valores) {
            soma += v;
        }

        return soma / valores.size();
    }

    // Função pra calcular desvio padrão
    public static double desvio(ArrayList<Double> valores) {
        double m = media(valores);
        double soma = 0.0;

        for (double v : valores) {
            soma += Math.pow(v - m, 2);
        }

        return Math.sqrt(soma / (valores.size() - 1)); // formula do desvio padrão
    }
}
