package com.billme.billme.service.wiki;

import com.billme.billme.common.jsoup.exception.JsoupConnectionException;
import com.billme.billme.common.jsoup.parser.wiki.WikiActiveCode;
import com.billme.billme.common.jsoup.parser.wiki.WikiActiveCodeTableParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WikiActiveCodeService {

    private static final String PATH = "https://en.wikipedia.org/wiki/ISO_4217";

    private final WikiActiveCodeTableParser parser;

    public WikiActiveCodeService(WikiActiveCodeTableParser parser) {
        this.parser = parser;
    }

    public List<WikiActiveCode> getActiveCodes() {
        Document htmlDocument = getHtmlDocument();
        return parser.parse(htmlDocument);
    }

    private Document getHtmlDocument() {
        try {
            return Jsoup.connect(PATH).get();
        } catch (IOException e) {
            String message = String.format("Unable execute request for '%s'", PATH);
            throw new JsoupConnectionException(message, e);
        }
    }

}
