package org.services;

import org.entities.TesteGratis;
import org.repository.TesteGratisRepository;

public class TesteGratisService {
    private static final TesteGratisRepository testeGratisRepository = new TesteGratisRepository();

    // verifica se já tem um email cadastrado na tabela de teste grátis, se não tiver, cria um novo registro
    public void Create(TesteGratis entidade) {
        if (testeGratisRepository.ReadByEmail(entidade.getEmail())) {
            return ;
        }
        testeGratisRepository.Create(entidade);
    }
}
