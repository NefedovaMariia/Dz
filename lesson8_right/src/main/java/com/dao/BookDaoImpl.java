package com.dao;
import com.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        return em.merge(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> q = cb.createQuery(Book.class);
        Root<Book> c = q.from(Book.class);
        q.select(c);
        TypedQuery<Book> query = em.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Book> findByName(String title) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title=:title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }


    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long getCount() {
        return em.createQuery("select count(b) from Book b",Long.class).getSingleResult();
    }

    @Override
    public List<Book> findAllBooksByAuthorId(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.author.id=:id",Book.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Book> findAllWithComments() {
        return null;
    }
}
