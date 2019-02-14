package com.billme.billme.common.jsoup.parser.wiki;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WikiActiveCodeTableParser {

    private static final int CODE_COLUMN_INDEX = 0;
    private static final int NUMBER_COLUMN_INDEX = 1;
    private static final int SCALING_COLUMN_INDEX = 2;
    private static final int TITLE_COLUMN_INDEX = 3;

    private static final String SPECIAL_CHARACTER = "*";
    private static final String DOT_SPECIAL_CHARACTER = ".";

    public List<WikiActiveCode> parse(Document document) {
        Elements rows = extractTableRows(document);
        return rowsToActiveCodeList(rows);
    }

    private Elements extractTableRows(Document document) {
        Element table = document.selectFirst(".wikitable.sortable");
        return table.select("tr");
    }

    private List<WikiActiveCode> rowsToActiveCodeList(Elements rows) {
        return rows.stream()
                .skip(1)
                .map(this::rowToActiveCode)
                .collect(Collectors.toList());
    }

    private WikiActiveCode rowToActiveCode(Element row) {
        Elements columns = row.select("td");
        WikiActiveCode activeCode = new WikiActiveCode();
        activeCode.setCode(removeReference(columns.get(CODE_COLUMN_INDEX)).text());
        activeCode.setNumber(removeReference(columns.get(NUMBER_COLUMN_INDEX)).text());
        activeCode.setScaling(adjustScalingElementValue(columns.get(SCALING_COLUMN_INDEX)));
        activeCode.setTitle(removeReference(columns.get(TITLE_COLUMN_INDEX)).text());

        return activeCode;
    }

    private Integer adjustScalingElementValue(Element scaling) {
        String value = removeReference(scaling).text();
        String newValue = value.replace(SPECIAL_CHARACTER, "");
        return newValue.equals(DOT_SPECIAL_CHARACTER) ? null : Integer.valueOf(newValue);
    }

    private Element removeReference(Element element) {
        Optional.ofNullable(element.selectFirst("sup")).ifPresent(Element::remove);
        return element;
    }
}
