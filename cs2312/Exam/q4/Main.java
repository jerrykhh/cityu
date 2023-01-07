public class Main {
    public static void main(String args[]) {
		
        HTML n1 = new HTML();
        Paragraph n2 = new Paragraph();
        A n3 = new A("logo.jpg", "_blank"); // attributes: href, target
        Text n4 = new Text("Hello world");
        Img n5 = new Img("logo.jpg");
        Text n6 = new Text("open logo");

        n1.add(n2);
        n1.add(n3);

        n2.add(n4);
        n2.add(n5);

        n3.add(n6);

        System.out.println(n1.to_html());
		
    }
}
