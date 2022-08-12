package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pb1 = new Publisher("Tim Evans","Test Street","London","England","1234");
        Publisher pb2 = new Publisher("Mary Evans","Test Street","Berlin","Germany","1234");
        publisherRepository.save(pb1);
        publisherRepository.save(pb2);

        Author eric = new Author("Eric","Evans");
        Book ddd = new Book("Domain Dricen Design","123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(pb1);
        pb1.getBooks().add(ddd);


        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(pb1);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development withour EJB", "34378289");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(pb2);
        pb2.getBooks().add(ddd);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(pb2);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: "  + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());

        System.out.println("Number of Publisher " + pb1.getName() + ": " + pb1.getBooks().size());
    }
}
