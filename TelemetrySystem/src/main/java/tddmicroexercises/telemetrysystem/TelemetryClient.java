package tddmicroexercises.telemetrysystem;

import java.util.Random;

public class TelemetryClient implements Client
{
    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";

    private boolean onlineStatus;
    private String diagnosticMessageResult = "";




    private final ConnectionSimulator connectionEventsSimulator = new ConnectionSimulator(42);

    @Override
    public boolean getOnlineStatus()
    {
        return onlineStatus; 
    }

    @Override
    public void connect(String telemetryServerConnectionString)
    {
        if (telemetryServerConnectionString == null || "".equals(telemetryServerConnectionString))
        {
            throw new IllegalArgumentException();
        }

        // simulate the operation on a real modem
        boolean success = connectionEventsSimulator.connect(10) <= 8;

        onlineStatus = success;
    }

    @Override
    public void disconnect()
    {
        onlineStatus = false;
    }

    @Override
    public void send(String message)
    {
        if (message == null || "".equals(message))
        {
            throw new IllegalArgumentException();
        }

        if (message == DIAGNOSTIC_MESSAGE)
        {
            // simulate a status report
            diagnosticMessageResult =
                  "LAST TX rate................ 100 MBPS\r\n"
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
                + "Remote Rtrn Count........... 00";

            return;
        }

        // here should go the real Send operation (not needed for this exercise)
    }

   @Override
   public String receive()
    {
        String message;

        if (diagnosticMessageResult == null || "".equals(diagnosticMessageResult))
        {
            // simulate a received message (just for illustration - not needed for this exercise)
            message = "";
            int messageLength = connectionEventsSimulator.connect(50) + 60;
            for(int i = messageLength; i >=0; --i)
            {
                message += (char)connectionEventsSimulator.connect(40) + 86;
            }
            
        } 
        else
        {                
            message = diagnosticMessageResult;
            diagnosticMessageResult = "";
        }

        return message;
    }
}

