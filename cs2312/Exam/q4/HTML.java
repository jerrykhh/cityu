public class HTML {
    private ArrayList<HtmlEle> htmlEle;

    public HTML() {
        htmlEle = new ArrayList<HtmlEle>();
    }

    public void add(HtmlEle htmlEle) {
        this.htmlEle.add(htmlEle);
    }

    public String to_html() {
        String result = "<html>\n";
        for (HtmlEle html : htmlEle) {
            result += html.printStructur();
        }
        result += "</html>";
        return result;
    }
}
