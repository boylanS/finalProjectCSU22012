import java.util.Queue;
import java.util.LinkedList;

public class TST<Value> {

    private int n;
    private Node<Value> root;

    private static class Node<Value>
    {
        private char c;
        private Node<Value> left, mid, right;
        private Value val;
    }

    public TST()
    {

    }

    public int size() {
        return n;
    }

    public boolean contains(String key)
    {
        if (key == null)
        {
            throw new IllegalArgumentException("argument to contains() is null");
        }


        return get(key) != null;
    }

    public Value get(String key)
    {

        if (key == null)
        {
            throw new IllegalArgumentException("calls get() with null" +
                    "argument");
        }

        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");

        Node<Value> x = get(root,key,0);

        if (x == null) return null;

        return x.val;
    }

    private Node<Value> get(Node<Value> x, String key, int d)
    {
        if (x == null) return null;

        if (key.length() == 0) throw new IllegalArgumentException("key must have length >=1");

        char c = key.charAt(d);

        if (c < x.c) return get(x.left, key, d);
        else if (c > x.c) return get(x.right, key,d);
        else if (d < key.length() -1) return get(x.mid, key, d+1);
        else return x;
    }

    public void put(String key, Value val)
    {
        if (key == null)
        {
            throw new IllegalArgumentException("calls put() with null key");
        }

        if (!contains(key)) n++;
        else if(val == null) n--;
        root = put(root, key, val, 0);

    }

    private Node<Value> put(Node<Value> x, String key,
                            Value val, int d)
    {
        char c = key.charAt(d);
        if (x == null)
        {
            x = new Node<Value>();
            x.c = c;
        }
        if (c < x.c) x.left = put(x.left, key, val, d);
        else if (c > x.c) x.right = put(x.right, key, val, d);
        else if (d < key.length() -1) x.mid = put(x.mid, key, val, d+1);
        else x.val = val;

        return x;
    }


    public Iterable<String> keysWithPrefix(String prefix)
    {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        Queue<String> queue = new LinkedList<String>();
        Node<Value> x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val != null) queue.add(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        System.out.println(queue);
        return queue;
    }

    private void collect(Node<Value> x, StringBuilder prefix, Queue<String> queue)
    {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.val != null) queue.add(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }









}
