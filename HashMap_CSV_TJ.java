import java.util.HashMap;

public class HashMap_CSV_TJ {
    // Define the HashMap to store Vendor ID -> Vendor Name mappings
    private static HashMap<String, String> vendorMap = new HashMap<>();

    public static void showPCI() {
        pciInfo pci_test_tj = new pciInfo();
        pci_test_tj.read();

        System.out.print("Number of buses = " + pci_test_tj.busCount() + "\nYou're welcome :)");

        // Populate the vendorMap (you could alternatively load from a CSV file)
        vendorMap.put("0x8086", "Intel Corporation");
        vendorMap.put("0x10DE", "NVIDIA Corporation");
        vendorMap.put("0x1414", "Microsoft Corporation");
        vendorMap.put("0x106B", "Apple Corporation");
        vendorMap.put("0x046D", "Logitech Corporation");
        vendorMap.put("0x1043", "Asus Corporation");
        vendorMap.put("0x17AA", "Lenovo Corporation");
        vendorMap.put("0x1028", "Dell Corporation");
        vendorMap.put("0x0781", "SanDisk Corporation");
        vendorMap.put("0x103C", "HP Corporation");
        vendorMap.put("0x80EE", "Oracle Corporation");


        // Add more entries as needed

        for (int i = 0; i < pci_test_tj.busCount(); i++) {
            System.out.println("\nBus " + i + " has " + pci_test_tj.deviceCount(i) + " devices... nice");

            for (int j = 0; j < 32; j++) {
                if (pci_test_tj.functionCount(i, j) > 0) {
                    System.out.println("Bus " + i + " device " + j + " has " +
                            pci_test_tj.functionCount(i, j) + " functions");

                    for (int k = 0; k < 8; k++) {
                        if (pci_test_tj.functionPresent(i, j, k) > 0) {
                            // Get Vendor ID
                            String vendorId = String.format("0x%04X", pci_test_tj.vendorID(i, j, k));

                            // Lookup Vendor Name in the HashMap
                            String vendorName = vendorMap.getOrDefault(vendorId, "Unknown Vendor");

                            System.out.println("Bus " + i + " device " + j + " function " + k +
                                    " has vendor " + vendorId + " (" + vendorName + ")" +
                                    " and product " + String.format("0x%04X", pci_test_tj.productID(i, j, k)));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.loadLibrary("sysinfo");
        showPCI();
    }
}
