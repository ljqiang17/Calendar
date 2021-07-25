import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyClock extends JPanel implements Runnable {
    public Label showTime;
    int ls_x = 50, ls_y = 30, lm_x = 50, lm_y = 30, lh_x = 50, lh_y = 30;

    public MyClock(){
        setLayout(null);
        showTime = new Label("");
        showTime.setBounds(70, 180, 55, 20);
        showTime.setBackground(new Color(150,200,250));
        add(showTime);
    }

    @Override
    /*画时钟*/
    public void paint(Graphics g){
        /*设置背景色*/
        g.setColor(new Color(150, 200, 250));
        g.fillRect(0,0,200,300);
        /*画表盘并设置表盘色*/
        int center_x = 100;
        int center_y = 100;
        g.setColor(Color.WHITE);
        g.fillOval(center_x - 60, center_y - 60, 120, 120);
        /*画表盘上的数字并设置颜色*/
        g.setFont(new Font("TimesRoman", Font.ITALIC, 12));
        g.setColor(Color.black);
        g.drawString("9", center_x - 55, center_y);
        g.drawString("3",center_x + 45, center_y);
        g.drawString("12", center_x - 5, center_y -45);
        g.drawString("6", center_x - 5, center_y + 55);
        /*获取当前时间*/
        Calendar ins = Calendar.getInstance();
        Date curTime = ins.getTime();
        int hour = curTime.getHours();
        int minute = curTime.getMinutes();
        int second = curTime.getSeconds();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        showTime.setText(formatter.format(curTime));//设置显示时间的标签
        /*计算指针所在位置*/
        final double PI = 3.14;
        int s_x = (int)(Math.cos(second * PI/30 - PI/2) * 45 + center_x);
        int s_y = (int)(Math.sin(second * PI/30 - PI/2) * 45 + center_y);
        int m_x = (int)(Math.cos(minute * PI/30 - PI/2) * 40 + center_x);
        int m_y = (int)(Math.sin(minute * PI/30 - PI/2) * 40 + center_y);
        int h_x = (int)(Math.cos((hour * 30 + minute / 2.0) * PI / 180 - PI / 2) * 30 + center_x);
        int h_y = (int)(Math.sin((hour * 30 + minute / 2.0) * PI / 180 - PI / 2) * 30 + center_y);
        /*画指针并设置颜色*/
        g.setColor(Color.gray);
        g.drawLine(center_x, center_y, s_x, s_y);//秒针
        g.drawLine(center_x, center_y - 2, m_x, m_y);
        g.drawLine(center_x - 2, center_y, m_x, m_y);//分针
        g.drawLine(center_x, center_y - 2, h_x, h_y);
        g.drawLine(center_x - 2, center_y, h_x, h_y);//时针
        /*保存指针位置*/
        ls_x = s_x;
        ls_y = s_y;
        lm_x = m_x;
        lm_y = m_y;
        lh_x = h_x;
        lh_y = h_y;

    }

    @Override
    public void run() {
        while (true){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            repaint();

        }

    }
}
