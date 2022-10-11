package calculator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/calculaсte")//так было в тз
public class VacationPayController {


    private long counter;

    @GetMapping
    public VacationPayAmount calculate(@RequestParam(value="salary") float avgSalary,
                                      @RequestParam(value="days") int vacationDays) {

        float vacationPayAmount = (float) (avgSalary/29.3 * vacationDays);
        //делим среднюю зарплату на среднее количество дней в месяце и умножаем на количество дней в отпуске

        return new VacationPayAmount(counter++,
                 vacationPayAmount);
    }

    @GetMapping("/dates")
    public VacationPayAmount calculateWithDate(@RequestParam(value="salary") float avgSalary,
                                               @RequestParam(value="date1") String date1,
                                               @RequestParam(value="date2") String date2) {

        LocalDate firstDate = LocalDate.parse(date1);
        LocalDate secondDate = LocalDate.parse(date2);

        if(firstDate.isAfter(secondDate)){
            LocalDate temp = firstDate;
            firstDate = secondDate;
            secondDate = temp;
        }//если первая дата позже второй, они меняются местами

        Set<DayOfWeek> weekend = Set.of(
                DayOfWeek.SATURDAY, DayOfWeek.SUNDAY//воскресенье и суббота
        );

        Set<String> officialHolidays = Set.of(//месяц.день
                "1.1","1.2","1.3","1.4","1.5","1.6","1.7","1.8","1.9",//Новогодние праздники
                "2.23",//День защитника Отечества
                "3.8",//Международный женский день
                "5.1",//День весны и труда
                "5.9",//День Победы
                "6.12",//День России
                "11.4",//День народного единства
                "12.31"//Новый год
        );


        long vacationDays = firstDate.datesUntil(secondDate.plusDays(1))//метод datesUntil принимает значение не включительно
                .filter(t -> !weekend.contains(t.getDayOfWeek()) &&
                            !officialHolidays.contains(t.getMonthValue()+"."+t.getDayOfMonth()))
                .count();


        float vacationPayAmount = (float) (avgSalary/29.3 * vacationDays);
        //делим среднюю зарплату на среднее количество дней в месяце и умножаем на количество дней в отпуске

        return new VacationPayAmount(counter++,
                vacationPayAmount);
    }

}