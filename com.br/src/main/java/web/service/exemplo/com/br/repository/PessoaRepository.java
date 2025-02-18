package web.service.exemplo.com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.service.exemplo.com.br.entidade.Pessoa;

@Repository
public interface PessoaRepository
            extends JpaRepository<Pessoa, Long> {

}
