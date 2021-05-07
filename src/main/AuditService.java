package main;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuditService {
    private static AuditService instance = null;
    private AuditService()
    {
        try {
            FileWriter csvWriter = new FileWriter("./InOut/Output/audit.csv", true);
            csvWriter.write("Message,DateTime\n");

            csvWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot print into audit.csv");
        }


    }

    public static AuditService getInstance()
    {
        if(instance == null)
        {
            instance = new AuditService();
        }
        return instance;
    }

    public void auditWrite(String mesaj)
    {
        Service service = Service.getInstance();
        try {
            FileWriter csvWriter = new FileWriter("./InOut/Output/audit.csv",true);
            csvWriter.write(mesaj+","+ LocalDateTime.now()+"\n");

            csvWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Cannot print into audit.csv");
        }
    }

}
