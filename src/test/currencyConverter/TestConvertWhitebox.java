package currencyConverter;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestConvertWhitebox {
    ArrayList<Currency> currencyList;
    Map<String, Currency> currencyMap;

    {
        currencyList = Currency.init();
        currencyMap = new HashMap<>();
        for (Currency c : currencyList) {
            currencyMap.put(c.getShortName(), c);
        }
    }

    @Test
    public void testCoverageConvert() {
        assertEquals(0.06, Currency.convert(0.2, 0.3), 0.0001);
        assertEquals(0.00, Currency.convert(0.2, 0.03), 0.0001);
    }

    @Test
    public void testMainWindowInstructions() {
        // 1
        assertEquals(0.0, MainWindow.convert("A", "B", currencyList, 0.5), 0.0001);
        // not 1, 2
        assertEquals(0.0, MainWindow.convert("Euro", "B", currencyList, 0.5), 0.0001);
        // not 1, not 2
        assertEquals(0.5, MainWindow.convert("Euro", "Euro", currencyList, 0.5), 0.0001);
    }



    @Test
    public void testMainWindowArc() {
        // 1, 2, 3, 4, 5, 6, 7
        assertEquals(0.35, MainWindow.convert("British Pound", "Euro", currencyList, 0.25), 0.0001);
        // 1, 6, 8
        assertEquals(0.0, MainWindow.convert("Not Found 1", "Euro", currencyList, 0.25), 0.0001);
        // 1, 2, 3, 4, 7, 9
        assertEquals(0.0, MainWindow.convert("British Pound", "Not Found 2", currencyList, 0.25), 0.0001);
        // 1, 2, 3, 4, 5 only
        assertEquals(0.25, MainWindow.convert("US Dollar", "US Dollar", currencyList, 0.25), 0.0001);
        // 1, 8
        assertEquals(0.0, MainWindow.convert("US Dollar", "US Dollar", new ArrayList<>(), 0.25), 0.0001);
    }


    @Test
    public void testMainWindowPathsIndependent() {
        // 1 -> 2 -> 3 -> 4 -> 5
        assertEquals(0.25, MainWindow.convert("US Dollar", "US Dollar", currencyList, 0.25), 0.0001);
        // 1 -> 2 -> 6 -> 1 -> 2-> 3 -> 4 -> 5
        assertEquals(0.27, MainWindow.convert("Euro", "US Dollar", currencyList, 0.25), 0.0001);
        // 1 -> 8
        assertEquals(0.0, MainWindow.convert("US Dollar", "US Dollar", new ArrayList<>(), 0.25), 0.0001);
        // 1 -> 2 -> 6 -> 8
        assertEquals(0.0, MainWindow.convert("Not Found", "US Dollar", currencyList, 0.25), 0.0001);
        // 1 -> 2 -> 3 -> 4 -> 7 -> 3 -> 9
        assertEquals(0.0, MainWindow.convert("US Dollar", "Not Found", currencyList, 0.25), 0.0001);
        // 1 -> 2 -> 3 -> 4 -> 7 -> 3 ->  5
        assertEquals(0.23, MainWindow.convert("US Dollar", "Euro", currencyList, 0.25), 0.0001);
        // 1 -> 2 -> 6 ->  2 -> 3 -> 4 -> 7 -> 4 -> 5
        assertEquals(0.25, MainWindow.convert("Euro", "Euro", currencyList, 0.25), 0.0001);
    }

    @Test
    public void testMainWindowCondition() {
        // c
        assertEquals(0.0, MainWindow.convert("Euro", "Euro", new ArrayList<>(), 0.25), 0.0001);
        // b, c
        assertEquals(0.0, MainWindow.convert("Not Found1", "Euro", currencyList, 0.25), 0.0001);
        // a, b, f
        // not applicable, which requires currentList be non-empty for first loop and be empty for the second loop
        // a, b, e, f
        assertEquals(0.0, MainWindow.convert("US Dollar", "Not Found2", currencyList, 0.25), 0.0001);
        // a, b, d, e
        assertEquals(0.23, MainWindow.convert("US Dollar", "Euro", currencyList, 0.25), 0.0001);
    }

    @Test
    public void testMainWindowIPaths() {
        // {0}
        assertEquals(0.0, MainWindow.convert("Euro", "Euro", new ArrayList<>(), 0.25), 0.0001);
        // {1..6} x {1..6}
        for (int i = 1; i <= 6; ++i) {
            for (int j = 1; j <= 6; ++j) {
                double value = currencyList.get(i - 1).getExchangeValues().get(currencyList.get(j - 1).getShortName());
                assertEquals(value, MainWindow.convert(currencyList.get(i - 1).getName(),
                        currencyList.get(j - 1).getName(), currencyList, 1.0), 0.0001);
            }
        }
        // {7} x {1..6}
        for (int j = 1; j <= 6; ++j) {
            assertEquals(0.0, MainWindow.convert("Not found 1",
                    currencyList.get(j - 1).getName(), currencyList, 1.0), 0.0001);
        }
        // {1..6} x {7}
        for (int j = 1; j <= 6; ++j) {
            assertEquals(0.0, MainWindow.convert(
                    currencyList.get(j - 1).getName(), "Not found 2", currencyList, 1.0), 0.0001);
        }

    }

}