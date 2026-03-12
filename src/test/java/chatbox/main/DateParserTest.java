package chatbox.main;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DateParserTest {

    @Test
    public void parseDateTime_validFormats_parsesSuccessfully() {
        LocalDateTime dt1 = DateParser.parseDateTime("2/12/2019 1800");
        LocalDateTime dt2 = DateParser.parseDateTime("2019-12-02 1800");
        LocalDateTime dt3 = DateParser.parseDateTime("02-12-2019 1800");

        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), dt1);
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), dt2);
        assertEquals(LocalDateTime.of(2019, 12, 2, 18, 0), dt3);
    }

    @Test
    public void parseDateTime_invalidFormat_returnsNull() {
        assertNull(DateParser.parseDateTime("not-a-date"));
    }

    @Test
    public void format_null_returnsEmptyString() {
        assertEquals("", DateParser.format(null));
    }

    @Test
    public void format_validDate_returnsReadableString() {
        LocalDateTime dt = LocalDateTime.of(2020, 1, 1, 9, 30);
        String formatted = DateParser.format(dt);
        assertNotNull(formatted);
    }
}
