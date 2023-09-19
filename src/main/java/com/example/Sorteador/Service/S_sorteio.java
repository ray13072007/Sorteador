package com.example.Sorteador.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class S_sorteio {

    public List<Integer> realizarSorteio(int minimo, int maximo, int quantidadeSorteio, boolean ordenar, boolean permitirRepetidos) {
        List<Integer> numerosSorteados = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < quantidadeSorteio; i++) {
            int numeroSorteado = random.nextInt(maximo - minimo + 1) + minimo;
            if (!permitirRepetidos && numerosSorteados.contains(numeroSorteado)) {
                i--;
            } else {
                numerosSorteados.add(numeroSorteado);
            }
        }
        if (ordenar) {
            numerosSorteados.sort(Integer::compareTo);
        }

        return numerosSorteados;
    }
}
