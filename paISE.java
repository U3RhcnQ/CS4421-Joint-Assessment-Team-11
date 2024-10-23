public class paISE {
    public static void main(String[] args) {
        usbInfo usb = new usbInfo();
        usb.read();
        // Maybee change the format of the info
        // Vendor Information
        for (int j = 1; j <= usb.deviceCount(i); j++) {
            System.out.println("Bus "+i+" device "+j+
                            " has vendor "+String.format("0x%04X", usb.vendorID(i,j))+

                    // Product Information
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus "+i+" device "+j+
                        " and product "+String.format("0x%04X", usb.productID(i,j)));

                // Number of USB's
                System.out.println("\nThis machine has "+
                        usb.busCount()+" USB buses ");

                // USB buses
                for (int i = 1; i <= usb.busCount(); i++) {
                    System.out.println("Bus "+i+" has "+
                            usb.deviceCount(i)+" devices");

                    // Look-up table yoke


                }
            }



        }
