package sbu.cs.Semaphore;

import java.util.concurrent.Semaphore;

public class Controller {

    public static void main(String[] args) {
        Semaphore smf = new Semaphore(2);

        Operator operator1 = new Operator("operator1", smf);
        Operator operator2 = new Operator("operator2", smf);
        Operator operator3 = new Operator("operator3", smf);
        Operator operator4 = new Operator("operator4", smf);
        Operator operator5 = new Operator("operator5", smf);

        operator1.start();
        operator2.start();
        operator3.start();
        operator4.start();
        operator5.start();



    }
}
