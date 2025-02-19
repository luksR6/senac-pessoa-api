package web.service.exemplo.com.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.service.exemplo.com.br.entidade.Pessoa;
import web.service.exemplo.com.br.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepositorio;

    /*
    public PessoaService(PessoaRepository pessoaRepository) {

    }
    */

        public Pessoa criarPessoa(Pessoa pessoa) {
            try {
                Pessoa pessoaResult = pessoaRepositorio.save(pessoa);

                return pessoaResult;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
