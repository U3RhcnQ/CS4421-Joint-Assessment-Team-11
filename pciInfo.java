/*
 *  PCI information class for JNI
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 */

public class pciInfo 
{
    // Refresh the current values and counters - call this before other methods
    public native void read ();

    // Return the number of PCI buses
    public native int busCount ();

    // Return the number of devices on a PCI bus
    public native int deviceCount (int bus);

    // Return the number of functions in a PCI device
    public native int functionCount (int bus, int device);

    public int totalFunctionCount(){// returns total amount of functions inside all devices
        int totalFunctionCount = 0;

        for (int i = 0; i < busCount(); i++) { // Iterate through each bus

            for (int j = 0; j < deviceCount(i); j++) { // Iterate through each device until at last device count
                    totalFunctionCount += functionCount(i, j);
            }
        }
        return totalFunctionCount;
    }

    // Return the number of functions in a PCI device
    public native int functionPresent (int bus, int device, int function);

    // Return the vendor ID of a PCI device
    public native int vendorID (int bus, int device, int function);

    // Return the product ID of a PCI device
    public native int productID (int bus, int device, int function);
}

