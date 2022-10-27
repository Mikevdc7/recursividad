package org.mvallesg.recursividad.ejemplo;

import org.mvallesg.recursividad.ejemplo.models.Componente;

import java.util.stream.Stream;

public class EjemploRecursividad {
    public static void main(String[] args) {

        Componente pc = new Componente("Torre PC ATX");
        Componente fuenteAlimentacion = new Componente("Alimentación 700W");
        Componente placaBase = new Componente("MainBoard Asus sockets AMD");
        Componente cpu = new Componente("Cpu AMD Ryzen 5 2800");
        Componente ventilador = new Componente("Ventilador CPU");
        Componente disipador = new Componente("Disipador");
        Componente tarjetaGrafica = new Componente("Nvidia RTX 3080 8GB");
        Componente gpu = new Componente("Nvidia GPU RTX");
        Componente gpuRam = new Componente("4GB Ram");
        Componente gpuRam2 = new Componente("4GB Ram");
        Componente gpuVentiladores = new Componente("Ventiladores");
        Componente ram = new Componente("Memoria Ram 32GB");
        Componente ssd = new Componente("Disco SSD 2TB");

        // Montamos la cpu
        cpu.addComponente(ventilador)
                .addComponente(disipador);

        // Montamos la tarjeta gráfica
        tarjetaGrafica.addComponente(gpu)
                .addComponente(gpuRam)
                .addComponente(gpuRam2)
                .addComponente(gpuVentiladores);

        // Montamos la placa base, que contiene la cpu y la tarjeta gráfica
        placaBase.addComponente(cpu)
                .addComponente(tarjetaGrafica)
                .addComponente(ram)
                .addComponente(ssd);

        // Montamos el pc, que contiene la placa base
        pc.addComponente(fuenteAlimentacion)
                .addComponente(placaBase)
                .addComponente(new Componente("Teclado"))
                .addComponente(new Componente("Ratón"));

        //metodoRecursivo(pc, 0);
        metodoRecursivoJava8(pc, 0).forEach(componente -> {
            System.out.println("\t".repeat(componente.getNivel()) + componente.getNombre());
        });
    }

    public static Stream<Componente> metodoRecursivoJava8(Componente c, int nivel){
        c.setNivel(nivel);
        return Stream.concat(Stream.of(c),
                c.getHijos().stream().flatMap(hijo -> metodoRecursivoJava8(hijo, nivel +1)));

    }

    public static void metodoRecursivo(Componente c, int nivel){
        System.out.println("\t".repeat(nivel) + c.getNombre());
        if(c.tieneHijos()){
            for(Componente hijo: c.getHijos()){
                metodoRecursivo(hijo, nivel+1);
            }
        }
    }
}
