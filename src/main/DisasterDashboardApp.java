package main;

import service.DisasterService;
import model.Disaster;
import java.util.*;

public class DisasterDashboardApp {
    public static void main(String[] args) {
        try {
            System.out.println("🌍 Fetching latest disaster data...");
            List<Disaster> disasters = DisasterService.getAllDisasters();

            System.out.println("\n=== 🌪️ Disaster Dashboard ===\n");
            for (Disaster d : disasters)
                System.out.println(d);
        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }
}
