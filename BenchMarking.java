public class BenchMarking {

    public static void main(String[] args) {
        int tamanhoMatriz;

        //! vê se o usuário forneceu um argumento
        if (args.length > 0) {
            try {

                //* converte a string do argumento passado p/ um número inteiro
                tamanhoMatriz = Integer.parseInt(args[0]);
                
                if (tamanhoMatriz <= 0) {
                    System.out.println("O tamanho da matriz deve ser um número inteiro positivo.");

                    return; //* encerra o programa se o tamanho não for válido por questões de conveniência e boa prática
                }
            
            } catch (NumberFormatException e) {

                //! segura o erro se o argumento não for um número
                System.out.println("Entrada inválida! Por favor, digite um número inteiro para o tamanho da matriz.");

                return; //* encerra o programa para esse caso tbm
            }
        
        } 
        //! se o usuário não fornecer um argumento
        else {
            //* caso nenhum argumento seja fornecido vai usar um valor padrão
            System.out.println("Nenhum tamanho de matriz fornecido. Usando o valor padrão de 1000.");

            tamanhoMatriz = 1000;
        }

        //! cria as matrizes com o tamanho obtido, ou do usuário, ou do valor padrão
        int[][] A = new int[tamanhoMatriz][tamanhoMatriz];
        int[][] B = new int[tamanhoMatriz][tamanhoMatriz];
        int[][] C = new int[tamanhoMatriz][tamanhoMatriz];

        //! preenche as matrizes com valores aleatŕios (de 0 a 9) usando o Math.Random
        for (int i = 0; i < tamanhoMatriz; i++) {
            for (int j = 0; j < tamanhoMatriz; j++) {
                A[i][j] = (int) (Math.random() * 10);
                B[i][j] = (int) (Math.random() * 10);
            }
        }
        
        //! início do Benchmarking
        long tempoInicio = System.nanoTime();

        //! lógica p/ a multiplicação das matrizes
        for (int i = 0; i < tamanhoMatriz; i++) {

            for (int j = 0; j < tamanhoMatriz; j++) {

                for (int k = 0; k < tamanhoMatriz; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        
        //! fim do Benchmarking
        long tempoFim = System.nanoTime();
        long duracao = tempoFim - tempoInicio;
        double duracaoSegundos = (double) duracao / 1_000_000_000.0;

        System.out.printf("Tempo de execução para a matriz %dx%d: %.4f segundos%n", tamanhoMatriz, tamanhoMatriz, duracaoSegundos);
    }
}