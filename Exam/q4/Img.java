public class Img implements HtmlEle{
    private String src;
    public Img(String src){
        this.src = src;
    }

    @Override
    public String printStructur() {
        return "<img src=\"" + src + "\">\n";
    }
}
