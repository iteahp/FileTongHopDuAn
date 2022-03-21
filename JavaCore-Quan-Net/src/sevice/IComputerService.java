package sevice;

import model.Computer;

import java.util.List;

public interface IComputerService extends IService<Computer> {
    int findIndexById(int id);
}
