package ru.otus.spring.repositories;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repositories.impl.AuthorRepository;
import ru.otus.spring.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if(author.getId() == 0){
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Author findById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public void remove(Author author) {
        Query query = em.createQuery("delete from Book b where b.author.id = :author_id");
        query.setParameter("author_id", author.getId());
        query.executeUpdate();

        em.remove(em.contains(author) ? author : em.merge(author));
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

}