/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.01.2020
 */
package com.alllexe.soap;

import unisoft.ws.FNSNDSCAWS2;
import unisoft.ws.FNSNDSCAWS2Port;
import unisoft.ws.fnsndscaws2.request.NdsRequest2;
import unisoft.ws.fnsndscaws2.response.NdsResponse2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        FNSNDSCAWS2 fnsndscaws2 = new FNSNDSCAWS2();
        FNSNDSCAWS2Port port = fnsndscaws2.getFNSNDSCAWS2Port();
        NdsRequest2 request = new NdsRequest2();
        List<NdsRequest2.NP> list = request.getNP();
        NdsRequest2.NP np = new NdsRequest2.NP();
        np.setDT("01.01.2020");
        np.setINN("7725271233");
        np.setKPP("772501001");
        list.add(np);
        NdsResponse2 ndsResponse2 = port.ndsRequest2(request);
        ndsResponse2.getNP().forEach(n->{
            System.out.println(n.getDT()+" "+n.getINN()+" "+n.getKPP()+" "+n.getState());
        });

    }
}
