// this is what I am working off
public class pa {
    public static void showUSB()
    {
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis computer has "+
                usb.busCount()+" USB buses ");

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus "+i+" has "+
                    usb.deviceCount(i)+" devices");

            // Iterate through all of the USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus "+i+" device "+j+
                        " has vendor "+String.format("0x%04X", usb.vendorID(i,j))+
                        " and product "+String.format("0x%04X", usb.productID(i,j)));

            // Lookup table
                System.out.println("This USB's vendor ID is " + usb.vendorID(i, j) + " the company who makes it is called");

                System.out.println("This USB's product ID is " + usb.productID(i, j) + " the company who makes it is called");

            }
        }
    }
}