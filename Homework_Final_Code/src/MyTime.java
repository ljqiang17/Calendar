import javax.swing.*;
import java.awt.*;

public class MyTime extends JFrame {

    public MyTime() {
        /*基本属性设置*/
        super("日历和时钟");
        setSize(500, 300);

        /*将panel分为两部分，一部分作日历，一部分作时钟*/
        JSplitPane sp = new JSplitPane();
        sp.setDividerLocation(300);
        sp.setDividerSize(-1);

        MyCalendar calender = new MyCalendar();//日历版面
        MyClock clock = new MyClock();//时钟版面
        sp.setLeftComponent(calender);
        sp.setRightComponent(clock);
        add(sp, BorderLayout.CENTER);

        new Thread(clock).start();//更新时钟的线程
    }

}
