/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.01.2020
 */
package com.alllexe.soap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckFns {

    private final List<String> states = Arrays.asList(
            "ИНН+КПП+ДАТА+ Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в интервале ± 6 дней от даты запроса",
            "ИНН+КПП+ДАТА- Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в интервале ± 6 дней от даты запроса",
            "ИНН+КПП+ Налогоплательщик зарегистрирован в ЕГРН (в запросе не указана дата)",
            "ИНН+КПП- Несоотвествие КПП указанному в запросе ИНН (дата могла быть указана а могла и быть не указана)",
            "ИНН- Налогоплательщик с указанным ИНН не зарегистрирован в ЕГПН (дата могла быть указана а могла и быть не указана");


}
