package tddmicroexercises.telemetrysystem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TelemetryDiagnosticControlsTest {
    private Client telemetryClient;
    private TelemetryDiagnosticControls telemetryDiagnosticControls;


Client client = Mockito.mock(TelemetryClient.class);
TelemetryDiagnosticControls telemetryDiagnosticControls1 = new TelemetryDiagnosticControls(client);

    @BeforeEach
    public void init () {
        telemetryClient = new TelemetryClient();
        telemetryDiagnosticControls = new TelemetryDiagnosticControls(telemetryClient);
    }

    @Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() {
        try {
            telemetryDiagnosticControls.checkTransmission();
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
        }
        Assertions.assertEquals(telemetryDiagnosticControls.getDiagnosticInfo(), "LAST TX rate................ 100 MBPS\r\n"
                + "HIGHEST TX rate............. 100 MBPS\r\n"
                + "LAST RX rate................ 100 MBPS\r\n"
                + "HIGHEST RX rate............. 100 MBPS\r\n"
                + "BIT RATE.................... 100000000\r\n"
                + "WORD LEN.................... 16\r\n"
                + "WORD/FRAME.................. 511\r\n"
                + "BITS/FRAME.................. 8192\r\n"
                + "MODULATION TYPE............. PCM/FM\r\n"
                + "TX Digital Los.............. 0.75\r\n"
                + "RX Digital Los.............. 0.10\r\n"
                + "BEP Test.................... -5\r\n"
                + "Local Rtrn Count............ 00\r\n"
                + "Remote Rtrn Count........... 00");
    }
    @Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response_with_exception() {

        Mockito.when(client.getOnlineStatus()).thenReturn(false);
        Exception exception = Assertions.assertThrows(Exception.class, telemetryDiagnosticControls1::checkTransmission);
        Assertions.assertEquals("Unable to connect.", exception.getMessage());
        System.out.println(exception.getMessage());
    }

}


