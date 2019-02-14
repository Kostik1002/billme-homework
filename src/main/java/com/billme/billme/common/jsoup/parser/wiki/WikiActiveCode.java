package com.billme.billme.common.jsoup.parser.wiki;

public class WikiActiveCode {

    private String code;
    private String number;
    private Integer scaling;
    private String title;

    public WikiActiveCode() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getScaling() {
        return scaling;
    }

    public void setScaling(Integer scaling) {
        this.scaling = scaling;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WikiActiveCode activeCode = (WikiActiveCode) o;

        if (code != null ? !code.equals(activeCode.code) : activeCode.code != null) return false;
        if (number != null ? !number.equals(activeCode.number) : activeCode.number != null) return false;
        if (scaling != null ? !scaling.equals(activeCode.scaling) : activeCode.scaling != null) return false;
        return title != null ? title.equals(activeCode.title) : activeCode.title == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (scaling != null ? scaling.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", number=" + number +
                ", scaling=" + scaling +
                ", title='" + title + '\'' +
                '}';
    }
}
