package sevice;

import io.ReadAndWriteFile;
import model.Computer;
import model.Revenue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RevenueService implements IRevenue {
    public List<Revenue> revenues = new ArrayList<>();
    public static final String FILE_PATH = "C:\\Users\\MSI\\Desktop\\Module2\\CaseStudyModule2\\doanhthu.txt";
    ReadAndWriteFile<Revenue> revenueReadAndWriteFile = new ReadAndWriteFile<>();
    public  RevenueService (){
        revenues = revenueReadAndWriteFile.readFromFile(FILE_PATH);
    }
    @Override
    public List<Revenue> findAll() {
        revenues = revenueReadAndWriteFile.readFromFile(FILE_PATH);
        return revenues;
    }

    @Override
    public void save(double turnover, String name, LocalDateTime dateTime) {
        Revenue revenue = new Revenue(turnover,name,dateTime);
        revenues.add(revenue);
        revenueReadAndWriteFile.writeToFile(FILE_PATH, revenues);
    }

}


