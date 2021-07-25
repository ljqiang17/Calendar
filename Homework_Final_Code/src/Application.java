import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {
    public static void main(String[] args) {
        MyTime myTime = new MyTime();
        myTime.setVisible(true);
        myTime.setResizable(false);
        myTime.setLocation(400,200);

        /*关闭窗口*/
        myTime.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

    }
}
