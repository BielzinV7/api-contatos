package br.com.gabriel.apicontatos.repository;

import br.com.gabriel.apicontatos.model.Contato;
import br.com.gabriel.apicontatos.repository.contato.ContatoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato,Long>, ContatoRepositoryQuery {

}
