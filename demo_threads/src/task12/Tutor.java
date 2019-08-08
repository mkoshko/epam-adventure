package task12;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tutor extends Thread {
    private Integer idTutor;
    private List<Student> list;

    public Tutor() {
        this.list = new ArrayList<>();
    }

    public Tutor(List<Student> list) {
        this.list = list;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer id) {
        this.idTutor = id;
    }

    public void run() {
        for (Student st : list) {
            List<Task> tasks = st.getTaskList();
            for (Task current : tasks) {
                int mark = 3 + new Random().nextInt(7);
                current.setMark(mark);
                System.out.println(mark + " for student N "
                        + st.getIdStudent());
                st.getCountDownLatch().countDown();
            }
            System.out.println("All estimates made for " + st.getIdStudent());
        }
    }
}
