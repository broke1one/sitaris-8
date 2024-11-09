package main;

import factory.DataLoaderFactory;
import loader.DataLoader;
import repository.PlaneRepository;
import strategy.StrategyExecutor;
import operations.GroupByBrandOperation;
import operations.CountPlanesOperation;

public class Main {
    public static void main(String[] args) {
        // Фабрика загрузчиков данных
        DataLoader xmlLoader = DataLoaderFactory.getDataLoader("xml");
        DataLoader textLoader = DataLoaderFactory.getDataLoader("text");

        PlaneRepository repository = PlaneRepository.getInstance();
        repository.addCars(xmlLoader.loadData("data/planes.xml"));
        repository.addCars(textLoader.loadData("data/planes.txt"));

        // Создание и выполнение операций
        StrategyExecutor executor = new StrategyExecutor();

        executor.setOperation(new GroupByBrandOperation());
        executor.executeOperation(repository.getAllPlanes());

        executor.setOperation(new CountPlanesOperation());
        executor.executeOperation(repository.getAllPlanes());
    }
}
