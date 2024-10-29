// this is not what I am working off
public class pa {
    public static void showUSB()
    {
        usbInfo usb = new usbInfo();
        usb.read();
        System.out.println("\nThis computer has "+ usb.busCount()+" USB buses ");

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus "+i+" has "+ usb.deviceCount(i)+" devices");

            // Iterate through all of the USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus "+i+" device "+j+ " has vendor ID "+String.format("0x%04X", usb.vendorID(i,j))+
                        " and product ID "+String.format("0x%04X", usb.productID(i,j)));

            // Lookup table
            for (int k = 1; k <= usb.vendorID(i,j); k++) {

                System.out.println("The device whose Vendor ID is " + String.format("0x%04X", usb.vendorID(i,j)), "is made by " +   );


            for (int l = 1; l <= usb.productID(i,j); l++) {

                System.out.println("The device whose Product ID is " + String.format("0x%04X", usb.productID(i,j)), "is made by " +   );
            }





            }


            }
        }
    }
}