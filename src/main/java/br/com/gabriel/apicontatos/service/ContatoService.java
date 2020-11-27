package br.com.gabriel.apicontatos.service;

import br.com.gabriel.apicontatos.model.Contato;
import br.com.gabriel.apicontatos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    public Contato atulizarNumero(Long id, String  numero) {

        Contato contatoAt = buscarPeloId(id);
        contatoAt.setNumero(numero);
       return contatoRepository.save(contatoAt);
    }

    public Contato buscarPeloId(Long id){

        Contato contatoSalvo = contatoRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(1));
        return contatoRepository.save(contatoSalvo);

    }
}
