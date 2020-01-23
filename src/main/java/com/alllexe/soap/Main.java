/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 17.01.2020
 */
package com.alllexe.soap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import unisoft.ws.fnsndscaws2.response.NdsResponse2;

public class Main {

  public static void main(String[] args) {
    CheckFns checkFns = new CheckFns();

    Scanner in = new Scanner(System.in);
    System.out.println("Для проверки контрагента на сервере ФНС необходимо ввести данные");
    System.out.println("Введите ИНН:");
    String inn = in.nextLine();
    System.out.println("Введите КПП:");
    String kpp = in.nextLine();
    System.out
        .println("Введите дату запроса в формате 01.01.2019 (или запрос будет на текущую дату):");
    String dateStr = in.nextLine();
    LocalDate date = LocalDate.now();
    try {
      date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    } catch (Exception e) {

    }
    System.out.println("Выполняется проверка.. ");
    Kontragent kontragent = new Kontragent(inn, kpp);

    NdsResponse2 ndsResponse2 = checkFns.checkKontragent(date, kontragent);
    checkFns.ndsResponse2ToMessages(ndsResponse2).forEach(System.out::println);
  }
}
