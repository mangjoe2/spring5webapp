package guru.springframework.spring5webapp.boostrap;

import guru.springframework.spring5webapp.Repositories.AuthorRepository;
import guru.springframework.spring5webapp.Repositories.BookRepository;
import guru.springframework.spring5webapp.Repositories.PublisherRepository;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in BootStrap");
        Publisher publisher = new Publisher();

        Author eric =new Author("Eric", "Manman");
        Book addd = new Book("Domain Design", "49653211");


        publisher.setCity("Fairfax");
        publisher.setState("VA");
        publisher.setStreetName("9888 Shallow Creek Loop");
        publisher.setZipCode("22030");

        publisherRepository.save(publisher);
        System.out.println("Publisher count: "+ publisherRepository.count());

        eric.getBooks().add(addd);
        addd.getAuthors().add(eric);

        addd.setPublisher(publisher);
        publisher.getBooks().add(addd);

        authorRepository.save(eric);
        bookRepository.save(addd);


        Author rod =new Author("Rod", "Joshon");
        Book noEJB = new Book("JEE Development without EJB", "641324555");

        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        rod.getBooks().add(noEJB);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of publisher: "+ publisherRepository.count());
        System.out.println("Publisher Number of Books: "+ publisher.getBooks().size());
        //System.out.println("The Publisher Info is: "+ publisher.toString());
        System.out.println("Number of Books: " + bookRepository.count());

    }
}
