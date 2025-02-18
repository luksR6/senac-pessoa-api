package web.service.exemplo.com.br.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.service.exemplo.com.br.WebServiceExemploApplication;
import web.service.exemplo.com.br.entidade.Pessoa;
import web.service.exemplo.com.br.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pessoa")
public class PessoaController  {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<Pessoa>> listarPessoa(){
        List<Pessoa> pessoaRetorno =
                pessoaRepository.findAll();

        return ResponseEntity.ok().body(pessoaRetorno);

    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoa> criarPessoa(
            @RequestBody Pessoa pessoa
    ){
        Pessoa retorno = pessoaRepository.save(pessoa);

        if(retorno != null){
            return ResponseEntity.ok().body(retorno);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(
            @RequestBody Pessoa pessoa,
            @PathVariable Long id
    ){
        Optional<Pessoa> retorno = pessoaRepository.findById(id)
                .map(record -> {
                    record.setNome(pessoa.getNome());

                    return pessoaRepository.save(record);
                });
        if (retorno.isPresent()){
            return ResponseEntity.ok().body(retorno.get());
        }

        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);

        if (pessoaExistente.isPresent()) {
            pessoaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
