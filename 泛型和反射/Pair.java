class Pair<T> {
    private T first;
    private T second;

    public Pair(){
        first = null;
        second = null;
    }

    public Pair(T f, T s){
        first = f;
        second = s;
    }

    public T getFirst(){
        return first;
    }

    public T getSecond(){
        return second;
    }

    public void setFirst(T f){
        first = f;
    }

    public void setSecond(T s){
        second = s;
    }

    public String toString(){
        return "first:" + first + "\nsecond:" + second;
    }
}
