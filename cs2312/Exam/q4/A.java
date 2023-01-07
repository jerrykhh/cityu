public class A implements HtmlEle {
    private String href;
    private String target;
    private ArrayList<HtmlEle> htmlEle;

    public A(String href, String target) {
        this.href = href;
        this.target = target;
        htmlEle = new ArrayList<HtmlEle>();
    }

    @Override
    public String printStructur() {
        String result = "<a href=\"" + href + "\" target=\"" + target + "\">\n";
        for (HtmlEle html : htmlEle) {
            result += html.printStructur();
        }
        result += "</a>\n";
        return result;
    }

    void add(HtmlEle htmlEle) {
        this.htmlEle.add(htmlEle);
    }
}