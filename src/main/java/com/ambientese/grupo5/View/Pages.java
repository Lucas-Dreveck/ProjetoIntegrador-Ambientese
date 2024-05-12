package com.ambientese.grupo5.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Pages {

    @GetMapping("/empresas")
    public String pageEmpresas(Model model) throws InterruptedException {
        Thread.sleep(2000); // Simulated Lag
        return "pages/cadastro-empresas";
    }

    @GetMapping("/funcionarios")
    public String pageFuncionarios(Model model) throws InterruptedException {
        Thread.sleep(2000); // Simulated Lag
        return "pages/cadastro-funcionarios";
    }

    @GetMapping("/ranking")
    public String pageRanking(Model model) throws InterruptedException {
        Thread.sleep(2000); // Simulated Lag
        return "pages/ranking";
    }

    @GetMapping("/start-avaliacao")
    public String pageStartAvaliacao(Model model) throws InterruptedException {
        return "pages/avaliacao/selecao-empresa";
    }

    @GetMapping("/avaliacao")
    public String pageAvaliacao(Model model) throws InterruptedException {
        return "pages/avaliacao/avaliacao";
    }
    
    @GetMapping("/result-avaliacao")
    public String pageResultAvaliacao(Model model) throws InterruptedException {
        return "pages/avaliacao/result-avaliacao";
    }
}
