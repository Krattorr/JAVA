/*
 * Unit testing methods for an exercise
 */

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class TestExercise {

    @ParameterizedTest
    @CsvSource(value={
        "'', false",
        "'   ', false",
        "1,true",
        "-1,true",
        "+1,true",
        "123,true",
        "-456,true",
        "-4a6,false",
        "' 1',false",
        "'1 ',false",
    })
    @DisplayName("test esEnter()")
    public void esEnter(String text, boolean esperat) {
        boolean obtingut = UtilString.esEnter(text);
        assertEquals(esperat, obtingut, "Revisa el cas en que l'entrada sigui '" +
                                        text + "'");
    }
}