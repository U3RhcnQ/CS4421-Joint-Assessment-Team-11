import java.util.HashMap;

public class Pci_TJ {

    private static HashMap<String, String> vendorMap = new HashMap<>();

    private static void loadVendorMap() {
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
    }
    private static  void 

    // Collects PCI information and returns as string
    public static String getPCIInfo() {
        pciInfo pci = new pciInfo(); //created an instance of pciInfo class
        pci.read();

        StringBuilder pciInfoOutput = new StringBuilder();
        int busCount = pci.busCount();
        pciInfoOutput.append("System PCI Information:\n"); //append basically adds the exact string to the end of whatever is currently in pciInfoOutput
        pciInfoOutput.append("Total Number of Buses: ").append(busCount).append("\n");
        pciInfoOutput.append("-------------------------------------\n");


        loadVendorMap(); //basically ensures that vendorMap hashmap is fully populated and ready to use.
                         //generally it's just a good thing to do before processing all the pci information

        for (int i = 0; i < busCount; i++) { // Iterate through each bus
            int deviceCount = pci.deviceCount(i); //pci.deviceCount(i) = returns the amount of devices on the  (ith bus) corresponding number i bus.
            pciInfoOutput.append("\nBus ").append(i).append(" Information:\n");
            pciInfoOutput.append("Total Devices on Bus ").append(i).append(": ").append(deviceCount).append("\n");

            for (int j = 0; j < deviceCount; j++) { // Iterate through each device until at last device count
                int functionCount = pci.functionCount(i, j); // returns the amount of functions on the jth device on the ith bus
                if (functionCount > 0) {
                    pciInfoOutput.append("Device ").append(j).append(" has ").append(functionCount).append(" functions\n");

                    for (int k = 0; k < functionCount; k++) { // Iterate through each function
                        if (pci.functionPresent(i, j, k) > 0) { //sees if a function is present. i.e. if a function is present then...

                            String vendorId = String.format("0x%04X", pci.vendorID(i, j, k));// a method that returns the vendorid of function k on device j on bus i
                            String productId = String.format("0x%04X", pci.productID(i, j, k));//ditto but w product id

                            //attempts to find a name for vendorId in the vendorMap.//otherwise it defaults to unknown vendor
                            String vendorName = vendorMap.getOrDefault(vendorId, "Unknown Vendor");//basically: get = attempts to retrieve the value associated with a specified key.(vendorId)
                                                                                                                            //If the key is present in the map, it returns the corresponding value.
                            pciInfoOutput.append("Function ").append(k).append(":\n");
                            pciInfoOutput.append("Vendor ID: ").append(vendorId).append(" (").append(vendorName).append(")\n");
                            pciInfoOutput.append("Product ID: ").append(productId).append("\n");
                        }
                    }
                } else {
                    pciInfoOutput.append("Device ").append(j).append(" has no functions.\n");
                }
            }
            pciInfoOutput.append("-------------------------------------\n");
        }

        return pciInfoOutput.toString(); // Returns the formatted PCI information as a String
    }

    public static void main(String[] args) {
        System.loadLibrary("sysinfo");
        String pciInfo = getPCIInfo(); // Call the method and store the result in a variable
        System.out.println(pciInfo);    // Print to console (optional) or pass to GUI
    }
}
