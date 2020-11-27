package br.com.gabriel.apicontatos.repository.contato;

import br.com.gabriel.apicontatos.model.Contato;
import br.com.gabriel.apicontatos.repository.filter.ContatoFilter;

import java.util.List;

public interface ContatoRepositoryQuery {

    public List<Contato>filtrar(ContatoFilter contatoFilter);
}
