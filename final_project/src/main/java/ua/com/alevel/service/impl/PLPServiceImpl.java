package ua.com.alevel.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import org.springframework.util.StreamUtils;
import ua.com.alevel.exception.BadRequestException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.publisher.Publisher;
import ua.com.alevel.persistence.repository.book.BookRepository;
import ua.com.alevel.persistence.repository.book.PublisherRepository;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PLPServiceImpl implements PLPService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final CrudRepositoryHelper<Publisher, PublisherRepository> crudRepositoryHelper;

    public PLPServiceImpl(
            BookRepository bookRepository,
            PublisherRepository publisherRepository,
            CrudRepositoryHelper<Publisher, PublisherRepository> crudRepositoryHelper) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public List<Book> search(Map<String, Object> queryMap) {
        if (queryMap.get(WebUtil.PUBLISHER_PARAM) != null) {
            Long publisherId = Long.parseLong(String.valueOf(queryMap.get(WebUtil.PUBLISHER_PARAM)));
            Optional<Publisher> publisher = crudRepositoryHelper.findById(publisherRepository, publisherId);
            return bookRepository.findByPublisher(publisher.get());
        }
        return bookRepository.findAll();
    }
}
