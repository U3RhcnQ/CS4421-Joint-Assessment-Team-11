import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PciTJ {

    
    private pciInfo pci;
    private HashMap<String, String> vendorMap = new HashMap<>();
    private HashMap<String, String> productMap = new HashMap<>();

    public PciTJ(){
        this.pci = new pciInfo();
    }

    private void loadVendorMap() {
        vendorMap.put("0x8086", "Intel Corporation"); //declare that I used chatgpt to list the most common vendor ids and corresponding company name
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
        vendorMap.put("0x14E4", "Broadcom Corporation");
        vendorMap.put("0x168C", "Qualcomm Atheros");
        vendorMap.put("0x1969", "Qualcomm Atheros (formerly Attansic)");
        vendorMap.put("0x8087", "Intel Corporation (Wireless)");
        vendorMap.put("0x10EC", "Realtek Semiconductor Corp.");
        vendorMap.put("0x0A5C", "Broadcom Inc. (formerly Broadcom Corporation)");
        vendorMap.put("0x0BDA", "Realtek Semiconductor Corp.");
        vendorMap.put("0x1AE0", "Qualcomm Technologies, Inc.");
        vendorMap.put("0x1055", "Epson Corporation");
        vendorMap.put("0x1D6A", "Linux Foundation");
        vendorMap.put("0x0409", "NEC Corporation");
        vendorMap.put("0x046D", "Logitech Inc.");
        vendorMap.put("0x413C", "Dell Inc.");
        vendorMap.put("0x04F2", "Chicony Electronics Co., Ltd.");
        vendorMap.put("0x0557", "ATEN International Co., Ltd.");
        vendorMap.put("0x05AC", "Apple Inc.");
        vendorMap.put("0x093A", "Pixart Imaging Inc.");
        vendorMap.put("0x0CF3", "Qualcomm Atheros Communications");
        vendorMap.put("0x0E8D", "MediaTek Inc.");
        vendorMap.put("0x13D3", "IMC Networks");
        vendorMap.put("0x18D1", "Google Inc.");
        vendorMap.put("0x1BCF", "Sunplus Innovation Technology Inc.");
        vendorMap.put("0x5986", "Acer Inc.");
        vendorMap.put("0x05E3", "Genesys Logic, Inc.");
        vendorMap.put("0x0C45", "Sonix Technology Co., Ltd.");
        vendorMap.put("0x413C", "Dell Inc.");
        vendorMap.put("0x17EF", "Lenovo Group Limited");
        vendorMap.put("0x12D1", "Huawei Technologies Co., Ltd.");
        vendorMap.put("0x19D2", "ZTE Corporation");
        vendorMap.put("0x0489", "Foxconn / Hon Hai");
        vendorMap.put("0x1B96", "Pegatron Corporation");
        vendorMap.put("0x15AD", "VMware");

    }

    private void loadProductMap() {
        productMap.put("0x5917", "Intel UHD Graphics 620");
        productMap.put("0x9D71", "Intel Integrated Sensor Hub");
        productMap.put("0xA2A1", "Intel Management Engine Interface");
        productMap.put("0xA2A3", "Intel High Definition Audio Controller");
        productMap.put("0x3E92", "Intel UHD Graphics 630");
        productMap.put("0x1C49", "Intel 82579LM Gigabit Network Connection");
        productMap.put("0x1C3A", "Intel 6 Series/C200 Series Chipset Family USB Enhanced Host Controller");
        productMap.put("0x10DE", "NVIDIA GeForce GTX 1050");
        productMap.put("0x1C20", "Intel Ethernet Connection I218-LM");
        productMap.put("0xA370", "Intel Optane Memory");
        productMap.put("0x15D8", "Intel Wireless-AC 9260");
        productMap.put("0x5A84", "Intel UHD Graphics 600");
        productMap.put("0xD138", "AMD Radeon RX 560X");
        productMap.put("0x67EF", "AMD Radeon RX 460/560");
        productMap.put("0x1662", "Qualcomm Atheros QCA6174 802.11ac Wireless Network Adapter");
        productMap.put("0xA3F0", "Intel High Definition Audio Controller");
        productMap.put("0x24F3", "Realtek RTL8822CE 802.11ac PCIe Wireless Network Adapter");
        productMap.put("0x8176", "Realtek RTL8188CE 802.11b/g/n WiFi Adapter");
        productMap.put("0x57D2", "SanDisk Ultra USB 3.0 Flash Drive");
        productMap.put("0x1237", "Intel 82440FX PCI and Memory Controller (Natoma)");
        productMap.put("0x7000", "Intel 82371SB PIIX3 ISA Bridge");
        productMap.put("0x7111", "Intel 82371AB/EB/MB PIIX4 IDE Controller");
        productMap.put("0x0405", "Unknown Product");
        productMap.put("0x100E", "Intel 82540EM Gigabit Ethernet Controller");
        productMap.put("0xCAFE", "Placeholder/Development ID");
        productMap.put("0x2415", "Intel 82801AA AC'97 Audio Controller");
        productMap.put("0x003F", "Unknown Product");
        productMap.put("0x7113", "Intel 82371AB/EB/MB PIIX4 ACPI Controller");
        productMap.put("0x265C", "Unknown Product");
        productMap.put("0x2829", "Intel 82801HBM/HEM SATA AHCI Controller (ICH8M)");
        productMap.put("0x0405", "USB product identifier");


    }

    public String[][] getPCIInfo() {
        pci.read();
        loadVendorMap();
        loadProductMap();

        List<String[]> pciFunctionList = new ArrayList<>();  // Use ArrayList for dynamic sizing

        for (int i = 0; i < pci.busCount(); i++) {  // Iterate through each bus
            for (int j = 0; j < pci.deviceCount(i); j++) {  // Iterate through each device
                for (int k = 0; k < pci.functionCount(i, j); k++) {  // Iterate through each function
                    if (pci.functionPresent(i, j, k) > 0) {  // Check if function is present
                        String vendorId = String.format("0x%04X", pci.vendorID(i, j, k));
                        String productId = String.format("0x%04X", pci.productID(i, j, k));
                        String vendorName = vendorMap.getOrDefault(vendorId, "Unknown Vendor");
                        String productName = productMap.getOrDefault(productId, "Unknown Product");

                        pciFunctionList.add(new String[]{
                                String.valueOf(i),         // Bus number
                                String.valueOf(j),         // Device number
                                String.valueOf(k),         // Function number
                                vendorId,                  // Vendor ID in hex
                                vendorName,                // Vendor name
                                productId,                 // Product ID in hex
                                productName                // Product description
                        });
                    }
                }
            }
        }

        // Convert the ArrayList to a String[][] array
        return pciFunctionList.toArray(new String[0][0]);
    }



}

