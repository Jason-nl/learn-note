package com.itcast.xa;

import org.junit.Test;

public class XATests {
    AP ap = new AP();
    TM tm = new TM();
    @Test
    public void xatest() throws  Exception{
        //System.out.println(ap.getRmAccountConn());
        //System.out.println(ap.getRmRedConn());
        tm.execute(ap.getRmZhiFuBaoConn(),ap.getRmYuEBaoConn());
    }
}
