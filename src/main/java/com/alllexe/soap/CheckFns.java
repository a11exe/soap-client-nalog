/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.01.2020
 */
package com.alllexe.soap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import unisoft.ws.FNSNDSCAWS2;
import unisoft.ws.FNSNDSCAWS2Port;
import unisoft.ws.fnsndscaws2.request.NdsRequest2;
import unisoft.ws.fnsndscaws2.response.NdsResponse2;
import unisoft.ws.fnsndscaws2.response.NdsResponse2.NP;

public class CheckFns {

    private final FNSNDSCAWS2Port port;

    private final String responseFormat = "Дата запроса: %s, ИНН: %s, КПП: %s, Ответ: %s (%s)";

    private final String[] statesUL = new String[]{
        "Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату",
        "Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату",
        "Налогоплательщик зарегистрирован в ЕГРН",
        "Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН, КПП не соответствует ИНН или не указан",
        "Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН",
        "Некорректный ИНН",
        "Недопустимое количество символов ИНН",
        "Недопустимое количество символов КПП",
        "Недопустимые символы в ИНН",
        "Недопустимые символы в КПП",
        "",
        "некорректный формат даты",
        "некорректная дата (ранее 01.01.1991 или позднее текущей даты)"
    };

    private final String[] statesFL = new String[]{
        "Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату",
        "Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату",
        "Налогоплательщик зарегистрирован в ЕГРН",
        "",
        "Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН",
        "Некорректный ИНН",
        "Недопустимое количество символов ИНН",
        "",
        "Недопустимые символы в ИНН",
        "",
        "КПП не должен использоваться при проверке ИП",
        "некорректный формат даты",
        "некорректная дата (ранее 01.01.1991 или позднее текущей даты)"
    };

    public CheckFns() {
        FNSNDSCAWS2 fnsndscaws2 = new FNSNDSCAWS2();
        port = fnsndscaws2.getFNSNDSCAWS2Port();
    }

    public NdsResponse2 checkKontragent(LocalDate date, Kontragent kontragent) {
        NdsRequest2 request = new NdsRequest2();
        List<NdsRequest2.NP> list = request.getNP();
        NdsRequest2.NP np = new NdsRequest2.NP();
        np.setDT(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        np.setINN(kontragent.getInn());
        np.setKPP(kontragent.getKpp());
        list.add(np);
        return port.ndsRequest2(request);
    }

    public List<String> ndsResponse2ToMessages(NdsResponse2 ndsResponse2) {
        List<String> messages = new ArrayList<>();
        ndsResponse2.getNP().forEach(n -> messages.add(String.format(responseFormat, n.getDT(), n.getINN(), n.getKPP(), n.getState(), getStateMsg(n, n.getState()))));
        return messages;
    }

    private String getStateMsg(NP n, String stateNum) {
        String msg;
        Integer num = Integer.valueOf(stateNum) - 1;
        if (n.getINN().length() == 10) {
          msg = statesUL[num];
        } else {
          msg = statesFL[num];
        }
        return msg;
    }

}
