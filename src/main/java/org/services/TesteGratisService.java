package org.services;

import org.entities.TesteGratis;
import org.repository.TesteGratisRepository;

public class TesteGratisService {
    private static final TesteGratisRepository testeGratisRepository = new TesteGratisRepository();

    /**
     * Método que verifica se já tem um teste grátis cadastrado com o email informado e caso não tenha, cria um novo
     * @param entidade - Entidade TesteGratis
     * @return TesteGratis - Retorna o teste grátis criado
     */
    public TesteGratis Create(TesteGratis entidade) {
        if (testeGratisRepository.ReadByEmail(entidade.getEmail())) {
            return null;
        }
        testeGratisRepository.Create(entidade);
        // retorna o teste grátis criado para ser adicionado uma atividade no site
        return testeGratisRepository.ReadAll().stream().filter(e ->
                e.getEmail().equalsIgnoreCase(entidade.getEmail())).findFirst().orElse(null);
    }
}
