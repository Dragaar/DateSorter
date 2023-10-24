import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 *
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        HashSet<Integer> monthWithR = new HashSet<>(8);
        monthWithR.add(1);
        monthWithR.add(2);
        monthWithR.add(3);
        monthWithR.add(4);
        monthWithR.add(9);
        monthWithR.add(10);
        monthWithR.add(11);
        monthWithR.add(12);

        Predicate<LocalDate> containsR = d -> monthWithR.contains(d.getMonthValue());

        ArrayList<LocalDate> monthWithRList
                = unsortedDates.stream()
                .filter(containsR)
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<LocalDate> monthWithoutRList
                = unsortedDates.stream()
                .filter(containsR.negate())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));

        monthWithRList.addAll(monthWithoutRList);

        return monthWithRList;
    }

    /* public static void main(String[] args) {
        //before use make sortDates static

        List<LocalDate> dates = new ArrayList<>();
         dates.add(LocalDate.parse("2004-07-01"));
         dates.add(LocalDate.parse("2005-01-02"));
         dates.add(LocalDate.parse("2007-01-01"));
         dates.add(LocalDate.parse("2032-05-03"));

         //(2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)

         Collection<LocalDate> result = sortDates(dates);
         System.out.println(result);


         List<LocalDate> dates2 = new ArrayList<>();
         dates2.add(LocalDate.parse("2000-12-02"));
         dates2.add(LocalDate.parse("2026-05-04"));
         dates2.add(LocalDate.parse("2298-06-11"));
         dates2.add(LocalDate.parse("2125-07-10"));

         //(2000-12-02, 2298-06-11, 2125-07-10, 2026-05-04)

         Collection<LocalDate> result2 = sortDates(dates2);
         System.out.println(result2);
     }*/
}