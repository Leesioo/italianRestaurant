package italianrestaurant;

import italianrestaurant.staff.Chef;
import italianrestaurant.staff.Waiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Waiter waiter1 = new Waiter();
//        Waiter waiter2 = new Waiter();
        Chef chef1 = new Chef();
//        Chef chef2 = new Chef();
//        Chef chef3 = new Chef();

        Thread w1t = new Thread(waiter1);
//        Thread w2t = new Thread(waiter2);

        Thread c1t = new Thread(chef1);
//        Thread c2t = new Thread(chef2);
//        Thread c3t = new Thread(chef3);

        w1t.start();
//        w2t.start();
        c1t.start();
//        c2t.start();
//        c3t.start();
    }
}
