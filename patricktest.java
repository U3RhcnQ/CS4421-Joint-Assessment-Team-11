public class patricktest {

    public static void main(String[] args) {
        // Call the method to display USB information
        showUSB();
    }

    public static void showUSB() {
        usbInfo usb = new usbInfo();
        usb.read(); // Read the USB bus information
        System.out.println("\nThis machine has " + usb.busCount() + " USB buses");

        // Iterate through all of the USB buses
        for (int i = 1; i <= usb.busCount(); i++) {
            System.out.println("Bus " + i + " has " + usb.deviceCount(i) + " devices");

            // Iterate through all of the USB devices on the bus
            for (int j = 1; j <= usb.deviceCount(i); j++) {
                System.out.println("Bus " + i + " device " + j +
                        " has vendor " + String.format("0x%04X", usb.vendorID(i, j)) +
                        " and product " + String.format("0x%04X", usb.productID(i, j)));
            }
        }
    }
}
