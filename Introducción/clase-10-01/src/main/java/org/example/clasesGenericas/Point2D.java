package org.example.clasesGenericas;

public class Point2D <T extends Number>{ //t solo será de tipo que extiendan de number
    T a;
    T b;

    public Point2D(T a, T b){
        this.a=a;
        this.b=b;
    }
    public void print(){
        System.out.println(a);
        System.out.println(b);
    }

    public T getA(){
        return this.a;
    }
    public T getB(){
        return this.b;
    }

    public Number suma(){
        if(a instanceof Double){
            return a.doubleValue()+b.doubleValue();
        }else if(a instanceof Integer){
            return a.intValue()+b.intValue();
        }
        return 0;
    }

}
