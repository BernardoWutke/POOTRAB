import java.util.LinkedList;
import java.util.Queue;

public class CarrinhoMaps {
    
    private Queue<Ponto> queue = new LinkedList<Ponto>();
    private int[][] movimentos = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private Ponto[][] mapaPontos;

    public CarrinhoMaps(int tamanhoHorizontal, int tamanhoVertical){
        mapaPontos = gerarMapa(tamanhoHorizontal, tamanhoVertical);
    }
 
    public String gerarCaminho(int xInicial,int yInical, int xDestino, int yDestino) {
        for (Ponto[] pontos : mapaPontos) {
            for (Ponto ponto : pontos) {
                ponto.setVisitado(false);
            }
        }

        queue.add(mapaPontos[xInicial][yInical]);
        String caminho = "";
        while (queue.size() != 0) {
            Ponto ponto = queue.poll();
            ponto.setVisitado(true);
            for(int i = 0; i < 4; i++){
                int newX = ponto.getCoordenadaX() + movimentos[i][0];
                int newY = ponto.getCoordenadaY() + movimentos[i][1];
                if(newX >= 0 && newX < mapaPontos.length && newY >= 0 && newY < mapaPontos[0].length){
                    Ponto newPonto = mapaPontos[newX][newY];
                    if(!newPonto.foiVisitado() && !newPonto.temObstaculo()) {
                        queue.add(newPonto);
                        newPonto.setCoordenadaXPai(ponto.getCoordenadaX());
                        newPonto.setCoordenadaYPai(ponto.getCoordenadaY());
                    }
                }
            }
        }
        int x = xDestino, y = yDestino;
        while(x != xInicial || y != yInical){
            Ponto ponto = mapaPontos[x][y];
            int movX = x - ponto.getCoordenadaXPai();
            int movY = y - ponto.getCoordenadaYPai();
            caminho = movX + "," + movY + ";" + caminho;

            x = ponto.getCoordenadaXPai();
            y = ponto.getCoordenadaYPai();

        }
        return caminho;
    }

    public Ponto[][] gerarMapa(int tamanhoHorizontal, int tamanhoVertical){
        Ponto[][] mapa = new Ponto[tamanhoVertical][tamanhoHorizontal];
        for(int i = 0; i < tamanhoVertical; i++){
            for(int j = 0; j < tamanhoHorizontal; j++){

                mapa[i][j] = new Ponto(i, j);
            }
        }
        return mapa;
    }

    public void marcarObstaculo(int coordenadaX, int coordenadaY){
        mapaPontos[coordenadaX][coordenadaY].setObstaculo(true);
    }
}