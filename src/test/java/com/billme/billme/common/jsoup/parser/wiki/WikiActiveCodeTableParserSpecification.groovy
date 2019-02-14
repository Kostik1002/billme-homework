package com.billme.billme.common.jsoup.parser.wiki

import org.jsoup.Jsoup
import spock.lang.Specification
import spock.lang.Unroll

class WikiActiveCodeTableParserSpecification extends Specification {

    def parser = new WikiActiveCodeTableParser()

    def classLoader = getClass().getClassLoader();
    def htmlFile = new File(classLoader.getResource("activeCode.html").getFile())
    def document = Jsoup.parse(htmlFile, "utf-8")
    def table = document.selectFirst("table")

    @Unroll
    def "should properly convert rows to active code pojo for currency #code"() {
        given:
            def rows = table.select("tr")
        when:
            def activeCodes = parser.rowsToActiveCodeList(rows)
            def currentActiveCode = activeCodes.get(index)
        then:
            activeCodes.size == 3
            currentActiveCode.getCode() == code
            currentActiveCode.getNumber() == number
            currentActiveCode.getScaling() == scaling
            currentActiveCode.getTitle() == title
        where:
            index | code  | number | scaling | title
            0     | "AED" | "784"  | 2       | "United Arab Emirates dirham"
            1     | "AFN" | "971"  | 2       | "Afghan afghani"
            2     | "ALL" | "008"  | null    | "Albanian lek"
    }
}
