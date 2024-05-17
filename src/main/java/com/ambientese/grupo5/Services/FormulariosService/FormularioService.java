package com.ambientese.grupo5.Services.FormulariosService;

import com.ambientese.grupo5.Model.CertificadoModel;
import com.ambientese.grupo5.Model.FormularioModel;
import com.ambientese.grupo5.Model.PerguntasModel;
import com.ambientese.grupo5.Model.RespostaUsuarioModel;
import com.ambientese.grupo5.Model.Enums.RespostasEnum;

import java.util.List;

public class FormularioService {

    public static FormularioModel gerarFormularioAleatorio(List<PerguntasModel> perguntas) {
        // Verificar se há perguntas disponíveis
        if (perguntas == null || perguntas.isEmpty()) {
            throw new IllegalArgumentException("A lista de perguntas não pode ser vazia");
        }

        // Lógica para selecionar um conjunto aleatório de perguntas
        // Aqui, vamos supor que selecionamos todas as perguntas para simplificar
        FormularioModel formulario = new FormularioModel();
        formulario.setPerguntas(perguntas);
        return formulario;
    }

    public static double calcularNivelSatisfacaoCertificado(FormularioModel formulario) {
        // Verificar se há respostas disponíveis
        List<RespostaUsuarioModel> respostas = formulario.getRespostas();
        if (respostas == null || respostas.isEmpty()) {
            throw new IllegalArgumentException("A lista de respostas não pode ser vazia");
        }

        // Contar o número de respostas satisfatórias
        int satisfatorias = 0;
        for (RespostaUsuarioModel resposta : respostas) {
            if (resposta.getRespostas() == RespostasEnum.Conforme) {
                satisfatorias++;
            }
        }

        // Calcular a porcentagem de respostas satisfatórias
        return (double) satisfatorias / respostas.size() * 100;
    }

    public static CertificadoModel determinarCertificado(double porcentagem) {
        CertificadoModel certificado = new CertificadoModel();
        if (porcentagem == 100) {
            certificado.setDescricao("Muito satisfatório (3 estrelas)");
        } else if (porcentagem >= 75.1) {
            certificado.setDescricao("Satisfatório (2 estrelas)");
        } else if (porcentagem >= 50.1) {
            certificado.setDescricao("Pouco satisfatório (1 estrela)");
        } else {
            certificado.setDescricao("Não satisfatório (0 estrela)");
        }
        return certificado;
    }
}
