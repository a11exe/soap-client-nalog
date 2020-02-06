package com.alllexe.soap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.Test;
import unisoft.ws.fnsndscaws2.response.NdsResponse2;
import unisoft.ws.fnsndscaws2.response.NdsResponse2.NP;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.01.2020
 */
public class CheckFnsTest {

  @Test
  public void whenCheckKontragentShouldGetAnswer() {
    String inn = "7723650437";
    String kpp = "771701001";
    LocalDate date = LocalDate.parse("01.01.2020", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    CheckFns checkFns = new CheckFns();
    Kontragent kontragent = new Kontragent(inn, kpp);
    NdsResponse2 ndsResponse2 = checkFns.checkKontragent(date, kontragent);

    assertThat(ndsResponse2.getNP().size(), is(1));
    assertThat(ndsResponse2.getNP().get(0).getState(), is("1"));
  }

  @Test
  public void ndsResponse2ToMessages() {
    CheckFns checkFns = new CheckFns();

    NdsResponse2 ndsResponse2 = new NdsResponse2();
    List<NP> nps = ndsResponse2.getNP();
    NP np = new NP();
    np.setINN("7723650437");
    np.setKPP("771701001");
    np.setDT("01.01.2020");
    np.setState("1");
    nps.add(np);

    assertThat(checkFns.ndsResponse2ToMessages(ndsResponse2).size(), is(1));
    assertThat(checkFns.ndsResponse2ToMessages(ndsResponse2).get(0),
        is("Дата запроса: 01.01.2020, ИНН: 7723650437, КПП: 771701001, Ответ: 1 (Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату)"));
  }
}