package br.com.gabriel.apicontatos.repository.contato;

import br.com.gabriel.apicontatos.model.Contato;
import br.com.gabriel.apicontatos.repository.filter.ContatoFilter;
import org.hibernate.Criteria;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepositoryImpl implements ContatoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Contato> filtrar(ContatoFilter contatoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Contato> criteria = builder.createQuery(Contato.class);

        Root<Contato> root = criteria.from(Contato.class);

        Predicate [] predicates = criarRestricoes(contatoFilter,builder,root);

        criteria.where(predicates);

        TypedQuery<Contato> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    private Predicate[] criarRestricoes(ContatoFilter contatoFilter, CriteriaBuilder builder, Root<Contato> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(contatoFilter.getNome())) {



            predicates.add(builder.like(builder.lower(root.get("nome")),"%" + contatoFilter.getNome().toLowerCase()+"%"));

        }

        return predicates.toArray(new Predicate[predicates.size()]);

    }
}
