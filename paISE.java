// Main use for USB
//import java.util.ArrayList;
import java.util.HashMap;

class USBInfo {
    String bus;
    String device;
    String vendorID;
    String vendorName;
    String productID;
    String productDescription;

    public USBInfo(String bus, String device, String vendorID, String vendorName, String productID, String productDescription) {
        this.bus = bus;
        this.device = device;
        this.vendorID = vendorID;
        this.vendorName = vendorName;
        this.productID = productID;
        this.productDescription = productDescription;
    }

    public void displayInfo() {
        System.out.println("Bus: " + bus);
        System.out.println("Device: " + device);
        System.out.println("Vendor ID: " + vendorID);
        System.out.println("Vendor Name: " + vendorName);
        System.out.println("Product ID: " + productID);
        System.out.println("Product Description: " + productDescription);
        System.out.println("-------------------------");
    }
}
public class paISE {

    // Lookup table for Vendor IDs and Vendor Names
    private static HashMap<String, String> vendorMap = new HashMap<>();

    // Lookup table for Product IDs and Product Descriptions
    private static HashMap<String, String> productMap = new HashMap<>();

    static {

        // Vendor ID to vendor description
        vendorMap.put("0x8086", "Intel Corporation");
        vendorMap.put("0x10DE", "NVIDIA Corporation");
        vendorMap.put("0x1414", "Microsoft Corporation");
        vendorMap.put("0x106B", "Apple Corporation");
        vendorMap.put("0x046D", "Logitech Corporation");
        vendorMap.put("0x05AC", "Apple, Inc.");
        vendorMap.put("0x04B3", "IBM Corporation");
        vendorMap.put("0x17EF", "Lenovo Group Limited");
        vendorMap.put("0x18D1", "Google Inc.");
        vendorMap.put("0x12D1", "Huawei Technologies Co., Ltd.");
        vendorMap.put("0x1D6B", "Linux Foundation");
        vendorMap.put("0x1A86", "QinHeng Electronics");
        vendorMap.put("0x05E3", "Genesys Logic, Inc.");
        vendorMap.put("0x045E", "Microsoft Corporation");
        vendorMap.put("0x0BDA", "Realtek Semiconductor Corp.");
        vendorMap.put("0x1C4F", "SiGma Micro");
        vendorMap.put("0x04E8", "Samsung Electronics Co., Ltd.");
        vendorMap.put("0x0A5C", "Broadcom Corp.");
        vendorMap.put("0x0461", "Primax Electronics, Ltd.");
        vendorMap.put("0x04A9", "Canon Inc.");
        vendorMap.put("0x093A", "Pixart Imaging, Inc.");
        vendorMap.put("0x13FE", "Kingston Technology Company");
        vendorMap.put("0x0CF3", "Qualcomm Atheros Communications");
        vendorMap.put("0x22B8", "Motorola PCS");
        vendorMap.put("0x0403", "Future Technology Devices International, Ltd.");
        vendorMap.put("0x0D28", "ARM, Ltd.");
        vendorMap.put("0x0951", "Kingston Technology Company, Inc.");
        vendorMap.put("0x0557", "ATEN International Co., Ltd.");
        vendorMap.put("0x04BB", "I-O Data Device, Inc.");
        vendorMap.put("0x054C", "Sony Corporation");
        vendorMap.put("0x13D3", "IMC Networks");
        vendorMap.put("0x04D8", "Microchip Technology, Inc.");
        vendorMap.put("0x0451", "Texas Instruments, Inc.");
        vendorMap.put("0x12BA", "Aptiv Services US, LLC");
        vendorMap.put("0x046A", "Cherry GmbH");
        vendorMap.put("0x07B4", "ONTOTech LLC");
        vendorMap.put("0x08BB", "Texas Instruments Japan");
        vendorMap.put("0x0572", "Conexant Systems, Inc.");
        vendorMap.put("0x17CC", "Core Logic");
        vendorMap.put("0x04B4", "Cypress Semiconductor Corp.");
        vendorMap.put("0x0B05", "ASUSTek Computer Inc.");
        vendorMap.put("0x04DA", "Panasonic (Matsushita)");
        vendorMap.put("0x0731", "Sun Microsystems, Inc.");
        vendorMap.put("0x1234", "SigmaTel, Inc.");
        vendorMap.put("0x05A9", "OmniVision Technologies, Inc.");
        vendorMap.put("0x06CB", "Synaptics, Inc.");
        vendorMap.put("0x1B1C", "Corsair Memory, Inc.");



        // Product ID to Product Description
        productMap.put("0x1234", "USB 3.0 Controller");
        productMap.put("0x5678", "Gaming Mouse");
        productMap.put("0x8765", "Wireless Adapter");
        productMap.put("0x4321", "Bluetooth Module");
        productMap.put("0x1001", "USB 2.0 Hub");
        productMap.put("0x2002", "Webcam HD 720p");
        productMap.put("0x3003", "USB-C to HDMI Adapter");
        productMap.put("0x4004", "Ethernet Adapter");
        productMap.put("0x5005", "External Hard Drive");
        productMap.put("0x6006", "USB Flash Drive 32GB");
        productMap.put("0x7007", "Wireless Keyboard Receiver");
        productMap.put("0x8008", "External DVD Drive");
        productMap.put("0x9009", "Fingerprint Reader");
        productMap.put("0xA00A", "Graphics Tablet");
        productMap.put("0xB00B", "Portable Charger");
        productMap.put("0xC00C", "Thermal Printer");
        productMap.put("0xD00D", "Smart Card Reader");
        productMap.put("0xE00E", "Sound Card");
        productMap.put("0xF00F", "Wireless Display Adapter");
        productMap.put("0x1111", "USB Hub 4-Port");
        productMap.put("0x2222", "Digital Audio Interface");
        productMap.put("0x3333", "MIDI Controller");
        productMap.put("0x4444", "USB 3.1 Type-C Adapter");
        productMap.put("0x5555", "VR Headset Interface");
        productMap.put("0x6666", "Touchscreen Controller");
        productMap.put("0x7777", "USB Wi-Fi Dongle");
        productMap.put("0x8888", "LED Light Strip Controller");
        productMap.put("0x9999", "Portable Projector");
        productMap.put("0xAAAA", "USB to Serial Adapter");
        productMap.put("0xBBBB", "3D Printer Interface");
        productMap.put("0xCCCC", "Temperature Sensor");
        productMap.put("0xDDDD", "Motor Controller for Robotics");
        productMap.put("0xEEEE", "Camera Interface Module");
        productMap.put("0xFFFF", "Gaming Controller Adapter");

    }
    // Actual code

    public static void patrickUSB() {
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis computer has " + usb.busCount() + " USB buses");

        // Go through all USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus " + i + " has " + usb.deviceCount(i) + " devices");

            // Go through all USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {

                // Format Vendor and Product IDs as hexadecimal
                String vendorId = String.format("0x%04X", usb.vendorID(i, j));
                String productId = String.format("0x%04X", usb.productID(i, j));

                // Vendor if else
                String vendorName;
                if (vendorMap.containsKey(vendorId)) {
                    vendorName = vendorMap.get(vendorId);
                } else {
                    vendorName = "Unknown company";
                }

                // Product if else

                String productDescription;
                if (productMap.containsKey(productId)) {
                    productDescription = productMap.get(productId);
                } else {
                    productDescription = "Unknown product";
                }

                // Output
                System.out.println("Bus " + i + " device " + j + " has vendor ID " + vendorId + "\n" + " This means it is made by "
                        + " " + vendorName + "\n" + " Also, the product ID is " + productId + " thus it is a" +
                        " " + productDescription );
            }
        }
    }

    public static void main(String[] args) {

        patrickUSB();


    }
}
