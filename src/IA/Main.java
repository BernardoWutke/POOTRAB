public class Main {
    public static void main(String[] args){
        CarrinhoMaps maps = new CarrinhoMaps(5, 5);
        
        maps.marcarObstaculo(0, 1);
        maps.marcarObstaculo(1, 1);
        maps.marcarObstaculo(2, 1);
        maps.marcarObstaculo(3, 1);
        maps.marcarObstaculo(4, 3);
        maps.marcarObstaculo(3, 3);

        String caminho = maps.gerarCaminho(0, 0, 4, 4);
        System.out.println(caminho);
    }
}
