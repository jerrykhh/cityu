public class Text implements HtmlEle{
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String printStructur() {
        return text + "\n";
    }
}
