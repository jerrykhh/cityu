public class Paragraph {
    private ArrayList<HtmlEle> htmlEle;
    
    public Paragraph(){
        htmlEle = new ArrayList<HtmlEle>();
    }

    @Override
    public String printStructur() {
        String result = "<p>\n";
        for(HtmlEle htmlEle: htmlEle){
            result += htmlEle.printStructur();
        }
        result += "</p>\n";
        return result;
    }

    void add(HtmlEle htmlEle) {
        this.htmlEle.add(htmlEle);
    }
}
