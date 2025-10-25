package edu.fra.uas.v3autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MasterV3 {
    @Autowired
    @Qualifier("Geselle2")
    Journeyman2 journeyman;

    public void delegateWork() {
        journeyman.performWork();
    }
}
