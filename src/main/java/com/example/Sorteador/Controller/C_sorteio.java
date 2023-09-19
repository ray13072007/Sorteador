package com.example.Sorteador.Controller;

import com.example.Sorteador.Service.S_sorteio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class C_sorteio {

    @Autowired
    private S_sorteio sorteioService;

    @GetMapping("/")
    public String exibirHome() {
        return "Home";
    }

    @PostMapping("/sortear")
    @ResponseBody
    public List<Integer> realizarSorteio(
            @RequestParam("minimo") int minimo,
            @RequestParam("maximo") int maximo,
            @RequestParam("quantidade") int quantidadeSorteio,
            @RequestParam(value = "ordenar", required = false) boolean ordenar,
            @RequestParam(value = "repetir", required = false) boolean permitirRepetidos,
            Model model) {

        List<Integer> numerosSorteados = sorteioService.realizarSorteio(minimo, maximo, quantidadeSorteio, ordenar, permitirRepetidos);

        model.addAttribute("numerosSorteados", numerosSorteados);

        return numerosSorteados;
    }
}
