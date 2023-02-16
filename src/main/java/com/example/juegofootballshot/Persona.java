package com.example.juegofootballshot;

public class Persona {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int partidasJugadas;

    public int getPuntuacionMaxima() {
        return puntuacionMaxima;
    }

    public void setPuntuacionMaxima(int puntuacionMaxima) {
        this.puntuacionMaxima = puntuacionMaxima;
    }

    private int puntuacionMaxima;

    public Persona(String name, int partidasjugdas , int p){
        this.name=name;
        this.partidasJugadas =partidasjugdas;
        this.puntuacionMaxima=p;
    }
    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int compareTo(Persona o2) {
        if(this.getPuntuacionMaxima()> o2.getPuntuacionMaxima()) return -1;
        else if (this.getPuntuacionMaxima() < o2.getPuntuacionMaxima()) return 1;
        else return 0;
    }
}
