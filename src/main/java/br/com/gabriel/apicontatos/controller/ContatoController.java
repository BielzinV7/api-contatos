package br.com.gabriel.apicontatos.controller;

import br.com.gabriel.apicontatos.model.Contato;
import br.com.gabriel.apicontatos.repository.ContatoRepository;
import br.com.gabriel.apicontatos.repository.filter.ContatoFilter;
import br.com.gabriel.apicontatos.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Cipher;
import javax.persistence.PersistenceUnit;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController{

    @Autowired
     private ContatoRepository contatoRepository;

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<Contato> listar(ContatoFilter contatoFilter){

        return contatoRepository.filtrar(contatoFilter);

    }

    @PostMapping
    public ResponseEntity<Contato> cadastrar(@Valid @RequestBody  Contato contato, UriComponentsBuilder uriBuilder){

        Contato contatoSalvo = contatoRepository.save(contato);

        URI uri = uriBuilder.path("/contato/{id}").buildAndExpand(contato.getId()).toUri();

        return ResponseEntity.created(uri).body(contatoSalvo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPeloId(@PathVariable Long id){

      return contatoRepository.findById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar( @PathVariable Long id){

        contatoRepository.deleteById(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarNumero(@PathVariable Long id, @RequestBody String numero){

        contatoService.atulizarNumero( id,numero);



    }

}
