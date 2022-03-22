package ch02.n1617;

public class N1617 {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.add("aaaa");
        queue.add("bbbb");
        queue.add("cccc");
        queue.add("dddd");
        queue.add("eeee");
        queue.add("ffff");
        queue.remove();
        Queue.Iterator iterator = queue.iterator();
        for (int i = 0; i < queue.getSize(); i++) {
            if(iterator.hasNext()) {
                System.out.println(iterator.getCurrentString());
                iterator.setCurrent(iterator.next());
            }
        }
    }
}
