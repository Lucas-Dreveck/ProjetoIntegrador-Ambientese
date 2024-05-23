package com.ambientese.grupo5.Controller.FormularioController;

import com.ambientese.grupo5.DTO.FormularioRanking;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Services.FormulariosService.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/pontuacao")
    public List<FormularioRanking> classificarPorPontuacao() {
        return rankingService.classificarPorPontuacao();
    }

    @GetMapping("/ambiental")
    public List<FormularioModel> classificarPorEixoAmbiental() {
        return rankingService.classificarPorEixoAmbiental();
    }

    @GetMapping("/social")
    public List<FormularioModel> classificarPorEixoSocial() {
        return rankingService.classificarPorEixoSocial();
    }

    @GetMapping("/governamental")
    public List<FormularioModel> classificarPorEixoGovernamental() {
        return rankingService.classificarPorEixoGovernamental();
    }
}
