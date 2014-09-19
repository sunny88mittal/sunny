package Generics;

//Note that, in this context, extends is used in a general sense to mean either "extends" (as in classes) or "implements" (as in interfaces).
public class BoundedBoxExtends<T> {

    private T t;          

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        BoundedBoxExtends<Integer> integerBox = new BoundedBoxExtends<Integer>();
        integerBox.set(new Integer(10));
        //integerBox.inspect("some text"); // error: this is still String!
    }
}
