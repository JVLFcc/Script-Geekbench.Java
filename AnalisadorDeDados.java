import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AnalisadorDeDados {

    public static void main(String[] args) {
        String arquivo = "Planilha2.CSV"; //? para verificar diferentes arquivos, é só mudar o nome aqui

        ArrayList<Double> single = new ArrayList<>();
        ArrayList<Double> multi = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean primeira = true;

            while ((linha = br.readLine()) != null) {
                if (primeira) { //* pra pular os nomes
                    
                    primeira = false;
                    continue;
                }
                String[] partes = linha.trim().split(","); //! metodo trim() apenas para se certificar com espeços em branco
                if (partes.length >= 3) {
                    double singleValue = Double.parseDouble(partes[1]);
                    double multiValue = Double.parseDouble(partes[2]);

                    single.add(singleValue);
                    multi.add(multiValue);
                }
            }

            System.out.println("----- Resultados Calculados do Geekbench -----");
            System.out.printf("Single-Core (média): %.4f%n", media(single));
            System.out.printf("Single-Core (desvio padrão): %.4f%n", desvio(single));
            System.out.printf("Multi-Core (média): %.4f%n", media(multi));
            System.out.printf("Multi-Core (desvio padrão): %.4f%n", desvio(multi));

        } catch (IOException e) {
            e.printStackTrace(); //todo: meio desnecessário, mas como outra pessoa que vai rodar, é útil deixar assim
        }
    }

    //! função q calcula a média
    public static double media(ArrayList<Double> valores) {
        double soma = 0.0;

        for (double v : valores) {
            soma += v;
        }

        return soma / valores.size();
    }

    //! função pra calcular desvio padrão
    public static double desvio(ArrayList<Double> valores) {
        double m = media(valores);
        double soma = 0.0;

        for (double v : valores) {
            soma += Math.pow(v - m, 2);
        }

        return Math.sqrt(soma / (valores.size() - 1)); //* formula do desvio padrão
    }
}
