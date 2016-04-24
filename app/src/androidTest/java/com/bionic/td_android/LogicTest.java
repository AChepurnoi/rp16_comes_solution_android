package com.bionic.td_android;

import com.bionic.td_android.MainWindow.Overview.Utility.PeriodCalculator;

/**
 * Created by user on 23.04.2016.
 */
public class LogicTest extends ApplicationTest {

    public void test(){




        PeriodCalculator calculator = new PeriodCalculator();

//        calculator.getPeriodWeeks(2016,1);
//        calculator.getPeriodWeeks(2016,2);
//        calculator.getPeriodWeeks(2015,52);
        calculator.getPeriodMonths(2016,1);
        calculator.getPeriodMonths(2016,2);
        calculator.getPeriodMonths(2016,3);
        calculator.getPeriodMonths(2016,4);
        calculator.getPeriodMonths(2016,5);
        calculator.getPeriodMonths(2016,6);
        calculator.getPeriodMonths(2016,7);
        calculator.getPeriodMonths(2016,8);
        calculator.getPeriodMonths(2016,9);
        calculator.getPeriodMonths(2016,10);
        calculator.getPeriodMonths(2016,11);
        calculator.getPeriodMonths(2016,12);





    }
}
