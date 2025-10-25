package edu.fra.uas.v3autowired;

import edu.fra.uas.v3autowired.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("Geselle2")
public class Journeyman2 {
    @Autowired
    @Qualifier("pleaseDrill")
    Work work;

    public void performWork() {

        work.doWork();
    }
}