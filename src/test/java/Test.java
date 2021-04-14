import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;
import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.jupiter.api.Test
    public void messageSenderImplRuTest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Assertions.assertEquals(messageSender.send(map), "Добро пожаловать");
    }

    @org.junit.jupiter.api.Test
    public void messageSenderImplEnTest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP)).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Assertions.assertEquals(messageSender.send(map), "Welcome");
    }

    @org.junit.jupiter.api.Test
    public void byIpTest() {
        String ip = GeoServiceImpl.NEW_YORK_IP;
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location location = new Location("New York", Country.USA, "10th Avenue", 32);
        Assertions.assertEquals(geoService.byIp(ip), location);
    }

    @org.junit.jupiter.api.Test
    public void localeTest() {
        Country country = Country.BRAZIL;
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String actual = "Welcome";
        Assertions.assertEquals(localizationService.locale(country), actual);
    }
}
