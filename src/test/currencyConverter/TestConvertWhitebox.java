package currencyConverter;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.HashMap;
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
    public void testCoverageWindowConvertInstructions() {
        assertEquals(0.0, MainWindow.convert("A", "B", currencyList, 0.5), 0.0001);
        assertEquals(0.0, MainWindow.convert("Euro", "B", currencyList, 0.5), 0.0001);
        assertEquals(0.5, MainWindow.convert("Euro", "Euro", currencyList, 0.5), 0.0001);
    }



}