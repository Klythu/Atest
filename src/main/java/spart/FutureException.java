package spart;

public class FutureException extends Exception{
    public FutureException() {
    }

    public void futureException (String i) {
        System.out.println("Exception: DateNotCorrectData");
        System.out.printf("It`s future data: %s", i);
        System.out.println();
    }
}
