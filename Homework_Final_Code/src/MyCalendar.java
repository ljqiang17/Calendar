import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class MyCalendar extends JPanel {
    public Panel selectPanel;
    public Choice monthChooser;
    public Choice yearChooser;
    public Label day[] = new Label[42];
    public Panel showPanel;
    public Panel controlPanel;


    public MyCalendar(){
        setLayout(null);
        addSelectPanel();
        addShowPanel();
        initShowPanel();
        addControlPanel();
    }


    public void addSelectPanel(){
        selectPanel = new Panel();
        monthChooser = new Choice();
        yearChooser = new Choice();
        selectPanel.setLayout(new GridLayout(1,2));
        selectPanel.setBounds(0,0, 300, 35);
        add(selectPanel);
        selectPanel.add(monthChooser, BorderLayout.WEST);
        selectPanel.add(yearChooser, BorderLayout.WEST);

        /*添加月份1到12月*/
        for(int i = 1; i <= 12; i++){
            String m = String.valueOf(i);
            monthChooser.add(m);
        }
        /*添加年份2000-2999年，共1000年*/
        for(int i = 2000; i < 3000; i++){
            String y = String.valueOf(i);
            yearChooser.add(y);
        }

        /*对年份月份的下拉选单进行初始化，使其默认为当前年月*/
        Calendar ins = Calendar.getInstance();
        int year = ins.get(Calendar.YEAR);
        int month = ins.get(Calendar.MONTH);
        String year_lab = String.valueOf(year);
        String month_lab = String.valueOf(month);
        yearChooser.select(year_lab);
        monthChooser.select(month_lab);

    }
    public void addShowPanel(){
        showPanel = new Panel();
        showPanel.setBounds(0, 50, 300, 150);
        showPanel.setLayout(new GridLayout(7,7));
        showPanel.add(new Label("Sun."));
        showPanel.add(new Label("Mon."));
        showPanel.add(new Label("Tue."));
        showPanel.add(new Label("Wed."));
        showPanel.add(new Label("Thur."));
        showPanel.add(new Label("Fri."));
        showPanel.add(new Label("Sat."));

        for(int i = 0; i < 42; i++){
            day[i] = new Label();
            showPanel.add(day[i]);
        }
        add(showPanel);
    }

    public void initShowPanel(){
        Calendar curCal=Calendar.getInstance();
        int month=curCal.get(Calendar.DAY_OF_MONTH);
        int week=curCal.get(Calendar.DAY_OF_WEEK);
        int startDay=7-(month-week) % 7;
        if(startDay==0)
            startDay=7;

        /*显示空格*/
        int k=0;
        for(int i=0;i<startDay;i++){
            day[k].setText("");
            showPanel.add(day[k++]);
        }
        /*当前月的日期*/
        for(int i=1;i<=curCal.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
            day[k].setText(i+"");
            showPanel.add(day[k++]);
        }
        /*显示空格*/
        for(int i=1;i<=42-startDay-curCal.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
            day[k].setText("");
            showPanel.add(day[k++]);
        }

    }

    public void addControlPanel(){
        controlPanel = new Panel();
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setBounds(0, 225, 300,50);
        add(controlPanel);

        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        ActionListener ok_l = new Ok_L();
        ActionListener cancel_l = new Cancel_L();
        ok.addActionListener(ok_l);
        cancel.addActionListener(cancel_l);

        controlPanel.add(ok,BorderLayout.WEST);
        controlPanel.add(cancel, BorderLayout.WEST);
    }

    public class Ok_L implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int year = Integer.parseInt(yearChooser.getSelectedItem());
            int month = Integer.parseInt(monthChooser.getSelectedItem());
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, 1);
            int startDay = cal.get(Calendar.DAY_OF_WEEK);
            int maxDay = maxDayInMonth(year, month);
            int k = 0;
            for(int i = 0; i < startDay - 1; i++){
                day[k].setText(" ");
                k++;
            }
            for(int i = 1; i <= maxDay; i++){
                String d = String.valueOf(i);
                day[k].setText(d);
                k++;
            }
            for(int i = 1; i <= 42 - startDay- maxDay; i++){
                day[k].setText(" ");
                k++;
            }
        }
        public  int maxDayInMonth(int year, int month) {
            int max = 30;
            if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12)
                max = 31;
            if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                    max = 28;
                else
                    max = 29;
            }
            return max;
        }
    }

    public  class Cancel_L implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}