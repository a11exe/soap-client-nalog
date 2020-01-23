package com.alllexe.soap;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.01.2020
 */
public class Kontragent {

  private final String inn;
  private final String kpp;

  public Kontragent(String inn, String kpp) {
    this.inn = inn;
    this.kpp = kpp;
  }

  public String getInn() {
    return inn;
  }

  public String getKpp() {
    return kpp;
  }
}
