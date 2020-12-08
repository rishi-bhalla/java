package com.example.gc;

//Study of :
//* Life of Object
//* Garbage Collection Mechanism
//* finalize method (Destructor like method in Java)

//Refer : Garbage Collection.txt
//See: GCDemo.png

class Myclass {//extends Object
    String label;
    int arr[];

    Myclass(String x, int size) {
        label = x;
        arr = new int[size];
    }

    void display() {
        System.out.println(label + " has array of " + arr.length + " elements");
    }

    //override
    protected void finalize() {
        System.out.println("in finalize for "+ label);
    }
}

class GCDemo {
    static void fx(String l, int s) {
        System.out.println("---- in fx-----");
        Myclass temp = new Myclass(l, s);
        temp.display();
        System.out.println("---- fx ends -----");
    }

    static Myclass fx1(String l, int s) {
        System.out.println("---- in fx1-----");
        Myclass temp = new Myclass(l, s);
        temp.display();
        System.out.println("---- fx1 ends -----");
        return temp;
    }

    public static void main(String args[]) {
        fx("A", 1000);
        Myclass m = fx1("B", 5000);
        System.gc(); //explicit invokation of Java's garbage collection mechanism

        try {
            Thread.sleep(1000);//1 second delay to observe the gc action
        }
        catch(Exception ex) {}

        System.out.println("---Alive in main as well----");
        m.display();
        m = null;
        System.out.println("---Bye Bye----");
        System.gc(); //explicit invokation of Java's garbage collection mechanism

        try {
            Thread.sleep(1000);//1 second delay to watch the gc action
        }
        catch(Exception ex)
        {}
    }//main
}

