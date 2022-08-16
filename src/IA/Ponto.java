public class Ponto {
    private boolean visitado;
    private boolean obstaculo;
    private int coordenadaX;
    private int coordenadaY;
    private int coordenadaXPai;
    private int coordenadaYPai;
    
    public Ponto(int coordenadaX, int coordenadaY){
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public boolean foiVisitado(){
        return visitado;
    }

    public void setObstaculo(boolean obstaculo){
        this.obstaculo = obstaculo;
    }
    public boolean temObstaculo(){
        return obstaculo;
    }

    public void setCoordenadaX(int coordenadaX){
        this.coordenadaX = coordenadaX;
    }
    public int getCoordenadaX(){
        return coordenadaX;
    }

    public void setCoordenadaY(int coordenadaY){
        this.coordenadaY = coordenadaY;
    }
    public int getCoordenadaY(){
        return coordenadaY;
    }
    public void setCoordenadaXPai(int coordenadaXPai){
        this.coordenadaXPai = coordenadaXPai;
    }
    public int getCoordenadaXPai(){
        return coordenadaXPai;
    }

    public void setCoordenadaYPai(int coordenadaYPai){
        this.coordenadaYPai = coordenadaYPai;
    }
    public int getCoordenadaYPai(){
        return coordenadaYPai;
    }

    public String toString(){
        return("[" + getCoordenadaX() + ", " + getCoordenadaY() + "]");
    }
}