package currencyConverter;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestConvertBlackbox {
    ArrayList<Currency> currencyList;
    Map<String, Currency> currencyMap;

    {
        currencyList = Currency.init();
        currencyMap = new HashMap<>();
        for (Currency c : currencyList) {
            currencyMap.put(c.getShortName(), c);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0.000000, 0.000000, 0.000000",
            "0.000000, 0.100000, 0.000000",
            "0.000000, 0.010000, 0.000000",
            "0.000000, 0.001000, 0.000000",
            "0.000000, 0.000100, 0.000000",
            "0.000000, 10.000000, 0.000000",
            "0.000000, 100.000000, 0.000000",
            "1.000000, 0.000000, 0.000000",
            "1.000000, 0.100000, 0.100000",
            "1.000000, 0.010000, 0.010000",
            "1.000000, 0.001000, 0.000000",
            "1.000000, 0.000100, 0.000000",
            "1.000000, 10.000000, 10.000000",
            "1.000000, 100.000000, 100.000000",
            "2.500000, 0.000000, 0.000000",
            "2.500000, 0.100000, 0.250000",
            "2.500000, 0.010000, 0.030000",
            "2.500000, 0.001000, 0.000000",
            "2.500000, 0.000100, 0.000000",
            "2.500000, 10.000000, 25.000000",
            "2.500000, 100.000000, 250.000000",
            "0.500000, 0.000000, 0.000000",
            "0.500000, 0.100000, 0.050000",
            "0.500000, 0.010000, 0.010000",
            "0.500000, 0.001000, 0.000000",
            "0.500000, 0.000100, 0.000000",
            "0.500000, 10.000000, 5.000000",
            "0.500000, 100.000000, 50.000000",
            "4.000000, 0.000000, 0.000000",
            "4.000000, 0.100000, 0.400000",
            "4.000000, 0.010000, 0.040000",
            "4.000000, 0.001000, 0.000000",
            "4.000000, 0.000100, 0.000000",
            "4.000000, 10.000000, 40.000000",
            "4.000000, 100.000000, 400.000000",
            "100.000000, 0.000000, 0.000000",
            "100.000000, 0.100000, 10.000000",
            "100.000000, 0.010000, 1.000000",
            "100.000000, 0.001000, 0.100000",
            "100.000000, 0.000100, 0.010000",
            "100.000000, 10.000000, 1000.000000",
            "100.000000, 100.000000, 10000.000000",
            "999999.990000, 0.000000, 0.000000",
            "999999.990000, 0.100000, 100000.000000",
            "999999.990000, 0.010000, 10000.000000",
            "999999.990000, 0.001000, 1000.000000",
            "999999.990000, 0.000100, 100.000000",
            "999999.990000, 10.000000, 9999999.900000",
            "999999.990000, 100.000000, 99999999.000000",
            "1000000.000000, 0.000000, 0.000000",
            "1000000.000000, 0.100000, 100000.000000",
            "1000000.000000, 0.010000, 10000.000000",
            "1000000.000000, 0.001000, 1000.000000",
            "1000000.000000, 0.000100, 100.000000",
            "1000000.000000, 10.000000, 10000000.000000",
            "1000000.000000, 100.000000, 100000000.000000",
    })
    public void testCurrencyConverter(double amount, double rate, double expected) {
        assertEquals(expected,
                Currency.convert(amount,
                        rate
                ), 1e-2, "test converting " + amount + " * " + rate);
    }


    @ParameterizedTest
    @CsvSource({
            "US Dollar,US Dollar,0.500000,0.500000",
            "US Dollar,US Dollar,2.000000,2.000000",
            "US Dollar,US Dollar,1000.000000,1000.000000",
            "US Dollar,US Dollar,1000000.000000,1000000.000000",
            "US Dollar,US Dollar,3.141500,3.140000",
            "US Dollar,US Dollar,123.456000,123.460000",
            "US Dollar,Euro,0.500000,0.470000",
            "US Dollar,Euro,2.000000,1.860000",
            "US Dollar,Euro,1000.000000,930.000000",
            "US Dollar,Euro,1000000.000000,930000.000000",
            "US Dollar,Euro,3.141500,2.920000",
            "US Dollar,Euro,123.456000,114.810000",
            "US Dollar,British Pound,0.500000,0.330000",
            "US Dollar,British Pound,2.000000,1.320000",
            "US Dollar,British Pound,1000.000000,660.000000",
            "US Dollar,British Pound,1000000.000000,660000.000000",
            "US Dollar,British Pound,3.141500,2.070000",
            "US Dollar,British Pound,123.456000,81.480000",
            "US Dollar,Swiss Franc,0.500000,0.510000",
            "US Dollar,Swiss Franc,2.000000,2.020000",
            "US Dollar,Swiss Franc,1000.000000,1010.000000",
            "US Dollar,Swiss Franc,1000000.000000,1010000.000000",
            "US Dollar,Swiss Franc,3.141500,3.170000",
            "US Dollar,Swiss Franc,123.456000,124.690000",
            "Euro,US Dollar,0.500000,0.540000",
            "Euro,US Dollar,2.000000,2.150000",
            "Euro,US Dollar,1000.000000,1073.000000",
            "Euro,US Dollar,1000000.000000,1073000.000000",
            "Euro,US Dollar,3.141500,3.370000",
            "Euro,US Dollar,123.456000,132.470000",
            "Euro,Euro,0.500000,0.500000",
            "Euro,Euro,2.000000,2.000000",
            "Euro,Euro,1000.000000,1000.000000",
            "Euro,Euro,1000000.000000,1000000.000000",
            "Euro,Euro,3.141500,3.140000",
            "Euro,Euro,123.456000,123.460000",
            "Euro,British Pound,0.500000,0.360000",
            "Euro,British Pound,2.000000,1.420000",
            "Euro,British Pound,1000.000000,710.000000",
            "Euro,British Pound,1000000.000000,710000.000000",
            "Euro,British Pound,3.141500,2.230000",
            "Euro,British Pound,123.456000,87.650000",
            "Euro,Swiss Franc,0.500000,0.540000",
            "Euro,Swiss Franc,2.000000,2.160000",
            "Euro,Swiss Franc,1000.000000,1080.000000",
            "Euro,Swiss Franc,1000000.000000,1080000.000000",
            "Euro,Swiss Franc,3.141500,3.390000",
            "Euro,Swiss Franc,123.456000,133.330000",
            "British Pound,US Dollar,0.500000,0.760000",
            "British Pound,US Dollar,2.000000,3.020000",
            "British Pound,US Dollar,1000.000000,1510.000000",
            "British Pound,US Dollar,1000000.000000,1510000.000000",
            "British Pound,US Dollar,3.141500,4.740000",
            "British Pound,US Dollar,123.456000,186.420000",
            "British Pound,Euro,0.500000,0.710000",
            "British Pound,Euro,2.000000,2.820000",
            "British Pound,Euro,1000.000000,1410.000000",
            "British Pound,Euro,1000000.000000,1410000.000000",
            "British Pound,Euro,3.141500,4.430000",
            "British Pound,Euro,123.456000,174.070000",
            "British Pound,British Pound,0.500000,0.500000",
            "British Pound,British Pound,2.000000,2.000000",
            "British Pound,British Pound,1000.000000,1000.000000",
            "British Pound,British Pound,1000000.000000,1000000.000000",
            "British Pound,British Pound,3.141500,3.140000",
            "British Pound,British Pound,123.456000,123.460000",
            "British Pound,Swiss Franc,0.500000,0.760000",
            "British Pound,Swiss Franc,2.000000,3.040000",
            "British Pound,Swiss Franc,1000.000000,1520.000000",
            "British Pound,Swiss Franc,1000000.000000,1520000.000000",
            "British Pound,Swiss Franc,3.141500,4.780000",
            "British Pound,Swiss Franc,123.456000,187.650000",
            "Swiss Franc,US Dollar,0.500000,0.500000",
            "Swiss Franc,US Dollar,2.000000,1.980000",
            "Swiss Franc,US Dollar,1000.000000,990.000000",
            "Swiss Franc,US Dollar,1000000.000000,990000.000000",
            "Swiss Franc,US Dollar,3.141500,3.110000",
            "Swiss Franc,US Dollar,123.456000,122.220000",
            "Swiss Franc,Euro,0.500000,0.470000",
            "Swiss Franc,Euro,2.000000,1.860000",
            "Swiss Franc,Euro,1000.000000,930.000000",
            "Swiss Franc,Euro,1000000.000000,930000.000000",
            "Swiss Franc,Euro,3.141500,2.920000",
            "Swiss Franc,Euro,123.456000,114.810000",
            "Swiss Franc,British Pound,0.500000,0.330000",
            "Swiss Franc,British Pound,2.000000,1.320000",
            "Swiss Franc,British Pound,1000.000000,660.000000",
            "Swiss Franc,British Pound,1000000.000000,660000.000000",
            "Swiss Franc,British Pound,3.141500,2.070000",
            "Swiss Franc,British Pound,123.456000,81.480000",
            "Swiss Franc,Swiss Franc,0.500000,0.500000",
            "Swiss Franc,Swiss Franc,2.000000,2.000000",
            "Swiss Franc,Swiss Franc,1000.000000,1000.000000",
            "Swiss Franc,Swiss Franc,1000000.000000,1000000.000000",
            "Swiss Franc,Swiss Franc,3.141500,3.140000",
            "Swiss Franc,Swiss Franc,123.456000,123.460000",
    })
    public void testMainWindowConverter(String from, String to, double fromAmount, double expected) {
        assertEquals(expected,
                MainWindow.convert(findNameByName(from),
                        findNameByName(to), currencyList, fromAmount),
                1e-2
                );

    }

    private String findNameByName(String name) {
        for (Currency cur : currencyList) {
            if (cur.getName().equals(name)) {

                return cur.getName();
            }
        }
        return null;
    }



    @ParameterizedTest
    @CsvSource({
        "USD, USD, 0.5", // short names
        "USD, British Pound, 0.5",
        "British Pound, USD, 0.5",
        "A, B, 0.5", // names not found
        "A, British Pound, 0.5",
        "British Pound, A, 0.5",
        "British Pound, British Pound, 0.001", // amount too small
    })
    public void testInvalidWindowConvert(String from, String to, double fromAmount) {
        assertEquals(0.0,
                MainWindow.convert(findNameByName(from),
                        findNameByName(to), currencyList, fromAmount),
                1e-2, "test converting " + from + " -> " + to + " with amount " + fromAmount);
    }
}